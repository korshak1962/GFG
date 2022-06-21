package com.slava.merging;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

//https://leetcode.com/discuss/interview-question/790459/Visa-OA-Hackerrank-New-Grad-2019-2020
public class MeetupSchedule {

    public int findMax(int[] firstD, int[] lastD) {
        if (firstD.length == 0) {
            return 0;
        }
        Interval[] intervals = new Interval[firstD.length];
        for (int i = 0; i < firstD.length; i++) {
            intervals[i] = new Interval(firstD[i], lastD[i]);
        }
        Arrays.sort(intervals);
        int start = intervals[0].startD;
        int emptyDays = start - 1;
        int last = intervals[0].lastD;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].startD > last + 1) {
                emptyDays += intervals[i].startD - (last + 1);
                // start = intervals[i].startD;
                last = intervals[i].lastD;
            } else if (intervals[i].lastD > last) {
                last = intervals[i].lastD;
            }
        }
        return intervals[intervals.length - 1].lastD - emptyDays;
    }

    static private class Interval implements Comparable {
        int startD;
        int lastD;

        public Interval(int startD, int lastD) {
            this.startD = startD;
            this.lastD = lastD;
        }

        @Override
        public int compareTo(Object o) {
            return Integer.valueOf(startD)
                    .compareTo(Integer.valueOf(((Interval) o).startD));
        }
    }

    @Test
    public void testfindMax() {
        int[] firstD = {2, 3, 3, 3, 1};
        int[] lastD = {2, 3, 4, 4, 2};
        int res = findMax(firstD, lastD);
        Assert.assertEquals(4, res);
        //Assert.assertArrayEquals();
    }

}
