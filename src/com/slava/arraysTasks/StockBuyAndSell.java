package com.slava.arraysTasks;

import org.junit.Assert;
import org.junit.Test;

public class StockBuyAndSell {

    public static int stockBuyAndSell(int n, int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - prices[i - 1];
            if (profit > 0) {
                res += profit;
            }
        }
        return res;
    }

    @Test
    public void test() {
        int[] prices = {3, 4, 1, 5};
        int res = stockBuyAndSell(prices.length, prices);
        Assert.assertEquals(5, res);
    }

    @Test
    public void test1() {
        int[] prices = {1, 3, 5, 7, 9};
        int res = stockBuyAndSell(prices.length, prices);
        Assert.assertEquals(8, res);
    }
}
