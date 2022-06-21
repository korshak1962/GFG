package com.slava.dp;

import org.junit.Assert;
import org.junit.Test;

public class findSubsequenceCountMe {

    static int findSubsequenceCount(String baseString, String sequence) {
        int dimSequence = sequence.length();
        int dimbaseString = baseString.length();
        int rect[][] = new int[dimSequence][dimbaseString];
        int k = 0;
        for (int i = 0; i < dimbaseString; i++) {
            if (sequence.charAt(0) == baseString.charAt(i)) {
                k++;
            }
            rect[0][i] = k;
        }
        for (int i = 1; i < dimSequence; i++) {
            for (int j = 1; j < dimbaseString; j++) {
                if (sequence.charAt(i) == baseString.charAt(j)) {
                        k = rect[i][j - 1] + rect[i - 1][j - 1];
                }
                rect[i][j] = k;
            }
        }
        return rect[dimSequence - 1][dimbaseString - 1];
    }

    @Test
    public  void test() {
        String S = "wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmco ";
        String T =        "wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxp";

        //String T = "ge";
        //String S = "geeksforgeeks";
        System.out.println(findSubsequenceCount(S, T));
        Assert.assertEquals(4,findSubsequenceCount(S, T));
    }

}
