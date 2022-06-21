package com.slava.microFocus;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InterView_2 {




    @Test
    public void test() {
        Map<String, List<String>> nameToPhove = new HashMap<>();
        nameToPhove.put("S", Arrays.asList("123","23"));
        nameToPhove.put("G", Arrays.asList("456","78"));
        List<String> phsF=nameToPhove.values().stream().
                flatMap(phs->phs.stream()).collect(Collectors.toList());
        System.out.println(phsF);
        List<List<String>> phsFF=nameToPhove.values().stream().map(s->s.stream().map(s1->s1+"a").collect(Collectors.toList())).collect(Collectors.toList());
        System.out.println(phsFF);
    }
}
