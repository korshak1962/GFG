package com.slava;

import com.slava.dp.MaxSumWithoutAdjacents;
import org.junit.Assert;
import org.junit.Test;

public class MaxSumWithoutAdjacentsTest {

    @Test
    public void getClosestTest() {
        int[] inArray= new int[] {5,5,10,100,10,5,10,10};
        int res= MaxSumWithoutAdjacents.getSum(inArray);
        System.out.println(res); //110
        Assert.assertEquals(res,120);
    }

    @Test
    public void getClosestTest1() {
        int[] inArray= new int[] {3,2,7,10};
        int res=MaxSumWithoutAdjacents.getSum(inArray);
        System.out.println(res); //13
        Assert.assertEquals(res,13);
        System.out.println((int)(double)(3.7/2));
    }

    @Test
    public void getClosestTest3() {
        int[] inArray= new int[] {468,335,1,170,225,479,359,463,465,206,146,282,328,462,492,496,443,328,437,392,
                105,403,154,293,383,422,217,219,396,448,227,272,39,370,413,168,300,36,395,204,312,323};
        int res=MaxSumWithoutAdjacents.getSum(inArray);
                System.out.println(res); //7234
        Assert.assertEquals(7234,res);

    }

}
