package com.slava.lists;

public class NodeOneWay<E> {

    public E item;
    public NodeOneWay<E> nextNode;

    public NodeOneWay(E item) {
        this.item = item;
    }
}
