package lino.irs.pt.service;

import lino.irs.pt.input.IRSRequest;
import lino.irs.pt.input.SituationTypeEnum;
import lino.irs.pt.output.IRSSummary;

import java.math.BigDecimal;

public class Calculator {

    public static BigDecimal FIXED_DEDUCTION = new BigDecimal(4104);
    public static BigDecimal FIXED_RNH_RATE = new BigDecimal(0.20);

    public static IRSSummary calculateIRS(IRSRequest request) {

        BigDecimal referenceValue = null;
        BigDecimal amountSubjectToTax = null;
        BigDecimal totalIrs = null;
        if(request.getSituationTypeEnum() == SituationTypeEnum.RNH) {
            amountSubjectToTax = request.getTotalGross();
            totalIrs = calculateIRSValue(amountSubjectToTax, true);
        }
        else {
            amountSubjectToTax = request.getTotalGross().subtract(FIXED_DEDUCTION);
            if(request.isMarried()) {
                if(request.getSituationTypeEnum() == SituationTypeEnum.Married2) {
                    amountSubjectToTax = amountSubjectToTax.subtract(FIXED_DEDUCTION);
                }
                referenceValue = amountSubjectToTax.divide(new BigDecimal(2));
            }
            else {
                referenceValue = amountSubjectToTax;
            }

            totalIrs = calculateIRSValue(referenceValue, false);
            if(request.isMarried()) {
                totalIrs = totalIrs.multiply(new BigDecimal(2));
            }
        }

        BigDecimal profitStockShares = request.getTotalProfitStockShareBuyAndSellInEuro();
        BigDecimal irsStockShares = calculateIRSValueForStockShares(profitStockShares);
        totalIrs = totalIrs.add(irsStockShares);
        BigDecimal irsToPay = totalIrs.subtract(request.getTotalRetention());
        return new IRSSummary(amountSubjectToTax, profitStockShares, totalIrs, irsToPay);
    }

    private static BigDecimal calculateIRSValueForStockShares(BigDecimal totalProfitStockShareBuyAndSellInEuro) {
        if(totalProfitStockShareBuyAndSellInEuro.compareTo(BigDecimal.ZERO) > 0) {
            return totalProfitStockShareBuyAndSellInEuro.multiply(new BigDecimal(0.28));
        }
        else {
            return BigDecimal.ZERO;
        }
    }

    private static BigDecimal calculateIRSValue(BigDecimal totalValue, boolean isRnh) {

        /*  totalValue = 35.000

            referenceValue#1 = 35.000
            residualValue#1 = 35.000 - 7.112 = 27.888
            irsRange#1 = 14.5/100 * 7.112 = 1.031,24
            totalIrs#1 = 1.031,24

            referenceValue#2 = 27.888
            residualValue#2 = 27.888 - (10.732 - 7.112) = 24.268
            irsRange#2 = 23.0/100 * (10.732 - 7.112) = 832.60
            totalIrs#2 = 1.031,385 + 832.60

            referenceValue#3 = 24.268
            residualValue#3 = 24.268 - (20.322 - 10.732) = 14.678
            irsRange#3 = 28.5/100 * (20.322 - 10.732) = 2.733,15
            totalIrs#3 = 1.031,385 + 832,60 + 2.733,15

            referenceValue#4 = 14.678
            residualValue#4 = 14.678 - (25.075 - 20.322) = 9.925
            irsRange#4 = 35/100 * (25.075 - 20.322) = 1.663,55
            totalIrs#4 = 1.031,385 + 832.83 + 2.733,15 + 1.663,55

            referenceValue#5 = 9.925
            residualValue#5 = 9.925 - (36.967 - 25.075) = -1967 (SHOULD STOP)
            irsRange#5 = 37/100 * 9.925 = 3.672,25
            totalIrs#5 = 1.031,385 + 832.83 + 2.733,15 + 1.663,55 + 3.672,25

            totalIrs = 9.933,165
         */
        if(isRnh) {
            return totalValue.multiply(FIXED_RNH_RATE);
        }
        BigDecimal referenceValue = totalValue;
        BigDecimal totalIrs = BigDecimal.ZERO;
        for (IRSRangeEnum range: IRSRangeEnum.values()) {

            if(range.interval == null) {
                BigDecimal irsRange = range.tax.multiply(referenceValue);
                totalIrs = totalIrs.add(irsRange);
                break;
            }

            BigDecimal residualValue = referenceValue.subtract(range.interval);
            if(residualValue.compareTo(BigDecimal.ZERO) >= 0) {
                BigDecimal irsRange = range.tax.multiply(range.interval);
                totalIrs = totalIrs.add(irsRange);
                 referenceValue = residualValue;
            }
            else {
                BigDecimal irsRange = range.tax.multiply(referenceValue);
                totalIrs = totalIrs.add(irsRange);
                break;
            }
        }

        return totalIrs;
    }
}
