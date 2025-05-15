package com.slava.meta;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class WrongAnswers {

    // 1 trie
// minimum meeting room
//
    //https://www.metacareers.com/profile/coding_puzzles?puzzle=203188678289677&source=career_site_login_panel_sign_up_button
    public long getMaxAdditionalDinersCount(long N, long K, int M, long[] S) {
        if (M == 0 || K > N) {
            return 0L;
        }
        Arrays.sort(S);
        long res = 0;
        for (int i = 0; i < S.length - 1; i++) {
            // res += dinersInGap(S[i + 1] - S[i] - 1, K);
            res += (S[i + 1] - S[i] - 1 - K) / (K + 1);
        }
        res += (S[0] - 1) / (K + 1);
        res += (N - S[S.length - 1] - K - 1) / (K + 1);
        if ((N - S[S.length - 1]) >= K + 1) {
            res++;
        }
        return res;
    }

    long dinergggsInGap(long gap, long space) {
        return (gap - space) / (space + 1);
    }


    @Test
    public void test() {
        long N = 10;
        long K = 1;
        int M = 2;
        long[] S = {2, 6};
        long res = getMaxAdditionalDinersCount(N, K, M, S);
        Assert.assertEquals(3, res);
    }

    @Test
    public void test1() {
        long N = 15;
        long K = 2;
        int M = 3;
        long[] S = {11, 6, 14};
        long res = getMaxAdditionalDinersCount(N, K, M, S);
        Assert.assertEquals(1, res);
    }

    //https://www.metacareers.com/profile/coding_puzzles?puzzle=870874083549040&source=career_site_login_panel_sign_up_button
    public int getArtisticPhotographCount(int N, String C, int X, int Y) {
        final char photographer = 'P';
        final char actor = 'A';
        final char backdrop = 'B';
        int res = 0;
        char[] chars = C.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == photographer) {
                for (int k = i + X; k <= i + Y && k<chars.length; k++) {
                    if (chars[k] == actor) {
                        for (int j = k + X; j <= k + Y && j<chars.length; j++) {
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
                for (int k = i + X; k <= i + Y && k<chars.length; k++) {
                    if (chars[k] == actor) {
                        for (int j = k + X; j <= k + Y && j<chars.length; j++) {
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
    public void test3(){
        int N = 5;
        String C = "APABA";
        int X = 1;
        int Y = 2;
        int res =getArtisticPhotographCount(N,C,X,Y);
        Assert.assertEquals(1,res);
    }

    @Test
    public void test4(){
        int N = 5;
        String C = "APABA";
        int X = 2;
        int Y = 3;
        int res =getArtisticPhotographCount(N,C,X,Y);
        Assert.assertEquals(0,res);
    }

    @Test
    public void test5(){
        int N = 8;
        String C = ".PBAAP.B";
        int X = 1;
        int Y = 3;
        int res =getArtisticPhotographCount(N,C,X,Y);
        Assert.assertEquals(3,res);
    }
}
