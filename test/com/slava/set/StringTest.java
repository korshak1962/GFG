package com.slava.set;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class StringTest {

    @Test
    public void test() {
        Set<Character> original = new HashSet<>();
        original.add('a');
        original.add('b');
        Set<Character> chars = new HashSet<>();
        chars.add('a');
     //   chars.add('b');
        System.out.println(chars.containsAll(original));
    }
}
