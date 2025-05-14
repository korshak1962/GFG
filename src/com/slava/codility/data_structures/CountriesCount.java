package com.slava.codility.data_structures;


import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

//https://app.codility.com/programmers/trainings/7/countries_count/
public class CountriesCount {
    boolean[][] visited;
    int counter = 0;
    int[][] A;
    Queue<int[]> queue = new LinkedList<>();

    public int solution(int[][] A) {
        this.A = A;
        visited = new boolean[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (!visited[i][j]) {
                    counter++;
                    visitCountry(i, j, A[i][j]);
                }

            }
        }
        return counter;
    }

    private void visitCountry(int i, int j, int color) {
        if (i < 0 || i >= A.length || j < 0 || j >= A[0].length || visited[i][j] || A[i][j] != color) return;
        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (current[0] < 0 || current[0] >= A.length || current[1] < 0
                    || current[1] >= A[0].length || visited[current[0]][current[1]]
                    || A[current[0]][current[1]] != color) continue;
            visited[current[0]][current[1]] = true;
            queue.add(new int[]{current[0] + 1, current[1]});
            queue.add(new int[]{current[0] - 1, current[1]});
            queue.add(new int[]{current[0], current[1] - 1});
            queue.add(new int[]{current[0], current[1] + 1});
        }
    }

    @Test
    public void test() {
        int A[][] = new int[7][3];
        A[0][0] = 5;
        A[0][1] = 4;
        A[0][2] = 4;
        A[1][0] = 4;
        A[1][1] = 3;
        A[1][2] = 4;
        A[2][0] = 3;
        A[2][1] = 2;
        A[2][2] = 4;
        A[3][0] = 2;
        A[3][1] = 2;
        A[3][2] = 2;
        A[4][0] = 3;
        A[4][1] = 3;
        A[4][2] = 4;
        A[5][0] = 1;
        A[5][1] = 4;
        A[5][2] = 4;
        A[6][0] = 4;
        A[6][1] = 1;
        A[6][2] = 1;
        int res = solution(A);
        Assert.assertEquals(11, res);
    }
}
