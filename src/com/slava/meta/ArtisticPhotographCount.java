package com.slava.meta;

import org.junit.Assert;
import org.junit.Test;

public class ArtisticPhotographCount {

    //https://www.metacareers.com/profile/coding_puzzles?puzzle=870874083549040&source=career_site_login_panel_sign_up_button
    public int getArtisticPhotographCount1(int N, String C, int X, int Y) {
        final char photographer = 'P';
        final char actor = 'A';
        final char backdrop = 'B';
        int res = 0;
        char[] chars = C.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == photographer) {
                for (int k = i + X; k <= i + Y && k < chars.length; k++) {
                    if (chars[k] == actor) {
                        for (int j = k + X; j <= k + Y && j < chars.length; j++) {
                            if (chars[j] == backdrop) {
                                res++;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == backdrop) {
                for (int k = i + X; k <= i + Y && k < chars.length; k++) {
                    if (chars[k] == actor) {
                        for (int j = k + X; j <= k + Y && j < chars.length; j++) {
                            if (chars[j] == photographer) {
                                res++;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    @Test
    public void test3() {
        int N = 5;
        String C = "APABA";
        int X = 1;
        int Y = 2;
        int res = getArtisticPhotographCount(N, C, X, Y);
        Assert.assertEquals(1, res);
    }

    @Test
    public void test4() {
        int N = 5;
        String C = "APABA";
        int X = 2;
        int Y = 3;
        int res = getArtisticPhotographCount(N, C, X, Y);
        Assert.assertEquals(0, res);
    }

    @Test
    public void test5() {
        int N = 8;
        String C = ".PBAAP.B";
        int X = 1;
        int Y = 3;
        int res = getArtisticPhotographCount(N, C, X, Y);
        Assert.assertEquals(3, res);
    }


    public int getArtisticPhotographCount(int N, String C, int X, int Y) {
        int count = 0;

        // Precompute cumulative sums for P and B
        int[] pSum = new int[N + 1];
        int[] bSum = new int[N + 1];

        for (int i = 0; i < N; i++) {
            char c = C.charAt(i);
            pSum[i + 1] = pSum[i] + (c == 'P' ? 1 : 0);
            bSum[i + 1] = bSum[i] + (c == 'B' ? 1 : 0);
        }

        // For each 'A', check all possible combinations
        for (int i = 0; i < N; i++) {
            if (C.charAt(i) != 'A') continue;

            // Check all potential PAB combinations
            // P is in range [i-Y, i-X] and B is in range [i+X, i+Y]
            int pLeftCount = pSum[Math.max(0, i - X + 1)] - pSum[Math.max(0, i - Y)];
            int bRightCount = bSum[Math.min(N, i + Y + 1)] - bSum[Math.min(N, i + X)];
            count += pLeftCount * bRightCount;

            // Check all potential BAP combinations
            // B is in range [i-Y, i-X] and P is in range [i+X, i+Y]
            int bLeftCount = bSum[Math.max(0, i - X + 1)] - bSum[Math.max(0, i - Y)];
            int pRightCount = pSum[Math.min(N, i + Y + 1)] - pSum[Math.min(N, i + X)];
            count += bLeftCount * pRightCount;
        }

        return count;
    }

}
