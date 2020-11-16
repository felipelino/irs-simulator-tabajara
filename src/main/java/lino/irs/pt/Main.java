package lino.irs.pt;

import lino.irs.pt.input.IRSRequest;
import lino.irs.pt.input.MonthlyIncomeList;
import lino.irs.pt.input.RSUVestList;
import lino.irs.pt.input.SituationTypeEnum;
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

    public static RSUVestList readRSUVestListFromCSV(String pathToCSV) throws Exception {
        RSUVestList rsuVestList = new RSUVestList();
        if(pathToCSV == null) {
            return rsuVestList;
        }

        File file = new File(pathToCSV);
        if(!file.exists()){
            return rsuVestList;
        }

        BufferedReader buff = new BufferedReader(new FileReader(file));
        String line = buff.readLine();
        while(line != null) {
            String array[] = line.split(";");
            if(array.length < 3) {
                throw new Exception("The expected format is: quantity;unitValue;toEuro");
            }

            rsuVestList.add(Integer.parseInt(array[0]), new BigDecimal(array[1]), new BigDecimal(array[2]));
            line = buff.readLine();
        }

        if(buff != null) {
            buff.close();
        }

        return rsuVestList;
    }

    public static void main(String[] args) throws Exception {
        SituationTypeEnum situationTypeEnum = SituationTypeEnum.valueOf(args[0]);
        String monthlyCsvFile = args[1];
        String rsusCsvFile = null;
        if(args.length > 2) {
            rsusCsvFile = args[2];
        }

        MonthlyIncomeList monthlyIncomeList = readMonthlyIncomeListFromCSV(monthlyCsvFile);
        RSUVestList rsuVestList = readRSUVestListFromCSV(rsusCsvFile);

        IRSRequest request = new IRSRequest(situationTypeEnum, monthlyIncomeList, rsuVestList);
        IRSSummary summary = Calculator.calculateIRS(request);
        Report report = new Report(request, summary);
        System.out.println(report);
    }
}
