package com.slava.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

//https://practice.geeksforgeeks.org/problems/allocate-minimum-number-of-pages/0
public class BooksAndPages {

    /*
    You are given N number of books. Every ith book has Pi number of pages.
You have to allocate books to M number of students. There can be many ways or permutations to do so. In each permutation one of the M students will be allocated the maximum number of pages. Out of all these permutations, the task is to find that particular permutation in which the maximum number of pages allocated to a student is minimum of those in all the other permutations, and print this minimum value.
Each book will be allocated to exactly one student. Each student has to be allocated atleast one book.
Note: Return -1 if a valid assignment is not possible, and allotment should be in contiguous order (see explanation for better understanding).

Input:
The first line contains 'T' denoting the number of testcases. Then follows description of T testcases:Each case begins with a single positive integer N denoting the number of books.The second line contains N space separated positive integers denoting the pages of each book.And the third line contains another integer M, denoting the number of students.

Output:
For each test case, output a single line containing minimum number of pages each student has to read for corresponding test case.

Expected Time Complexity: O(NLogN).
Expected Auxiliary Space: O(1).

Constraints:
1 <= T <= 100
1 <= N <= 106
1 <= A [ i ] <= 106
1 <= M <= 106

Example:
Input:
2
4
12 34 67 90
2
3
15 17 20
2
Output:
113
32
     */

    @Test
    public void maxPages() {
        int[] books = {12, 34, 67, 90};
        int numStudents = 2;
        Assert.assertEquals(113, maxPages(books, numStudents));
    }

    @Test
    public void maxWork3() {
        int[] books = {15, 17, 20};
        int numStudents = 2;
        Assert.assertEquals(32, maxPages(books, numStudents));
    }

    @Test
    public void minPages4() {
        int[] books = {250, 74, 159, 181, 23, 45, 129, 174};
        int numStudents = 6;
        Assert.assertEquals(250, maxPages(books, numStudents));
    }

    @Test
    public void minPages2() {
        int[] books = {148, 122};
        int numStudents = 1;
        Assert.assertEquals(270, maxPages(books, numStudents));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        // List<Integer> boards = new LinkedList<>();
        for (int it = 0; it < nTest; it++) {
            int numBooks = sc.nextInt();
            int[] books = new int[numBooks];
            for (int i = 0; i < numBooks; i++) {
                books[i] = sc.nextInt();
            }
            // System.out.println(numBooks);
            int numStudents = sc.nextInt();
            System.out.println(maxPages(books, numStudents));
        }
        sc.close();
    }

    public static int maxPages(int books[], int numStudents) {
        if (numStudents > books.length) {
            return -1;
        }
        long lowBoundPages = 0;
        long maxBoundPages = 0;
        long totalPages = 0;
        for (int pgsInBook : books) {
            totalPages += pgsInBook;
        }
        long currentPages = 0;
        lowBoundPages = totalPages / numStudents;
        maxBoundPages = totalPages;
        while (lowBoundPages < maxBoundPages) {
            currentPages = (maxBoundPages + lowBoundPages) / 2;
            if (canDistribute(books, numStudents, currentPages)) {
                maxBoundPages = currentPages;
            } else {
                lowBoundPages = currentPages + 1;
            }
        }
        return (int)maxBoundPages;
    }

    private static boolean canDistribute(int books[], int numStudents, long maxPages) {
        long sumPgs = 0;
        long counter = 1;
        for (int pgsInBook : books) {
            if (pgsInBook > maxPages) {
                return false;
            }
            long currentSum = sumPgs + pgsInBook;
            if (currentSum <= maxPages) {
                sumPgs = currentSum;
            } else {
                counter++;
                if (counter > numStudents) {
                    return false;
                } else {
                    sumPgs = pgsInBook;
                }
            }
        }
        return true;
    }


    public static int maxPages0(int books[], int numStudents) {
        int nBooks = books.length;
        int[] dp0 = new int[nBooks];
        dp0[0] = books[0];
        for (int i = 1; i < nBooks; i++) {
            dp0[i] = dp0[i - 1] + books[i];
        }
        int[] dpPrev = Arrays.copyOf(dp0, dp0.length);
        int[] dpNext = Arrays.copyOf(dp0, dp0.length);
        for (int j = 1; j < numStudents; j++) {
            for (int i = 1; i < nBooks; i++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < i; k++) {
                    int max = Integer.max(dpPrev[k], dp0[i] - dp0[k]);
                    min = Integer.min(min, max);
                }
                dpNext[i] = min;
            }
            dpPrev = Arrays.copyOf(dpNext, dpNext.length);
        }
        return dpNext[nBooks - 1];
    }

}
