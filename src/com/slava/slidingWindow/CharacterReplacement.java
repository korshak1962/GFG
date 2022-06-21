package com.slava.slidingWindow;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//https://www.educative.io/courses/grokking-the-coding-interview/R8DVgjq78yR
public class CharacterReplacement {

    /*
    Given a string with lowercase letters only, if you are allowed to replace no more than ‘k’
    letters with any letter, find the length of the longest substring having the same letters after replacement.

    Input: String="abbcb", k=1
Output: 4
Explanation: Replace the 'c' with 'b' to have a longest repeating substring "bbbb".
     */

    @Test
    public void test1() {
        String str = "abbcb";
        int k = 1;
        Assert.assertEquals(4,findLength(str, k));
    }

    public static int findLength(String str, int k) {
        Map<Character, Integer> charToScore = new HashMap<>();
        int leftIndex = 0;
        int forReturn = 0;
        for (int index = 0; index < str.length(); index++) {
            final char curChar = str.charAt(index);
            charToScore.computeIfPresent(curChar, (key, value) -> value++);
            charToScore.putIfAbsent(curChar, 1);
            while (charToScore.size() - k > 1) {
                char leftChar = str.charAt(leftIndex);
                if (charToScore.get(leftChar) == 1) charToScore.remove(leftChar);
                else charToScore.compute(leftChar, (key, value) -> value--);
                leftIndex++;
            }
            forReturn = Integer.max(forReturn, index - leftIndex + 1);
        }
        return forReturn;
    }


    public static int findLength1(String str, int k) {
        Map<Character, Integer> charToScore = new HashMap<>();
        int leftIndex = 0;
        int forReturn = 0;
        int maxRepeatLetterCount = 0;
        for (int rightIndex = 0; rightIndex < str.length(); rightIndex++) {
            final char curChar = str.charAt(rightIndex);
            final int score = charToScore.getOrDefault(curChar, 0) + 1;
            maxRepeatLetterCount = Integer.max(maxRepeatLetterCount, score);
            charToScore.put(curChar, score);
            if (rightIndex - maxRepeatLetterCount - leftIndex + 1 > k) {
                charToScore.put(str.charAt(leftIndex), charToScore.get(str.charAt(leftIndex)));
                leftIndex++;
            }
            forReturn = Integer.max(forReturn, rightIndex - leftIndex + 1);
        }
        return forReturn;
    }

}
