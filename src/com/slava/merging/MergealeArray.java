package com.slava.merging;

import java.util.Comparator;
import java.util.Set;

public class MergealeArray<T extends Comparable> implements Mergeable<T> {
    T[] array;
    int index = 0;

    public MergealeArray(T[] array) {
        this.array = array;
    }

    @Override
    public T peek() {
        if (index < array.length - 1)
            return array[index];
        return null;
    }

    @Override
    public T poll() {
        T forReturn = peek();
        index++;
        return forReturn;
    }

    @Override
    public int compareTo(Mergeable<T> o) {
        return this.peek().compareTo(o.peek());
    }
}
