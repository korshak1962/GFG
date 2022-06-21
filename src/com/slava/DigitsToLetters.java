package com.slava;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DigitsToLetters {
    static Map<Integer, String> digitToString = Map.of(1, "abc", 2, "def");
    //static Map<Integer, String> digitToString1 = Map.of(1, "abc", 2, "def",3,"ghi");

    public static List<String> letterCombinations(String digits) {
        return ListMultiplyListOfListString(new LinkedList<>(digitToString.values()));
    }

    static List<String> ListMultiplyListOfListStringWORecursion(List<String> listStr) {
        List<String> allCombinations = stringToListString(listStr.get(0));
        for (String bigString:listStr.subList(1,listStr.size())){
            allCombinations=ListMultiplyTwoListStrings(allCombinations,stringToListString(bigString));
        }
        return allCombinations;
    }

    static List<String> stringToListString(String firstString) {
        return firstString.chars().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.toList());
    }

    static List<String> ListMultiplyTwoListStrings(List<String> listA, List<String> listB) {
        List<String> result = new LinkedList<>();
        for (String strA : listA) {
            for (String strB : listB) {
                result.add(strA + strB);
            }
        }
        return result;
    }


    static List<String> ListMultiplyListOfListString(List<String> listStr) {
        if (listStr.size() == 2) {
            return ListMultiplyTwoListStrings(stringToListString(listStr.get(0)),
                    stringToListString(listStr.get(1)));
        }
        return ListMultiplyTwoListStrings(stringToListString(listStr.get(0)),
                ListMultiplyListOfListString(listStr.subList(1, listStr.size())));
    }

    public static List<String> letterCombinations1(String digits) {
        List<String> toReturn = new LinkedList<>();
        if (digits.length() == 1) {
            return getAllPermutations(digitToString.get(Integer.valueOf(digits)));
        }
        toReturn.addAll(letterCombinations1(digits.substring(0, 1)));
        toReturn.addAll(letterCombinations1(digits.substring(1, digits.length())));
        return toReturn;
    }

    static List<String> getAllPermutations(String str) {
        if (str.length() == 2) {
            StringBuilder strBuilder = new StringBuilder(str);
            return List.of(str, strBuilder.reverse().toString());
        }
        List<String> toReturn = new LinkedList<>();
        for (String strPermuted : getAllPermutations(str.substring(1, str.length()))) {
            for (int i = 0; i < strPermuted.length(); i++) {
                StringBuilder strBuilder = new StringBuilder(strPermuted);
                toReturn.add(strBuilder.insert(i, str.charAt(0)).toString());
            }
        }
        return toReturn;
    }

}
