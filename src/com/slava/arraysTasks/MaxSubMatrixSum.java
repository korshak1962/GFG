package com.slava.arraysTasks;

import java.util.Arrays;


//https://www.geeksforgeeks.org/problems/max-sum-submatrix2725/1
public class MaxSubMatrixSum {
    // static int[][] matrix = new int[3][4];
    // static int k = 2;

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4, 3}, {0, -1, 3, -9, 2}, {2, 0, 0, 0, 2}};
        int k = 3;
        int[][] prefixMatrix = fillPrefixMatrix(matrix, k);
        //print2D(matrix);
        //System.out.println(Arrays.deepToString(matrix));
        //       System.out.println("res=");
        print2D(prefixMatrix);
        int res = findMaxSum(prefixMatrix, k);
        System.out.println("res=" + res);
    }

    public int maxSubmatrix(int[][] matrix, int k) {
        int[][] prefixMatrix = fillPrefixMatrix(matrix, k);
        int res = findMaxSum(prefixMatrix, k);
        return res;
    }

    public static int[][] fillPrefixMatrix(int[][] matrix, int k) {
        int[][] prefixMatrix = new int[matrix.length][matrix[0].length - k + 1];
        for (int irow = 0; irow < matrix.length; irow++) {
            int columnSum = 0;
            for (int icolumn = 0; icolumn < k; icolumn++) {
                columnSum += matrix[irow][icolumn];
            }
            prefixMatrix[irow][0] = columnSum;
            for (int icolumn = k; icolumn < matrix[0].length; icolumn++) {
                prefixMatrix[irow][icolumn - k + 1] = prefixMatrix[irow][icolumn - k] + matrix[irow][icolumn] - matrix[irow][icolumn - k];
            }
        }
        return prefixMatrix;
    }

    public static int findMaxSum(int[][] prefixMatrix, int k) {
        int max = Integer.MIN_VALUE;
        int columnValues[] = new int[prefixMatrix.length];
        for (int icolumn = 0; icolumn < prefixMatrix[0].length; icolumn++) {
            for (int irow = 0; irow < prefixMatrix.length; irow++) {
                columnValues[irow] = prefixMatrix[irow][icolumn];
            }
            max = Integer.max(max, maxSeqInArray(columnValues, k));
        }
        return max;
    }

    public static int maxSeqInArray(int[] arr, int k) {
        // System.out.println("column: " + Arrays.toString(arr));
        int columnSum = 0;
        for (int i = 0; i < k; i++) {
            columnSum += arr[i];
        }
        int max = columnSum;
        for (int i = k; i < arr.length; i++) {
            columnSum = columnSum + arr[i] - arr[i - k];
            max = Integer.max(max, columnSum);
        }
        return max;
    }

    public static void print2D(int mat[][]) {
        // Loop through all rows
        for (int[] row : mat)

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }

}
