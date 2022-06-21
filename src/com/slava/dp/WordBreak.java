package com.slava.dp;

//Word Break
//10 40
//https://practice.geeksforgeeks.org/problems/word-break1352/1/?category[]=Dynamic%20Programming&amp;page=1&amp;query=category[]Dynamic%20Programmingpage1
/*
Given a string A and a dictionary of n words B, find out if A can be segmented into a space-separated sequence of dictionary words.


Example 1:

Input:
n = 12
B = { "i", "like", "sam", "sung", "samsung", "mobile",
"ice","cream", "icecream", "man", "go", "mango" }
A = "ilike"
Output: 1
Explanation:The string can be segmented as "i like".
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class WordBreak {


    public static int wordBreak(String A, ArrayList<String> B) {
        boolean dp[] = new boolean[A.length()+1];  // dynamic programming array, true if reachable
        Map<Integer, List<Integer>> starToEnd = new HashMap<>();  // start index of String A where words from B matches
        fillMaping(A, B, starToEnd);
        List<Integer> val;
        for (int v : starToEnd.get(0)) {
            dp[v] = true; // mark as reachable
        }
        for (int i = 1; i < A.length(); i++) {
            if (dp[i] && (val = starToEnd.get(i)) != null) {
                for (int v : val) {
                    dp[i + v] = true; // mark as reachable
                }
            }
        }
        if (dp[A.length()]) return 1;
        return 0;
    }

    private static void fillMaping(String a, ArrayList<String> b, Map<Integer, List<Integer>> startToEnd) {
        List<Integer> val;
        startToEnd.put(0,new ArrayList<>());
        for (String dicWord : b) {
            int res = -1;
            for (int fromIndex = 0; fromIndex < a.length(); fromIndex++) {
                if ((res = a.indexOf(dicWord, fromIndex)) > -1) {
                    if ((val = startToEnd.get(res)) == null) {
                        val = new ArrayList<>();
                    }
                    val.add(dicWord.length());
                    startToEnd.putIfAbsent(res, val);
                }
            }
        }
    }

    @Test
    public void simpleTest() {
        String[] b = {"i", "like", "sam", "sung", "samsung", "mobile", "ice", "cream",
                "icecream", "man", "go", "mango"};
        ArrayList<String> B = new ArrayList<>(Arrays.asList(b));
        String A = "ilike";
        Assert.assertEquals(1, wordBreak(A, B));
    }

    @Test
    public void simpleTest2() {
        String[] b = {"i", "like", "sam", "sung", "samsung", "mobile", "ice", "cream",
                "icecream", "man", "go", "mango"};
        ArrayList<String> B = new ArrayList<>(Arrays.asList(b));
        String A = "ilikesss";
        Assert.assertEquals(0, wordBreak(A, B));
    }

    @Test
    public void simpleTestHard() {
        String[] b = {"acehc"};
        ArrayList<String> B = new ArrayList<>(Arrays.asList(b));
        String A = "acehcacehc";
        Assert.assertEquals(1, wordBreak(A, B));
    }


}
