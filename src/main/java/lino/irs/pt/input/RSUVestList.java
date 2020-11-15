package lino.irs.pt.input;

import java.math.BigDecimal;
import java.util.ArrayList;

public class RSUVestList extends ArrayList<RSUVest> {


    public void add( int total, BigDecimal priceDollar, BigDecimal toEuroRate ){
        super.add(new RSUVest(total, priceDollar, toEuroRate));
    }

    public BigDecimal getTotalInEuro() {
        BigDecimal total = new BigDecimal(0);
        for ( RSUVest rsu: this) {
            total = total.add(rsu.GetTotalInEuro());
        }
        return total;
    }
}
