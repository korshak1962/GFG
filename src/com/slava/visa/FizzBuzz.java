package com.slava.visa;

public class FizzBuzz {

    static boolean div3, div5;

    public static void fizzBuzz(int n) {
        for (int i = 1; i < n + 1; i++) {
            // Write your code here
            div3 = (i % 3 == 0);
            div5 = (i % 5 == 0);
            if (div3 && div5) {
                System.out.println("FizzBuzz");
                continue;
            }
            if (div3 && !div5) {
                System.out.println("Fizz");
                continue;
            }
            if (!div3 && div5) {
                System.out.println("Buzz");
                continue;
            }
            System.out.println(i);
            continue;
        }
    }


}
