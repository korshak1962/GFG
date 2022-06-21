package com.slava.visa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//https://leetcode.com/discuss/interview-question/790459/Visa-OA-Hackerrank-New-Grad-2019-2020
public class SlowestKey {

    public static final int NUM_ALFABET_CHAR = 25;
    static int worstTimes[] = new int[NUM_ALFABET_CHAR];
    static Map<Integer, Character> intToLetter = new HashMap<>();

    static {
        int startLeeter = 'a';
        for (int i = 0; i < NUM_ALFABET_CHAR; i++) {
            intToLetter.put(i, Character.valueOf((char) (startLeeter + i)));
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("resources/slowestKey.txt"));
        int keyTimes = sc.nextInt();
        //int size2 = sc.nextInt();
        int prevTimeAbs = 0;
        int letter = 0;
        int timeForLetterAbs = 0;
        for (int i = 0; i < keyTimes; i++) {
            letter = sc.nextInt();
            timeForLetterAbs = sc.nextInt();
            final int timeForLetter = timeForLetterAbs - prevTimeAbs;
            if (worstTimes[letter] < timeForLetter) {
                worstTimes[letter] = timeForLetter;
            }
            prevTimeAbs = timeForLetterAbs;
        }
        int worstTime = 0;
        int worstLetter = 0;
        for (int i = 0; i < NUM_ALFABET_CHAR; i++) {
            if (worstTimes[i] > worstTime) {
                worstTime = worstTimes[i];
                worstLetter = i;
            }
        }
        System.out.println("" + intToLetter.get(worstLetter));
    }


}
