package com.slava.stacks;

import org.junit.Test;

import java.util.*;

//https://practice.geeksforgeeks.org/problems/reversing-the-equation2205/1
public class ReverseEquation {
    Deque<Character> operations = new LinkedList<>();
    Deque<String> args = new LinkedList<>();
    Set<Character> oper = new HashSet<>(Arrays.asList('+', '-', '*', '/'));

    @Test
    public void test() {
        String s = "20-3+5*2";
        String str = new ReverseEquation().reverseEqn(s);
        System.out.println(str);
    }


    String reverseEqn(String S) {
        StringBuilder sb = new StringBuilder();
        for (char ch : S.toCharArray()) {
            if (oper.contains(ch)) {
                operations.add(ch);
                args.add(sb.toString());
                sb.delete(0, sb.length());
            } else {
                sb.append(ch);
            }
        }
        args.add(sb.toString());
        StringBuilder result = new StringBuilder();
        while (!args.isEmpty()) {
            result.append(args.removeLast());
            Character op;
            if (!operations.isEmpty()) {
                op = operations.removeLast();
                result.append(op);
            }
        }
        return result.toString();
    }
}