package com.slava.meta;

import java.util.Arrays;

//https://www.metacareers.com/profile/coding_practice_question/?problem_id=2903692913051025&c=655788137337556&psid=275492097255885&practice_plan=0&b=0111122
public class Change {
    boolean canGetExactChange(int targetMoney, int[] denominations) {
        Arrays.sort(denominations);
        boolean[] possibleChange = new boolean[targetMoney +1];
        for (int coin:denominations){
            if(coin <= targetMoney) possibleChange[coin] = true;
        }
        for (int index = denominations[0]; index < targetMoney + 1; index++){
            if (possibleChange[index]){
                for (int coin:denominations){
                    int newIndex = index + coin;
                    if (newIndex == targetMoney) {
                        return true;
                    }
                    if (newIndex < targetMoney){
                        possibleChange[newIndex] = true;
                    }
                }
            }
        }
        return false;
    }

}
