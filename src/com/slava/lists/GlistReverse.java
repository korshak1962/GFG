package com.slava.lists;

import org.junit.Test;

public class GlistReverse {

    // write method to reverse a linked list using RemoveLoopInList.Node
    public static void reverse(RemoveLoopInList.Node head) {
        RemoveLoopInList.Node previous = head;
        RemoveLoopInList.Node next;
        while (head.next!= null) {
            next = head.next;
            head.next = previous;
            previous = head;
            head = next;
        }
        head.next = previous;
    }
    // write unit test for GlistReverse.reverse method
    @Test
    public void test1() {
        // 1 -> 3 -> 4->3
        RemoveLoopInList.Node first = new RemoveLoopInList.Node();
        first.data = 1;
        RemoveLoopInList.Node second = new RemoveLoopInList.Node();
        first.next = second;
        second.data = 3;
        RemoveLoopInList.Node third = new RemoveLoopInList.Node();
        second.next = third;
        third.data = 4;
        third.next = third;
        // the below code fragment can be found in:
        // GFG\src\com\slava\lists\ListReversingAndReorder.java
        RemoveLoopInList.Node head = new RemoveLoopInList.Node(2);
        head.next = new RemoveLoopInList.Node(4);
        head.next.next = new RemoveLoopInList.Node(6);
    }
    @Test
    public void test() {
        // 1 -> 3 -> 4->3
        RemoveLoopInList.Node first = new RemoveLoopInList.Node();
        first.data = 1;
        RemoveLoopInList.Node second = new RemoveLoopInList.Node();
        first.next = second;
        second.data = 3;
        RemoveLoopInList.Node third = new RemoveLoopInList.Node();
        second.next = third;
        third.data = 4;
        third.next = third;
        // the below code fragment can be found in:
        // GFG\src\com\slava\lists\ListReversingAndReorder.java
        RemoveLoopInList.Node head = new RemoveLoopInList.Node(2);
        head.next = new RemoveLoopInList.Node(4);
        head.next.next = new RemoveLoopInList.Node(6);
    }


    /*
    1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 ->8

  Inputs:  head
           left, right


           2, 4

    Output: (reverse order of elements between left and right index)

Class  Link {
  int data, Link next;
}


    1 -> 4 -> 3 -> 2 -> 5 -> 6 -> 7 -> 8


      1. go thropugh list then I meet left-1 kepp leftLink = LinkM1;
      I keep Link reversed = Link.next;

then I continue iterate until I got right+1 I put Link to LinkR1, I stop iterate;

2 Reverse internal list

3 I put LinkLeft.next to the start of reversed list
4. put the end the reversed list to LinkRight1.


  Link reversedInterval(final Link head, int start,int end){
  Link left1,right1,startRev;

 Link iterated=head;

for (int=0;i<end+1;i++){
if (i==start-1) {
  left1=iterated;
  left1.next = null;
  startRev = iterated.next;
}

  if (i==end) {
  right1=iterated.next;
  iterated.next=null;
}
  iterated=iterated.next;
}

  if (right1==null)( throw  Exception("reach end of the list"))

Link reversedList = reversList(startRev);

  left1.next=reversedList;
  startRev.next = right1;

}


Link reversList(Link tobeReversed){
Link next;Link nextNext;
while (tobeReversed!=null){
next = tobeReversed.next;
  nextNext=next.next;
  next.next=tobeReversed;
  tobeReversed = next;
  tobeReversed.next =nextNext;
}
}


0(1)  space

0(N)  time compexity
     */
}
