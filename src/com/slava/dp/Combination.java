package com.slava.dp;

import org.junit.Test;

import java.util.*;

public class Combination {


    @Test
    public void test() {
        int N = 5;
        int R = 3;
        generate(N, R);
        for (int[] combination : combinations) {
            //  System.out.println(Arrays.toString(combination));
        }
        //generateAll(N,R);
    }

    List<int[]> combinations = new LinkedList<>();
    int data[];
    int end;
    int counter = 0;

    @Test
    public void testDP() {
        int n = 5;
        int r = 3;
        generateDP(n, r);
    }

    public void generateDP(int n, int r) {
        fillArray(n);
        List DP[][] = new List[n + 1][r + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int k = 0; k < r + 1; k++) {
                DP[i][k] = Collections.emptyList();
            }
        }
        for (int i = 1; i < n + 1; i++) {
            List<Integer> lst = new ArrayList<>();
            lst.add(data[i - 1]);
            List<List<Integer>> lstLst = new ArrayList<>();
            lstLst.add(lst);
            lstLst.addAll(DP[i - 1][1]);
            DP[i][1] = lstLst;
        }
        printRow(n,1, DP);
        for (int k = 2; k < r + 1; k++) {
            for (int i = 2; i < n + 1; i++) {
                List<List<Integer>> lstLst = new ArrayList<>();
                lstLst.addAll(DP[i - 1][k]);
                for (List<Integer> lstLstDP : (List<List<Integer>>) DP[i - 1][k - 1]) {
                    List<Integer> lst = new ArrayList<>(lstLstDP);
                    lst.add(data[i - 1]);
                    lstLst.add(lst);
                }
                DP[i][k] = lstLst;
            }
        }
        printRow(n,2, DP);
        // lst.add(123);lst.add(1001);
        // List<List<Integer>> lstLst = new ArrayList<>();
        // lstLst.add(lst);
        // DP[0][0] = lstLst;
        for (List<Integer> lstLstDP : (List<List<Integer>>) DP[n][r]) {
            System.out.println(lstLstDP);
        }
    }

    private void printRow(int n,int k, List[][] DP) {
        System.out.println("k="+k);
        for (int i = 2; i < n + 1; i++) {
            System.out.println("i=" + i  + " " + DP[i][k]);
        }
    }

    public void generateAll(int n, int r) {
        fillArray(n);
        List<List<Integer>> resComb = new LinkedList<>();

        for (int i = 0; i < data.length; i++) {
            int resCombSize = resComb.size();
            for (int nn = 0; nn < resCombSize; nn++) {

            }
            List<Integer> comb = new LinkedList<>();
            comb.add(data[i]);
            resComb.add(comb);
        }
        for (List<Integer> comb : resComb) {
            if (comb.size() == r) {
                System.out.println(comb);
            }
        }
    }

    private void fillArray(int n) {
        data = new int[n];
        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }
    }

    public void generate(int n, int r) {
        data = new int[r];
        end = n - 1;
        helper(0, 0);
    }

    // index - index  in combination to be filled with start
    private void helper(int index, int start) {
        if (index == data.length) {   // collect r numbers, finish this  combination
            System.out.println(Arrays.toString(data.clone()) + " counter=" + counter);
        } else if (start <= end) {  // add index to combination
            data[index] = start;
            counter++;
            //           System.out.println("==="+Arrays.toString(data.clone()));
            helper(index + 1, start + 1);
            helper(index, start + 1); // increase at index
        }
    }
}
