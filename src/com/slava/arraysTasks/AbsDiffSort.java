package com.slava.arraysTasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import org.junit.Test;

public class AbsDiffSort {
  static void sortABS(int k, List<Integer> arr) {
    Integer[] indices = new Integer[arr.size()];
    for (int i = 0; i < arr.size(); i++) {
      indices[i] = i;
    }

    // Copy values to temporary array to ensure consistent values during sorting
    final List<Integer> values = arr;

    Arrays.sort(indices, (i, j) -> {
      int diffI = Math.abs(values.get(i) - k);
      int diffJ = Math.abs(values.get(j) - k);
      int diffCompare = Integer.compare(diffI, diffJ);
      return diffCompare != 0 ? diffCompare : Integer.compare(i, j);
    });

    // Create result in temp array first
    List<Integer> result = new ArrayList<>(arr.size());
    for (int i=0;i<arr.size();i++) {
      result.add(values.get(indices[i]));
    }

    for (int i=0;i<arr.size();i++) {
      arr.set(i,result.get(i));
    }
    System.out.println(arr);
  }
  @Test
  public void test() {
  //  7

    List<Integer> arr = Arrays.asList(10, 5, 3, 9, 2);
    sortABS(7, arr);
    System.out.println(arr);
  }
}
