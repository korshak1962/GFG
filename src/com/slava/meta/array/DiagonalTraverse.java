package com.slava.meta.array;

import org.junit.Test;

import java.util.Arrays;

public class DiagonalTraverse {

    public int[] findDiagonalOrder(int[][] mat) {
        int yDim = mat.length;
        int xDim = mat[0].length;

        int[] result = new int[xDim * yDim];
        int iResult = 0;
        int ix = 0;
        int iy = yDim-1;
        while (ix < xDim) {
            while (iy <= yDim - 1) {
                result[iResult++] = mat[ix++][iy++]; //up
            }
            if (ix > xDim - 1) ix--;
            if (iy > yDim - 1) iy--;


            while (ix >= 0) {
                result[iResult++] = mat[ix--][iy--]; //down
            }
            if (iy < 0) iy++;
            ix++;
        }
        return result;
    }


    @Test
    public void test() {
        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
            System.out.println(mat[0][0]);
           System.out.println(mat[1][0]);
          System.out.println(mat[0][1]);
        System.out.println(Arrays.toString(findDiagonalOrder(mat)));
    }

}
