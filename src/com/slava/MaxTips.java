package com.slava;

import java.util.*;

public class MaxTips {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        for (int it = 0; it < nTest; it++) {
            nTotal = sc.nextInt();
            nA = sc.nextInt();
            aList = new LinkedList<>();
            nB = sc.nextInt();
            bList = new LinkedList<>();
            for (int i = 0; i < nTotal; i++) {
                aList.add(sc.nextInt());
            }
            for (int i = 0; i < nTotal; i++) {
                bList.add(sc.nextInt());
            }
            System.out.println(getMaxTipsSum());
        }
        sc.close();
    }

    static List<Integer> aList;
    static int nTotal;
    static int nA;
    static int nB;
    static List<Integer> bList;

    static class GainAndIndex {
        int index;
        int gain;

        int getGain() {
            return gain;
        }

        GainAndIndex(int gain, int index) {
            this.gain = gain;
            this.index = index;
        }
    }

    static int getMaxTipsSum() {
        List<GainAndIndex> aGains = new LinkedList<>();
        for (int i = 0; i < aList.size(); i++) {
            aGains.add(new GainAndIndex(aList.get(i) - bList.get(i), i));
        }
        Collections.sort(aGains, Comparator.comparingInt(GainAndIndex::getGain).reversed());
        return getMaxTips(new LinkedList(aGains.subList(0, nA)), new LinkedList(aGains.subList(aGains.size() - nB, aGains.size())));
    }

    static int getMaxTips(List<GainAndIndex> aAllowedGains, List<GainAndIndex> bAllowedGains) {
        List<Integer> insexesA = new LinkedList<>();
        List<Integer> insexesB = new LinkedList<>();
        for (int i = 0; i < nTotal; i++) {
            if (aAllowedGains.isEmpty()) {
                insexesB.add(bAllowedGains.remove(bAllowedGains.size() - 1).index);
                continue;
            }
            if (bAllowedGains.isEmpty()) {
                insexesA.add(aAllowedGains.remove(0).index);
                continue;
            }
            GainAndIndex a = aAllowedGains.get(0);
            GainAndIndex b = bAllowedGains.get(bAllowedGains.size() - 1);
            if (a.getGain() >= -b.getGain()) {
                insexesA.add(aAllowedGains.remove(0).index);
                continue;
            } else {
                insexesB.add(bAllowedGains.remove(bAllowedGains.size() - 1).index);
            }
        }
        int sum = 0;
        for (int index : insexesA) {
            sum += aList.get(index);
        }
        for (int index : insexesB) {
            sum += bList.get(index);
        }
        return sum;
    }

}
