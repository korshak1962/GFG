package com.slava;

import org.junit.Test;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Trie {

    @Test
    public void test() {
        SortedMap<String, String> nameNum = new TreeMap<String, String>();
        nameNum.put("aaa", "vaaa");
        nameNum.put("abbb", "vabbb");
        nameNum.put("accc", "vaccc");
        nameNum.put("ccc", "vccc");
// put your phone numbers
        String prefix = "a";
        for (Map.Entry<String, String> entry : filterPrefix(nameNum, prefix).entrySet()) {
            System.out.println(entry);
        }
        for (char c='a';c<='z';c++){
            System.out.println((int)c);
        }
    }

    public <V> SortedMap<String, V> filterPrefix(SortedMap<String, V> baseMap, String prefix) {
        if (prefix.length() > 0) {
            char nextLetter = (char) (prefix.charAt(prefix.length() - 1) + 1);
            String end = prefix.substring(0, prefix.length() - 1) + nextLetter;
            return baseMap.subMap(prefix, end);
        }
        return baseMap;
    }

}
