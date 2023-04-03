package com.slava.backTracking;

import org.junit.Test;

import java.util.*;

//https://practice.geeksforgeeks.org/problems/n-queen-problem0315/1/?problemStatus=unsolved&problemType=functional&difficulty[]=2&page=1&sortBy=submissions&query=problemStatusunsolvedproblemTypefunctionaldifficulty[]2page1sortBysubmissions
public class NQueen {
    static ChesBoard chesBoard;

    static ArrayList<ArrayList<Integer>> nQueen(int n) {
        chesBoard = new ChesBoard(n);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Deque<Queen> queens = new LinkedList<>();
        queens.add(new Queen(0, -1));
        while (!queens.isEmpty()) {
            if (queens.peekLast().nextMove()) {
                if (queens.size() == n) {
                    //we found position
                    final Iterator<Queen> iterator = queens.iterator();
                    ArrayList<Integer> newResult = new ArrayList<>();
                    while (iterator.hasNext()) {
                        newResult.add(iterator.next().row+1);
                    }
                    result.add(newResult);
                } else {
                    queens.add(new Queen(queens.peekLast().column + 1, -1)); // add next queen
                }
            } else {
                queens.pollLast();  //remove to allow previous queen to make next move
                // move
            }
        }
        return result;
    }

    static class Queen {
        int column;
        int row;

        Queen(int column, int row) {
            this.column = column;
            this.row = row;
            //  cheesBoard.fillPosition(column, row, 1);
        }

        public boolean nextMove() {
            if (row >= 0) chesBoard.fillPosition(column, row, 0);
            row++;
            while (row < chesBoard.position[0].length) {
                if (chesBoard.isPositionAllowed(column, row)) {
                    chesBoard.fillPosition(column, row, 1);
                    return true;
                }
                row++;
            }
            return false;
        }
    }

    static class ChesBoard {
        int size;
        int position[][];

        public ChesBoard(int size) {
            this.size = size;
            position = new int[size][size];
        }

        public boolean isPositionAllowed(final int column, final int row) {
            if (!isLegalPosition(column, row)) {
                return false;
            }
            int iColumn = column;
            int iRow = row;
            while (isLegalPosition(iColumn, iRow)) {
                if (position[iColumn][iRow] == 1) {
                    return false;
                }
                iColumn++;
                iRow++;
            }
            iColumn = column;
            iRow = row;
            while (isLegalPosition(iColumn, iRow)) {
                if (position[iColumn][iRow] == 1) {
                    return false;
                }
                iColumn++;
                iRow--;
            }
            iColumn = column;
            iRow = row;
            while (isLegalPosition(iColumn, iRow)) {
                if (position[iColumn][iRow] == 1) {
                    return false;
                }
                iColumn--;
                iRow--;
            }
            iColumn = column;
            iRow = row;
            while (isLegalPosition(iColumn, iRow)) {
                if (position[iColumn][iRow] == 1) {
                    return false;
                }
                iColumn--;
                iRow++;
            }
            iColumn = column;
            iRow = row;
            while (isLegalPosition(iColumn, iRow)) {
                if (position[iColumn][iRow] == 1) {
                    return false;
                }
                iColumn--;
            }
            while (isLegalPosition(iColumn, iRow)) {
                if (position[iColumn][iRow] == 1) {
                    return false;
                }
                iColumn++;
            }
            iColumn = column;
            iRow = row;
            while (isLegalPosition(iColumn, iRow)) {
                if (position[iColumn][iRow] == 1) {
                    return false;
                }
                iRow--;
            }
            while (isLegalPosition(iColumn, iRow)) {
                if (position[iColumn][iRow] == 1) {
                    return false;
                }
                iRow++;
            }
            return true;
        }

        private boolean isLegalPosition(int iColumn, int iRow) {
            return iColumn >= 0 && iColumn < size && iRow >= 0 && iRow < size;
        }

        public void fillPosition(int column, int row, int val) {
            position[column][row] = val;
        }

    }

    @Test
    public void test() {
        ArrayList<ArrayList<Integer>> res = nQueen(1);
        System.out.println(res);
        res = nQueen(2);
        System.out.println(res);
        res = nQueen(3);
        System.out.println(res);

    }

    @Test
    public void test4() {
        ArrayList<ArrayList<Integer>> res = nQueen(4);
        System.out.println(res);
    }

}
