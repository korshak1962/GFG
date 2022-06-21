package com.slava;

import org.junit.Test;

public class PowerOf2 {

    boolean check(int n) {
        int c = n & (n - 1);
        return c == 0;
    }

    @Test
    public void testInt() {
        int n=3;
        System.out.println(check(n));
        n=4;
        System.out.println(check(n));
        n=6;
        System.out.println(check(n));
        System.out.println(n>>1);
    }

}
