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
        Assert.assertEquals("44275.70", summary.getAmountSubjectToTax() + "");
        Assert.assertEquals("7902.03", summary.getTotalIrs() + "");
        Assert.assertEquals("-3017.97", summary.getIrsToPay() + "");
    }

    @Test
    public void calculateIRSTest_Married2_WithoutVests_WithISSLessThanRetention_SingleLine() {

        MonthlyIncomeList monthlyIncomes = new MonthlyIncomeList();
        monthlyIncomes.add(new BigDecimal(53200), new BigDecimal(10920), new BigDecimal(3852));
        IRSRequest request = new IRSRequest(SituationTypeEnum.Married2, monthlyIncomes, new StockShareList());
        IRSSummary summary = Calculator.calculateIRS(request);
        Assert.assertNotNull(summary);
        Assert.assertEquals("44275.70", summary.getAmountSubjectToTax() + "");
        Assert.assertEquals("7902.03", summary.getTotalIrs() + "");
        Assert.assertEquals("-3017.97", summary.getIrsToPay() + "");
    }

    @Test
    public void calculateIRSTest_Married2_WithoutVests_WithISSMoreThanRetention() {

        MonthlyIncomeList monthlyIncomes = new MonthlyIncomeList();
        BigDecimal tsuRetention = Calculator.FIXED_DEDUCTION.multiply(new BigDecimal(3));
        monthlyIncomes.add(new BigDecimal(53200), new BigDecimal(10920), tsuRetention);
        IRSRequest request = new IRSRequest(SituationTypeEnum.Married2, monthlyIncomes, new StockShareList());
        IRSSummary summary = Calculator.calculateIRS(request);
        Assert.assertNotNull(summary);
        Assert.assertEquals("39813.55", summary.getAmountSubjectToTax() + "");
        Assert.assertEquals("6813.26", summary.getTotalIrs() + "");
        Assert.assertEquals("-4106.74", summary.getIrsToPay() + "");
    }
}
