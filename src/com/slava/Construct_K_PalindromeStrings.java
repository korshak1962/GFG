package com.slava;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/problems/construct-k-palindrome-strings/
public class Construct_K_PalindromeStrings {

    @Test
    public void testCount(){
        int res = getUnEven("annabelle");
        Assert.assertEquals(1,res);
    }

    @Test
    public void test1(){
        boolean res = canConstruct("annabelle", 2);
        Assert.assertEquals(true,res);
    }

    @Test
    public void test2(){
        boolean res = canConstruct("leetcode", 3);
        Assert.assertEquals(false,res);
    }

    public boolean canConstruct(String s, int k) {
        int unEvenCount = getUnEven(s);
        if (unEvenCount > k) return false;
        if (s.length() < k) return false;
        return true;
    }

    int getUnEven(String str) {
        Map<Character, Integer> hMap = new HashMap<>();
        for (Character ch : str.toCharArray()) {
            hMap.computeIfPresent(ch, (k, v) -> ++v);
            hMap.putIfAbsent(ch, 1);
        }
        int unEven = 0;
        for (Integer val : hMap.values()) {
            if (val % 2 != 0) unEven++;
        }
        return unEven;
    }

}
