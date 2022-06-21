package com.slava.combination;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

//https://www.educative.io/courses/grokking-the-coding-interview/NEXBg8YA5A2
public class generateParentheses {
    public static void main(String[] args) {
        List<String> allSubsets = generateValidParentheses(8);
        System.out.println("allSubsets=" + allSubsets);
    }

    @Test
    public void testPerf() {
        List<String> allSubsets = generateValidParentheses(3);
        System.out.println("allSubsets.size=" + allSubsets.size() + " allSubsets=" + allSubsets);
    }

    private static List<String> generateValidParentheses(int num) {
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

    class mySet extends HashSet {

    }
}
