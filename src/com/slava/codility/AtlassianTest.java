package com.slava.codility;

import org.junit.Test;

import java.util.*;

public class AtlassianTest {
    public int solution(int[] A) {
        Set<Integer> presented = new HashSet<>();
        for (int a : A) {
            if (a > 0) presented.add(a);
        }
        for (int i = 1; i <= 1000000; i++) {
            if (!presented.contains(i)) {
                return i;
            }
        }
        return 0;
    }

    @Test
    public void simple() {
        int[] A = {1, 3, 6, 4, 1, 2};
        System.out.println(solution(A));
    }

    @Test
    public void generateOddLetterString1() {
        System.out.println("res=" + generateOddLetterString(0));
        System.out.println(generateOddLetterString(1));
        System.out.println(generateOddLetterString(2));
        System.out.println(generateOddLetterString(3));
        System.out.println(generateOddLetterString(4));
    }

    public static String generateOddLetterString(int N) {
        if (N < 0) {
            throw new IllegalArgumentException("N must be non-negative");
        }

        StringBuilder result = new StringBuilder();
        if (N % 2 == 0) {
            for (int i = 0; i < N - 1; i++) {
                result.append('a');
            }
            result.append('b');
        } else {
            for (int i = 0; i < N; i++) {
                result.append('a');
            }
        }
        return result.toString();
    }

    public static String generateOddLetterString1(int N) {
        if (N < 0) {
            throw new IllegalArgumentException("N must be non-negative");
        }

        StringBuilder result = new StringBuilder();
        int[] counts = new int[26];
        int lettersUsed = 0;

        for (int i = 0; i < N; i++) {
            int letterIndex;
            if (lettersUsed < 26) {
                letterIndex = lettersUsed;
                lettersUsed++;
            } else {
                letterIndex = findMinOddIndex(counts);
            }

            counts[letterIndex]++;
            result.append((char) ('a' + letterIndex));
        }

        return result.toString();
    }

    private static int findMinOddIndex(int[] counts) {
        int minOdd = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] % 2 == 1 && counts[i] < minOdd) {
                minOdd = counts[i];
                minIndex = i;
            }
        }
        return minIndex;
    }


    @Test
    public void countMoves1() {
        int[] arr = {10, -10, -1, -1, 10};
        System.out.println("res=" + countMoves(arr));
        System.out.println("res=" + minMovesToPositivePrefixSum(arr));
    }


    public int countMoves(int[] A) {
        int moves = 0;
        long currentSum = 0;

        PriorityQueue<Integer> negatives = new PriorityQueue<>();
        for (int num : A) {
            if (num < 0) negatives.add(num);
            currentSum += num;
            while (currentSum < 0) {
                Integer poll = negatives.poll();
                currentSum -= poll;
                moves++;
            }
        }
        return moves;
    }

    public static int minMovesToPositivePrefixSum(int[] nums) {
        int prefixSum = 0;
        int moves = 0;
        List<Integer> negatives = new ArrayList<>();

        for (int num : nums) {
            prefixSum += num;

            if (prefixSum <= 0 && num < 0) {
                negatives.add(num);
            }

            // Check if the prefix sum is negative, then we must make adjustments.
            if (prefixSum <= 0) {
                // Sort the negative numbers to move the most negative (smallest) ones first.
                Collections.sort(negatives);

                // Move the most negative number to the end
                while (!negatives.isEmpty() && prefixSum <= 0) {
                    int mostNegative = negatives.remove(negatives.size() - 1);
                    prefixSum -= mostNegative;
                    moves++;
                }
            }
        }
        return moves;
    }

}
