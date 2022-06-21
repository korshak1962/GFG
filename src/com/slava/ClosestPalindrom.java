package com.slava;

import java.util.Scanner;

public class ClosestPalindrom {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        for (int it = 0; it < nTest; it++) {
            Long input = sc.nextLong();
            System.out.println(getClosest(input));
        }
        sc.close();
    }

    public static String getClosest(Long input) {
        final String inputStr = input.toString();
        if (isPalindrome(input)) {
            return inputStr;
        }
        int rest = inputStr.length() % 2;
        String bigPart = inputStr.substring(0, inputStr.length() / 2 + rest);
        Long bigLong = Long.parseLong(bigPart);
        final long flat = makePalindrom(bigLong,rest);
        long deltaFlat = Math.abs(input - flat);
        final long minus = makePalindrom(bigLong - 1,rest);
        long deltaMinus = Math.abs(input - minus);
        final long plus = makePalindrom(bigLong + 1,rest);
        long deltaPlus = Math.abs(input - plus);
        long minDelta = Long.min(Long.min(deltaFlat, deltaMinus), deltaPlus);
        if (minDelta == deltaMinus) {
            return Long.toString(minus);
        }
        if (minDelta == deltaFlat) {
            return Long.toString(flat);
        }
        if (minDelta == deltaPlus) {
            return Long.toString(plus);
        }
        return null;
    }

    private static long makePalindrom(Long bigLong, int res) {
        final String bigLongStr = bigLong.toString();
        String smallPart = new StringBuilder(bigLongStr.substring(0, bigLongStr.length() - res))
                .reverse().toString();
        String palindrom = bigLong + smallPart;
        Long longPalindrom = Long.parseLong(palindrom);
        return longPalindrom;
    }

    private static boolean isPalindrome(Long input) {
        if (new StringBuilder(input.toString()).reverse().toString().equalsIgnoreCase(input.toString())) {
            return true;
        }
        return false;
    }

}
