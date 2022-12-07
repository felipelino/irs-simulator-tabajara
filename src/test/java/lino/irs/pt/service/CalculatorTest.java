package lino.irs.pt.service;

import lino.irs.pt.input.IRSRequest;
import lino.irs.pt.input.MonthlyIncomeList;
import lino.irs.pt.input.StockShareList;
import lino.irs.pt.input.SituationTypeEnum;
import lino.irs.pt.output.IRSSummary;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class CalculatorTest {

    @Test
    public void calculateIRSTest_Married2_WithoutVests_WithISSLessThanRetention() {

        MonthlyIncomeList monthlyIncomes = new MonthlyIncomeList();
        monthlyIncomes.add(new BigDecimal(35000), new BigDecimal(8540), new BigDecimal(1850));
        monthlyIncomes.add(new BigDecimal(18200), new BigDecimal(2380), new BigDecimal(2002));
        IRSRequest request = new IRSRequest(SituationTypeEnum.Married2, monthlyIncomes, new StockShareList());
        IRSSummary summary = Calculator.calculateIRS(request);
        Assert.assertNotNull(summary);
        Assert.assertEquals("44992.00", summary.getAmountSubjectToTax() + "");
        Assert.assertEquals("10203.01", summary.getTotalIrs() + "");
        Assert.assertEquals("-716.99", summary.getIrsToPay() + "");
    }

    @Test
    public void calculateIRSTest_Married2_WithoutVests_WithISSLessThanRetention_SingleLine() {

        MonthlyIncomeList monthlyIncomes = new MonthlyIncomeList();
        monthlyIncomes.add(new BigDecimal(53200), new BigDecimal(10920), new BigDecimal(3852));
        IRSRequest request = new IRSRequest(SituationTypeEnum.Married2, monthlyIncomes, new StockShareList());
        IRSSummary summary = Calculator.calculateIRS(request);
        Assert.assertNotNull(summary);
        Assert.assertEquals("44992.00", summary.getAmountSubjectToTax() + "");
        Assert.assertEquals("10203.01", summary.getTotalIrs() + "");
        Assert.assertEquals("-716.99", summary.getIrsToPay() + "");
    }

    @Test
    public void calculateIRSTest_Married2_WithoutVests_WithISSMoreThanRetention() {

        MonthlyIncomeList monthlyIncomes = new MonthlyIncomeList();
        BigDecimal tsuRetention = Calculator.FIXED_DEDUCTION.multiply(new BigDecimal(3));
        monthlyIncomes.add(new BigDecimal(53200), new BigDecimal(10920), tsuRetention);
        IRSRequest request = new IRSRequest(SituationTypeEnum.Married2, monthlyIncomes, new StockShareList());
        IRSSummary summary = Calculator.calculateIRS(request);
        Assert.assertNotNull(summary);
        Assert.assertEquals("40888.00", summary.getAmountSubjectToTax() + "");
        Assert.assertEquals("8799.89", summary.getTotalIrs() + "");
        Assert.assertEquals("-2120.11", summary.getIrsToPay() + "");
    }
}
