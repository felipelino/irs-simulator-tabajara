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
        String reportAsString = "\nIRS Type....................:  "+ request.getSituationTypeEnum();
        if(request.getTotalTSU().compareTo(BigDecimal.ZERO) != 0) {
            reportAsString += "\nTotal TSU retido na fonte...:  "+ request.getTotalTSU().setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        reportAsString +=
                "\nValor bruto total recebido..:  "+ request.getTotalGross().setScale(2, BigDecimal.ROUND_HALF_UP) +
                "\n\t = Total bruto salario..:  "+ request.getTotalMonthlyIncome().setScale(2, BigDecimal.ROUND_HALF_UP) +
                "\n\t + Total RSUs Vested....:  "+ request.getTotalStockShareVestedInEuro().setScale(2, BigDecimal.ROUND_HALF_UP) +
                "\nDeducao.....................: -"+ summary.getDeduction() +
                "\n\nTotal sujeito a tributo.....:  "+summary.getAmountSubjectToTax() +
                "\nTotal do lucro de acoes.....:  "+summary.getProfitStockShares() +
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
