package com.slava;

import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

public class Concurrent {

    @Test
    public void test(){
        CopyOnWriteArrayList<Integer> cwList=new CopyOnWriteArrayList();
        cwList.get(1);

    }
}
