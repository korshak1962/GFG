package com.slava.intuit;

import java.util.*;

public class CallLimit {


    /*
     ** Implement a rate-limiting algorithm that can be used to restrict the number of calls to a web service.
     */

    public static void main(String[] args) {
        RateLimiter rateLimiter = new RateLimiter();

        //Return true within 30 seconds if allowed
        System.out.println(rateLimiter.rateLimit("device_info", 30, 3));
        System.out.println(rateLimiter.rateLimit("device_info", 30, 3));
        System.out.println(rateLimiter.rateLimit("device_info", 30, 3));

        //Return true to limit and deny the request
        System.out.println(rateLimiter.rateLimit("device_info", 30, 3));
    }
}

//O(1) -time complexity
// lsat 30 seconds

// |   30s       |    30s   25s 1  26s 1 |  0     30s     |

// LinkedList<Long> 1000000000
class RateLimiter {
    Map<String, LinkedList<Long>> ketToQueue = new HashMap<>();

    /*
     ** key is the unique identifier of the calling service
     ** intervalInSecs is the time interval we want to limit between
     ** maxLimit is the maximum amount of times a "key" can call this service
     */
    public boolean rateLimit(String key, int intervalInSecs, int maxLimit) {
        Long timeMs = System.currentTimeMillis();
        ketToQueue.computeIfAbsent(key, (k) -> new LinkedList<>());
        LinkedList<Long> queue = ketToQueue.get(key);
        while ((!queue.isEmpty() && (timeMs - queue.peekFirst()) > intervalInSecs)) {
            queue.pollFirst();
        }
        if (queue.size() > maxLimit) {
            return false;
        }
        queue.addLast(timeMs);
        // Return "false" if no more requests are allowed, and "true" otherwise
        return true;
    }
}

