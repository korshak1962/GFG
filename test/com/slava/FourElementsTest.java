package com.slava;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FourElementsTest {

    @Test
    public void getClosestTest() {
        int sumOfFour = 7;
        List<Integer> lst = Arrays.asList(1, 5, 1, 0, 6, 0);
        System.out.println(FourElements.findInListDP(lst, sumOfFour));
    }

    @Test
    public void getClosestTest1() {
        int sumOfFour = 22;
        List<Integer> lst = Arrays.asList(38, 7, 44, 42, 28, 16, 10, 37, 33, 2, 38, 29, 26, 8, 25);
        System.out.println(FourElements.findInListDP(lst, sumOfFour));
    }

}
