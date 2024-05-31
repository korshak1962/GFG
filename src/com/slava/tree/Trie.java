package com.slava.tree;

import org.junit.Test;
//https://www.geeksforgeeks.org/problems/trie-insert-and-search0651/1
public class Trie {

    static final int ALPHABET_SIZE = 26;

    // trie node
    static class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];

        // isEndOfWord is true if the node represents
        // end of a word
        boolean isEndOfWord;

        TrieNode() {
            isEndOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; i++) children[i] = null;
        }


        //Function to insert string into TRIE.
        static void insert(TrieNode root, String key) {
            if (key == null || key.isEmpty()) {
                root.isEndOfWord = true;
                return;
            }
            if (root.children[key.charAt(0) - 'a'] == null) {
                TrieNode newNode = new TrieNode();
                root.children[key.charAt(0) - 'a'] = newNode;
                insert(newNode, key.substring(1));
            } else {
                insert(root.children[key.charAt(0) - 'a'], key.substring(1));
            }
        }

        //Function to use TRIE data structure and search the given string.
        static boolean search(TrieNode root, String key) {
            if ((key == null || key.isEmpty())) {
                return root.isEndOfWord;
            }
            if (root.children[key.charAt(0) - 'a'] != null) {
                return search(root.children[key.charAt(0) - 'a'], key.substring(1));
            }
            return false;
        }
    }

    @Test
    public void test1() {
        String list[] = {"the", "a", "there", "answer", "any", "by", "bye", "their", "org " };
        TrieNode root = new TrieNode();
        for (String s : list) {
            TrieNode.insert(root, s);
        }
        System.out.println(TrieNode.search(root, list[3]));
        System.out.println(TrieNode.search(root, "notPresent"));

    }

    @Test
    public void test2() {
        String list[] = {"org" };
        TrieNode root = new TrieNode();
        for (String s : list) {
            TrieNode.insert(root, s);
        }
        System.out.println(TrieNode.search(root, "or"));
    }
}
