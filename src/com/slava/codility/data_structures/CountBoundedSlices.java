package com.slava.codility.data_structures;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

//https://app.codility.com/programmers/trainings/7/count_bounded_slices/
public class CountBoundedSlices {

        // Inner class to hold both value and index
        private static class ValueAndIndex {
            int value;
            int index;

            public ValueAndIndex(int value, int index) {
                this.value = value;
                this.index = index;
            }
        }

        public int solution(int K, int[] A) {
            final int N = A.length;
            final int MAX_INT = 1000000000;

            // Only need two queues now instead of four
            Deque<ValueAndIndex> maxQ = new ArrayDeque<>();
            Deque<ValueAndIndex> minQ = new ArrayDeque<>();

            int j = 0, result = 0;

            for (int i = 0; i < N; i++) {
                while (j < N) {
                    // Add new maximum element
                    while (!maxQ.isEmpty() && maxQ.peekLast().value <= A[j]) {
                        maxQ.pollLast();
                    }
                    maxQ.offerLast(new ValueAndIndex(A[j], j));

                    // Add new minimum element
                    while (!minQ.isEmpty() && minQ.peekLast().value >= A[j]) {
                        minQ.pollLast();
                    }
                    minQ.offerLast(new ValueAndIndex(A[j], j));

                    if (maxQ.peekFirst().value - minQ.peekFirst().value <= K) {
                        j++;
                    } else {
                        break;
                    }
                }

                result += (j - i);
                if (result >= MAX_INT) {
                    return MAX_INT;
                }

                // Remove elements leaving the window
                if (!minQ.isEmpty() && minQ.peekFirst().index == i) {
                    minQ.pollFirst();
                }

                if (!maxQ.isEmpty() && maxQ.peekFirst().index == i) {
                    maxQ.pollFirst();
                }
            }

            return result;
        }


    @Test
    public void test(){
        int K =2;
        int[] A ={0,1};
     int res = solution(K, A);
        Assert.assertEquals(3,res);
     System.out.println(res);
    }

    @Test
    public void test1(){
        int K =2;
        int[] A ={0,1,5};
        int res = solution(K, A);
        Assert.assertEquals(4,res);
        System.out.println(res);
    }

    @Test
    public void test2(){
        int K =2;
        int[] A ={3,5,7,6,3};
        int res = solution(K, A);
        Assert.assertEquals(9,res);
        System.out.println(res);
    }

}
