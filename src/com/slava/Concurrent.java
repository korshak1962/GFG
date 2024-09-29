package com.slava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

public class Concurrent {

    @Test
    public void test(){
        CopyOnWriteArrayList<Integer> cwList=new CopyOnWriteArrayList();
        cwList.get(1);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
       // executorService.
    }
}
