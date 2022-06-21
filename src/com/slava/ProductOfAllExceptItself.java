package com.slava;

import org.junit.Assert;
import org.junit.Test;

public class ProductOfAllExceptItself {

    @Test
    public void test() {
        // i/p: [1, 2, 3, 4] - inputArray
        int[] inputArray = {1, 2, 3, 4};
        int[] resultArray = getProductExceptItself(inputArray);
        int[] expectedArray = {24, 12, 8, 6};
        Assert.assertArrayEquals(expectedArray, resultArray);
    }

    int[] getProductExceptItself(int[] inputArray) {
        int[] resultArray = new int[inputArray.length];
        int[] prefixArrayProduct = new int[inputArray.length];
        int[] postfixArrayProduct = new int[inputArray.length];
        prefixArrayProduct[0] = inputArray[0];
        for (int index = 1; index < inputArray.length; index++) {
            prefixArrayProduct[index] = prefixArrayProduct[index - 1] * inputArray[index];
        }
        postfixArrayProduct[inputArray.length - 1] = inputArray[inputArray.length - 1];
        for (int index = inputArray.length - 2; index >= 0; index--) {
            postfixArrayProduct[index] = postfixArrayProduct[index + 1] * inputArray[index];
        }
        resultArray[0] = postfixArrayProduct[1];
        resultArray[inputArray.length - 1] = prefixArrayProduct[inputArray.length - 2];
        for (int index = 1; index < inputArray.length - 1; index++) {
            resultArray[index] = prefixArrayProduct[index - 1] * postfixArrayProduct[index + 1];
        }
        return resultArray;
    }
/*
    i/p: [1, 2, 3, 4] - inputArray

    Prefix[1,2,6,24]
    postfix[24,24,12,4]

    o/p: [24, 12, 8, 6]
    Int[] resultArray=new int(inputArray.length);
    Int[] prefixArrayProduct=new int(inputArray.length);
    Int[] postfixArrayProduct=new int(inputArray.length);
    prefixArrayProduct[0]=inputArray[0];
    For (int index=1;index<inputArray.length;index++) {
        prefixArrayProduct[index-1]*inputArray[index];
    }
    postfixArrayProduct[inputArray.length-1]=inputArray[inputArray.length];
    For (int index=inputArray.length-2;index>=0;index--) {
        postfixArrayProduct[index+1]*inputArray[index];
    }
    resultArray[0]=inputArray[0];
    resultArray[inputArray.length-1]=prefixArrayProduct[inputArray.length-2]*resultArray[inputArray.length-1];
    For (int index=1;index<inputArray.length-2;index++) {
        resultArray[index]=prefixArrayProduct[index-1]*postfixArrayProduct[index+1];
    }

    long productOfAll=1;
    For (int index=0;index<inputArray.length;index++) {
        productOfAll=productOfAll*inputArray[index];
    }


    For (int index=0;index<inputArray.length;index++) {
        resultArray[index]=productOfAll/inputArray[index]}

*/


}
