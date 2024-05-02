package com.slava.google;

public class TestClass {

    @org.junit.jupiter.api.Test
    void name() {
        Bclass b = new Bclass();
        Cclass c = new Cclass();

        System.out.println(b.state);
        b.state=2;
        System.out.println(c.state);
    }
}
