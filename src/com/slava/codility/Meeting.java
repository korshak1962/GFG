package com.slava.codility;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.regex.Pattern;

public class Meeting {

    public int solution(String S) {
        // write your code in Java SE 8
        return 0;
    }
    String S = "Sun 10:00-20:00 Fri 05:00 - 10:00 " +
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
    public void test() throws ParseException {
        String S = "Sun 10:00-20:00 Fri 05:00 - 10:00 ";
        Date date = new Date(0);
        System.out.println(date);
        DateFormat dateFormat = new SimpleDateFormat("EEE HH:mm");
        System.out.println(dateFormat.parse("Sun 10:00"));
        System.out.println(dateFormat.parse("Tue 03:30"));
        System.out.println(dateFormat.parse("Fri 16:30"));

        Calendar c = GregorianCalendar.getInstance();
        Instant instant=        Instant.now()  ;
                // instant.
    }

    public void test0() {
        final String regex = "(?=\\p{Upper})";
        String[] splitted = S.split(regex);
        Map<Long, Long> startToEnd = new TreeMap<>();
        PriorityQueue<Long> free = new PriorityQueue<>(Comparator.reverseOrder());
        DateFormat dateFormat = new SimpleDateFormat("EEE HH:mm");

        long startFree = 0;
        for (String sdate : splitted) {
            String[] sdat = sdate.split("-");
            Date dateEnd = null;
            Date dateStart = null;
            try {
                dateStart = dateFormat.parse(sdat[0]);
                final String endMeeting = sdat[0].substring(0, 3) + " " + sdat[1];
                dateEnd = dateFormat.parse(endMeeting);
                startToEnd.put(dateStart.getTime() / 60000, dateEnd.getTime() / 60000);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        for (Map.Entry<Long, Long> entry : startToEnd.entrySet()) {
            final long freeTime = entry.getKey() - startFree;
            free.add(freeTime);
            startFree = entry.getKey();
        }

        final Long peek = free.peek();
        System.out.println(peek);
    }

    //  int stringToInt(String sd){
    // 10:00-20:00
    //      String[] times= sd.split("-");
    //      String hhmm=times[0].split()
    //  }
}
