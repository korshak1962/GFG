package com.slava.lists;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class LinkableLinkedListTest {

    @Test
    public void testArrayList() {
        ArrayList<Integer> arL=new ArrayList<>(2);

        arL.add(0,0);
        arL.add(1,1);
    }

    @Test
    public void testCreate() {
        LinkableLinkedList lst=new LinkableLinkedList(1);
        System.out.println(lst.getList());
    }

    @Test
    public void testAdd() {
        LinkableLinkedList<Integer> lst=new LinkableLinkedList(1);
        lst.add(2);
        System.out.println(lst.getList());
    }

    @Test
    public void testLink() {
        LinkableLinkedList<Integer> lst1=new LinkableLinkedList(1);
        lst1.add(2);
        LinkableLinkedList<Integer> lst2=new LinkableLinkedList(3);
        lst2.add(4);
        System.out.println(LinkableLinkedList.linkTwoLists(lst1,lst2).getList());
    }

}
