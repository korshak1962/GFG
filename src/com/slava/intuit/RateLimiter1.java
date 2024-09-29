package com.slava.intuit;

public class RateLimiter1 {

  private final double rate;
  private double tokens;
  private long lastRefillTimestamp;

  public RateLimiter1(int maxCalls, long timeFrameSeconds) {
    this.rate = (double) maxCalls / timeFrameSeconds;
    this.tokens = maxCalls;
    this.lastRefillTimestamp = System.nanoTime();
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
