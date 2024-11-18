package general;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.Test;

public class InterviewNotesTest {

  @Test
  public void testString() {
    String str = "qwer ty";
    final char[] chars = str.toCharArray();
    for (int i = 0; i < str.length(); i++) {
      char ch = chars[i];
    }
    StringBuilder builder = new StringBuilder(str).reverse();
    String s = str.replace(" ", "");
    System.out.println(s);
    Optional<String> opt = Optional.empty();
    opt.isEmpty();
    opt = Optional.ofNullable(str);
    opt.ifPresent(System.out::println);
    System.out.println(opt.orElse(str));
    System.out.println(opt.orElseGet(() -> String.valueOf(1)));
  }

  @Test
  public void testMap() {
    Map<Integer, Integer> intToInt = new TreeMap<>();
    Integer maxKey = -1;
    for (Map.Entry<Integer, Integer> entry : intToInt.entrySet()) {
      if (entry.getValue() > 2) {
        maxKey = Integer.max(entry.getKey(), maxKey);
      }
      // entry.setValue()
    }
    intToInt.computeIfPresent(1, (k, v) -> v + 4);
    intToInt.computeIfAbsent(1, (key) -> 3 + 1 + key);
    intToInt.entrySet().iterator().next().getValue();
    //intToInt.keySet().iterator()
    final NavigableMap<Integer, Integer> intToIntNavigable =
        (NavigableMap<Integer, Integer>) intToInt;
    int r = intToIntNavigable.firstEntry().getValue();
    r = intToIntNavigable.floorEntry(1).getValue();
    NavigableMap<Integer, Integer> descMap = intToIntNavigable.descendingMap(); // reverse order
    intToIntNavigable.headMap(1, true); // before key
    intToIntNavigable.subMap(1, 5);
    intToIntNavigable.tailMap(2, true);
// compare by value
    intToIntNavigable.entrySet().stream()
        .sorted( Map.Entry.<Integer, Integer>  comparingByValue().reversed())
        .limit(2).forEach(System.out::println);
  }

  public static class Interval implements Comparable {
    public int start;
    int end;

    public  Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }

    public int getStart() {
      return start;
    }

