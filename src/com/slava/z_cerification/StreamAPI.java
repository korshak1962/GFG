package com.slava.z_cerification;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class StreamAPI {
    Supplier<LocalDate> supDate = LocalDate::now;

    Supplier<StringBuilder> sb = StringBuilder::new;

    StreamAPI(){
        supDate.get();
        var map = new HashMap<String, Integer>();
        BiConsumer<String, Integer> b1 = map::put;
        Stream<String> s = Stream.of("Monkey", "Gorilla", "Bonobo");
        s.forEach(System.out::print); // MonkeyGorillaBonobo
    }

    @Test
    public void whenFlatMapEmployeeNames_thenGetNameStream() {
        List<List<String>> namesNested = Arrays.asList(
                Arrays.asList("Jeff", "Bezos"),
                Arrays.asList("Bill", "Gates"),
                Arrays.asList("Mark", "Zuckerberg"));

        final Function<List<String>, Stream<? extends String>> fun = Collection::stream;
        List<String> namesFlatStream = namesNested.stream()
                .flatMap(fun)
                .collect(Collectors.toList());

        assertEquals(namesFlatStream.size(), namesNested.size() * 2);

        final Function<String,String> fun1 = TT::m2;
        final Function<String,String> fun2 = aa::m3;

    }

    static interface aa extends Collection{
          static String m1(){return "";};
        default  String m2(String s){return "";}
        static  String m3(String s){return "";}
    }


    static class TT{
        static public  String m1(){return "";}
        public static String m2(String s) {return ""; }
    }

}
