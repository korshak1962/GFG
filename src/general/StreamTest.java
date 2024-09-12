package general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import org.junit.Test;

public class StreamTest {

  public void test() {
    var list = List.of("a", "b", "c");
    Stream<String> fromListParallel = list.parallelStream();
    list.stream();
    Stream<Integer> oddNumberUnder100 = Stream.iterate(
        1, // seed
        n -> n < 100, // Predicate to specify when done
        n -> n + 2);
  }

  @Test
  public void test1() {
    List<InterviewNotesTest.Interval> inters1 = new LinkedList<>();
    inters1.add(new InterviewNotesTest.Interval(1, 2));
    inters1.add(new InterviewNotesTest.Interval(0, 2));
    Set<Integer> starts = inters1.stream().sorted().map(a -> a.start).collect(Collectors.toSet());
    starts.contains(new InterviewNotesTest.Interval(1, 2));
    System.out.println(starts);

    List<List<InterviewNotesTest.Interval>> listList = new ArrayList<>();
    List<InterviewNotesTest.Interval> inters2 = new LinkedList<>();
    inters2.add(new InterviewNotesTest.Interval(1, 8));
    inters2.add(new InterviewNotesTest.Interval(0, 7));
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

  @Test
  public void testStreamToMap1() {
    Stream<String> s = Stream.of("monkey", "ape", "bonobo");
    Optional<String> min = s.min((s1, s2) -> s1.length() - s2.length());
    min.ifPresent(System.out::println); // ape
    s = Stream.of("monkey", "gorilla", "bonobo");
    System.out.println(s.count()); // 3

    Stream<String> stream = Stream.of("w", "o", "l", "f");
    stream.reduce((a, b) -> a + b).ifPresent(System.out::println); //wolf

    stream = Stream.of("black bear", "brown bear", "grizzly");
    long count = stream.filter(s1 -> s1.startsWith("b"))
        .peek(System.out::println).count(); // grizzly
    System.out.println(count); // 1

    Stream<Integer> streamInt = Stream.of(1, 2, 3);
    System.out.println(streamInt.mapToInt(x -> x).sum()); // 6
    IntStream.of(5, 6);

    Collection<Long> longCollection = Arrays.asList(1L, 2L, 3L, 4L, 5L);

    // Method 1: Using mapToLong()
    LongStream longStream1 = longCollection.stream().mapToLong(Long::longValue);
    System.out.println("Sum using mapToLong(): " + longStream1.sum());

    // Method 2: Using flatMapToLong()
    LongStream longStream2 = longCollection.stream().flatMapToLong(LongStream::of);
    System.out.println("Sum using flatMapToLong(): " + longStream2.sum());

    LongSummaryStatistics stat = longStream2.summaryStatistics();
    stat.combine(stat);
  }

  @Test
  public void testStreamToMap() {
    List<List<Integer>> nestedLists = Arrays.asList(
        List.of(1, 2, 3),
        List.of(1, 4, 5),
        List.of(3, 3, 6)
    );
    Map<Integer, Long> occurrenceMap = nestedLists.stream()
        .flatMap(List::stream) // Flatten the nested lists
        .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
    System.out.println(occurrenceMap);
    Map<Integer, String> useridToName = new HashMap<>();
    useridToName.put(1, "One");
    useridToName.put(2, "Two");
    useridToName.put(3, "Three");
    nestedLists.stream()
        .flatMap(List::stream) // Flatten the nested lists
        .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()))
        .entrySet().stream().filter(e -> e.getValue() >= 2)
        .map(Map.Entry::getKey)
        .map(useridToName::get)
        .forEach(System.out::println);
  }

  static class Person {
    String name;
    int age;

    Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    @Override
    public String toString() {
      return name + " (" + age + ")";
    }
  }

  @Test
  public void testCollectors() {


    List<Person> people = Arrays.asList(
        new Person("Alice", 25),
        new Person("Bob", 17),
        new Person("Charlie", 30),
        new Person("David", 16),
        new Person("Eve", 18)
    );


    Map<Boolean, List<Person>> partitionedByAge = people.stream()
        .collect(Collectors.partitioningBy(person -> person.age >= 18));

    System.out.println("Adults (18 and over):");
    partitionedByAge.get(true).forEach(System.out::println);

    System.out.println("\nMinors (under 18):");
    partitionedByAge.get(false).forEach(System.out::println);

    // Example of nested collectors
    Map<Boolean, Long> countByAgeGroup = people.stream()
        .collect(Collectors.partitioningBy(
            person -> person.age >= 18,
            Collectors.counting()
        ));

    System.out.println("\nCount of adults: " + countByAgeGroup.get(true));
    System.out.println("Count of minors: " + countByAgeGroup.get(false));
  }

  @Test
  public void testTeeingCollectors() {
    List<Person> people = Arrays.asList(
        new Person("Alice", 25),
        new Person("Bob", 30),
        new Person("Charlie", 35),
        new Person("David", 40)
    );

    record AgeStats(Map<String, Double> averageAgeByName, double overallAverageAge) {
    }
    record Separations(String spaceSeparated, String commaSeparated) {}
    AgeStats ageStats = people.stream().collect(
        Collectors.teeing(
            Collectors.groupingBy(
                p -> p.name,
                Collectors.averagingInt(p -> p.age)
            ),
            Collectors.averagingInt(p -> p.age),
            AgeStats::new
        )
    );

    System.out.println("Average age by name:");
    ageStats.averageAgeByName().forEach((name, avgAge) ->
        System.out.println(name + ": " + avgAge));
    System.out.println("Overall average age: " + ageStats.overallAverageAge());

    var list = List.of("x", "y", "z");
    Separations result = list.stream()
        .collect(Collectors.teeing(
            Collectors.joining(" "),
            Collectors.joining(","),
            Separations::new));
    System.out.println(result);
  }

  private static Stream<Integer> boxing(IntStream stream) {
    Stream<Integer> iStream = Stream.of(1,2,3);

    return stream.boxed();
  }

}

