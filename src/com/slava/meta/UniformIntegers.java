package com.slava.meta;

import org.junit.Assert;
import org.junit.Test;

import java.util.NavigableSet;
import java.util.TreeSet;
//https://www.metacareers.com/profile/coding_puzzles?puzzle=228269118726856&source=career_site_login_panel_sign_up_button
public class UniformIntegers {

    public int getUniformIntegerCountInInterval(long A, long B) {
        NavigableSet<Long> set = new TreeSet<>();
        for (int i = 1;i <= 9;i++){
            StringBuilder builder = new StringBuilder();
            for (int k=1;k<=12;k++){
                builder.append(i);
                set.add( Long.valueOf(builder.toString()));
            }
        }
        return set.subSet(A,true,B,true).size();
    }

    @Test
    public void test(){
      int res =  getUniformIntegerCountInInterval(75,300);
        Assert.assertEquals(5,res);
    }
}
