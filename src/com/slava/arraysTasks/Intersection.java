package com.slava.arraysTasks;

// intersection of two arrays
//
//// int nums1[] ={2,2,7,5,15}; int nums2[]={3,3,8,99,7,15}  -> 7,15

import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Intersection {


    public int[] solution(int[] nums1,int[] nums2){
        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        return Arrays.stream(nums2)
                .filter(set1::contains)
                .toArray();
    }

    @Test
    public void test(){
        int nums1[] ={2,2,7,5,15};
        int nums2[]={3,3,8,99,7,15};
        System.out.println(Arrays.toString(solution(nums1,nums2)));
    }

}
