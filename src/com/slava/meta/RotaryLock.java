package com.slava.meta;

//https://www.metacareers.com/profile/coding_puzzles?puzzle=990060915068194&source=career_site_login_panel_sign_up_button
public class RotaryLock {
    public long getMinCodeEntryTime(int N, int M, int[] C) {
        // Calculate initial distance from 1 to first code number
        long res = Math.min(C[0] - 1, N - (C[0] - 1));

        // Calculate distance between consecutive code numbers
        for (int i = 1; i < M; i++) {
            int clockwise = Math.abs(C[i] - C[i-1]);
            int counterclockwise = N - clockwise;
            res += Math.min(clockwise, counterclockwise);
        }
        return res;
    }
}
