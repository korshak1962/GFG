package com.slava.visa;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

//https://leetcode.com/discuss/interview-question/507545/Visa-or-OA-2020-or-Distinct-Characters-Count
public class DistinctCharCount {

    static class CharactersCount {
        int distinctCharacterCount;
        String name;

        public CharactersCount(int distinctCharacterCount, String name) {
            this.distinctCharacterCount = distinctCharacterCount;
            this.name = name;
        }
    }

    static class Filter {
        Predicate<String> nameStartingWithPrefix(String prefix) {
            return name -> name.startsWith(prefix);
        }
    }

    static class Mapper {
        Function<String, CharactersCount> getDiscintCharactersCount() {
            return name -> {
                Set<Character> characterSet = new HashSet<>();
                for (Character c : name.toCharArray()) {
                    characterSet.add(c);
                }
                return new CharactersCount(characterSet.size(), name);
            };
        }
    }

}

