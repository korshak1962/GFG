package com.slava.infosys;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CharStream {

    public String FirstNonRepeating(String s) {
        int i =0;
        Set<Character> repeating = new HashSet<>();
        Map<Character,Integer> nonRepeating = new HashMap<>();
        StringBuilder builder  = new StringBuilder();

        for (int i=0;i<s.length();i++){
            if (repeating.contains(s.charAt(i))) {continue;

            }
            if (nonRepeating.add(s.charAt(i))==null)){
                repeating.add(s.charAt(i));
                nonRepeating.remove(s.charAt(i));
                if (nonRepeating.isEmpty()){
                    hashInd.add(i);
                }
            }
        }
    }


}
