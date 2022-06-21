package com.slava.stacks;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayDeque;
import java.util.Deque;
//https://app.codility.com/programmers/lessons/7-stacks_and_queues/nesting/
public class NestedValidBrackets {

    @Test
    public void testNested() {
        String S = "";
        int res = solution(S);
        Assert.assertEquals(1, res);
    }

    @Test
    public void testNested1() {
        String S = "(";
        int res = solution(S);
        Assert.assertEquals(0, res);
    }

    @Test
    public void testNested2() {
        String S = ")";
        int res = solution(S);
        Assert.assertEquals(0, res);
    }

    @Test
    public void testNested3() {
        String S = "()()";
        int res = solution(S);
        Assert.assertEquals(1, res);
    }

    @Test
    public void testNested4() {
        String S = "())";
        int res = solution(S);
        Assert.assertEquals(0, res);
    }

    public int solution(String S) {
        char ch;
        Deque<Character> deq = new ArrayDeque<>();
        for (int i = 0; i < S.length(); i++) {
            ch = S.charAt(i);
            if (ch == '(') {
                deq.addLast(ch);
            } else {
                if (deq.isEmpty()) {
                    return 0;
                }
                deq.removeLast();
            }
        }
        if (!deq.isEmpty()) {
            return 0;
        }
        return 1;
    }

}
