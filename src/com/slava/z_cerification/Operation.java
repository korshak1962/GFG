package com.slava.z_cerification;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class Operation {


    @Test
    public void testIntToString() {
        Callable c;
        Future f;
    }

    @Test
    public void testUnreach() {
        List<Integer> myFavoriteNumbers = new ArrayList<>();
        myFavoriteNumbers.add(10);
        myFavoriteNumbers.add(14);
        for (int b : myFavoriteNumbers) {
            continue;
           // System.out.print(b + ", ");
        }
    }

    @Test
    public void test(){
        boolean canine = true, wolf = true;
        int teeth = 20;
        canine = (teeth != 10) ^ (wolf=false);
        System.out.println(canine+", "+teeth+", "+wolf);
        final int x = 2 % 3;
        System.out.println(x);
    }

    static long addCandy(double fruit, float vegetables)
    {
        return (long) ((int)fruit+vegetables);
    }

    @Test
    public void test1(){
             System.out.print(addCandy(1.4, 2.4f) + "-");
             System.out.print(addCandy(1.9, (float)4) + "-");
             System.out.print(addCandy(2L,
                    (float)4)); }
}

