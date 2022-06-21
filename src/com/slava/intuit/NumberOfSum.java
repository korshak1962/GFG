package com.slava.intuit;

import org.junit.Test;

public class NumberOfSum {

    @Test
    public void test() {
        int nCom = "a+b=5".split("=")[0].split("\\+").length;
        int sum = Integer.valueOf("a+b=5".split("=")[1]);
      //  System.out.println(nCom + "  " + sum);

        if (nCom==0 || sum<=0)  System.out.println(0);
            System.out.println(findAllComb(nCom,sum));
    }

    long findAllComb(int nInput,int sum){
     //   C(nInput,from sum)
        if (nInput<=1 || sum<=1) return 0;
        long for_return=sum-1;
        for (int i=1;i<sum;i++){
            for_return+=findAllComb(nInput-1,sum-i);
        }
        return for_return;
    }

}
