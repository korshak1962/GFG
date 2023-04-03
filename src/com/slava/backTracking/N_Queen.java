package com.slava.backTracking;

import org.junit.Test;

import java.util.*;

public class N_Queen {

    @Test
    public void t2() {
        solve(4);
    }

    public List<ChesBoard> solve(int size) {
        solveRecur(size, new ChesBoard(size), new ChesBoard.Position(0, 0, size));
      //  System.out.println(result.size());
        return result;
    }

    List<ChesBoard> result = new ArrayList<>();

    boolean solveRecur(int queens, ChesBoard board, ChesBoard.Position position) {
        if (queens == 0) {
            result.add(board);
            return true;
        }
        while (position != null) {
            if (board.isPositionAllowed(position)) {
                solveRecur(queens - 1, new ChesBoard(board, position), new ChesBoard.Position(position));
            }
            position = position.next();
        }
        return false;
    }

    private static class ChesBoard {

        int size;
        int positions[][];

        public ChesBoard(int size) {
            this.size = size;
            positions = new int[size][size];
        }

        public ChesBoard(ChesBoard board, Position position) {
            this.size = board.size;
            this.positions = new int[size][size];
            for (int i = 0; i < size; i++) {
                this.positions[i] = Arrays.copyOf(board.positions[i], size);
            }
            this.positions[position.x][position.y] = 1;
        }

        public boolean isPositionAllowed(final Position position) {
            for (int i = 0; i < size; i++) {
                if (positions[i][position.y] > 0) return false;
                if (positions[position.x][i] > 0) return false;
            }
            int ix = position.x;
            int iy = position.y;
            while (isPositionLegal(ix, iy)) {
                if (positions[ix][iy] > 0) return false;
                ix--;
                iy--;
            }
            ix = position.x;
            iy = position.y;
            while (isPositionLegal(ix, iy)) {
                if (positions[ix][iy] > 0) return false;
                ix++;
                iy++;
            }
            ix = position.x;
            iy = position.y;
            while (isPositionLegal(ix, iy)) {
                if (positions[ix][iy] > 0) return false;
                ix--;
                iy++;
            }
            ix = position.x;
            iy = position.y;
            while (isPositionLegal(ix, iy)) {
                if (positions[ix][iy] > 0) return false;
                ix++;
                iy--;
            }
            return true;
        }

        private boolean isPositionLegal(int ix, int iy) {
            return ix >= 0 && ix < size && iy >= 0 && iy < size;
        }

        static class Position {
            int x;
            int y;
            int size;

            public Position(int x, int y, int size) {
                this.x = x;
                this.y = y;
                this.size = size;
            }

            public Position(Position position) {
                this.x = position.x;
                this.y = position.y;
                this.size = position.size;
            }

            public Position next() {
                if (x < size - 1) {
                    x++;
                    return this;
                }
                if (y < size - 1) {
                    y++;
                    x = 0;
                    return this;
                }
                return null;
            }
        }
    }
}
