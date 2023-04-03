package com.slava.backTracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//https://www.youtube.com/watch?v=qBbZ3tS0McI
//https://www.educative.io/courses/grokking-the-coding-interview/NEXBg8YA5A2
//https://practice.geeksforgeeks.org/problems/generate-all-possible-parentheses/1?company%5B%5D=Google&company%5B%5D=Google&page=1&query=company%5B%5DGooglepage1company%5B%5DGoogle
public class GenerateParentheses {
    //right from youtube
    public static void main(String[] args) {
        List<String> allSubsets = generateValidParentheses(3);
        System.out.println("allSubsets=" + allSubsets);
    }

    public List<String> AllParenthesis(int num) {
        List<String> list1 = new ArrayList<>();
        list1.add("()");
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < num - 1; i++) {
            list2 = insertOpen(list1);
        }
        return list2;
    }

    List<String> insertOpen(List<String> list) {
        for (String str : list) {
            for (int i = 0; i < str.length(); i++) {

            }
        }
        return null;
    }

    List<String> insertClose(List<String> list) {
        return null;
    }

    @Test
    public void testPerf() {
        List<String> allSubsets = generateValidParentheses(3);
        System.out.println("allSubsets.size=" + allSubsets.size() + " allSubsets=" + allSubsets);
    }

    static List<String> paranrthesis = new LinkedList<>();
    static int num;

//right from youtube
    private static List<String> generateValidParentheses(int num) {
        GenerateParentheses.num = num;
        recursion(0, 0, "");
        return paranrthesis;
    }

    //right from youtube
    private static void recursion(int open, int close, String str) {
        if (str.length() == GenerateParentheses.num * 2) {
            paranrthesis.add(str);
            return;
        }
        if (open < GenerateParentheses.num) {
            recursion(open + 1, close, str + "(");
        }
        if (close < open) {
            recursion(open, close + 1, str + ")");
        }
    }

    private static List<String> generateValidParentheses1(int num) {
        List<String> allSubsets = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            builder.insert(0, "(");
            builder.append(")");
        }
        List<String> newPortion = new LinkedList<>();
        allSubsets.add(builder.toString());
        for (int i = 0; i < num; i++) {
            for (String oldStr : allSubsets) {
                for (int ii = i + 1; ii < num; ii++) {
                    if (oldStr.charAt(i) == ')') {
                        continue;
                    }
                    newPortion.add(createSwapString(oldStr, num + i, ii));
                }
            }
            allSubsets.addAll(newPortion);
            newPortion.clear();
        }
        return allSubsets;
    }

    private static String createSwapString(final String sourceString, int from, int to) {
        char charFrom = sourceString.charAt(from);
        StringBuilder builder = new StringBuilder(sourceString);
        builder.deleteCharAt(from).insert(to, charFrom);
        final String toReturn = builder.toString();
        //System.out.println("sourceString="+sourceString+" toReturn=" + toReturn + " from=" + from + " to=" + to);
        return toReturn;
    }

}
