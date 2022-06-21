package com.slava.bfs;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
//https://leetcode.com/problems/rotting-oranges/
//https://practice.geeksforgeeks.org/problems/rotten-oranges2536/1

public class RottenOranges {

    @Test
    public void test() {
        Map<String, Integer> strToNum=new HashMap<>();
        strToNum.put("a",1);
        strToNum.put("c",5);
        strToNum.put("e2",4);
        strToNum.entrySet().stream()
                .sorted( Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(2).forEach(System.out::println);

    }


    @Test
    public void test1() {
        int nX = 67;
        int nY = 33;
        for (int ii = 0; ii < 100; ii++) {
            int[][] grid = new int[nX][nY];
            for (int i = 0; i < nX; i++) {
                for (int k = 0; k < nY; k++) {
                    if (k == i) grid[i][k] = 2;
                    else {
                        grid[i][k] = 1;
                    }
                }
            }
            grid[nX - 1][0] = 2;
            grid[0][nY - 1] = 2;

            int lifeSpan = getlifeSpan();
            System.out.println(lifeSpan);
            //       Assert.assertEquals(3, lifeSpan);
        }
    }


    //passed

    public class Cell {
        int index1;
        int index2;

        public Cell(int index1, int index2) {
            if (index1 >= grid.length || index2 >= grid[0].length) {
                throw new RuntimeException("index1=" + index1 + " grid.length " + grid.length +
                        " index2=" + index2 + " grid[0].length" + grid[0].length);
            }
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    List<Cell> newGeneration;
    int[][] grid;

    public static void main(String[] args) throws FileNotFoundException {
        RottenOranges rottenOranges = new RottenOranges();
        Scanner sc = new Scanner(new File("resources/RottenOranges.txt"));
        //Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        for (int it = 0; it < nTest; it++) {
            int nIndex1 = sc.nextInt();
            int nIndex2 = sc.nextInt();
            rottenOranges.grid = new int[nIndex1][nIndex2];
            for (int index1 = 0; index1 < nIndex1; index1++) {
                for (int index2 = 0; index2 < nIndex2; index2++) {
                    rottenOranges.grid[index1][index2] = sc.nextInt();
                }
            }
            System.out.println(rottenOranges.getlifeSpan());
        }
    }

    int getlifeSpan() {
        int forReturn = 0;
        List<Cell> oldGeneration = new ArrayList<>();
        for (int index1 = 0; index1 < grid.length; index1++) {
            for (int index2 = 0; index2 < grid[0].length; index2++) {
                if (grid[index1][index2] == 2) {
                    oldGeneration.add(new Cell(index1, index2));
                }
            }
        }
        while (true) {
            newGeneration = new ArrayList<>();
            for (Cell rottenOrange : oldGeneration) {
                infectIndex1(rottenOrange, rottenOrange.index1 + 1);
                infectIndex1(rottenOrange, rottenOrange.index1 - 1);
                infectIndex2(rottenOrange, rottenOrange.index2 + 1);
                infectIndex2(rottenOrange, rottenOrange.index2 - 1);
            }
            if (newGeneration.isEmpty()) {
                for (int i = 0; i < grid.length; i++) {
                    for (int k = 0; k < grid[0].length; k++) {
                        if (grid[i][k] == 1) {
                            return -1;
                        }
                    }
                }
                return forReturn;
            } else {
                forReturn++;
                oldGeneration = newGeneration;
            }
        }
    }

    private void infectIndex1(Cell rottenOrange, int index1) {
        if (index1 >= 0 && index1 < grid.length && grid[index1][rottenOrange.index2] == 1) {
            newGeneration.add(new Cell(index1, rottenOrange.index2));
            grid[index1][rottenOrange.index2] = 2;
        }
    }

    private void infectIndex2(Cell rottenOrange, int index2) {
        if (index2 >= 0 && index2 < grid[0].length && grid[rottenOrange.index1][index2] == 1) {
            newGeneration.add(new Cell(rottenOrange.index1, index2));
            grid[rottenOrange.index1][index2] = 2;
        }
    }

}
