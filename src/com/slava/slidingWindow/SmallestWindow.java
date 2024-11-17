package com.slava.slidingWindow;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

//https://www.geeksforgeeks.org/problems/smallest-window-in-a-string-containing-all-the-characters-of-another-string-1587115621/1?page=1&company=Atlassian&difficulty=Medium,Hard&sortBy=submissions
public class SmallestWindow {

  //Function to find the smallest window in the string string1 consisting
  //of all the characters of string string2.
  public static String smallestWindow(String string1, String string2) {
    if (string2.isEmpty() || string1.isEmpty()) {
      return "";
    }
    Map<Character, Integer> characterToNum = new HashMap<>();
    for (int i = 0; i < string2.length(); i++) {
      characterToNum.put(string2.charAt(i), characterToNum.getOrDefault(string2.charAt(i), 0) + 1);
    }
    int left = 0;
    int right = 1;
    String result = null;
    Integer val = characterToNum.computeIfPresent(string1.charAt(left), (k, v) -> v - 1);
    if (val != null && val == 0) {
      if (checkMap(characterToNum)) {
        return string1.substring(left, right);
      }
    }
    while (left <= right && right < string1.length()) {
      val = characterToNum.computeIfPresent(string1.charAt(right), (k, v) -> v - 1);
      if (val != null && val == 0 && checkMap(characterToNum)) {
        while (left <= right) {
          val = characterToNum.computeIfPresent(string1.charAt(left), (k, v) -> v + 1);
          if (val != null && val > 0) {
            if (result == null || result.length() > right - left + 1) {
              result = string1.substring(left, right + 1);
            }
            left++;
            break;
          }
          left++;
        }
      }
      right++;
    }
    return result;
  }

  static boolean checkMap(Map<Character, Integer> characterToNum) {
    for (Integer v : characterToNum.values()) {
      if (v > 0) {
        return false;
      }
    }
    return true;
  }

@Test
  public void test() {
    String string1 = "timetopractice";
    String string2 = "toc";
    System.out.println(smallestWindow(string1, string2));
  }

}
