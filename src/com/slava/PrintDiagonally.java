package com.slava;

import org.junit.Test;

import java.util.Scanner;

public class PrintDiagonally {

    /*
    https://practice.geeksforgeeks.org/problems/print-diagonally/0/
    Give a N*N square matrix, return an array of its anti-diagonals. Look at the example for more details.

For Example:
If the matrix is

1 2 3
4 5 6
7 8 9
The output should Return the following :

[
  [1],
  [2, 4],
  [3, 5, 7],
  [6, 8],
  [9]
]
i.e print the elements of array diagonally.

Note: The input array given is in single line and you have to output the answer in one line only.



Input:

The first line contains an integer T, depicting total number of test cases.
Then following T lines contains an integer N depicting the size of square matrix and next line followed by the value of array.


Output:

Print the elements of matrix diagonally in separate line.


Constraints:

1 ≤ T ≤ 30
1 ≤ N ≤ 20
0 ≤ A[i][j] ≤ 9


Example:

Input:
2
2
1 2 3 4
3
1 2 3 4 5 6 7 8 9
Output:
1 2 3 4
1 2 4 3 5 7 6 8 9
     */


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        for (int it = 0; it < nTest; it++) {
            int length = sc.nextInt();
            int matrix[][] = new int[length][length];
            for (int iy = 0; iy < length; iy++) {
                for (int ix = 0; ix < length; ix++) {
                    matrix[ix][iy] = sc.nextInt();
                }
            }
            printDiag(matrix);
        }
    }

    static void printDiag(int matrix[][]) {
        int length = matrix[0].length;
        for (int ix = 0; ix < matrix[0].length; ix++) {
            //        System.out.println();
            int ixx = ix;
            int iyy = 0;
            while (ixx >= 0 && ixx < length && iyy >= 0 && iyy < length) {
                System.out.print(matrix[ixx--][iyy++]+" ");
            }
        }
        for (int iy = 1; iy < matrix[0].length; iy++) {
            //      System.out.println();
            int iyy = iy;
            int ixx = length-1;
            while (ixx >= 0 && ixx < length && iyy >= 0 && iyy < length) {
                System.out.print(matrix[ixx--][iyy++]+" ");
            }
        }
        System.out.println();
    }
    @Test
    public void testPrintDiag() {
        int matrix[][] = new int[2][2];
        matrix[0][0] = 0;
        matrix[1][0] = 1;
        matrix[0][1] = 2;
        matrix[1][1] = 3;
        printDiag(matrix);
    }

}
