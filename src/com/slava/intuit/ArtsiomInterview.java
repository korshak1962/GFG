package com.slava.intuit;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
/*
Hi Artsiom, you have  2 yes (me and Divyesh) in feedback ⁠
Smile
 , congratulations! Just edge case for coding task -
 imagine we have 1 call in 1st second and 99 calls in 60th second and 99 in 61
 You flush counter on 1st in 61 second and allow next 99.
 In this case you allowed almost 199 in 2 seconds - 60 and 61, right?
 */
public class ArtsiomInterview {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static AtomicLong atomicLong = new AtomicLong(System.currentTimeMillis());

    public static void main(String[] args) {
    }

    private static String rateLimitedWrapperMethod(String input) {
      if (System.currentTimeMillis() - atomicLong.get() > 60000) {
        atomicInteger.set(0);
        atomicLong.set(System.currentTimeMillis());
      }
      if (atomicInteger.get() > 99) {
        throw new RuntimeException("Too many requests");
      }
      atomicInteger.getAndIncrement();
      String result = actualMethod(input);
      return result;
    }

    private static String actualMethod(String input) {
      try {
        Thread.sleep(15000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      return input;
    }

    public void testSuccessFlow() {
      atomicInteger.set(0);
      atomicLong.set(System.currentTimeMillis() - 70000);
      String actualResult = rateLimitedWrapperMethod("string");
      assert actualResult == "string";
    }
  }

