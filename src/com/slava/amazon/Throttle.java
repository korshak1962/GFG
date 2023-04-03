package com.slava.amazon;

public class Throttle {
/*
    throttle()
1. return true for max X calls per sec and
2. return true for max Y calls in Z secs

    X = 2, Y = 3, Z = 5sec

 00:00:00 throttle() returns true
            00:00:00 throttle() returns true
            00:00:00 throttle() returns false
            00:00:03 throttle() returns true
            00:00:03 throttle() returns false
            00:00:05 throttle() returns true

    Queue: {0} {0} {3}
    Queue: {00:00,2} {00:03,1} {00:03333,4}

    LinkedList O(1)+O(X)= O(X)

    ArrayList   O(size)+O(1)  = O(size) /// O(Log(size))


    Deque<Long> times = new LinkedList<>();
    boolean throttle(Long time,x,y,z){
        if (times.isEmpty()){
            times.add(time);
            return true;
        }

        if (times.size()>0Y){
            while (time -times.peekfirst()>=Z {
                times.pollFirst();
            }
        }
        if (times.size()>=Y){
            return false;
        }

        if ( times.size()=<X){
            times.add(time);
            return true;
        }

        if (times.get(times.size()-X)<=1){return false;}
        times.add(time);
        return true;

    }


*/
}
