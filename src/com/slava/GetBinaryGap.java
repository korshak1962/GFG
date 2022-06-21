package com.slava;

import org.junit.Test;

import java.util.*;
import java.util.LinkedList;
import java.util.List;
//https://app.codility.com/programmers/lessons/1-iterations/binary_gap/
public class GetBinaryGap {

    @Test
    public void testPerf() {
        int n = 33;
        int forReturn = getGap(n);
        System.out.println("" + forReturn);
    }

    private int getGap(int N) {
        final String binaryString = Integer.toBinaryString(N);
        //System.out.println("binaryString=" + binaryString);
        final String[] strings = binaryString.split("1+");
        List<Integer> lst = new LinkedList<>();
        for (String str : strings) {
            lst.add(str.length());
        }
        if (binaryString.charAt(0) == '0' && !lst.isEmpty()) {
            lst.remove(0);
        }
        if (binaryString.charAt(binaryString.length() - 1) == '0' && !lst.isEmpty()) {
            lst.remove(lst.size() - 1);
        }
        int forReturn = 0;
        if (!lst.isEmpty()) {
        forReturn = Collections.max(lst);}
        return forReturn;
    }
}
