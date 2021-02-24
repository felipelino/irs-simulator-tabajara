package lino.irs.pt.input;


import java.math.BigDecimal;

public class IRSRequest {
    private boolean isMarried = false;
    private SituationTypeEnum situationTypeEnum;
    private MonthlyIncomeList monthlyIncomeList;
    private StockShareList stockShareList;

    public IRSRequest(SituationTypeEnum situationTypeEnum, MonthlyIncomeList monthlyIncomeList, StockShareList stockShareList) {
        this.situationTypeEnum = situationTypeEnum;
        this.isMarried = situationTypeEnum != SituationTypeEnum.Single;
        this.monthlyIncomeList = monthlyIncomeList;
        this.stockShareList = stockShareList;
    }

    public BigDecimal getTotalGross() {
        return getTotalMonthlyIncome()
                .add(getTotalStockShareVestedInEuro());
    }

    public BigDecimal getTotalMonthlyIncome() {
        return monthlyIncomeList.getTotalGrossValue();
    }

    public BigDecimal getTotalStockShareVestedInEuro() {
        return stockShareList.getTotalStockShareVestedInEuro();
    }

    public BigDecimal getTotalProfitStockShareBuyAndSellInEuro() {
        return stockShareList.getTotalProfitStockShareBuyAndSellInEuro();
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
