package com.slava.meta;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//https://www.metacareers.com/profile/coding_puzzles?puzzle=977526253003069&source=career_site_login_panel_sign_up_button
public class Frog {

    public long getSecondsRequiredM(long N, int F, long[] P) {
        long res = 0;
        Arrays.sort(P);
        long start = P[0];
        boolean[] frogsAndSpace = new boolean[(int) (N - start + 1)];
        for (long l : P) {
            frogsAndSpace[(int) (l - start)] = true;
        }
        int frog = 0;
        int space = 0;
        while (frog < frogsAndSpace.length && space < frogsAndSpace.length) {
            while (frogsAndSpace[frog]) {
                if (!frogsAndSpace[space]) {
                    res++;
                    frogsAndSpace[space] = true;
                    frogsAndSpace[frog] = false;
                    space++;
                } else {
                    space++;
                }
            }
            frog++;
        }
        res+=frogsAndSpace.length-frog - 1;
        return res;
    }

    @Test
    public void test() {
        long N = 3;
        int F = 1;
        long[] P = {1};
        long res = getSecondsRequiredL(N, F, P);
        Assert.assertEquals(2, res);
    }

    @Test
    public void test1() {
        long N = 6;
        int F = 3;
        long[] P = {5, 2, 4};
        long res = getSecondsRequiredL(N, F, P);
        Assert.assertEquals(4, res);
    }

    public long getSecondsRequiredL(long N, int F, long[] P) {
        // Create a new array with one extra space for N
        long[] extendedP = new long[P.length + 1];

        // Copy elements from P to extendedP
        for (int i = 0; i < P.length; i++) {
            extendedP[i] = P[i];
        }

        // Add N at the end
        extendedP[P.length] = N;

        // Sort the array
        Arrays.sort(extendedP);

        long count = 0;
        for (int i = 0; i < extendedP.length - 1; i++) {
            count += extendedP[i + 1] - 1 - extendedP[i];
        }

        count += F;
        return count;
    }
}
