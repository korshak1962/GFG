package com.slava.stacks;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// gfg is not finished
public class AsteroidCollision {
    public static int[] asteroidCollision(int N, int[] asteroids) {
        Deque<Integer> queue = new LinkedList<>();
        queue.add(asteroids[0]);
        for (int i = 1; i < asteroids.length; i++) {
            if (!queue.isEmpty() && queue.peekLast() * asteroids[i] < 0) {
                if (!queue.isEmpty() && Math.abs(queue.peekLast()) == Math.abs(asteroids[i])) {
                    queue.pollLast();
                    continue;
                }
                while (!queue.isEmpty() && Math.abs(queue.peekLast()) < Math.abs(asteroids[i])) {
                    queue.pollLast();
                    if (queue.isEmpty()) queue.addLast(asteroids[i]);
                }
            } else {
                queue.addLast(asteroids[i]);
            }
        }
        return queue.stream().mapToInt(Integer::intValue).toArray();
    }

    @Test
    public void test() {
        int[] asteroids = {3, 5, -3};
        System.out.println(Arrays.toString(asteroidCollision(asteroids.length, asteroids)));
    }

    @Test
    public void test1() {
        int[] asteroids = {-5, 5, 2, 1, 1, -2};
        System.out.println(Arrays.toString(asteroidCollision(asteroids.length, asteroids)));
        //It's Correct output is:        -5 5
    }

    @Test
    public void test2() {
        int[] asteroids = {1, -7, -3, -12, 1, -8};
        System.out.println(Arrays.toString(asteroidCollision(asteroids.length, asteroids)));
        //It's Correct output is:        -7 -3 -12 -8
    }

}
