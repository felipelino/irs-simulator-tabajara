package lino.irs.pt.input;

import java.math.BigDecimal;

public class RSUVest {

    private BigDecimal priceDollar;
    private BigDecimal toEuroRate;
    private int total;

    public RSUVest(int total, BigDecimal priceDollar, BigDecimal toEuroRate) {
        this.priceDollar = priceDollar;
        this.toEuroRate = toEuroRate;
        this.total = total;
    }

    public BigDecimal GetTotalInEuro() {
        return this.priceDollar
                .multiply(new BigDecimal(total))
                .multiply(this.toEuroRate);
    }
}
