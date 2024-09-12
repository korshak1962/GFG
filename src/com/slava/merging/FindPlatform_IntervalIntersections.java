package com.slava.merging;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

//https://www.geeksforgeeks.org/problems/minimum-platforms-1587115620/1?page=1&difficulty=Medium,Hard&sortBy=submissions
public class FindPlatform_IntervalIntersections {

    static int findPlatform(final int arr[], int dep[], int n) {
        int res = 0;
        Arrays.sort(arr);
        Arrays.sort(dep);
        int iArr = 0, iDep = 0;
        int platforms = 0;
        while (iArr < arr.length) {
            if (arr[iArr] <= dep[iDep]) {
                platforms++;
                iArr++;
                res = Integer.max(res, platforms);
            } else {
                platforms--;
                iDep++;
            }
        }
        return res;
    }

    static int findPlatform1(final int arr[], int dep[], int n) {
        int res = 0;
        List<Schedule> schedules = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            schedules.add(new Schedule(arr[i], dep[i]));
        }
        Collections.sort(schedules, Comparator.comparingInt(s -> s.in));
        Queue<Integer> platforms = new PriorityQueue<>();
        for (Schedule schedule : schedules) {
            platforms.add(schedule.out);
            platforms.removeIf(time -> time < schedule.in);
            res = Integer.max(res, platforms.size());
        }
        return res;
    }

    static class Schedule {
        int in;
        int out;

        public Schedule(int in, int out) {
            this.in = in;
            this.out = out;
        }
    }

    @Test
    public void test() {
        int arr[] = {900, 940, 950, 1100, 1500, 1800};
        int dep[] = {910, 1200, 1120, 1130, 1900, 2000};
        Assert.assertEquals(3, findPlatform(arr, dep, arr.length));
    }

    @Test
    public void test1() {
        int arr[] = {900, 1100, 1235};
        int dep[] = {1000, 1200, 1240};
        Assert.assertEquals(1, findPlatform(arr, dep, arr.length));
    }


}
