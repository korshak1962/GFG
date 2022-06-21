package com.slava;

import java.util.List;

public class TestClassTreeTest {

    @org.junit.Test
    public void stringToTreeTest(){
        TestClassTree tree=new TestClassTree(5);
        TestClassTree.Node root = tree.stringToTree("3 5 L 3 0 R  5 6 L 5 4 R");
        tree.printTree(root);
    }

    @org.junit.Test
    public void maxDiffTest(){
        TestClassTree tree=new TestClassTree(5);
        TestClassTree.Node root = tree.stringToTree("6 14 L 6 10 R  14 3 L 14 13 R  10 8 L 10 5 R  3 1 L");
        tree.printTree(root);
        int res=tree.maxDiff(root);
        System.out.println("res="+res);
    }

    @org.junit.Test
    public void treeTraverse(){
        TestClassTree tree=new TestClassTree(5);
        String str = "54 92 L 54 9 R  92 4 L 92 41 R  9 26 L 9 63 R";
        TestClassTree.Node root = tree.stringToTree(str);
        tree.printTree(root);
      //  List<TestClassTree.Node> nodes=TestClassTree.links(root);
      //  System.out.println("nodes="+nodes);
        TestClassTree.populateNext(root);
        while(root.next!=null){
            System.out.println(" "+root);
            root=root.next;
        }
    }

}
