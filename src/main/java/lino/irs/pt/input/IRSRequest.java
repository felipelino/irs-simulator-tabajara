package lino.irs.pt.input;


import java.math.BigDecimal;

public class IRSRequest {
    private boolean isMarried = false;
    private SituationTypeEnum situationTypeEnum;
    private MonthlyIncomeList monthlyIncomeList;
    private RSUVestList rsuVestList;

    public IRSRequest(SituationTypeEnum situationTypeEnum, MonthlyIncomeList monthlyIncomeList, RSUVestList rsuVestList) {
        this.situationTypeEnum = situationTypeEnum;
        this.isMarried = situationTypeEnum != SituationTypeEnum.Single;
        this.monthlyIncomeList = monthlyIncomeList;
        this.rsuVestList = rsuVestList;
    }

    public BigDecimal getTotalGross() {
        return getTotalMonthlyIncome().add(getTotalRSUsInEuro());
    }

    public BigDecimal getTotalMonthlyIncome() {
        return monthlyIncomeList.getTotalGrossValue();
    }

    public BigDecimal getTotalRSUsInEuro() {
        return rsuVestList.getTotalInEuro();
    }

    public BigDecimal getTotalTSU() {
        return monthlyIncomeList.getTotalTSU();
    }

    public BigDecimal getTotalRetention() {
        return monthlyIncomeList.getTotalRetention();
    }

    public boolean isMarried() {
        return isMarried;
    }

    public SituationTypeEnum getSituationTypeEnum() {
        return situationTypeEnum;
    }
}
