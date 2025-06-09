package com.slava.meta.comparator;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AlienDicVerification {


    public boolean isAlienSorted(String[] words, String order) {

        int i = 0;
        String[] wordsCopy = new String[words.length];

        for (String w : words) {
            wordsCopy[i++] = w;
        }
        i = 0;
        final Map<Character, Integer> charToNum = new HashMap<>();
        for (Character ch : order.toCharArray()) {
            charToNum.put(ch, i++);
        }
        Arrays.sort(wordsCopy, (s1, s2) -> {
            int lnth = Integer.min(s1.length(), s2.length());
            for (int k = 0; k < lnth; k++) {
                if (charToNum.get(s1.charAt(k)) > charToNum.get(s2.charAt(k))) return 1;
                if (charToNum.get(s1.charAt(k)) < charToNum.get(s2.charAt(k))) return -1;
            }
            return s1.length() - s2.length();
        });
        return Arrays.equals(words, wordsCopy);
    }

    @Test
    public void test() {
        String[] words = {"hello", "leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.println(isAlienSorted(words, order));
    }

    @Test
    public void test1() {
        String[] words = {"word", "world", "row"};
        String order = "worldabcefghijkmnpqstuvxyz";
        System.out.println(isAlienSorted(words, order));
    }

}
