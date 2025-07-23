package com.slava.strings;

import org.junit.Test;
//https://www.geeksforgeeks.org/problems/string-palindromic-ignoring-spaces4723/1
public class Palindrome {

    public boolean sentencePalindrome(String s) {
        int left = 0;
        String slowCase = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int right = slowCase.length() - 1;
        while (right > left) {
            char cright = slowCase.charAt(right--);
            char cleft = slowCase.charAt(left++);
            if (cleft != cright) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test(){
        String s="_[;S@\n";
        System.out.println(sentencePalindrome(s));
    }
}

