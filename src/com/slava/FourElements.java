package com.slava;

import java.util.*;

public class FourElements {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        List<Integer> ints = new LinkedList<>();
        for (int it = 0; it < nTest; it++) {
            int nInts = sc.nextInt();
            ints.clear();
            for (int i = 0; i < nInts; i++) {
                ints.add(sc.nextInt());
            }
            int sumOfFour = sc.nextInt();
            System.out.println(findInListDP(ints, sumOfFour));
        }
        sc.close();
    }

    static int findInListDP(List<Integer> lst, int sumOfFour) {
        if (lst.size() < 4) {
            return 0;
        }
        List<SumOfTwo> listSumOfTwo = computeCombinationOfTwo(lst, sumOfFour);
        SumOfTwo sumOfTwoToFind;
        for (int i = 0; i < listSumOfTwo.size(); i++) {
            final SumOfTwo sumOfTwoOriginal = listSumOfTwo.get(i);
            final int sumOfTwoInt = sumOfFour - sumOfTwoOriginal.sum;
            sumOfTwoToFind = new SumOfTwo(sumOfTwoInt, -1, -1);
            final int foundIndex = Collections.binarySearch(listSumOfTwo, sumOfTwoToFind);
            if (foundIndex >= 0) {
                SumOfTwo sumOfTwoFound = listSumOfTwo.get(foundIndex);
                int ii = 0;
                while (sumOfTwoFound.sum == sumOfTwoInt) {
                    if (sumOfTwoFound.i != sumOfTwoOriginal.i && sumOfTwoFound.i != sumOfTwoOriginal.k
                            && sumOfTwoFound.k != sumOfTwoOriginal.k && sumOfTwoFound.k != sumOfTwoOriginal.i) {
                        return 1;
                    } else {
                        ii++;
                        sumOfTwoFound = listSumOfTwo.get(foundIndex + ii);
                    }
                }
            }
        }
        return 0;
    }

    private static List<SumOfTwo> computeCombinationOfTwo(List<Integer> lst, int sumOfFour) {
        List<SumOfTwo> listSumOfTwo = new ArrayList<>();
        for (int i = 0; i < lst.size(); i++) {
            for (int k = i + 1; k < lst.size(); k++) {
                final int sumOfTwo = lst.get(i) + lst.get(k);
                if (sumOfTwo < sumOfFour) {
                    listSumOfTwo.add(new SumOfTwo(sumOfTwo, i, k));
                }
            }
        }
        Collections.sort(listSumOfTwo);
        return listSumOfTwo;
    }

    static class SumOfTwo implements Comparable<SumOfTwo> {
        int sum;
        int i;
        int k;

        public SumOfTwo(int sum, int i, int k) {
            this.sum = sum;
            this.i = i;
            this.k = k;
        }

        @Override
        public int compareTo(SumOfTwo o) {
            return this.sum - o.sum;
        }
    }

    static int findInList(List<Integer> lst, int sum, int qnty) {
        if (qnty == 1) {
            if (!lst.isEmpty() && lst.indexOf(sum) != -1) {
                return 1;
            } else {
                return 0;
            }
        }
        for (Integer mem : lst) {
            List<Integer> reducedList = new LinkedList<>(lst);
            reducedList.remove(mem);
            if (findInList(reducedList, sum - mem, qnty - 1) == 1) {
                return 1;
            }
        }
        return 0;
    }
}
