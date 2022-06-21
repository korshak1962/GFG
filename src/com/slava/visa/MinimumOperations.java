package com.slava.visa;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

//https://leetcode.com/discuss/interview-question/790459/Visa-OA-Hackerrank-New-Grad-2019-2020
public class MinimumOperations {

    int getMinOperations(int[] input) {
        int res = 0;
        ArrayList<Integer> sortedList = new ArrayList<>();
        sortedList.add(input[0]);
        res++;
        int startIndex = 0;
        int endIndex = 0;
        int minOps;
        for (int i = 1; i < input.length; i++) {
            startIndex = findStartIndex(sortedList, input[i]);
            if (startIndex < 0) {
                startIndex = Math.abs(startIndex) - 1;
                minOps = Integer.min(startIndex, (sortedList.size() - startIndex));
                res += 2 * minOps + 1;
                sortedList.add(startIndex,input[i]);
                continue;
            }
            endIndex = findStartIndex(sortedList, input[i] + 1);
            if (endIndex < 0) {
                endIndex = Math.abs(endIndex) - 1;
            }
            minOps = Integer.min(startIndex, (sortedList.size() - endIndex) + 1);
            res += 2 * minOps + 1;
            sortedList.add(startIndex-1,input[i]);
        }
        return res;
    }

    int findStartIndex(ArrayList<Integer> array, int target) {
        int lowIndex = 0;
        if (array.get(lowIndex) == target) {
            return lowIndex;
        } else if (array.get(lowIndex) > target) {
            return -1;
        }
        int highIndex = array.size() - 1;
        if (array.get(highIndex) < target) {
            return -1 * (highIndex + 2); // one for index and one for negative
        }
        int targetIndex = (highIndex + lowIndex) >>> 1;
        do {
            if (array.get(targetIndex) > target) {
                highIndex = targetIndex;
            } else if (array.get(targetIndex) < target) {
                lowIndex = targetIndex;
            } else if (array.get(targetIndex - 1) < target) {
                return targetIndex;
            } else {
                highIndex = targetIndex;
            }
            targetIndex = (highIndex + lowIndex) >>> 1;
        } while (targetIndex > lowIndex);
        if (array.get(targetIndex) != target) {
            return -1 * (targetIndex + 2);
        }
        return targetIndex;
    }

    @Test
    public void testGetMinOperations0() {
        int[] input={1,3,2};
        int res = getMinOperations(input);
        Assert.assertEquals(5, res);
    }

    @Test
    public void testGetMinOperations() {
        int[] input={10,6,2,3,7,1,2};
        int res = getMinOperations(input);
        Assert.assertEquals(13, res);
    }

}
