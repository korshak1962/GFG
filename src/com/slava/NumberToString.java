package com.slava;

import org.junit.Test;

import java.text.NumberFormat;
import java.util.*;

public class NumberToString {

    Comparator<Integer> cp = Integer::compare;
    Comparator<Integer> cpReversed = cp.reversed();

    public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
        int cmp1 = a.getValue().compareTo(b.getValue());
        if (cmp1 != 0) {
            return cmp1;
        } else {
            return a.getKey().compareTo(b.getKey());
        }
    }

    public void test() {
        int[][] source = new int[2][2];
        int[][] destination = Arrays.stream(source).map(a -> Arrays.copyOf(a, a.length))
                .toArray(int[][]::new);
    }

    @Test
    public void testInt() {
        final int number = 1234567;
        String nn = NumberFormat.getInstance().format(number);
        System.out.println(nn);
        String ss = String.valueOf(number);
        int numGroups=(int)Math.ceil((float)ss.length()/3);
        System.out.println(numGroups);
    }

    static Map<Integer, String> intToWords = new HashMap<>();

    static {
        intToWords.put(0, "");
        intToWords.put(1, "one");
        intToWords.put(2, "two");
        intToWords.put(3, "three");
        intToWords.put(4, "four");
        intToWords.put(5, "five");
        intToWords.put(6, "six");
        intToWords.put(7, "seven");
        intToWords.put(8, "eight");
        intToWords.put(9, "nine");

        intToWords.put(20, "twenty");
        intToWords.put(90, "ninety");
    }

    @Test
    public void test998() {
        System.out.println(up999(998));
    }

    @Test
    public void test998000() {
        System.out.println(numToString(998000));
    }

    @Test
    public void test998001() {
        System.out.println(numToString(998001));
    }

    @Test
    public void test998001000() {
        System.out.println(numToString(998001003));
    }

    String numToString(int num) {
        String[] groupedBy3digits = NumberFormat.getInstance().format(num).split(",");

        switch (groupedBy3digits.length) {
            case 3:
                return up999(Integer.valueOf(groupedBy3digits[0])) + " millions " +
                        numToString(Integer.valueOf(groupedBy3digits[1] + groupedBy3digits[2])) +" ";
            case 2:
                return up999(Integer.valueOf(groupedBy3digits[0])) + " thousands " +
                        numToString(Integer.valueOf(groupedBy3digits[1]));
            case 1:
                return up999(Integer.valueOf(groupedBy3digits[0]));
        }
        String forReturn = "";

        return forReturn;
    }

    String up999(int num) {
        if (num < 21) {
            return intToWords.get(num);
        }
        String numString = String.valueOf(num);
        if (num <= 99) {
            return intToWords.get(Integer.valueOf(numString.substring(0, 1) + "0")) + " "
                    + intToWords.get(Integer.valueOf(numString.substring(1, 2)));
        }
        return intToWords.get(Integer.valueOf(numString.substring(0, 1))) + " hundreds" + " " +
                up999(Integer.valueOf(numString.substring(1, 3)));
    }

    @Test
    public void testFormat(){
        String res=String.format("people[%d][%d]=%tc", 1, 2, new Date());
        System.out.println(res);
    }

}
