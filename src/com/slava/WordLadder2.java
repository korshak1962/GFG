package com.slava;

import org.junit.Test;

import java.util.*;

//https://practice.geeksforgeeks.org/problems/word-ladder-ii/1
public class WordLadder2 {


    @Test
    public void simple0() {
        String startWord = "der", targetWord = "dfs";
        String[] wordList = {"des", "der", "dfr", "dgt", "dfs"};
        ArrayList<ArrayList<String>> res = findSequences(startWord, targetWord, wordList);
        System.out.println(res);
    }

    @Test
    public void simple() {
        String startWord = "jlj", targetWord = "jjk";
        String[] wordList = {"jlk","jlj", "kjk", "lll","ljk", "jjk"};
        ArrayList<ArrayList<String>> res = findSequences(startWord, targetWord, wordList);
        System.out.println(res);
    }

    ArrayList<ArrayList<String>> findSequences(String startWord, String targetWord, String[] wordListIn) {
        LinkedList<String> wordList = new LinkedList<>(Arrays.asList(wordListIn));
        boolean found = false;
        ArrayList<ArrayList<String>> currentDeques = new ArrayList<>();
        ArrayList<ArrayList<String>> nextDeques = new ArrayList<>();
        ArrayList<String> deque = new ArrayList<>();
        deque.add(startWord);
        currentDeques.add(deque);
        List<String> toBeRemoved = new LinkedList<>();
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        while (!wordList.isEmpty() || !found) {
            for (List<String> currentDeque : currentDeques) {
                for (String wordFromList : wordList) {
                    if (ifOnlyOneOrNoneCharDif(currentDeque.get(currentDeque.size()-1), wordFromList)) {
                        if (wordFromList.equals(targetWord)) {
                            found = true;
                            ArrayList<String> newDeque = new ArrayList<>(currentDeque);
                            newDeque.add(wordFromList);
                            result.add(newDeque);
                        }
                        ArrayList<String> newDeque = new ArrayList<>(currentDeque);
                        newDeque.add(wordFromList);
                        nextDeques.add(newDeque);
                        toBeRemoved.add(wordFromList);
                    }
                }
            }
            if (nextDeques.isEmpty()) return result;
            wordList.removeAll(toBeRemoved);
            toBeRemoved.clear();
            currentDeques = nextDeques;
            nextDeques = new ArrayList<>();
        }
        return result;
    }

    List<List<String>> findSequences(String startWord, String targetWord, LinkedList<String> wordList) {
        boolean found = false;
        ArrayList<List<String>> layers = new ArrayList<>();
        List<String> prevLayer = new LinkedList<>();
        prevLayer.add(startWord);
        layers.add(prevLayer);

        while (!wordList.isEmpty() || !found) {
            List<String> nextLayer = new LinkedList<>();
            for (String prevListWord : prevLayer) {
                for (String wordFromList : wordList) {
                    if (ifOnlyOneOrNoneCharDif(prevListWord, wordFromList)) {
                        if (wordFromList.equals(targetWord)) {
                            found = true;
                        }
                        nextLayer.add(wordFromList);
                    }
                }
            }
            if (nextLayer.isEmpty()) return layers;
            if (!found) layers.add(nextLayer);
            wordList.removeAll(nextLayer);
            prevLayer = nextLayer;
        }
        return layers;
    }

    boolean ifOnlyOneOrNoneCharDif(String word1, String word2) {
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
