package lino.irs.pt.input;

import java.math.BigDecimal;

public class StockShare {

    private BigDecimal priceDollar;
    private BigDecimal toEuroRate;
    private int total;
    private StockOperationTypeEnum shareOperationType;

    public StockShare(int total, BigDecimal priceDollar, BigDecimal toEuroRate, StockOperationTypeEnum shareOperationType) {
        this.priceDollar = priceDollar;
        this.toEuroRate = toEuroRate;
        this.total = total;
        this.shareOperationType = shareOperationType;
    }

    public BigDecimal getTotalInEuro() {
        return this.priceDollar
                .multiply(new BigDecimal(total))
                .multiply(this.toEuroRate);
    }

    public boolean isVested() {
        return this.shareOperationType == StockOperationTypeEnum.VEST;
    }

    public StockOperationTypeEnum getShareOperationType() {
        return this.shareOperationType;
    }

    public BigDecimal getQuantity() { return new BigDecimal(total);}
}
