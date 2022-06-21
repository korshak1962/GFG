package com.slava;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class DigitsToLettersTest {

    @Test
    public void letterCombinationsTest() {
        List<String> result= DigitsToLetters.letterCombinations("12");
        System.out.println(result);
    }
    @Test
    public void letterCombinationsTest1() {
        String firstString="wertyui";
       // String[] strs=firstString.split("\\.");
      //  System.out.println(Arrays.toString(strs));
        Collectors.toList();
        List<String> res= DigitsToLetters.stringToListString(firstString);
        System.out.println(res);
        List<String> res1=firstString.chars().mapToObj(c->(String.valueOf((char)c))).collect(Collectors.toList());
        System.out.println(res1);
    }

    @Test
    public void letterCombinationsTest2() {
        String stringA="wertyui";
        String stringB="asdfgh";
        List<String> res=DigitsToLetters.ListMultiplyTwoListStrings(DigitsToLetters.stringToListString(stringA),
                DigitsToLetters.stringToListString(stringB));
        System.out.println(res);
    }

    @Test
    public void letterCombinationsTest3() {
        List<String> result= DigitsToLetters.letterCombinations("12");
        System.out.println(result);
    }

    @Test
    public void letterCombinationsTestWO() {
        List<String> inList = produceInputList();
        List<String> res=DigitsToLetters.ListMultiplyListOfListStringWORecursion(inList);
   //     System.out.println(res);
    }

    @Test
    public void letterCombinationsTestWith() {
        List<String> inList = produceInputList();
        List<String> res=DigitsToLetters.ListMultiplyListOfListString(inList);
//        System.out.println(res);
    }

    private List<String> produceInputList() {
        String stringA = "abcqwertyuiopsdfghn";
        String stringB = "defzxcvbnmwertyhuj";
        String stringC = "ghibnmklwertgh";
        String stringD = "ghibnmklasdfgh";
        String stringE = "ghibnmklasdfgh";
        return List.of(stringA, stringB, stringC, stringD, stringE);
    }

}
