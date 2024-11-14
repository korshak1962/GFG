package com.slava.dp;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class BestN_Trades {


    public List<BuySell> getTrades(List<Price> prices, int nTrade) {
        List<BuySell> trades = new ArrayList<>();
        List<Price> extremums= findExtremums( prices);
        double dp[][] = new double[extremums.size()][nTrade];
        // boundary condition
        for (int k = 0; k < nTrade; k++) {
            dp[0][k] = 0;
        }
        for (int k = 0; k < extremums.size(); k++) {

        }

        for (int iTrade = 0; iTrade < nTrade; iTrade++) {
            for (int iPrice = 0; iPrice < prices.size(); iPrice++) {


                //  dp[iPrice][iTrade] =
            }
        }
        return trades;
    }

    class BuySell {
        LocalDateTime localDateTime;
        int buySell;
    }

    class Price {
        LocalDateTime localDateTime;
        double closePrice;

        public Price(LocalDateTime localDateTime, double closePrice) {
            this.localDateTime = localDateTime;
            this.closePrice = closePrice;
        }
    }

    class Trade {
        Price buyPrice;
        Price sellPrice;

        public Trade(Price buyPrice, Price sellPrice) {
            this.buyPrice = buyPrice;
            this.sellPrice = sellPrice;
        }
    }


    private List<Price> findExtremums(List<Price> prices){
        List<Price> extremunPrices = new ArrayList<>();
        if (prices.isEmpty()){ return extremunPrices;}
        extremunPrices.add(prices.getFirst());

        for (int i =1;i<prices.size()-1;i++){
            if (
                    (prices.get(i-1).closePrice<prices.get(i).closePrice
                    && prices.get(i).closePrice>prices.get(i+1).closePrice)
                    ||
                            (prices.get(i-1).closePrice>prices.get(i).closePrice
                                    && prices.get(i).closePrice<prices.get(i+1).closePrice)
            ){
                extremunPrices.add(prices.get(i));
            }
        }

        extremunPrices.add(prices.getLast());
        return extremunPrices;
    }

    @Test
    public void testfindExtremums(){
        List<Price> prices = generatePrices();
        List<Price> extremums= findExtremums( prices);
        Assert.assertEquals(4, extremums.size());
    }


    //kadane's algorithm :)
    public Trade kadane(List<Price> prices, int iStart, int iEnd) {
        if (iEnd - iStart < 1 || iEnd > prices.size()) throw new IllegalArgumentException();
        double currentProfit = 0;
        double maxProfit = 0;
        int startTradePriceInd = iStart;
        Trade trade = null;
        for (int i = iStart; i < iEnd; i++) {
            currentProfit = currentProfit +
                    prices.get(i).closePrice - prices.get(startTradePriceInd).closePrice;
            if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
                trade = new Trade(prices.get(startTradePriceInd), prices.get(i));
            }
            if (currentProfit <= 0) {
                startTradePriceInd = i;
                currentProfit = 0;
            }
        }
        return trade;
    }

    @Test
    public void kadaneSimpleTest() {
        List<Price> prices = new ArrayList<>();

        Price p = new Price(LocalDateTime.ofEpochSecond(1, 0, ZoneOffset.ofTotalSeconds(1)), 1);
        prices.add(p);
        p = new Price(LocalDateTime.ofEpochSecond(2, 0, ZoneOffset.ofTotalSeconds(1)), 2);
        prices.add(p);
        p = new Price(LocalDateTime.ofEpochSecond(2, 0, ZoneOffset.ofTotalSeconds(1)), 3);
        prices.add(p);
        Trade trade = kadane(prices, 0, 3);

        Assert.assertEquals(trade.buyPrice.closePrice, 1D, 0D);
        Assert.assertEquals(trade.sellPrice.closePrice, 3D, 0D);
    }

    @Test
    public void kadaneNotSimpleTest() {
        List<Price> prices = generatePrices();
        Trade trade = kadane(prices, 0, 5);

        Assert.assertEquals(trade.buyPrice.closePrice, 1, 0);

        Assert.assertEquals(trade.sellPrice.closePrice, 50D, 0D);
    }

    private List<Price> generatePrices() {
        List<Price> prices = new ArrayList<>();

        Price p = new Price(LocalDateTime.ofEpochSecond(1, 0,
                ZoneOffset.ofTotalSeconds(1)), 1);
        prices.add(p);
        p = new Price(LocalDateTime.ofEpochSecond(2, 0,
                ZoneOffset.ofTotalSeconds(1)), 2);
        prices.add(p);
        p = new Price(LocalDateTime.ofEpochSecond(3, 0,
                ZoneOffset.ofTotalSeconds(1)), 3);
        prices.add(p);

        p = new Price(LocalDateTime.ofEpochSecond(4, 0,
                ZoneOffset.ofTotalSeconds(1)), 0);
        prices.add(p);
        p = new Price(LocalDateTime.ofEpochSecond(5, 0,
                ZoneOffset.ofTotalSeconds(1)), 50);
        prices.add(p);
        return prices;
    }

}
