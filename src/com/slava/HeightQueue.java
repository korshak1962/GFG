package com.slava;

import org.junit.Test;

import java.util.*;

//https://leetcode.com/problems/queue-reconstruction-by-height/
public class HeightQueue {
/*
    Suppose you have a random list of people standing in a queue.
     Each person is described by a pair of integers (h, k),
     where h is the height of the person and k is the number of people
     in front of this person who have a height greater than or equal to h.
      Write an algorithm to reconstruct the queue.
            Note:
    The number of people is less than 1,100.

    Example

    Input:
            [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

    Output:
            [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
*/

    @Test
    public void simpleTestNew() {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        reconstructQueueNew(people);
    }

    class HumanNew implements Comparable {
        int qNum;
        int height;
        HumanNew(int height, int qNum) {
            this.qNum = qNum;
            this.height = height;
        }

        @Override
        public int compareTo(Object o) {
            HumanNew other = (HumanNew) o;
            if (this.height > other.height) return -1;
            if (this.height < other.height) return 1;
            if (this.qNum > other.qNum) return 1;
            return 1;
        }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return "[" + height + "," + qNum + "]";
        }
    }

    public int[][] reconstructQueueNew(int[][] people) {
        List<HumanNew> hQueue = new LinkedList<>();
        for (int[] a : people) {
            System.out.println(Arrays.toString(a));
            hQueue.add(new HumanNew(a[0], a[1]));
        }
        Collections.sort(hQueue);
        System.out.println(hQueue);
        List<HumanNew> restoredHQueue = new LinkedList<>();

        for (HumanNew h : hQueue) {
            restoredHQueue.add(h.qNum, h);
        }
        System.out.println(restoredHQueue);

        return null;
    }


    @Test
    public void simpleTest() {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        for (int[] a : people)
            System.out.println(Arrays.toString(a));
        people = reconstructQueue(people);
        System.out.println("after===========");
        for (int[] a : people)
            System.out.println(Arrays.toString(a));
    }

    public int[][] reconstructQueue(int[][] people) {
        List<Human> queue = new ArrayList<>();
        for (int k = 0; k < people.length; k++) {
            queue.add(new Human(people[k][0], people[k][1]));
        }
        Collections.sort(queue, (h1, h2) -> {
            if (h1.height != h2.height) {
                return h1.height - h2.height;
            } else {
                return h1.num - h2.num;
            }
        });
        queue = sortQueue(queue);
        int i = 0;
        for (Human h : queue) {
            people[i][0] = h.height;
            people[i][1] = h.num;
            i++;
        }
        return people;
    }

    private List<Human> sortQueue(List<Human> queue) {
        List<Human> queueSorted = new ArrayList<>(queue.size());
        for (int i = 0; i < queue.size(); i++) {
            queueSorted.add(null);
        }
        for (int i = 0; i < queue.size(); i++) {
            addToEmptySpace(queueSorted, queue.get(i));
        }
        return queueSorted;
    }

    private void addToEmptySpace(List<Human> queueSorted, Human hh) {
        int counter = 0;
        for (int i = 0; i < queueSorted.size(); i++) {
            Human human = queueSorted.get(i);
            if (human == null || hh.height <= human.height) {
                if (counter == hh.num) {
                    queueSorted.set(i, hh);
                    return;
                }
                counter++;
            }
        }
    }

    static class Human {
        int height;
        int num;

        public Human(int height, int num) {
            this.height = height;
            this.num = num;
        }

    }

}
