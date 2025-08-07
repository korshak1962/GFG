package com.slava.system_design.rateLimiter;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public interface TokensProvider {

    HashMap<String, Integer>  getTokensForTimeFrame(TimeUnit timeFrime);
}
