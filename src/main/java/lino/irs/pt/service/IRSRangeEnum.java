package lino.irs.pt.service;

import java.math.BigDecimal;

/**
 * Updated with information from here: https://www.cgd.pt/Site/Saldo-Positivo/leis-e-impostos/Pages/diferenca-entre-escaloes-de-irs-e-tabelas-de-irs.aspx
 * 30/07/2024
 */
public enum IRSRangeEnum {

    RANGE1(new BigDecimal(8059), new BigDecimal("0.125"), new BigDecimal(8059)),
    RANGE2(new BigDecimal(12160), new BigDecimal("0.16"), new BigDecimal(12160 - 8059)),
    RANGE4(new BigDecimal(17233), new BigDecimal("0.215"), new BigDecimal(17233 - 12160)),
    RANGE5(new BigDecimal(22306), new BigDecimal("0.244"), new BigDecimal(22306 - 17233)),
    RANGE6(new BigDecimal(28400), new BigDecimal("0.314"), new BigDecimal(28400 - 22306)),
    RANGE7(new BigDecimal(41629), new BigDecimal("0.349"), new BigDecimal(41629 - 28400)),
    RANGE8(new BigDecimal(44987), new BigDecimal("0.431"), new BigDecimal(44987 - 41629)),
    RANGE9(new BigDecimal(83696), new BigDecimal("0.446"), new BigDecimal(83696 - 44987)),
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
