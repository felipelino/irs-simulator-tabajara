package lino.irs.pt;

import lino.irs.pt.input.*;
import lino.irs.pt.output.IRSSummary;
import lino.irs.pt.output.Report;
import lino.irs.pt.service.Calculator;

import java.io.*;
import java.math.BigDecimal;

//Reference: https://www.montepio.org/ei/pessoal/impostos/como-calcular-o-irs/
public class Main {

    public static MonthlyIncomeList readMonthlyIncomeListFromCSV(String pathToCSV) throws Exception {
        MonthlyIncomeList monthlyIncomeList = new MonthlyIncomeList();

        BufferedReader buff = new BufferedReader(new FileReader(new File(pathToCSV)));
        String line = buff.readLine();
        while(line != null) {
            String array[] = line.split(";");
            if(array.length < 2) {
                throw new Exception("The expected format is: grossReceived;retention;tsu(optional)");
            }

            BigDecimal tsu = array.length>=3 ? new BigDecimal(array[2]) : BigDecimal.ZERO;
            monthlyIncomeList.add(new BigDecimal(array[0]), new BigDecimal(array[1]), tsu);
            line = buff.readLine();
        }

        if(buff != null) {
            buff.close();
        }

        return monthlyIncomeList;
    }

    public static StockShareList readStockShareListFromCSV(String pathToCSV) throws Exception {
        StockShareList stockShareList = new StockShareList();
        if(pathToCSV == null) {
            return stockShareList;
        }

        File file = new File(pathToCSV);
        if(!file.exists()){
            return stockShareList;
        }

        BufferedReader buff = new BufferedReader(new FileReader(file));
        String line = buff.readLine();
        while(line != null) {
            String array[] = line.split(";");
            if(array.length < 3) {
                throw new Exception("The expected format is: quantity;unitValue;toEuro;VEST|BUY|SELL");
            }

            stockShareList.add(Integer.parseInt(array[0]), new BigDecimal(array[1]), new BigDecimal(array[2]), StockOperationTypeEnum.valueOf(array[3]));
            line = buff.readLine();
        }

        if(buff != null) {
            buff.close();
        }

        return stockShareList;
    }

    public static void main(String[] args) throws Exception {
        SituationTypeEnum situationTypeEnum = SituationTypeEnum.valueOf(args[0]);
        String monthlyCsvFile = args[1];
        String stockShareCsvFile = null;
        if(args.length > 2) {
            stockShareCsvFile = args[2];
        }

        MonthlyIncomeList monthlyIncomeList = readMonthlyIncomeListFromCSV(monthlyCsvFile);
        StockShareList stockShareList = readStockShareListFromCSV(stockShareCsvFile);

        IRSRequest request = new IRSRequest(situationTypeEnum, monthlyIncomeList, stockShareList);
        IRSSummary summary = Calculator.calculateIRS(request);
        Report report = new Report(request, summary);
        System.out.println(report);
    }
}
