package com.slava.meta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;
import org.junit.Test;

public class WordBreak {
  public boolean wordBreak(String s, List<String> wordDict) {
    Trie trie = new Trie();
    for (String str : wordDict) {
      trie.add(trie, str, 0);
    }
    NavigableSet<Integer> positions = new TreeSet<>();
    positions.add(0);
    while (!positions.isEmpty()) {
      int minPosition = positions.pollFirst();
      if (minPosition == s.length()) {
        return true;
      }
      List<String> matched = trie.getMatched(trie, s, minPosition);
      for (String str : matched) {
        positions.add(minPosition + str.length());
      }
    }
    return false;
  }

  class Trie {
    Map<Character, Trie> charToNode = new HashMap<>();
    boolean isEndOfWord = false;

    void add(Trie trie, String str, int strIndex) {
      if (strIndex == str.length()) {
        return;
      }
      if (strIndex == str.length() - 1) {
        trie.isEndOfWord = true;
      }
      Character ch = str.charAt(strIndex);
      Trie child = trie.charToNode.get(ch);
      if (child != null) {
        add(child, str, strIndex + 1);
      } else {
        Trie newChild = new Trie();
        trie.charToNode.put(ch, newChild);
        add(newChild, str, strIndex + 1);
      }
    }

    List<String> result = new ArrayList<>();
    int startIndex;

    List<String> getMatched(Trie trie, String str, int strIndex) {
      result.clear();
      startIndex = strIndex;
      getByPrefix(trie, str, strIndex);
      return result;
    }

    void getByPrefix(Trie trie, String str, int strIndex) {
      if (strIndex == str.length()) {
        return;
      }
      char key = str.charAt(strIndex);
      Trie child = trie.charToNode.get(key);
      if (child != null) {
        if (trie.isEndOfWord) {
          result.add(str.substring(startIndex, strIndex + 1));
        }
        if (child.isEndOfWord) {
          result.add(str.substring(startIndex, strIndex + 2));
        }
        getByPrefix(child, str, strIndex + 1);
      }
    }
  }

  @Test
  public void test() {
    String str = "abcggg";
    List<String> wordDict = List.of("abc", "ggg");
    System.out.println(wordBreak(str, wordDict));
  }

  @Test
  public void test1() {
    String str = "leetcode";
    List<String> wordDict = List.of("leet", "code");
    System.out.println(wordBreak(str, wordDict));
  }

  @Test
  public void testa() {
    String str = "a";
    List<String> wordDict = List.of("a", "code");
    System.out.println(wordBreak(str, wordDict));
  }

  @Test
  public void testA() {
    Trie trie = new Trie();
    trie.add(trie, "a", 0);
    System.out.println(trie);
  }

  @Test
  public void testGG() {
    List<String> wordDict = List.of("abc", "ggg");
    Trie trie = new Trie();
    trie.add(trie, "ggg", 0);
    System.out.println(trie);
  }
}
