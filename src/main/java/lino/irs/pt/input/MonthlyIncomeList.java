package lino.irs.pt.input;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MonthlyIncomeList extends ArrayList<MonthlyIncome> {

    public void add(BigDecimal grossValue, BigDecimal retentionValue, BigDecimal iss) {
        super.add(new MonthlyIncome(grossValue, retentionValue, iss));
    }

    public BigDecimal getTotalGrossValue() {
        BigDecimal total = new BigDecimal(0);
        for ( MonthlyIncome monthlyIncome: this) {
            total = total.add(monthlyIncome.getGrossValue());
        }
        return total;
    }

    public BigDecimal getTotalRetention() {
        BigDecimal total = new BigDecimal(0);
        for ( MonthlyIncome monthlyIncome: this) {
            total = total.add(monthlyIncome.getRetentionValue());
        }
        return total;
    }

    public BigDecimal getTotalTSU() {
        BigDecimal total = new BigDecimal(0);
        for ( MonthlyIncome monthlyIncome: this) {
            total = total.add(monthlyIncome.getTsu());
        }
        return total;
    }
}
