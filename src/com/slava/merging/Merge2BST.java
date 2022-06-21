package com.slava.merging;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

//https://practice.geeksforgeeks.org/problems/merge-two-bst-s/1/?company[]=Google&company[]=Google&page=1&query=company[]Googlepage1company[]Google

    // { Driver Code Starts
//Initial Template for Java

    /*package whatever //do not write package name here */

import java.io.*;
import java.util.*;
import java.math.*;
public class Merge2BST {

    class Node
    {
        int data;
        Node left, right;

        public Node(int d)
        {
            data = d;
            left = right = null;
        }
    }

// } Driver Code Ends


//User function Template for Java




        //Function to return a list of integers denoting the node
        //values of both the BST in a sorted order.
        public List<Integer> merge(Node root1,Node root2)
        {
            List<Integer> result = new LinkedList<>();

            // Write your code here
            return result;
        }

    List<Integer> traverseResult=new LinkedList<>();

    void traverseBST(Node root) {
        if (root == null) return;
        traverseBST(root.left);
        traverseResult.add(root.data);
        traverseBST(root.right);
    }

    @Test
public void traverseBST(){
        Node root = new Node(8);
        root.right = new Node(16);
        root.right.right = new Node(32);
        root.right.left = new Node(12);
        root.left = new Node(6);
        root.left.left = new Node(4);
        root.left.right = new Node(7);
        root.left.left.left = new Node(2);
        root.left.left.right = new Node(5);
        traverseBST(root);
        System.out.println(traverseResult);
    }
}
