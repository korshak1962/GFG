package com.slava.stacks;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class SkyLine {

    @Test
    public void test() {
        int[] H = new int[1];
        H[0] = 0;
        int res = solution(H);
        Assert.assertEquals(1, res);
    }

    @Test
    public void test1() {
        int[] H = new int[2];
        H[0] = 0;
        H[1] = 1;
        int res = solution(H);
        Assert.assertEquals(2, res);
    }

    // @Test
    public void test2() {
        int[] H = new int[3];
        H[0] = 0;
        H[1] = 0;
        H[2] = 1;
        int res = solution(H);
        Assert.assertEquals(2, res);
    }

    //   @Test
    public void test3() {
        int[] H = new int[4];
        H[0] = 0;
        H[1] = 0;
        H[2] = 1;
        H[3] = 0;
        int res = solution(H);
        Assert.assertEquals(1, res);
    }

    @Test
    public void test31() {
        int[] H = new int[4];
        H[0] = 0;
        H[1] = 1;
        H[2] = 2;
        H[3] = 3;
        int res = solution(H);
        Assert.assertEquals(4, res);
    }

    @Test
    public void test32() {
        int[] H = new int[4];
        H[0] = 3;
        H[1] = 2;
        H[2] = 1;
        H[3] = 0;
        int res = solution(H);
        Assert.assertEquals(4, res);
    }

    @Test
    public void test4() {
        int[] H = new int[9];
        H[0] = 8;
        H[1] = 8;
        H[2] = 5;
        H[3] = 7;
        H[4] = 9;
        H[5] = 8;
        H[6] = 7;
        H[7] = 4;
        H[8] = 8;
        int res = solution(H);
        Assert.assertEquals(7, res);
    }

    public int solution(int[] H) {
        int toReturn = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < H.length; i++) {
            while (!deque.isEmpty() && H[i] < deque.getLast()) {
                deque.removeLast();
                toReturn++;
            }
            if (deque.isEmpty() || H[i] > deque.getLast()) {
                deque.addLast(H[i]);
            }
        }
        return toReturn + deque.size();
    }

}
