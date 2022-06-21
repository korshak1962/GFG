package com.slava.codility;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
//https://app.codility.com/demo/results/trainingHUPEDV-PJD/
public class GenomicRangeQuery {

    public int[] solution(String S, int[] P, int[] Q) {
        int[] result = new int[P.length];
        int A[] = new int[S.length()];
        int C[] = new int[S.length()];
        int G[] = new int[S.length()];
        int T[] = new int[S.length()];
        switch (S.charAt(0)) {  // fill at 0
            case 'A':
                A[0]++;
                break;
            case 'C':
                C[0]++;
                break;
            case 'G':
                G[0]++;
                break;
            case 'T':
                T[0]++;
                break;
        }
        for (int i = 1; i < S.length(); i++) {  // fill up to end
            A[i] = A[i - 1];
            C[i] = C[i - 1];
            G[i] = G[i - 1];
            T[i] = T[i - 1];
            switch (S.charAt(i)) {
                case 'A':
                    A[i]++;
                    break;
                case 'C':
                    C[i]++;
                    break;
                case 'G':
                    G[i]++;
                    break;
                case 'T':
                    T[i]++;
                    break;
            }
        }
        for (int i = 0; i < P.length; i++) {
            if (P[i] == 0) { // if starts from 0 then only Q matters
                if (A[Q[i]] > 0) {
                    result[i] = 1;
                    continue; // next sequence
                }
                if (C[Q[i]] > 0) {
                    result[i] = 2;
                    continue;
                }
                if (G[Q[i]] > 0) {
                    result[i] = 3;
                    continue;
                }
                if (T[Q[i]] > 0) {
                    result[i] = 4;
                    continue;
                }
            }
            if (A[Q[i]] - A[P[i] - 1] > 0) {  //P[i] inclusive
                result[i] = 1;
                continue;
            }
            if (C[Q[i]] - C[P[i] - 1] > 0) {
                result[i] = 2;
                continue;
            }
            if (G[Q[i]] - G[P[i] - 1] > 0) {
                result[i] = 3;
                continue;
            }
            if (T[Q[i]] - T[P[i] - 1] > 0) {
                result[i] = 4;
                continue;
            }
        }
        return result;
    }

    /*
    for the input ('AC', [0, 0, 1], [0, 1, 1]) the solution returned a wrong answer (got [1, 2, 2] expected [1, 1, 2]).
     */
    @Test
    public void test1() {
        int P[] = {0, 1};
        int Q[] = {1, 1};
        int[] res = solution("AC", P, Q);
    }

    @Test
    public void test() {
        int P[] = {0};
        int Q[] = {0};
        int[] res = solution("G", P, Q);
    }
}
