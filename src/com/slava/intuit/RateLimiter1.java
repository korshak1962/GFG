package com.slava.intuit;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class RateLimiter1 {

  public static void main(String arg[]) throws InterruptedException {

    // testTrue
    RateLimiter1 rateLimiter = new RateLimiter1(3, 1);
    boolean isAllowed = rateLimiter.isAllowed();
    for (int i = 0; i < 2; i++) {
      isAllowed =rateLimiter.isAllowed(); // true
    }
    Assertions.assertTrue(isAllowed);

    // testFalse
      for (int i = 0; i < 5; i++) {
        isAllowed =rateLimiter.isAllowed(); // true
      }
      Assertions.assertFalse(isAllowed);

    // testTrue
    Thread.sleep(1000);
    for (int i = 0; i < 3; i++) {
      isAllowed =rateLimiter.isAllowed(); // true
    }
    Assertions.assertTrue(isAllowed);

  }

  private final double rate;
  private double tokens;
  private long lastRefillTimestamp;

  public RateLimiter1(){
    this.rate=0;
  }

  public RateLimiter1(int maxCalls, long timeFrameSeconds) {
    this.rate = (double) maxCalls / timeFrameSeconds;
    this.tokens = maxCalls;
    this.lastRefillTimestamp = System.nanoTime();
    int b[] = {3,8,99,7,15};
    List<Integer> lst = new ArrayList<>();
    lst.add(2,2);
  }

  public boolean isAllowed() {
    refillTokens();
    if (tokens >= 1) {
      tokens -= 1;
      return true;
    }
    return false;
  }

  private void refillTokens() {
    long now = System.nanoTime();
    double timePassed = (now - lastRefillTimestamp) / 1e9;
    double newTokens = timePassed * rate;
    tokens = Math.min(tokens + newTokens, rate);
    lastRefillTimestamp = now;
  }
}

