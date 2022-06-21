package com.slava;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class EasyGeek {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        sc.nextLine();
        for (int it = 0; it < nTest; it++) {
            final String str = sc.nextLine();
            String rafinateStr = str.replaceAll("\\W", "").toLowerCase();
            if (isPalindrome(rafinateStr)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean isPalindrome(String rafinateStr) {
        for (int i = 0; i < rafinateStr.length() / 2 + 1; i++) {
            if (rafinateStr.charAt(i) != rafinateStr.charAt(rafinateStr.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        for (int it = 0; it < nTest; it++) {
            int dimArray = sc.nextInt();
            int prev = sc.nextInt();
            long sum = prev, maxSum = prev, prevPrev = prev;
            for (int i = 0; i < dimArray - 1; i++) {
                int next = sc.nextInt();
                if (prev < prevPrev && prev < next) {
                    maxSum = Long.max(maxSum, sum);
                    sum = prev;
                }
                //System.out.println( prevPrev="+prevPrev+" prev="+prev+" next="+next+" sum="+sum);
                if (next != prev) {
                    sum += next;
                    prevPrev = prev;
                    prev = next;
                }
            }
            System.out.println(Long.max(sum, maxSum));
        }
    }

    /*
      public static void main(String[] args) {


          //  File file = new File("c:\\tmp\\gfg.txt");

          Scanner sc = new Scanner(System.in);
          //   try (Scanner sc = new Scanner(file)) {
          int nTest = sc.nextInt();
          for (int it = 0; it < nTest; it++) {
              Integer intToInsert = sc.nextInt();
              insertIntInSortedList(intToInsert);
              System.out.println(findMedianInSortedList());
              //printMajorityElement(sc, length);
          }
          sc.close();
          //  } catch (FileNotFoundException e) {
          //     e.printStackTrace();
          //  }
      }
  */
    static List<Integer> sortedList = new LinkedList();

    public static int findMedianInSortedList() {
        if (sortedList.size() % 2 == 1) {
            return sortedList.get(sortedList.size() / 2);
        }
        return (sortedList.get(sortedList.size() / 2 - 1) + sortedList.get(sortedList.size() / 2)) / 2;
    }

    public static void insertIntInSortedList(Integer intToInsert) {
        sortedList.add(Math.abs(Collections.binarySearch(sortedList, intToInsert) + 1), intToInsert);
    }

    public static int numPath(int n, int m) {
        int[][] ints = new int[n][m];
        for (int k = 0; k < n; k++)
            for (int i = m - 1; i >= 0; i--) {
                if (i == m - 1 && k == 0) {
                    ints[k][i] = 1;
                    continue;
                }
                int right = 0, down = 0;
                System.out.println("i=" + i + "k=" + k);
                if (i < m - 1) {
                    right = ints[k][i + 1];
                }
                if (k > 0) {
                    down = ints[k - 1][i];
                }
                ints[k][i] = right + down;
            }
        return ints[n - 1][0];
    }


    public static void printMajorityElement(Scanner sc, int length) {
        Map<Integer, Integer> elemToNumber = new HashMap();
        for (int i = 0; i < length; i++) {
            Integer key = sc.nextInt();
            elemToNumber.putIfAbsent(key, 0);
            Integer times = elemToNumber.get(key);
            if (times >= length / 2) {
                System.out.println("" + key);
                sc.nextLine();
                return;
            }
            elemToNumber.put(key, times + 1);
        }
        System.out.println("-1");
    }
}