    public int getEnd() {
      return end;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Interval)) {
        return false;
      }

      Interval interval = (Interval) o;

      if (start != interval.start) {
        return false;
      }
      return end == interval.end;
    }

    @Override
    public int hashCode() {
      int result = start;
      result = 31 * result + end;
      return result;
    }

    @Override
    public int compareTo(Object o) {
      if (!(o instanceof Interval)) {
        return 0;
      }
      Interval interval = (Interval) o;
      return start - interval.start;
    }

    @Override
    public String toString() {
      return this.start + "," + this.end;
    }
  }

  @Test
  public void testArray() {
    int[] arri = {1, 2, 3};
    List<Integer> list = Arrays.stream(arri)
        .boxed()
        .collect(Collectors.toList());
    list.sort(Comparator.naturalOrder());
    Integer arr[] = {1, 2, 3};
    List<Integer> list1 = Arrays.asList(arr);
    Interval[] ar = new Interval[3];
    ar[0] = new Interval(4, 8);
    ar[1] = new Interval(10, 2);
    ar[2] = new Interval(2, 3);
    //  ar.length;
    Arrays.sort(ar, 1, 3); // is Interval comparable
    Arrays.sort(ar, Comparator.comparing(Interval::getStart).reversed().
        thenComparing(Interval::getEnd));
    Arrays.sort(ar, (a, b) -> {
      if (a.start == b.start) {
        return 0;
      }
      if (a.start > b.start) {
        return 1;
      } else {
        return -1;
      }
    });
    Interval inter = new Interval(1, 2);
    int i = Arrays.binarySearch(ar, 1, 2, inter);// (-(insertion point) - 1)
    //Integer.max()
    //  Arrays.copyOfRange()
    int index = Arrays.binarySearch(ar, inter, Collections.reverseOrder());
  }

  @Test
  public void testHeap() {
    PriorityQueue<Interval> intervals = new PriorityQueue<>();
    Comparator.reverseOrder();//comparator
    Interval inter = new Interval(1, 2);
    intervals.add(inter);
    inter = intervals.peek();
    inter = intervals.poll();
    PriorityQueue<Interval> heap = new PriorityQueue<>(
        Comparator.comparing(Interval::getEnd).reversed());
    heap.size();
    heap.peek();
    heap.remove(inter);
  }

  @Test
  public void testCollections() {
    List<Interval> intervals = new LinkedList<>();
    for (Interval interval : intervals) {

    }
    Collections.sort(intervals, Comparator.comparing(Interval::getStart).reversed());
    Interval inter = new Interval(3, 4);
    int index =
        Collections.binarySearch(intervals, inter, Comparator.comparing(Interval::getStart));
    intervals.add(1, inter); //IndexOutOfBoundsExceptio
    ((Deque<Interval>) intervals).addFirst(inter);
    ((Deque<Interval>) intervals).peekLast();
    ((Deque<Interval>) intervals).pollFirst();

    ((Deque<Interval>) intervals).addFirst(inter);
    ((Deque<Interval>) intervals).addFirst(new Interval(4, 5));
    //  intervals.retainAll(new ArrayList<>());
    ListIterator<Interval> lIter = intervals.listIterator(1);
    lIter.hasPrevious();
    lIter.next();
    Interval max = Collections.max(intervals);
    Collections.sort(intervals, Comparator.comparing(Interval::getStart));
    System.out.println(intervals.size());
    Iterator<Interval> iter = intervals.iterator();
    Interval prev = iter.next();
    while (iter.hasNext()) {
      Interval current = iter.next();
      if (prev.end >= current.start) {
        prev.end = current.end;
        iter.remove();
        continue;
      }
      prev = current;
    }
    System.out.println(intervals.size());
    System.out.println(intervals.get(0));
  }

  @Test
  public void testStream() {
    List<Interval> inters1 = new LinkedList<>();
    inters1.add(new Interval(1, 2));
    inters1.add(new Interval(0, 2));
    Set<Integer> starts = inters1.stream().sorted().map(a -> a.start).collect(Collectors.toSet());
    starts.contains(new Interval(1, 2));
    System.out.println(starts);

    List<List<Interval>> listList = new ArrayList<>();
    List<Interval> inters2 = new LinkedList<>();
    inters2.add(new Interval(1, 8));
    inters2.add(new Interval(0, 7));
    listList.add(inters1);
    listList.add(inters2);
    List<Integer> ends = listList.stream().sorted((i1, i2) -> i1.size() - i2.size())
        .flatMap(currentList -> currentList.stream()).sorted(((in1, in2) -> in2.end - in1.end))
        .map(in -> in.end).collect(Collectors.toList());
    System.out.println(ends);

    boolean res = IntStream.range(1, 2).allMatch(e -> e > 0);
    System.out.println(res);
    int[] numbers = {1, 2, 3, 4, 5};

// Using Arrays.stream() for efficient operations on primitives
    IntStream numberStream1 = Arrays.stream(numbers);
    numberStream1.average()
        .ifPresent(avg -> System.out.println("Average: " + avg)); // Calculates average efficiently

// Using Stream.of() treats the array as a single element
    Stream<int[]> numberStream2 = Stream.of(numbers);
  }

  @Test
  public void stream2() {
    int[] numbers = {5, 8, 3, 2, 9};

    // Approach 1: Using max() with Optional handling
    OptionalInt maxOptional = Arrays.stream(numbers).max();
    System.out.println("Max value (Optional): " + maxOptional.getAsInt());

    // Approach 2: Using reduce() with a custom comparator
    int max = Arrays.stream(numbers).reduce(Integer.MIN_VALUE, Integer::max);
    System.out.println("Max value (reduce): " + max);
  }


  public List<Integer> findWord(String textString, String word) {
    List<Integer> indexes = new ArrayList<Integer>();
    String lowerCaseTextString = textString.toLowerCase();
    String lowerCaseWord = word.toLowerCase();

    int index = 0;
    while (index != -1) {
      index = lowerCaseTextString.indexOf(lowerCaseWord, index);
      if (index != -1) {
        indexes.add(index);
        index++;
      }
    }
    return indexes;
  }

  @Test
  public void testFindWord() {
    System.out.println(findWord("abababc", "aba"));
    Integer.valueOf("123");
  }

  @Test
  public void testList() {
    List<Interval> intervals = new LinkedList<>();
    intervals.add(0, new Interval(1, 2));
    List<Interval> intervals2 = new LinkedList<>();
    intervals2.add(0, new Interval(-1, -2));
    intervals2.add(0, new Interval(-3, -4));
    intervals.addAll(0, intervals2);
    System.out.println(intervals);
    List<Integer> list = new ArrayList<>(10);
    for (Integer in : list) {

    }
  }

  //https://leetcode.com/problems/buddy-strings/submissions/863948164/
  public boolean buddyStrings(String s, String goal) {
    if (s.length() != goal.length()) {
      return false;
    }
    Set<Character> setChar = new HashSet<>();
    List<Character> difList1 = new ArrayList<>();
    List<Character> difList2 = new ArrayList<>();
    for (int i = 0; i < s.length(); i++) {
      setChar.add(s.charAt(i));
      if (s.charAt(i) != goal.charAt(i)) {
        difList1.add(s.charAt(i));
        difList2.add(goal.charAt(i));
      }
    }
    if (setChar.size() < s.length() && difList1.size() == 0) {
      return true;
    }
    if (difList1.size() != 2) {
      return false;
    }
    if (new HashSet<>(difList1).equals(new HashSet<>(difList2))) {
      return true;
    }
    return false;
  }

  @Test
  public void testSet() {
    Set<Integer> s1 = new HashSet<>();
    s1.add(1);
    s1.add(2);
    Set<Integer> s2 = new HashSet<>();
    s2.add(2);
    s2.add(3);
    s1.retainAll(s2);
    System.out.println(s1);
  }

  @Test
  public void testPeriod() {
    var date = LocalDate.of(2022, 1, 20);
    var time = LocalTime.of(6, 15);
    var dateTime = LocalDateTime.of(date, time);
    var period = Period.ofMonths(1);
    System.out.println(date.plus(period)); // 2022–02–20
    System.out.println(dateTime.plus(period)); // 2022–02–20T06:15
    //System.out.println(time.plus(period));

    var daily = Duration.ofDays(1); // PT24H
    var hourly = Duration.ofHours(1); // PT1H
    var everyMinute = Duration.ofMinutes(1); // PT1M
    var everyTenSeconds = Duration.ofSeconds(10); // PT10S
    var everyMilli = Duration.ofMillis(1); // PT0.001S
    var everyNano = Duration.ofNanos(1); // PT0.000000001S

    date = LocalDate.of(2022, 5, 25);
    time = LocalTime.of(11, 55, 00);
    var zone = ZoneId.of("US/Eastern");
    var zonedDateTime = ZonedDateTime.of(date, time, zone);
    var instant = zonedDateTime.toInstant(); // 2022–05–25T15:55:00Z
    System.out.println(zonedDateTime); // 2022–05–25T11:55–04:00[US/Eastern]
    System.out.println(instant); // 202–05–25T15:55:00Z

    dateTime = dateTime.plusHours(1);
  }
}
