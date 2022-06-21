package com.slava.codility;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class CreativeNumber {

    public int solution(int A) {
        String orig = String.valueOf(A);
        StringBuilder builder = new StringBuilder();
        Deque<Character> deque = new LinkedList<>();
        for (Character character : orig.toCharArray()) {
            deque.add(character);
        }
        while (!deque.isEmpty()) {
            builder.append(deque.pollFirst());
            if (!deque.isEmpty()) {
                builder.append(deque.pollLast());
            }
        }
        final Integer result = Integer.valueOf(builder.toString());
        return result.intValue();
    }


    @Test
    public void test() {
        int res = solution(130);
        System.out.println(res);
    }

    @Test
    public void test0() {
        int res = solution(0);
        System.out.println(res);
    }
    @Test
    public void test1() {
        int res = solution(100000002);
        System.out.println(res);
    }

    @Test
    public void test2() {
        int res = solution(123456);  //162534
        System.out.println(res);
    }
}

