package com.slava.primeNumbers;

import org.junit.Test;

import java.util.*;

public class SumOfPrime {

    /*
    https://practice.geeksforgeeks.org/problems/sum-of-prime/0/

    Given a number (greater than 2), print two prime numbers whose sum will be equal to given number, else print -1 if no such number exists.

NOTE: A solution will always exist if the number is even. Read Goldbach’s conjecture.

If [a, b] is one solution with a <= b, and [c, d] is another solution with c <= d, and a < c then print [a, b] only
and not all possible solutions.

Input:
The first line contains an integer T, depicting total number of test cases. Then following T lines contains an integer N.

Output:
Print the two prime numbers in a single line with space in between.

Constraints:
1 ≤ T ≤ 50
2 ≤ N ≤ 1000000

Example:
Input:
2
8
3
Output
3 5
-1

Explanation:
Testcase 1: two prime numbers from 1 to 8 are 3 and 5 whose sum is 8.

     */


    static NavigableSet<Integer> simpleNums = new TreeSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        Integer[] nums=new Integer[nTest];
        for (int it = 0; it < nTest; it++) {
            nums[it] = sc.nextInt();
        }
        fillSimpleNums(Collections.max(Arrays.asList(nums)));
        for (int it = 0; it < nTest; it++) {
            final int[] primes = findPrimes(nums[it]);
            if (primes != null)
                System.out.println(primes[0] + " " + primes[1]);
            else System.out.println("-1");
        }
    }

    static int[] findPrimes(int num) {
        int[] primes = new int[2];
        int otherSimple;
        for (int simple : simpleNums.headSet(num, true)) {
            otherSimple = num - simple;
            if (otherSimple < simple) {
                break;
            }
            if (simpleNums.contains(otherSimple)) {
                primes[0] = simple;
                primes[1] = otherSimple;
                return primes;
            }
        }
        return null;
    }

    static void fillSimpleNums(int maxNum) {
        simpleNums.add(2);
        for (int i = 3; i <= maxNum; i += 2) {
            simpleNums.add(i);
        }
        int simple = 3;
        while (simple <= maxNum) {
            removeNonSimple(maxNum, simple);
            SortedSet<Integer> tail = simpleNums.tailSet(simple + 1);
            if (tail.isEmpty()) {
                break;
            }
            simple = tail.first();
        }
    }

    static private void removeNonSimple(int maxNum, int simple) {
        int start = simple * simple;
        while (true) {
            if (start > maxNum) break;
            simpleNums.remove(start);
            start += simple;
        }
    }


    @Test
    public void testFindPrimes() {
        int num = 800000;
        fillSimpleNums(num);
        final int[] primes = findPrimes(num);
        if (primes != null)
            System.out.println(primes[0] + " " + primes[1]);
        else System.out.println("-1");
    }

}
