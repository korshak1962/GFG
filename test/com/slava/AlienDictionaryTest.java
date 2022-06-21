package com.slava;

import com.slava.graph.AlienDictionary;
import org.junit.Test;

public class AlienDictionaryTest {

    @Test
    public void isOrderedTest() {
        int k = 4;
        String[] words = new String[]{"baa", "abcd", "abca", "cab", "cad"};
        System.out.println(AlienDictionary.isSorteable(words, k));
    }
    @Test
    public void isOrderedTest1() {
        int k = 3;
        String[] words = new String[]{"caa","aaa","aab"};
        System.out.println(AlienDictionary.isSorteable(words, k));
    }

    @Test
    public void isOrderedTest2() {
        int k = 3;
        String[] words = new String[]{"caa","aaa","cab"};
        System.out.println(AlienDictionary.isSorteable(words, k));
    }

}
