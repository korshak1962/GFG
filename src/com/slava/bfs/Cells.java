package com.slava.bfs;

import org.junit.Test;

import java.util.*;

public class Cells {
    /*
    Eight houses, represented as cells, are arranged in straight line. Each day every cell competes with its
    adjacent cells (neighbors). An integer value of 1 represents an active cell and a value of 0 represents an inactive cell.
     If the neighbors on both the sides of a cell are either active or inactive, the cell becomes inactive on the next day;
      otherwise the cell become active. The two cells on each end have a single adjacent cell, so assume that the
       unoccupied space on the opposite side is an inactive cell. Even after updating the cell state, consider its previous
       state when updating the state of other cells. The state information of all cells should be updated simultaneously.

Write an algorithm to output the state of the cells after the given number of days.

Input

The input to the function/method consists of two arguments:

states, a list of integers representing the current state of cells;

days, an integer representing the number of days.

Output

Return a list of integers representing the state of the cells after the given number of days.

Note

The elements of the list states contains 0s and 1s only.
     */

    @Test
    public void testCompete() {
        HashMap s;
        int[] states = {1, 0, 0, 0, 0, 1, 0, 0};
        states = compete(states);
        System.out.println(Arrays.toString(states));
    }

    @Test
    public void cellCompete() {
        int[] states = {1, 1, 1, 0, 1, 1, 1, 1};
        // 1 0 1 0 1 0 0 1
        // 0 0 0 0 0 1 1 0
        List<Integer> toPrint = cellCompete(states, 2);
        System.out.println(toPrint);// 0 0 0 0 0 1 1 0
    }

    public List<Integer> cellCompete(int[] states, int days) {
        int[] extendedStates = new int[states.length + 2];
        for (int cellIndex = 1; cellIndex < states.length + 1; cellIndex++) {
            extendedStates[cellIndex] = states[cellIndex - 1];
        }
        int[] nextStates = new int[states.length + 2];
        int[] temp;
        for (int day = 0; day < days; day++) {
            for (int cellIndex = 1; cellIndex < extendedStates.length - 1; cellIndex++) {
                nextStates[cellIndex] = extendedStates[cellIndex - 1] ^ extendedStates[cellIndex + 1];
            }
            temp=extendedStates;extendedStates=nextStates;nextStates=temp;
        }
        List<Integer> res = new ArrayList<>(states.length);
        for (int cellIndex = 1; cellIndex < extendedStates.length - 1; cellIndex++) {
            res.add(extendedStates[cellIndex]);
        }
        return res;
    }

    public List<Integer> cellCompete1(int[] states, int days) {
        List<Integer> toReturn = new LinkedList<>();
        for (int day = 0; day < days; day++) {
            states = compete(states);
        }
        for (int cell : states) {
            toReturn.add(cell);
        }
        return toReturn;
    }

    int[] compete(int[] states) {
        int[] extStates = new int[states.length + 2];
        extStates[0] = 0;
        extStates[states.length + 1] = 0;
        for (int cellIndex = 1; cellIndex < states.length + 1; cellIndex++) {
            extStates[cellIndex] = states[cellIndex - 1];
        }
        int[] newStates = new int[states.length + 2];
        for (int cellIndex = 1; cellIndex < states.length + 1; cellIndex++) {
            newStates[cellIndex] = 1;
            if (extStates[cellIndex - 1] == 0 && extStates[cellIndex + 1] == 0) {
                newStates[cellIndex] = 0;
            }
            if (extStates[cellIndex - 1] == 1 && extStates[cellIndex + 1] == 1) {
                newStates[cellIndex] = 0;
            }
        }
        return Arrays.copyOfRange(newStates, 1, states.length + 1);
    }

}
