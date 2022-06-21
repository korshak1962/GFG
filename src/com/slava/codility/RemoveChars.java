package com.slava.codility;

//https://app.codility.com/demo/results/trainingD44PMB-F7A/
//https://www.youtube.com/watch?v=b1PKlHIJ8Co&list=PL8R-T3NPRx5jrW8yQHryKVyGRKEK3vU2s&index=3
public class RemoveChars {

    public static void main(String[] args) {
        String S = "ABCBBCBA";
        System.out.println(solution(S));
        //  S = "accab";
        //   System.out.println(solution(S));

    }


    public static String solution(String S) {
        boolean toDelete[] = new boolean[S.length()];
        char next, prev;
        int iPrev = 0;
        int iNext = 1;
        while (iNext < S.length()) {
            prev = S.charAt(iPrev);
            next = S.charAt(iNext);
            while (next == prev && iNext < S.length()) {
                toDelete[iPrev] = true;
                toDelete[iNext] = true;
                while (toDelete[iPrev] == true && iPrev > 0) {
                    iPrev--;
                }
                if (iPrev == 0 && toDelete[iPrev] == true) {
                    if (toDelete[iNext] == true) {
                        iNext++;
                    }
                    iPrev = iNext;
                }
                iNext++;
                if (!(iNext < S.length())) {
                    break;
                }
                prev = S.charAt(iPrev);
                next = S.charAt(iNext);
            }
            iPrev = iNext;
            iNext++;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < toDelete.length; i++) {
    //        System.out.println(i + " " + toDelete[i]);
            if (!toDelete[i]) sb.append(S.charAt(i));
        }
  //      System.out.println("res= " + sb.toString());
        return sb.toString();
    }

}
