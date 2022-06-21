package com.slava.binarySearch;

import java.util.Arrays;

//https://leetcode.com/problems/median-of-two-sorted-arrays/
public class MedianTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length==0){}
        if (nums2.length==0){}
        return findMedianSortedArrays(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1);

    }

    private double findMedianSortedArrays(int[] nums1, int s1, int e1, int[] nums2, int s2, int e2) {
        if (nums1.length == 1) {
        }
        if (nums2.length == 1) {
        }
        int numMed1 = nums1.length / 2;
        int numMed2 = nums2.length / 2;
        if (nums1[numMed1] > nums2[numMed2]) {
            s1=numMed1+1;e2=numMed2;
        }
        if (nums1[numMed1] < nums2[numMed2]) {
            s1=numMed1+1;e2=numMed2;
        }
        if (nums1[numMed1] == nums2[numMed2]) {
           // if e1
        }
        return findMedianSortedArrays(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1);
    }

}
