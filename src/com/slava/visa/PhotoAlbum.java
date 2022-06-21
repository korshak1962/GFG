package com.slava.visa;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/discuss/interview-question/502092/VISA-or-OA-2020-or-Photo-Album
public class PhotoAlbum {

    public static int[] photoAlbum(int[] indexes, int[] identities) {
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < indexes.length; i++) {
            result.add(indexes[i], identities[i]);
        }
        return result.stream().mapToInt(i->i).toArray();
    }

    @Test
    public void testfindMax() {
        int[] indexes = {0, 1, 2, 1, 2};
        int[] identities = {0, 1, 2, 3, 4};
        int[] res = photoAlbum(indexes, identities);
        System.out.println(Arrays.toString(res));
        //Assert.assertEquals(4, res);
    }


}
