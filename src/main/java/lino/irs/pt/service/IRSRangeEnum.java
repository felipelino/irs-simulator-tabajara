package lino.irs.pt.service;

import java.math.BigDecimal;

public enum IRSRangeEnum {

    RANGE1(new BigDecimal(7479), new BigDecimal("0.145"), new BigDecimal(7479)),
    RANGE2(new BigDecimal(11284), new BigDecimal("0.21"), new BigDecimal(11284 - 7479)),
    RANGE4(new BigDecimal(15992), new BigDecimal("0.265"), new BigDecimal(15992 - 11284)),
    RANGE5(new BigDecimal(20700), new BigDecimal("0.285"), new BigDecimal(20700 - 15992)),
    RANGE6(new BigDecimal(26355), new BigDecimal("0.35"), new BigDecimal(26355 - 20700)),
    RANGE7(new BigDecimal(38632), new BigDecimal("0.37"), new BigDecimal(38632 - 26355)),
    RANGE8(new BigDecimal(50483), new BigDecimal("0.435"), new BigDecimal(50483 - 38632)),
    RANGE9(new BigDecimal(78834), new BigDecimal("0.45"), new BigDecimal(78834 - 50483)),
    RANGE10(null, new BigDecimal("0.48"), null);


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
