package com.slava.zaonecode;

import org.junit.Test;

import java.util.*;

public class LargestComponent {

    Set<String> set0 = Set.of("s11", "s21","qw");
    Set<String> set1 = Set.of("s1", "s2");
    Set<String> set2 = Set.of("s3");
    Comparator<String> comp = (String::compareTo);
    Comparator<Integer> compI = Integer::compare;
    public Comparator<Set<String>> getComp1() {
        return comp1;
    }

    public Comparator<String> getComp() {
        return comp;
    }

    Comparator<Set<String>> comp1 = ((s1, s2)->s1.size()-s2.size());
    //comp1.reversed()
    @Test
    public void test() {
        PriorityQueue<Set<String>> queue=new PriorityQueue<>(comp1);
        queue.remove(set1);
        queue.add(set1);
        queue.add(set2);
        queue.add(set0);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

    @Test
    public void testMap() {
        Map<Integer,Integer> intToInt=new TreeMap<>();
        Collections.sort(new ArrayList<>(intToInt.entrySet()),
                Comparator.comparingInt(Map.Entry::getValue));
    }

}


