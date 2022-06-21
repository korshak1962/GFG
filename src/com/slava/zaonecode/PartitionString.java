package com.slava.zaonecode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
//https://aonecode.com/amazon-online-assessment-partition-string
public class PartitionString {
/*
Given a string S of lowercase letters, partition S into as many as parts so that one letter only appear in one part.
Return the partitions as a list.
 */

    @Test
    public void testPS() {
        String input = "bbeadcxede";
        List<String> result = partitionString(input);
        List<String> expected = new LinkedList<>();
        expected.add("bb");
        expected.add("eadcxede");
        Assert.assertEquals(result, result);
    }

    public static List<String> partitionString(String input) {
        List<String> forReturn = new LinkedList<>();
        Set<Character> usedChar = new HashSet<>();
        int startIndex = 0;
        while (true) {
            char startChar = input.charAt(startIndex);
            if (startIndex >= input.length()) {
                return forReturn;
            }
            String partition = input.substring(0, input.indexOf(startChar, startIndex + 1));
            if (partition.isEmpty()) return forReturn;
            forReturn.add(partition);
            usedChar.addAll(partition.chars().mapToObj(c -> Character.valueOf((char) c)).collect(Collectors.toList()));
        }

    }
}
