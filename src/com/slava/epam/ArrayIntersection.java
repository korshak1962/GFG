package com.slava.epam;

import java.util.*;
import java.util.stream.*;

public class ArrayIntersection {
  public static void main(String[] args) {
    int[] a = {2, 2, 7, 5, 15};
    int[] b = {3, 8, 99, 7, 15};

    // Solution 1: Using boxed stream and filter
    List<Integer> intersection = Arrays.stream(a)
        .boxed()
        .distinct()  // Remove duplicates from array a
        .filter(num -> Arrays.stream(b).anyMatch(x -> x == num))
        .collect(Collectors.toList());

    System.out.println("Intersection: " + intersection); // [7, 15]

    // Solution 2: Using set intersection (more efficient)
    Set<Integer> result = Arrays.stream(a)
        .boxed()
        .collect(Collectors.toSet())
        .stream()
        .filter(num -> Arrays.stream(b).anyMatch(x -> x == num))
        .collect(Collectors.toSet());

    System.out.println("Intersection using sets: " + result); // [7, 15]

    // Solution 3: Most efficient using HashSet
    Set<Integer> setB = Arrays.stream(b)
        .boxed()
        .collect(Collectors.toSet());

    List<Integer> efficientResult = Arrays.stream(a)
        .boxed()
        .distinct()
        .filter(setB::contains)
        .collect(Collectors.toList());

    System.out.println("Efficient intersection: " + efficientResult); // [7, 15]
  }
}
