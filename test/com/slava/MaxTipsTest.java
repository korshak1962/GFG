package com.slava;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MaxTipsTest {

    @Test
    public void getClosestTest() {
        MaxTips.nTotal = 5;
        MaxTips.nA = 3;
        MaxTips.nB = 3;
        MaxTips.aList = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        MaxTips.bList = new LinkedList<>(Arrays.asList(5, 4, 3, 2, 1));
        System.out.println(MaxTips.getMaxTipsSum());
    }

    @Test
    public void getClosestTest1() {
        MaxTips.nTotal = 8;
        MaxTips.nA = 4;
        MaxTips.nB = 4;
        MaxTips.aList = new LinkedList<>(Arrays.asList(1, 4, 3, 2, 7, 5, 9, 6));
        MaxTips.bList = new LinkedList<>(Arrays.asList(1, 2, 3, 6, 5, 4, 9, 8));
        System.out.println(MaxTips.getMaxTipsSum());
    }

}
