package com.slava.system_design.rateLimiter;

import java.util.concurrent.TimeUnit;

public class RateLimitManager {

    public static void main(String[] args) {
        DistributedRateLimiter distributedRateLimiter = new DistributedRateLimiter(100);
        TokenFiller tokenFiller = new TokenFiller(distributedRateLimiter.getUserToTokens());
        tokenFiller.fillTokensByTmeFrame(TimeUnit.HOURS);
    }
}
