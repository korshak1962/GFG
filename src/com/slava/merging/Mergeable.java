package com.slava.merging;

public interface  Mergeable<T> extends Comparable<Mergeable<T>> {
    /**
     * get current value but not remove/move next
     * if none return null
     * @return T
     */
    T peek();
    /**
     * get current value and remove/move next
     * if none return null
     * @return T
     */
    T poll();
}
