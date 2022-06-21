package com.slava.codility;

import org.junit.Test;

//https://app.codility.com/demo/results/trainingYSRRMW-KX7/
public class CountDiv {
    public int solution(int A, int B, int K) {
        double div = (double) B / K;
        int up = (int) Math.floor(div);
        div = (double) A / K;
        int low = (int) Math.ceil(div);
        if (K <= B)
            return up - low + 1;
        else if (A == 0) return 1;
        else return 0;
    }

    @Test
    public void test() {
        int res = solution(11, 345, 17);
        System.out.println(res);
    }
}
