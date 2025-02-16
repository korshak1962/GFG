package com.slava.arraysTasks;

import java.util.Arrays;
import org.junit.Test;

public class SortZeroOneTwo {

  int[] arr;

  // Function to sort an array of 0s, 1s, and 2s
  public void sort012(int[] arr) {
    this.arr = arr;
    int zeroI = -1;
    int twoI = arr.length;
    int curInd = 0;
    while (curInd < arr.length) {
      if (arr[curInd] == 0) {
        if (curInd == zeroI + 1) {
          zeroI = curInd;
          curInd++;
          continue;
        } else {
          swap(curInd, zeroI + 1);
          zeroI++;
          continue;
        }
      } else if (arr[curInd] == 2) {
        if (curInd == twoI) {
          break;
        }
        swap(curInd, twoI - 1);
        twoI--;
        continue;
      }
      curInd++;
    }
  }

  void swap(int from, int to) {
    int tmp = arr[to];
    arr[to] = arr[from];
    arr[from] = tmp;
  }

  @Test
  public void test() {
    int[] arr = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
    sort012(arr);
    System.out.println(Arrays.toString(arr));
  }
}
