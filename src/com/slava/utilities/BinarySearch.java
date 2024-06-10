package com.slava.utilities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinarySearch {

    public static <T> int getMaxInd(List<? extends Comparable<? super T>> numsWithDupSorted, T key) {
        int indToInsert = Collections.binarySearch(numsWithDupSorted, key);
        while (indToInsert < numsWithDupSorted.size() - 1 && numsWithDupSorted.get(indToInsert + 1).equals(key)) {
            List<? extends Comparable<? super T>> subList = numsWithDupSorted.subList(indToInsert + 1, numsWithDupSorted.size());
            indToInsert += Collections.binarySearch(subList, key) + 1;
        }
        return indToInsert;
    }

    public static <T> int getMinInd(List<? extends Comparable<? super T>> numsWithDupSorted, T key) {
        int curInd = Collections.binarySearch(numsWithDupSorted, key);
        while (curInd > 0 && numsWithDupSorted.get(curInd - 1).equals(key)) {
            List<? extends Comparable<? super T>> subList = numsWithDupSorted.subList(0, curInd);
            curInd = Collections.binarySearch(subList, key);
        }
        return curInd;
    }

    public static <T> int getMinIndArray(T[] numsWithDupSorted, T key) {
        int curInd = Arrays.binarySearch(numsWithDupSorted, key);
        while (curInd > 0 && numsWithDupSorted[curInd - 1].equals(key)) {
            curInd = Arrays.binarySearch(numsWithDupSorted,0,curInd, key);
        }
        return curInd;
    }

    public static <T> int getMaxIndArray(T[] numsWithDupSorted, T key) {
        int curInd = Arrays.binarySearch(numsWithDupSorted, key);
        while (curInd > 0 && curInd < numsWithDupSorted.length - 1 && numsWithDupSorted[curInd + 1].equals(key)) {
            curInd = Arrays.binarySearch(numsWithDupSorted, curInd + 1, numsWithDupSorted.length, key);
        }
        return curInd;
    }

}
