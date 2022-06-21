package com.slava;

import org.junit.Test;

public class ClosestPalindromTest {
    @Test
    public void getClosestTest() {

        Long input = Long.valueOf(984);

        String res = ClosestPalindrom.getClosest(input);
        System.out.println("input=" + input + " res=" + res);

        input = Long.valueOf(1001);
        getAndPrint(input);

        input = Long.valueOf(101);
        getAndPrint(input);

        input = Long.valueOf(10);
        getAndPrint(input);

        input = Long.valueOf(489);
        getAndPrint(input);
    }

    @Test
    public void getClosestTest1() {
        Long input = Long.valueOf(489);
        System.out.println("input=" + input + " res=" + ClosestPalindrom.getClosest(input));
    }

    private void getAndPrint(Long input) {
        String res;
        res = ClosestPalindrom.getClosest(input);
        System.out.println("input=" + input + " res=" + ClosestPalindrom.getClosest(input));
    }
}
