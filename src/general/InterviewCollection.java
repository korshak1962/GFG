package general;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class InterviewCollection {

    @Test
    public void test() {
        Map<Integer, Integer> intToInt = new HashMap<>();
        intToInt.put(1, 5);
        intToInt.put(2, 3);
        intToInt.put(3, 1);
        intToInt.put(8, 2);

        List<Integer> sortedValues = intToInt.values().stream().sorted(Comparator.comparingInt(Integer::intValue)).collect(Collectors.toList());
        intToInt.entrySet().stream()
                .sorted(Comparator.comparing(entry -> entry.getKey()))
                .forEach(System.out::println);
        intToInt.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(System.out::println);
        intToInt.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry<Integer, Integer>::getKey).thenComparing(Map.Entry::getValue))
                .forEach(System.out::println);

// Sort by value then by key, and collect only the keys
        List<Integer> sortedKeys = intToInt.entrySet()
                .stream()
                .sorted((entry1, entry2) -> {
                    // First compare by value
                    int valueComparison = entry1.getValue().compareTo(entry2.getValue());
                    if (valueComparison != 0) {
                        return valueComparison;
                    }
                    // If values are equal, compare by key
                    return entry1.getKey().compareTo(entry2.getKey());
                })
                .map(Map.Entry::getKey)  // Extract only the key from each entry
                .collect(Collectors.toList());  // Collect into a List

// Print the sorted keys
        System.out.println(sortedKeys);
    }

    @Test
    public void test1() {
        int arr[] = {1, 1, 1, 2, 2, 8, 8, 8};
        // Arrays.stream(arr).sorted()
        Map<Integer, Integer> intToInt = new HashMap<>();
        for (int num : arr) {
            intToInt.put(num, intToInt.getOrDefault(num, 0) + 1);
        }

// Sort by value then by key, and collect only the keys
        List<Integer> sortedKeys = intToInt.entrySet()
                .stream()
                .sorted((entry1, entry2) -> {
                    // First compare by value
                    int valueComparison = entry1.getValue().compareTo(entry2.getValue());
                    if (valueComparison != 0) {
                        return valueComparison;
                    }
                    // If values are equal, compare by key
                    return entry1.getKey().compareTo(entry2.getKey());
                })
                .map(Map.Entry::getKey)  // Extract only the key from each entry
                .collect(Collectors.toList());  // Collect into a List

        // Print the sorted keys
        System.out.println(sortedKeys);
    }

    @Test
    public void test2() {
        int arr[] = {1, 1, 1, 2, 2, 8, 8, 8};
        sortByFrequencyThenValue(arr);

        // Print the sorted array
        System.out.println(Arrays.toString(arr));

    }

    public static void sortByFrequencyThenValue(int[] arr) {
        // Step 1: Count the frequency of each element
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Create a list of the unique elements
        List<Integer> uniqueElements = new ArrayList<>();
        for (int num : arr) {
            if (!uniqueElements.contains(num)) {
                uniqueElements.add(num);
            }
        }

        // Step 3: Sort the unique elements by frequency (descending) then by value (ascending)
        uniqueElements.sort((a, b) -> {
            int freqComparison = frequencyMap.get(b).compareTo(frequencyMap.get(a));
            if (freqComparison != 0) {
                return freqComparison; // Sort by frequency (descending)
            }
            return a.compareTo(b); // If same frequency, sort by value (ascending)
        });

        // Step 4: Build the result array
        int index = 0;
        for (int num : uniqueElements) {
            int frequency = frequencyMap.get(num);
            for (int i = 0; i < frequency; i++) {
                arr[index++] = num;
            }
        }
    }


    public class MapSortingExample {
        public static void main(String[] args) {
            Map<String, Integer> scores = new HashMap<>();
            scores.put("Alice", 85);
            scores.put("Bob", 92);
            scores.put("Charlie", 78);
            scores.put("Diana", 96);
            scores.put("Eve", 88);

            // Sort by values in ascending order
            Map<String, Integer> sortedByValue = scores.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new
                    ));

            System.out.println("Sorted by value (ascending):");
            sortedByValue.forEach((k, v) -> System.out.println(k + ": " + v));
        }
    }

    class StringToScore implements Comparable<StringToScore> {
        final private String name;
        final private int score;

        StringToScore(String name, int scorre) {
            this.name = name;
            this.score = scorre;
        }

        @Override
        public int compareTo(StringToScore o) {
            StringToScore oteher = (StringToScore) o;
            if (this.score > oteher.score) return 1;
            return this.name.compareTo(oteher.name);
        }

        @Override
        public String toString() {
            return "StringToScore{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }
    }

    @Test
    public void testSort() {
        List<StringToScore> rates = new ArrayList<>();
        rates.add(new StringToScore("a1", 100));
        StringToScore toAdd = new StringToScore("b1", 200);
        int index = Math.abs(Collections.binarySearch(rates, toAdd)) - 1;
        rates.add(index, toAdd);
        System.out.println(rates);
    }

}


