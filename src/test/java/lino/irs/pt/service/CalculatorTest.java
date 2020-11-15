package lino.irs.pt.service;

import lino.irs.pt.input.IRSRequest;
import lino.irs.pt.input.MonthlyIncomeList;
import lino.irs.pt.input.RSUVestList;
import lino.irs.pt.input.SituationTypeEnum;
import lino.irs.pt.output.IRSSummary;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class CalculatorTest {

    @Test
    public void calculateIRSTestWithoutVests() {

        MonthlyIncomeList monthlyIncomes = new MonthlyIncomeList();
        monthlyIncomes.add(new BigDecimal(35000), new BigDecimal(8540), new BigDecimal(3850));
        monthlyIncomes.add(new BigDecimal(18200), new BigDecimal(2380), new BigDecimal(2002));
        IRSRequest request = new IRSRequest(SituationTypeEnum.Married2, monthlyIncomes, new RSUVestList());
        IRSSummary summary = Calculator.calculateIRS(request);
        Assert.assertNotNull(summary);
        Assert.assertEquals("44992.00", summary.getAmountSubjectToTax() + "");
        Assert.assertEquals("10715.78", summary.getTotalIrs() + "");
        Assert.assertEquals("-204.22", summary.getIrsToPay() + "");
    }
}
