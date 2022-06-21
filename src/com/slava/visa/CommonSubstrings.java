package com.slava.visa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommonSubstrings {


    public static void commonSubstring(List<String> a, List<String> b) {
        for (int i = 0; i < a.size(); i++) {
            Set<Character> charsA = new HashSet<>();
            for (char chA : a.get(i).toCharArray()) {
                charsA.add(chA);
            }
            boolean yes = false;
            for (char chB : b.get(i).toCharArray()) {
                if (charsA.contains(chB)) {
                    System.out.println("YES");
                    yes = true;
                    break;
                }
            }
            if (!yes) {
                System.out.println("NO");
            }
        }
    }

}
