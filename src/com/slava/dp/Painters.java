package com.slava.dp;

import org.junit.Test;

import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//https://practice.geeksforgeeks.org/problems/the-painters-partition-problem1535/1
public class Painters {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        List<Integer> boards = new LinkedList<>();
        for (int it = 0; it < nTest; it++) {
            int nPainters = sc.nextInt();
            int nBoards = sc.nextInt();
            boards.clear();
            for (int i = 0; i < nBoards; i++) {
                boards.add(sc.nextInt());
            }
            System.out.println(nBoards);
            int[] Boards = new int[nBoards];
            int i = 0;
            for (Integer integ : boards) {
                Boards[i] = integ;
                i++;
            }
            System.out.println(maxWork(Boards, nPainters));
        }
        sc.close();
    }

    public static int maxWork(int Boards[], int nPaint) {
        int nBoards = Boards.length;
        int[][] dp = new int[nBoards][nPaint];
        for (int j = 0; j < nPaint; j++) {
            dp[0][j] = Boards[0];
        }
        for (int i = 1; i < nBoards; i++) {
            dp[i][0] = dp[i - 1][0] + Boards[i];
        }
        for (int j = 1; j < nPaint; j++) {
            for (int i = 1; i < nBoards; i++) {
               // int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < i; k++) {
                    int max = Integer.max(dp[k][j-1], dp[i][0] - dp[k][0]);
                    //max = Integer.max(max1, max);
                    min = Integer.min(min, max);
                }
                dp[i][j] = min;
            }
        }
        return dp[nBoards - 1][nPaint - 1];
    }

    @Test
    public void maxWork(){
        int[] Boards={10,10,10,10};
        int nPainters=2;
        System.out.println(maxWork(Boards, nPainters));
    }

    @Test
    public void qqq(){
        int  a=1;
        int b=a++;
        int c=++b;
        System.out.println(" "+a+" "+b+" "+c);
int aa=1<<2;
        System.out.println(" "+a);
        char z=0x20;
        int zz=1;
        System.out.println(z+zz);

        byte bb=Byte.MAX_VALUE;
        System.out.println(bb);
if(bb==-0x90){}

        System.out.println(0x90);

String str=null;

//if (str.equals("1")){}

        List<Integer> lst=new LinkedList();
        lst.add(10);lst.add(20);lst.add(30);lst.add(40);
lst.remove(3);
        System.out.println(lst);


     //   HttpURLConnection co;HttpURLConnection.HTTP_REQ_TOO_LONG
      //  co.getResponseCode()

    }

}
