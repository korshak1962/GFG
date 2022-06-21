package com.slava.stacks;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
//https://app.codility.com/programmers/lessons/7-stacks_and_queues/fish/
public class Fish {

    @Test
    public void testFish1() {
        int size = 5;
        int A[] = new int[size];
        int B[] = new int[size];
        A[0] = 4;
        B[0] = 0;
        A[1] = 3;
        B[1] = 1;
        A[2] = 2;
        B[2] = 0;
        A[3] = 1;
        B[3] = 0;
        A[4] = 5;
        B[4] = 0;
        int res = solution(A, B);
        System.out.println("res= " + res);
        Assert.assertEquals(2, res);
    }

    @Test
    public void testFish2() {
        int size = 2;
        int A[] = new int[size];
        int B[] = new int[size];
        A[0] = 0;
        B[0] = 1;
        A[1] = 1;
        B[1] = 1;
        int res = solution(A, B);
        System.out.println("res= " + res);
        Assert.assertEquals(2, res);
    }

    @Test
    public void testFish3() {
        int size = 1;
        int A[] = new int[size];
        int B[] = new int[size];
        A[0] = 0;
        B[0] = 1;
        int res = solution(A, B);
        System.out.println("res= " + res);
        Assert.assertEquals(1, res);
    }

    @Test
    public void testFish4() {
        int size = 7;
        int A[] = new int[size];
        int B[] = new int[size];
        A[0] = 1000;
        B[0] = 1;
        A[1] = 9;
        B[1] = 1;
        A[2] = 10;
        B[2] = 1;
        A[3] = 2;
        B[3] = 0;
        A[4] = 3;
        B[4] = 0;
        A[5] = 4;
        B[5] = 1;
        A[6] = 5;
        B[6] = 1;
        //A[7] = 4000;B[7] = 0;
        int res = solution(A, B);
        System.out.println("res= " + res);
        Assert.assertEquals(5, res);
    }

    public int solution(int[] A, int[] B) {
        int numLive = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < B.length; i++) {
            if (B[i] == 1) {
                deque.addLast(A[i]);
                continue;
            }
            while (!deque.isEmpty() && deque.getLast() < A[i]) {
                deque.removeLast();
            }
            if (!deque.isEmpty()) {
                continue;
            }
            numLive++;
        }
        return numLive + deque.size();
    }

}
