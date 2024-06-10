package com.slava.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {
    static public List<Integer> readListFromFile(String pathname) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(pathname));
        List<Integer> result = new ArrayList<>();
        while (sc.hasNext()) {
            result.add(sc.nextInt());
        }
        return result;
    }

    static public int[] readArrayFromFile(String pathname) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(pathname));
        List<Integer> list = new ArrayList<>();
        while (sc.hasNext()) {
            list.add(sc.nextInt());
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
