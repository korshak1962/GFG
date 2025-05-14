package com.slava.codility.data_structures;

import org.junit.Assert;
import org.junit.Test;

//https://app.codility.com/programmers/trainings/7/tree_longest_zig_zag/
//https://app.codility.com/demo/results/training3GKX4N-WVE/
public class ZigZag {

    public static final char RIGHT = 'R';
    public static final char LEFT = 'L';

    class Tree {
        public int x;
        public Tree l;
        public Tree r;
    }

    int res = 0;

    public int solution(Tree T) {
        if (T.l != null) {
            triverse(T.l, 0, LEFT);
        }
        if (T.r != null) {
            triverse(T.r, 0, RIGHT);
        }
        return res;
    }

    private void triverse(Tree T, int numTurns, char from) {
        if (T.l == null && T.r == null) {
            counter(numTurns);
            return;
        }
        if (T.l != null) {
            int newNumTurns = numTurns;
            if (from == RIGHT) {
                newNumTurns = numTurns + 1;
            }
            triverse(T.l, newNumTurns, LEFT);
        }

        if (T.r != null) {
            int newNumTurns = numTurns;
            if (from == LEFT) {
                newNumTurns = numTurns + 1;
            }
            triverse(T.r, newNumTurns, RIGHT);
        }
    }


    private void counter(int turns) {
        res = Integer.max(res, turns);
    }


    @Test
    public void test() {
        Tree t = new Tree();
        t.l = new Tree();
        t.r = new Tree();
        int res = solution(t);
        Assert.assertEquals(0, res);
    }

    @Test
    public void test1() {
        Tree t = new Tree();
        t.l = new Tree();
        t.r = new Tree();
        t.r.r = new Tree();
        t.r.l = new Tree();
        int res = solution(t);
        Assert.assertEquals(1, res);
    }
}
