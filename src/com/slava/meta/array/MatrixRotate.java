package com.slava.meta.array;

import org.junit.Test;

//https://www.geeksforgeeks.org/problems/rotate-by-90-degree-1587115621/1
public class MatrixRotate {

    static int mat[][];
    static int dim;

    static void rotateby90(int mat[][]) {
        MatrixRotate.mat = mat;
        dim = mat[0].length;
        for (int j=0;j<dim/2;j++) {
            for (int i = j; i < dim-1-j; i++) {
                rotateCounterClockWise(
                        rotateCounterClockWise(
                                rotateCounterClockWise(
                                        rotateCounterClockWise(new Cell(mat[i][j], i, j)))));
            }
        }
    }

    static Cell rotateCounterClockWise(Cell cell) {
        int newI = dim - 1 - cell.j;
        int newJ = cell.i;
        int newVlaue = mat[newI][newJ];
        mat[newI][newJ] = cell.value;

       // printMatrix(mat);

        return new Cell(newVlaue, newI, newJ);
    }

    private static void printMatrix(int[][] mat) {
        System.out.println();
        for (int[] row : mat) {
            for (int x : row) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }

    static class Cell {
        int value;
        int i;
        int j;

        public Cell(int value, int i, int j) {
            this.value = value;
            this.i = i;
            this.j = j;
        }
    }

    @Test
    public void test(){
        int[][] mat = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        printMatrix(mat);
        MatrixRotate.rotateby90(mat);
        // Print the rotated matrix
        printMatrix(mat);
    }

    @Test
    public void test1(){
        int[][] mat = {
                {1, 2, 3},
                {4,5, 6, },
                {7,8,9}
        };
        printMatrix(mat);
        MatrixRotate.rotateby90(mat);
        // Print the rotated matrix
        printMatrix(mat);
    }

    @Test
    public void test2(){
        int[][] mat = {
                {1, 2},
                {3,4 }
        };
        printMatrix(mat);
        MatrixRotate.rotateby90(mat);
        // Print the rotated matrix
        printMatrix(mat);
    }

}
