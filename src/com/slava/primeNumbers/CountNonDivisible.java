package com.slava.primeNumbers;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
//  https://app.codility.com/demo/results/trainingS6UPVF-Q8U/
public class CountNonDivisible {

    @Test
    public void testIntegerDiv() {
        int a=14;
        int b=4;
        int c=a%b;
        System.out.println(c);
        float aa=3;
        float bb=4;
        float cc=aa%bb;
        System.out.println(cc);
    }

    @Test
    public void testSpeed() {
        int a=2;
        int b=3;
        Double n=1e8;
        long N=n.longValue();
        long c=0;long d=Double.valueOf(2).longValue();
        long now=System.currentTimeMillis();
        for (int i=0;i<N;i++){
            c=c+i;
        }
        c++;
        System.out.println(System.currentTimeMillis()-now);
        now=System.currentTimeMillis();
        for (int i=1;i<N;i++){
            d=d*i;
        }
        d++;
        System.out.println(System.currentTimeMillis()-now);
    }


    @Test
    public void test1() {
        int[] A = new int[5];
        A[0] = 3;
        A[1] = 1;
        A[2] = 2;
        A[3] = 3;
        A[4] = 6;
        int[] res = solution(A);
        int correct[] = {2, 4, 3, 2, 0};
        //Assert.assertEquals(correct, res);
        Assert.assertArrayEquals(correct, res);   //Arrays.equals(res,correct);
    }

    public int[] solution(int[] A) {
      //  https://app.codility.com/demo/results/trainingS6UPVF-Q8U/
        int result[] = new int[A.length];
        Map<Integer, Integer> numToTimes = new HashMap<>();
        for (int a : A) {
            numToTimes.computeIfPresent(a, (k, v) -> ++v);   // number to how many times counter
            numToTimes.computeIfAbsent(a, (v) -> 1);
        }
        final Integer onesQnty = numToTimes.get(1);
        for (int i = 0; i < A.length; i++) {
            int numDiv = numToTimes.get(A[i]);
            if (A[i] != 1 && onesQnty != null) {
                numDiv += onesQnty;
            }
            for (int j = 2; j * j <= A[i]; j++) {
                if (A[i] % j == 0) {
                    final Integer numDivj = numToTimes.get(j);
                    if (numDivj != null) {
                        numDiv += numDivj;
                    }
                    final int jCounterPart = A[i] / j;
                    if (jCounterPart != j) {
                        final Integer numDivjC = numToTimes.get(jCounterPart);
                        if (numDivjC != null) {
                            numDiv += numDivjC;
                        }
                    }
                }
            }
            result[i] = A.length - numDiv;
        }
        return result;
    }


    public int[] solution3(int[] A) {
        //  https://app.codility.com/demo/results/trainingJ3NN26-FSC/    100%
        int result[] = new int[A.length];
        final int maxQnty = 100001;
        int[] numToTimes = new int[maxQnty];
        for (int a : A) {
            numToTimes[a]++;
        }
        final int onesQnty = numToTimes[1];
        for (int i = 0; i < A.length; i++) {
            int numDiv = numToTimes[A[i]];
            if (A[i] != 1) {
                numDiv += onesQnty;
            }
            for (int j = 2; j * j <= A[i]; j++) {
                if (A[i] % j == 0) {
                    numDiv += numToTimes[j];
                    final int JSymetrical = A[i] / j;
                    if (JSymetrical != j) {
                        numDiv += numToTimes[JSymetrical];
                    }
                }
            }
            result[i] = A.length - numDiv;
        }
        return result;
    }

    @Test
    public void testSimple() {
        fillSimpleNums(30);
        Assert.assertEquals(10, primeNums.size());
    }


    SortedSet<Integer> primeNums = new TreeSet<>();

    void fillSimpleNums(int maxNum) {
        for (int i = 2; i < maxNum; i++) {
            primeNums.add(i);
        }
        int simple = 2;
        while (simple < maxNum) {
            removeNonSimple(maxNum, simple);
            SortedSet<Integer> tail = primeNums.tailSet(simple + 1);
            if (tail.isEmpty()) {
                break;
            }
            simple = tail.first();
        }
    }

    private void removeNonSimple(int maxNum, int simple) {
        int nonSimple = simple * simple;
        while (true) {
            if (nonSimple > maxNum) break;
            primeNums.remove(nonSimple);
            nonSimple += simple;
        }
    }

    public int[] solution2(int[] A) {
        int max = Arrays.stream(A).max().getAsInt();
        fillSimpleNums(max);
        Map<Integer, List<Integer>> numToDividers = new HashMap<>();
        Map<List<Integer>, Integer> dividersToNum = computeNumToDiveders(A, numToDividers);
        int[] result = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            for (Map.Entry<List<Integer>, Integer> entry : dividersToNum.entrySet()) {
                if (numToDividers.get(A[i]).containsAll(entry.getKey())) {
                    result[i] += entry.getValue();
                }
            }
            result[i] = A.length - result[i];
        }
        return result;
    }

    private Map<List<Integer>, Integer> computeNumToDiveders(int[] A, Map<Integer, List<Integer>> numToDividers) {
        Map<List<Integer>, Integer> dividersToNums = new HashMap<>();
        for (int a : A) {
            List<Integer> dividers = numToDividers.get(a);
            if (dividers != null) {
                dividersToNums.put(dividers, dividersToNums.get(dividers) + 1);
                continue;
            }
            List<Integer> toAdd = new LinkedList<>();
            for (int simple : primeNums) {
                if (simple > a) {
                    break;
                }
                if (a % simple == 0) {
                    toAdd.add(simple);
                }
            }
            numToDividers.put(a, toAdd);
            dividersToNums.computeIfPresent(toAdd, (k, v) -> ++v);
            dividersToNums.computeIfAbsent(toAdd, (v) -> 1);
        }
        return dividersToNums;
    }

    public int[] solutionStack(int[] A) {
        int[][] D = new int[A.length * 2 + 1][2];
        for (int i = 0; i < A.length; i++) {
            D[A[i]][0]++;
            D[A[i]][1] = -1;
        }
        for (int i = 0; i < A.length; i++) {
            if (D[A[i]][1] == -1) {
                D[A[i]][1] = 0;
                for (int j = 1; j <= Math.sqrt(A[i]); j++) {
                    if (A[i] % j == 0 && A[i] / j != j) {
                        D[A[i]][1] += D[j][0];
                        D[A[i]][1] += D[A[i] / j][0];
                    } else if (A[i] % j == 0 && A[i] / j == j) {
                        D[A[i]][1] += D[j][0];
                    }
                }
            }
        }
        for (int i = 0; i < A.length; i++) {
            A[i] = A.length - D[A[i]][1];
        }
        return A;
    }

}
