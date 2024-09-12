package com.slava;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.Test;

public class tryJava {

  @Test
  public void test() {
    String a = "a";
    switch (a) {
      case "a":
        System.out.println("Monday");
      case "b":
        System.out.println("B");
    }
  }

  @Test
  public void test1() {
    final int score1 = 8, score2 = 3;
    char myScore = 7;
    var goal = switch (myScore) {
      default -> {
        if (10 > score1) {
          yield "unknown";
        } else {
          yield "default";
        }
      }

      case score1 -> "great";
      case 2, 4, 6 -> "good";
      case score2, 0 -> "bad";
    };
    System.out.println(goal);
  }

  @Test
  public void test2() {
    int moon = 9, star = 2 + 2 * 3;
    float sun = star > 10 ? 1 : 3;
    double jupiter = (sun + moon) - 1.0f;
    int mars = --moon <= 8 ? 2 : 3;
    System.out.println(sun + ", " + jupiter + ", " + mars);
  }

  volatile int b;

  @Test
  public void test3() {
    for (int i = 0; i < 10; i++) {
      List<Integer> data = new ArrayList<>();
      b = 0;
      IntStream.range(0, 100).parallel().map(a -> b = a).forEachOrdered(data::add);
      System.out.println(data.size());
    }
   /*
    Collections.sort(data);
    for (Integer in: data){
      System.out.println(in);
    }*/
  }

  @Test
  public void test4() {
    Predicate<String> empty = String::isEmpty;
    Predicate<String> notEmpty = empty.negate();
    // var result1 = Stream.generate(() -> "").map((a)->System.out.println(a));
    var result = Stream.generate(() -> "")
        .filter(notEmpty)
        .collect(Collectors.groupingBy(k -> k))
        .entrySet()
        .stream()
        .map(Map.Entry::getValue)
        .flatMap(Collection::stream)
        .collect(Collectors.partitioningBy(notEmpty));
    System.out.println(result);
  }

  public static void addToInt(int x, int amountToAdd) {
    x = x + amountToAdd;
  }

  @Test
  public void test5() {
    int a = 1, b = 2;
    addToInt(a, b);
    System.out.println(a + ", " + b);
  }

  @Test
  public void test6() {
    Locale en =  Locale.of("en", "US");
    Locale.setDefault(Locale.of("en", "US"));
    var b = ResourceBundle.getBundle("resource/Penguin", en);
    System.out.println(b.getString("name"));
  //  System.out.println(b.getString("age"));
  }

  @Test
  public void test7() {
    int[] array = {6,9,8};
    System.out.println("B" + Arrays.binarySearch(array,9));
    System.out.println("C" + Arrays.compare(array,
        new int[] {6, 9, 8}));
    System.out.println("M" + Arrays.mismatch(array,
        new int[] {6, 9, 8}));
  }

  @Test
  public void test8() throws Exception {
    Path path1 = null, path2 = null;
  //  Files.mismatch(path1,path2);
    Stream.iterate(1, x -> x++).limit(5).forEach(System.out::println);
  }

}
