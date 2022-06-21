package com.slava.tree.bst.dfs_bst;

import com.slava.lists.LinkableLinkedList;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CorrectBST {

    //https://practice.geeksforgeeks.org/problems/fixed-two-nodes-of-a-bst/1/?problemType=functional&difficulty[]=2&page=1&sortBy=submissions&query=problemTypefunctionaldifficulty[]2page1sortBysubmissions

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }

    @Test
    public void testBoth() {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(8);
        root.left.left = new Node(2);
        root.left.right = new Node(20);
        root = correctBST(root);
        Assert.assertEquals(true, isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    //93 61 87 9
    @Test
    public void test2() {
        Node root = new Node(93);
        root.left = new Node(61);
        root.right = new Node(87);
        root.left.left = new Node(9);
        root = correctBST(root);
        System.out.println(root.data);
        Assert.assertEquals(true, isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void test3() {
        String str = "2 1 83 N 3 47 96 4 N 6";
        Node root = buildTree(str);
        root = correctBST(root);
        Assert.assertEquals(true, isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    public Node correctBST(Node root) {
        LinkableLinkedList<Node> linkableLinkedList = getIncreasedListFromBST(root);
        final List<Node> list = linkableLinkedList.getList();
        //System.out.println(list);
        sortListWithTwoWrongs(list);
        //System.out.println(list);
        return root;
    }

    public LinkableLinkedList<Node> getIncreasedListFromBST(Node root) {
        //condition of exit
        if (root == null) return null;
        if (root.left == null && root.right == null) {
            LinkableLinkedList<Node> increasedList = new LinkableLinkedList<>(root);
            return increasedList;
        }
        // traversal in order
        LinkableLinkedList<Node> increasedListFromLeft = getIncreasedListFromBST(root.left);
        if (increasedListFromLeft != null) increasedListFromLeft.add(root);
        else increasedListFromLeft = new LinkableLinkedList<>(root);
        final LinkableLinkedList<Node> increasedListFromRight = getIncreasedListFromBST(root.right);
        return LinkableLinkedList.linkTwoLists(increasedListFromLeft, increasedListFromRight);
    }

    public void sortListWithTwoWrongs(List<Node> lst) {
        Node first = null;
        Node last = null;
        for (int i = 0; i < lst.size() - 1; i++) {
            if (lst.get(i + 1).data < lst.get(i).data) {
                first = lst.get(i);
                for (int j = lst.size() - 1; j > i; j--) {
                    if (lst.get(j).data < lst.get(j - 1).data) {
                        last = lst.get(j);
                        swapData(first, last);
                        return;
                    }
                }
            }
        }

    }

    private void swapData(Node node1, Node node2) {
        int temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }

    //=============================

    List<Node> wrongNodesLeft = new ArrayList<>();
    List<Node> wrongNodesRight = new ArrayList<>();

    public Node correctBST1(Node root) {
        checkBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (wrongNodesLeft.size() == 1 && wrongNodesRight.size() == 1)
            swapData(wrongNodesRight.get(0), wrongNodesLeft.get(0));
        if (wrongNodesLeft.isEmpty()) { // right side wrong
            Collections.sort(wrongNodesRight, (n1, n2) -> n1.data - n2.data);
            Node nodeToSwap = wrongNodesRight.get(0);

        }
        return root;
    }



    private void checkBst(Node node, int min, int max) {
        if (node == null) return;
        if (node.data > max) wrongNodesLeft.add(node);
        if (node.data < min) wrongNodesRight.add(node);
        if (node.left != null) {
            checkBst(node.left, min, Integer.min(node.data, max) - 1);
        }
        if (node.right != null) {
            checkBst(node.right, Integer.max(node.data, min) + 1, max);
        }
    }


    static Node buildTree(String str) {

        if (str.length() == 0 || str.charAt(0) == 'N') {
            return null;
        }

        String ip[] = str.split(" ");
        // Create the root of the tree
        Node root = new Node(Integer.parseInt(ip[0]));
        // Push the root to the queue

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);
        // Starting from the second element

        int i = 1;
        while (queue.size() > 0 && i < ip.length) {

            // Get and remove the front of the queue
            Node currNode = queue.peek();
            queue.remove();

            // Get the current node's value from the string
            String currVal = ip[i];

            // If the left child is not null
            if (!currVal.equals("N")) {

                // Create the left child for the current node
                currNode.left = new Node(Integer.parseInt(currVal));
                // Push it to the queue
                queue.add(currNode.left);
            }

            // For the right child
            i++;
            if (i >= ip.length)
                break;

            currVal = ip[i];

            // If the right child is not null
            if (!currVal.equals("N")) {

                // Create the right child for the current node
                currNode.right = new Node(Integer.parseInt(currVal));

                // Push it to the queue
                queue.add(currNode.right);
            }
            i++;
        }

        return root;
    }

    //    static void printInorder(Node root)
    //    {
    //        if(root == null)
    //            return;
    //
    //        printInorder(root.left);
    //        System.out.print(root.data+" ");
    //
    //        printInorder(root.right);
    //    }
    //
    public static boolean isBST(Node n, int lower, int upper) {
        if (n == null)
            return true;
        if (n.data <= lower || n.data >= upper)
            return false;
        return (isBST(n.left, lower, n.data) && isBST(n.right, n.data, upper));
    }
    //
    //    public static boolean compare( Node a, Node b, ArrayList<pair> mismatch )
    //    {
    //        if( a==null && b==null ) return true;
    //        if( a==null || b==null ) return false;
    //
    //        if( a.data != b.data )
    //            {
    //                pair temp = new pair(a.data,b.data);
    //                mismatch.add(temp);
    //            }
    //
    //
    //        return ( compare( a.left, b.left, mismatch ) && compare( a.right, b.right, mismatch ) );
    //    }
    //
    //	public static void main (String[] args) throws IOException
    //	{
    //	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //
    //	        int t=Integer.parseInt(br.readLine());
    //
    //	        while(t-- > 0)
    //	        {
    //	            String s = br.readLine();
    //    	    	Node root = buildTree(s);
    //    	    	Node duplicate = buildTree(s);
    //
    //                Sol g = new Sol();
    //
    //        		root = g.correctBST(root);
    //
    //        		 // check 1: is tree now a BST
    //                if(isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE) == false)
    //                {
    //                    System.out.println(0);
    //                    continue;
    //                }
    //
    //                // check 2: comparing with duplicate tree
    //
    //
    //                ArrayList<pair> mismatch = new ArrayList<pair>();
    //                // an arraylist to store data of mismatching nodes
    //
    //                if( compare( root, duplicate, mismatch) == false)
    //                {
    //                    // false output from this function indicates change in structure of tree
    //                    System.out.println(0);
    //                    continue;
    //                }
    //
    //                // finally, analysing the mismatching nodes
    //                if( mismatch.size() !=2 || mismatch.get(0).first!=mismatch.get(1).second || mismatch.get(0).second!=mismatch.get(1).first )
    //                    System.out.println(0);
    //                else
    //                    System.out.println(1);
    //
    //
    //
    //            }
    //    }
    //}
    //
    //// } Driver Code Ends
    //

    @Test
    public void testArr() {
        int arr[] = {1, 4, 3, 2, 6, 12, 13, 14};
        sortByOneSwap(arr);
        System.out.println(Arrays.toString(arr));
    }


    static void sortByOneSwap(int arr[]) {
        // Traverse the given array
        // from rightmost side

        for (int i = arr.length - 1; i > 0; i--) {
            // Check if arr[i]
            // is not in order
            if (arr[i] < arr[i - 1]) {
                // Find the other element
                // to be swapped with arr[i]
                int j = i - 1;
                while (j >= 0 && arr[i] < arr[j]) j--;
                // Swap the pair
                int temp = arr[i];
                arr[i] = arr[j + 1];
                arr[j + 1] = temp;
                break;
            }
        }
    }
}