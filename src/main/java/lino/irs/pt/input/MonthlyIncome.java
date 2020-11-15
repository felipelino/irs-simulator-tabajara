package lino.irs.pt.input;

import java.math.BigDecimal;

public class MonthlyIncome {
    private BigDecimal grossValue;
    private BigDecimal retentionValue;
    private BigDecimal tsu;

    public MonthlyIncome(BigDecimal grossValue, BigDecimal retentionValue, BigDecimal tsu) {
        this.grossValue = grossValue;
        this.retentionValue = retentionValue;
        this.tsu = tsu;
    }

    public BigDecimal getGrossValue() {
        return grossValue;
    }

    public BigDecimal getRetentionValue() {
        return retentionValue;
    }

    public BigDecimal getTsu() {
        return tsu;
    }
}
