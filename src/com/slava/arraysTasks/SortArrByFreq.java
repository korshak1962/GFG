package com.slava.arraysTasks;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class SortArrByFreq {

    public ArrayList<Integer> sortByFreq(int arr[]) {
        var numToQnty=Arrays.stream(arr).boxed()
                .collect(Collectors.groupingBy(a->a,Collectors.counting()));
        ArrayList<Integer> result= new ArrayList<>();
        numToQnty.entrySet().stream().sorted(
                Map.Entry.<Integer,Long>comparingByValue(Comparator.reverseOrder())
                .thenComparing(Map.Entry.comparingByKey())).forEach((e)->{
                    for(int i=0;i<e.getValue();i++){
                        result.add(e.getKey());
                    }});
        return result;
    }

    @Test
    public void test(){
        int arr[]={1,1,1,5,8,8,8,8,8,8,8,8};
        System.out.println(sortByFreq( arr));
    }
}
