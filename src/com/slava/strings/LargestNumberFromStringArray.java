package com.slava.strings;

import com.slava.utilities.Reader;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;

public class LargestNumberFromStringArray {

    String printLargest(int n, String[] arr) {
        Arrays.sort(arr, getComparator().reversed());
        StringBuilder builder = new StringBuilder();
        return String.join("", arr);
    }

    private static Comparator<String> getComparator() {
        return (s1, s2) -> (s1+s2).compareTo(s2+s1);
    }

    private static Comparator<String> getComparator2() {
        return (s1, s2) -> {
            if (Long.parseLong(s1+s2)>Long.parseLong(s2+s1)) return 1;
            if (Long.parseLong(s2+s1)>Long.parseLong(s1+s2)) return -1;
            else return 0;
        };
    }

    private static Comparator<String> getComparator1() {
        return (s1, s2) -> {
            for (int i = 0; i < Integer.min(s1.length(), s2.length()); i++) {
                if (s1.charAt(i) > s2.charAt(i)) {
                    return 1;
                }
                if (s1.charAt(i) < s2.charAt(i)) {
                    return -1;
                }
            }
            if (s1.length() == s2.length()) return 0;

            if (s1.length() > s2.length()) {
                for (int i = s2.length(); i < s1.length(); i++) {
                    if (s1.charAt(i) > s1.charAt(0)) {
                        return 1;
                    }
                    if (s1.charAt(i) < s1.charAt(0)) {
                        return -1;
                    }
                }
                return 1;
            } else {
                for (int i = s1.length(); i < s2.length(); i++) {
                    if (s2.charAt(i) > s2.charAt(0)) {
                        return -1;
                    }
                    if (s2.charAt(i) < s2.charAt(0)) {
                        return 1;
                    }
                }
                return -1;
            }
        };
    }

    @Test
    public void test() {
        String arr[] = {"54", "546", "548", "60"};
        Assert.assertEquals("6054854654", printLargest(arr.length, arr));
    }

    @Test
    public void test1() {
        String arr[] = {"3", "30", "34", "5", "9"};
        Assert.assertEquals("9534330", printLargest(arr.length, arr));
    }

    @Test
    public void test2() throws FileNotFoundException {
        int[] arr = Reader.readArrayFromFile("resources/comparator.txt");
        String[] strings = Arrays.stream(arr).mapToObj(Integer::toString)
                .toArray(String[]::new);
        System.out.println("length="+arr.length);
        System.out.println(printLargest(arr.length, strings));
        String correct ="99999999999999909966992992399149869797979797296969696609576956195094939339329324929189129090908989748954893888888888888888818787287198718698868584848484184183837835883428308308282348198188181818168816811380807985795789787867782781779779177877877777777777753767575756875667537497474677460742730726672547171257066986976968676766673668667666666666666632658658656546648648464637636863635163446136096605959158625858585705695619559555555555555555555375535522552154532531529528519515050324995495494949492449144885487648484847483948248047884782474724704684658453244974494482444444444442843543434343433543142774274242342034144141414128412441204040402399439938303828374373373336053553532350334343434334243333332843181316314313310930930532972972960294628852882828128128027272679265265026426426262621259258252522522423372327226322222222222222181217021212112082019671922192191916190185181771759173016851636162516161616115941588154151414141141136133128512111111111111111110310221010988086308608280808080710058305470505004940404040403802510230220176011010101009000800000000000000";
        Assert.assertEquals(correct, printLargest(arr.length, strings));
    }

    @Test
    public void test3() {
        String arr[] = {"5537", "5", "5", "5", "5"};
        Assert.assertEquals("55555537", printLargest(arr.length, arr));
    }
}
