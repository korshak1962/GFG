package com.slava.slidingWindow;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
//https://www.educative.io/courses/grokking-the-coding-interview/YQQwQMWLx80
public class SubstringKdistinctCharacters {

    /*
Given a string, find the length of the longest substring in it with no more than K distinct characters.

Example 1:

Input: String="araaci", K=2
Output: 4
Explanation: The longest substring with no more than '2' distinct characters is "araa".
*/
    @Test
    public void testNoop() {
        String inputString = "araaci";
        int K = 2;
        Assert.assertEquals(4, findLomgestSubstring(inputString, K));
    }

    @Test
    public void testNoop1() {
        String inputString = "araaci";
        int K = 1;
        Assert.assertEquals(2, findLomgestSubstring(inputString, K));
    }

    @Test
    public void testNoop2() {
        String inputString = "cbbebi";
        int K = 3;
        Assert.assertEquals(5, findLomgestSubstring(inputString, K));
    }

    int findLomgestSubstring(String inputString, int K) {
        int result = 0;
        int startIndex = 0;
        Map<Character, Integer> charToIndex = new HashMap<>();
        for (int i = 0; i < inputString.length(); i++) {
            charToIndex.put(inputString.charAt(i), i);
            while (charToIndex.size() > K) {
                char startChar = inputString.charAt(startIndex);
                startIndex = charToIndex.get(startChar) + 1;
                charToIndex.remove(startChar);
            }
            result = Integer.max(result, i - startIndex + 1);
        }
        return result;
    }
}
