package com.slava.arraysTasks;

import org.junit.Test;

//https://www.geeksforgeeks.org/problems/nearest-smaller-tower--170647/1?page=1&company=Atlassian&difficulty=Medium,Hard&sortBy=submissions
public class NearestSmallestTower {
  static int[] nearestSmallestTower(int[] arr) {
    int[] result = new int[arr.length];
    result[0] = -1;
    for (int i = 1; i < arr.length; i++) {
      int indexToCompare = i - 1;
      while (indexToCompare > -1) {
        if (arr[indexToCompare] < arr[i]) {
          break;
        } else {
          indexToCompare = result[indexToCompare];
        }
      }
      result[i] = indexToCompare;
    }

    int[] resultRight = new int[arr.length];
    resultRight[arr.length - 1] = -1;
    for (int i = arr.length - 2; i > -1; i--) {
      int indexToCompareRight = i + 1;
      while (indexToCompareRight > -1) {
        if (arr[indexToCompareRight] < arr[i]) {
          break;
        } else {
          indexToCompareRight = resultRight[indexToCompareRight];
        }
      }
      resultRight[i] = indexToCompareRight;
    }

    for (int i = 0; i < arr.length; i++) {
      if (resultRight[i] == -1) {
        continue;
      }
      if (result[i] == -1 || (i - result[i] > resultRight[i] - i)) {
        result[i] = resultRight[i];
        continue;
      }
      if (i - result[i] == resultRight[i] - i) {
        if (arr[result[i]] > arr[resultRight[i]]) {
          result[i] = resultRight[i];
        }
      }
    }
    return result;
  }

  @Test
  public void test() {
    int[] arr = {1, 3, 2, 4};
    int[] result = nearestSmallestTower(arr);
    for (int i : result) {
      System.out.print(i + " ");
    }
  }

  @Test
  public void test1() {
    int[] arr = {4, 8, 3, 5, 3};
    int[] result = nearestSmallestTower(arr);
    for (int i : result) {
      System.out.print(i + " ");
    }
  }
}
