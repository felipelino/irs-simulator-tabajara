package lino.irs.pt.output;

import lino.irs.pt.input.IRSRequest;
import lino.irs.pt.input.SituationTypeEnum;
import lino.irs.pt.service.Calculator;

import java.math.BigDecimal;

public class Report {

    private IRSRequest request;
    private IRSSummary summary;

    public Report(IRSRequest request, IRSSummary summary) {
        this.request = request;
        this.summary = summary;
    }

    public String toString(){
        String reportAsString = "";
        if(request.getTotalTSU().compareTo(BigDecimal.ZERO) != 0) {
            reportAsString = "\nTotal TSU retido na fonte...:  "+ request.getTotalTSU().setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        BigDecimal deduction = Calculator.FIXED_DEDUCTION;
        if(request.getSituationTypeEnum() == SituationTypeEnum.Married2) {
            deduction = Calculator.FIXED_DEDUCTION.multiply(new BigDecimal(2));
        }

        reportAsString +=
                "\nValor bruto total recebido..:  "+ request.getTotalGross().setScale(2, BigDecimal.ROUND_HALF_UP) +
                "\n\t = Total bruto salario....:  "+ request.getTotalMonthlyIncome().setScale(2, BigDecimal.ROUND_HALF_UP) +
                "\n\t + Total RSUs Vested......:  "+ request.getTotalRSUsInEuro().setScale(2, BigDecimal.ROUND_HALF_UP) +
                "\nDeducao fixa................: -"+ deduction.setScale(2, BigDecimal.ROUND_HALF_UP) +
                "\n\nTotal sujeito a tributo.....:  "+summary.getAmountSubjectToTax() +
                "\nValor total de IRS..........:  "+summary.getTotalIrs() +
                "\nValor de IRS retido na fonte: -"+ request.getTotalRetention().setScale(2, BigDecimal.ROUND_HALF_UP);
        if(summary.getIrsToPay().compareTo(BigDecimal.ZERO) >= 0) {
            reportAsString += "\nIRS Total a pagar...........:  "+summary.getIrsToPay();
        }
        else {
            reportAsString += "\nRestituicao a receber.......:  "+summary.getIrsToPay().multiply(new BigDecimal(-1)).setScale(2, BigDecimal.ROUND_DOWN);
        }

        return reportAsString;
    }
}
