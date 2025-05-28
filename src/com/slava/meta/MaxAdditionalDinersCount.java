package com.slava.meta;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MaxAdditionalDinersCount {

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


}
