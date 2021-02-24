package lino.irs.pt.input;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class StockShareList extends ArrayList<StockShare> {


    public void add( int total, BigDecimal priceDollar, BigDecimal toEuroRate, StockOperationTypeEnum shareOperationType){
        super.add(new StockShare(total, priceDollar, toEuroRate, shareOperationType));
    }

    public BigDecimal getTotalStockShareVestedInEuro() {
        BigDecimal total = new BigDecimal(0);
        for ( StockShare stockShare: this) {
            if(stockShare.isVested()) {
                total = total.add(stockShare.getTotalInEuro());
            }
        }
        return total;
    }



    public BigDecimal getTotalProfitStockShareBuyAndSellInEuro() {
        BigDecimal totalQtShares = new BigDecimal(0);
        BigDecimal totalShares = new BigDecimal(0);

        BigDecimal totalProfit = new BigDecimal(0);
        for ( StockShare stockShare: this) {
            if(stockShare.getShareOperationType() == StockOperationTypeEnum.SELL) {
                BigDecimal averagePrice = totalShares.divide(totalQtShares, 2 , RoundingMode.HALF_UP);
                BigDecimal totalBuyOrVestPrice = stockShare.getQuantity().multiply(averagePrice);
                totalProfit = totalProfit.add(stockShare.getTotalInEuro().subtract(totalBuyOrVestPrice));

                totalQtShares = totalQtShares.subtract(stockShare.getQuantity());
                if(totalQtShares.compareTo(BigDecimal.ZERO) == 0) {
                    totalShares = new BigDecimal(0);
                }
                else {
                    totalShares = totalShares.subtract(totalBuyOrVestPrice);
                }
            }
            else {
                totalQtShares = totalQtShares.add(stockShare.getQuantity());
                totalShares = totalShares.add(stockShare.getTotalInEuro());
            }
        }

        return totalProfit;
    }
}
