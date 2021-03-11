package lino.irs.pt.output;

import java.math.BigDecimal;

public class IRSSummary {

    private BigDecimal amountSubjectToTax;
    private BigDecimal profitStockShares;
    private BigDecimal totalIrs;
    private BigDecimal irsToPay;
    private BigDecimal deduction;

    public IRSSummary(BigDecimal amountSubjectToTax, BigDecimal profitStockShares, BigDecimal totalIrs, BigDecimal irsToPay, BigDecimal deduction) {
        this.amountSubjectToTax = amountSubjectToTax.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.profitStockShares = profitStockShares.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.totalIrs = totalIrs.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.irsToPay = irsToPay.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.deduction = deduction.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }

    public BigDecimal getAmountSubjectToTax() {
        return amountSubjectToTax;
    }

    public BigDecimal getTotalIrs() {
        return totalIrs;
    }

    public BigDecimal getIrsToPay() {
        return irsToPay;
    }

    public BigDecimal getProfitStockShares() {
        return profitStockShares;
    }

    public BigDecimal getDeduction() {
        return deduction;
    }
}
