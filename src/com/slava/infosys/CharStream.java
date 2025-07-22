package com.slava.infosys;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

public class CharStream {

  public String FirstNonRepeating(String s) {
    Set<Character> repeating = new HashSet<>();
    LinkedHashSet<Character> nonRepeating =
        new LinkedHashSet<>();
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if (repeating.contains(s.charAt(i))) {
        builder.append(builder.charAt(builder.length() - 1));
        continue;
      }
      if (nonRepeating.contains(s.charAt(i))) {
        repeating.add(s.charAt(i));
        nonRepeating.remove(s.charAt(i));
        if (nonRepeating.isEmpty()) {
          builder.append("#");
        }
      } else {
        nonRepeating.add(s.charAt(i));
        builder.append(nonRepeating.getFirst());
      }
    }
    return builder.toString();
  }

  @Test
  public void test() {
    String s = "aabc";
    String out = FirstNonRepeating(s);
    Assert.assertEquals("a#bb", out);
  }

  @Test
  public void test1() {
    String s = "yewaahkpuo";
    String out = FirstNonRepeating(s);
    Assert.assertEquals("yyyyyyyyyy", out);;
  }
}
