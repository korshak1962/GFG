package com.slava;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//https://practice.geeksforgeeks.org/problems/word-ladder/1/?problemStatus=unsolved&problemType=functional&difficulty[]=2&page=1&sortBy=submissions&query=problemStatusunsolvedproblemTypefunctionaldifficulty[]2page1sortBysubmissions
public class WordLadder {

    @Test
    public void testFind2() {
        String[] wordList = {"geek", "gefk"};
        String startWord = "gedk", targetWord = "geek";
        Assert.assertEquals(2, wordLadderLength(startWord, targetWord, wordList));
    }

    @Test
    public void testNotFind3() {
        String[] wordList = {"des", "der", "dfr", "dgt", "dfs"};
        String startWord = "der", targetWord = "dfs";
        Assert.assertEquals(3, wordLadderLength(startWord, targetWord, wordList));
    }

    @Test
    public void testNotFind7() {
        String[] wordList = {"poon", "plee", "same", "poie", "plea", "plie", "poin"};
        String startWord = "toon", targetWord = "plea";
        Assert.assertEquals(7, wordLadderLength(startWord, targetWord, wordList));
    }

    @Test
    public void testNotFind83() {
        String[] wordList = {"hot",
                "cog",
                "dog",
                "tot",
                "hog",
                "hop",
                "pot",
                "dot"};
        String startWord = "hot", targetWord = "dog";
        Assert.assertEquals(3, wordLadderLength(startWord, targetWord, wordList));
    }

    public int wordLadderLength(String startWord, String targetWord, String[] wordList) {
        int counter = 2;
        if (startWord.equals(targetWord)) return --counter;
        List<String> listWords = new LinkedList<>(Arrays.asList(wordList));
        List<String> listWordsCurrentLayer = new LinkedList<>();
        List<String> listWordsNextLayer = new LinkedList<>();
        for (String wordFromList : listWords) {
            if (ifOnlyOneCharDif(startWord, wordFromList)) {
                if (wordFromList.equals(targetWord)) return counter;
                listWordsCurrentLayer.add(wordFromList);
            }
        }
        listWords.removeAll(listWordsCurrentLayer);
        while (!listWordsCurrentLayer.isEmpty()) {
            for (String wordFromList : listWords) {
                for (String currentWord : listWordsCurrentLayer) {
                    if (ifOnlyOneCharDif(wordFromList, currentWord)) {
                        listWordsNextLayer.add(wordFromList);
                        if (wordFromList.equals(targetWord)) return ++counter;
                    }
                }
            }
            listWordsCurrentLayer = listWordsNextLayer;
            listWordsNextLayer = new LinkedList<>();
            counter++;
            listWords.removeAll(listWordsCurrentLayer);
        }
        return 0;
    }

    boolean ifOnlyOneCharDif(String word1, String word2) {
        int difCounter = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                difCounter++;
                if (difCounter > 1) return false;
            }
        }
        return true;
    }
}
