package com.slava.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//https://www.geeksforgeeks.org/problems/buy-maximum-stocks-if-i-stocks-can-be-bought-on-i-th-day/1?page=1&difficulty=Medium,Hard&sortBy=submissions
public class BuyMaximumProducts {

    public static int buyMaximumProducts(int n, int k, int[] price) {
        int res = 0;
        List<PriceToInd> priceToInds = new ArrayList<>();
        for (int i = 0; i < price.length; i++) {
            priceToInds.add(new PriceToInd(i + 1, price[i]));
        }
        priceToInds.sort(PriceToInd::comparePrice);
        for (int i =0;i<price.length;i++){
            if (k<=0) break;
            for (int j=0;j<priceToInds.get(i).ind;j++){
                k-=priceToInds.get(i).price;
                if (k<0) break;
                res++;
            }
        }
        return res;
    }

    static class PriceToInd {
        int ind;
        int price;
       public int comparePrice(PriceToInd priceToInd) {
            return Integer.compare(price,priceToInd.price);
        }
        public PriceToInd(int ind, int price) {
            this.ind = ind;
            this.price = price;

        }
    }

    @Test
    public void test(){
       int[] price = {10,7,19};
       int k = 45;
       int res = buyMaximumProducts(price.length,k,price);
        Assert.assertEquals(4,res);
    }

    @Test
    public void test1(){
        int[] price = {7,10,4};
        int k = 100;
        int res = buyMaximumProducts(price.length,k,price);
        Assert.assertEquals(6,res);
    }
}
