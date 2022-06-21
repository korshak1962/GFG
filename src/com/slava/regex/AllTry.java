package com.slava.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;
//http://www.quizful.net/post/Java-RegExp
public class AllTry {
    /*
        [abc]	Find one character from the options between the brackets
    [^abc]	Find one character NOT between the brackets
    [0-9]	Find one character from the range 0 to 9

    Metacharacter	Description
    |	Find a match for any one of the patterns separated by | as in: cat|dog|fish
    .	Find just one instance of any character
    ^	Finds a match as the beginning of a string as in: ^Hello
    $	Finds a match at the end of the string as in: World$
    \d	Find a digit
    \s	Find a whitespace character
    \b	Find a match at the beginning of a word like this: \bWORD, or at the end of a word like this: WORD\b

        */

    String S = "Sun 10:00 - 20:00 Fri 05:00 - 10:00 " +
            "Fri 16:30 - 23:50 " +
            "Sat 10:00 - 24:00 " +
            "Sun 01:00 - 04:00 " +
            "Sat 02:00 - 06:00 " +
            "Tue 03:30 - 18:15 " +
            "Tue 19:00 - 20:00 " +
            "Wed 04:25 - 15:14 " +
            "Wed 15:14 - 22:40 " +
            "Thu 00:00 - 23:59 " +
            "Mon 05:00 - 13:00 " +
            "Mon 15:00 - 21:00 ";
    @Test
    public void test() {
        String regex = "(?=\\p{Upper})";  //split by upper letter
        String[] splitted = S.split(regex);
        System.out.println(Arrays.toString(splitted));
        for (String str:splitted){
            switch (str.substring(0, 3)){
                case "Sun":System.out.println("Sun");
                    getTime(str);
                case "Fri":System.out.println("Fri");
                    getTime(str);
                default:System.out.println("str");
            }
        }
        //regex =
    }

    private void getTime(String str) {
        final Integer startHour = Integer.valueOf(str.substring(4, 6));
        System.out.println("start hours:"+ startHour);
        final Integer startMinutes = Integer.valueOf(str.substring(7, 9));
        System.out.println("start minutes:"+startMinutes);
        final Integer endHour = Integer.valueOf(str.substring(12, 14));
        System.out.println("end hours:"+endHour);
        final Integer endMinutes = Integer.valueOf(str.substring(15, 17));
        System.out.println("end minutes:"+endMinutes);
    }

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("w3schools", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher("Visit W3Schools! w3schools");

        while (matcher.find()) {
            System.out.println("Match found at "+matcher.start());
        }
    }
}
