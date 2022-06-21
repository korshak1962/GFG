package com.slava;

public class Delete_Node {
Node head;
Node tail;

    public static void main(String[] args) {
        Delete_Node delete_Node= new Delete_Node();
        GfG g=new GfG();

    }


}
class GfG
{
    Node prevNode=null;
    void deleteNode(Node node)
    {

        // Your code here
    }
}

 class Node
{
    int data ;
    Node next;
    Node(int d)
    {
        data = d;
        next = null;
    }
}