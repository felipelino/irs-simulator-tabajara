package lino.irs.pt.service;

import java.math.BigDecimal;

public enum IRSRangeEnum {

    RANGE1(new BigDecimal(7112), new BigDecimal("0.145"), new BigDecimal(7112)),
    RANGE2(new BigDecimal(10732), new BigDecimal("0.23"), new BigDecimal(10732 - 7112)),
    RANGE3(new BigDecimal(20322), new BigDecimal("0.285"), new BigDecimal(20322 - 10732)),
    RANGE4(new BigDecimal(25075), new BigDecimal("0.35"), new BigDecimal(25075 - 20322)),
    RANGE5(new BigDecimal(36967), new BigDecimal("0.37"), new BigDecimal(36967 - 25075)),
    RANGE6(new BigDecimal(80882), new BigDecimal("0.45"), new BigDecimal(80882 - 36967)),
    RANGE7(null, new BigDecimal("0.48"), null);


    public final BigDecimal maxRangeValue;
    public final BigDecimal tax;
    public final BigDecimal interval;

    IRSRangeEnum(BigDecimal maxRangeValue, BigDecimal tax, BigDecimal interval)
    {
        this.maxRangeValue = maxRangeValue;
        this.tax = tax;
        this.interval = interval;
    }
}
