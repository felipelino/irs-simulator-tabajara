package lino.irs.pt.service;

import java.math.BigDecimal;

/**
 * Updated with information from here: https://www.cgd.pt/Site/Saldo-Positivo/leis-e-impostos/Pages/diferenca-entre-escaloes-de-irs-e-tabelas-de-irs.aspx
 * 30/07/2024
 */
public enum IRSRangeEnum {

    RANGE1(new BigDecimal(7703), new BigDecimal("0.13"), new BigDecimal(7703)),
    RANGE2(new BigDecimal(11623), new BigDecimal("0.165"), new BigDecimal(11623 - 7703)),
    RANGE4(new BigDecimal(16472), new BigDecimal("0.22"), new BigDecimal(16472 - 11623)),
    RANGE5(new BigDecimal(21321), new BigDecimal("0.25"), new BigDecimal(21321 - 16472)),
    RANGE6(new BigDecimal(27146), new BigDecimal("0.3275"), new BigDecimal(27146 - 21321)),
    RANGE7(new BigDecimal(39791), new BigDecimal("0.355"), new BigDecimal(39791 - 27146)),
    RANGE8(new BigDecimal(43000), new BigDecimal("0.435"), new BigDecimal(43000 - 39791)),
    RANGE9(new BigDecimal(80000), new BigDecimal("0.45"), new BigDecimal(80000 - 43000)),
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
