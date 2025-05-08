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

}


