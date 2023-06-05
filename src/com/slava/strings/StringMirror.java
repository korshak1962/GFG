package com.slava.strings;

import org.junit.Test;

//https://practice.geeksforgeeks.org/problems/d385b9d635b7b10eef6bf365b84922aaeec9eb98/1
public class StringMirror {

    @Test
    public void simple() {
        String str = "nbgfugfbdsj";
        System.out.println(stringMirror(str));
    }

    public static String stringMirror(String str) {
        int minIndex = 0;
        char prevchar = str.charAt(0);
        // char[] chars = str.toCharArray();
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) > prevchar) {
                minIndex = i;
                break;
            }
            if (str.charAt(i) == prevchar && str.charAt(i) >= str.charAt(0)) {
                minIndex = i;
                break;
            }
            prevchar = str.charAt(i);
        }
        if (minIndex == 0) minIndex = str.length();
        final String substring = str.substring(0, minIndex);
        String toReturn = substring +
                new StringBuilder(substring).reverse();
        return toReturn;
    }
}

