package com.slava.dp;

public class ChangeReturn {

  public boolean canChange(int amount, int[] coins) {

    int[][] dp = new int[coins.length + 1][amount + 1];
    for (int i = 1; i < coins.length + 1; i++) {
      dp[i][0] = coins[i];
    }
    for (int i = 0; i < amount + 1; i++) {
      dp[0][i] = i;
    }

    for (int iamount = 0; iamount < amount + 1; iamount++) {
      for (int icoins = 1; icoins < coins.length + 1; icoins++) {

      //  dp[icoins][iamount] = Integer.max(dp[icoins][iamount-1],dp
      }
    }

    return false;
  }
}
