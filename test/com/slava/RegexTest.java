package com.slava;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    @Test
    public void getClosestTest() {
        Pattern p = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = p.matcher("wertyu(dfgh) (dfg)");
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }
}
