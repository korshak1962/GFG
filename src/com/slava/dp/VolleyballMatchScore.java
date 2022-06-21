package com.slava.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

//https://www.hackerrank.com/contests/w1/challenges/volleyball-match/problem

public class VolleyballMatchScore {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nX = sc.nextInt();
        int nY = sc.nextInt();
        final int max = Integer.max(nX, nY);
        final int min = Integer.min(nX, nY);
        System.out.println(findAllVars(min, max));
    }

    private static boolean validate(int max, int min) {
        boolean res = true;
        if (max < 25 || min == max || min < 0 || (min > 23 && (max - min) != 2) || max > 1000000000) {
            res = false;
        }
        return res;
    }

    static final int div = 1000000007;

    static int findAllVars(int nX, int nY) {
        int max = Integer.max(nX, nY);
        int min = Integer.min(nX, nY);
        if (!validate(max, min)) {
            return 0;
        }
        if (min > 23) {
            long subRes = allSequanceUnder24To24(25, 25);
            final int cell = min - 24;

            final int shift = 16;
            for (int i = 0; i < cell / shift; i++) {
                subRes = (subRes << shift) % div;
            }

            for (int i = 0; i < (cell%shift ); i++) {
                subRes = (subRes << 1) % div;
            }
            return Long.valueOf(subRes).intValue();
        }
        return allSequanceUnder24To24(max, min + 1);
    }

    private static int allSequanceUnder24To24(int max, int min) {
        int dpMaxCurrent[] = new int[max];
        int dpMaxPrevious[] = new int[max];
        Arrays.fill(dpMaxPrevious, 1);
        Arrays.fill(dpMaxCurrent, 1);
        dpMaxCurrent[0] = 1;
        for (int i = 1; i < min; i++) {
            for (int k = 1; k < max; k++) {
                dpMaxCurrent[k] = (dpMaxPrevious[k] + dpMaxCurrent[k - 1]) % div;
            }
            dpMaxPrevious = dpMaxCurrent;
        }
        return dpMaxCurrent[max - 1];
    }

    @Test
    public void testFindAllVars0() {
        int res = findAllVars(3, 25);
        Assert.assertEquals(2925, res);
    }

    @Test
    public void testFindAllVars1() {
        Assert.assertEquals(697118579, findAllVars(25, 18));
    }

    @Test
    public void testFindAllVars2() {
        Assert.assertEquals(1, findAllVars(0, 25));
    }

    @Test
    public void testFindAllVars3() {
        Assert.assertEquals(562467279, findAllVars(13, 25));
    }


    @Test
    public void testFindAllVars4() {
        Assert.assertEquals(0, findAllVars(1000000000, 1000000000));
    }

    @Test
    public void testFindAllVars5() {
        Assert.assertEquals(0, findAllVars(24, 25));
    }

    @Test
    public void testFindAllVars6() {
        Assert.assertEquals(603457371, findAllVars(24, 26));
    }

    @Test
    public void testFindAllVars7() {
        // System.out.println(Integer.MAX_VALUE);
        Assert.assertEquals(256417096, findAllVars(126187267, 126187269));
    }

    @Test
    public void testFindAllVars8() {
        Assert.assertEquals(575548948, 1000000000, 999999998);
    }

}
