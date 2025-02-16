package com.slava.apixio;


import java.util.*;
import java.util.stream.Collectors;

/// Problem Below:
// /**
// Given a string, return recursively a "cleaned" string
// where adjacent chars that are the same have been reduced to a single char.
// So "yyzzza" yields "yza".

// stringClean("yyzzza") → "yza"
// stringClean("abbbcdd") → "abcd"
// stringClean("Hello") → "Helo"
// */

public class RecursionRemoveLetter {
    /*
     * Click `Run` to execute the snippet below!
     */


    /*
     * To execute Java, please define "static void main" on a class
     * named Solution.
     *
     * If you need more classes, simply define them inline.
     */
    public static void main(String[] args) {

        //  System.out.println(stringClean("yyzzza"));
        System.out.println(stringClean("dd"));
        List<String> res=Arrays.stream(" a b".trim().split(" "))
                .collect(Collectors.toList());
        System.out.println(res);
        System.out.println(stringClean("Hello"));
        System.out.println(stringClean("dd".toUpperCase()));
    }


    static String stringClean(String input) {
        // Set<Character> chars = new HashSet<>();
        if (input.isEmpty()) return input;
        char previouz = input.charAt(0);
        for (int i = 1; i < input.length(); i++) {
            char next = input.charAt(i);
            if (previouz == next) {
                final String last = input.substring(i, input.length());
                return stringClean(input.substring(0, i - 1) + last);
            }
            previouz = next;
        }
        return input;
    }
}


// Your previous Plain Text content is preserved below:

// This is just a simple shared plaintext pad, with no execution capabilities.

// When you know what language you'd like to use for your interview,
// simply choose it from the dropdown in the top bar.

// You can also change the default language your pads are created with
// in your account settings: https://app.coderpad.io/settings

// Enjoy your interview!






