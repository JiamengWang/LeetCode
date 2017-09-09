package com.company;

/**
 * Created by wjm-harry on 9/7/17.
 */
public class StringSolution {

    /**longest palidrone
     *
     * */
    public String longestPalindrome(String s) {
        if (s == null) {
            return "";
        } else if (s.length()<= 1) {
            return s;
        }
        int maxleft = 0;
        int maxright = 0;
        int maxlen = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == i) {
                    dp[i][j] = true;
                } else if (j == i - 1) {
                    dp[i][j] = s.charAt(j) == s.charAt(i);
                } else {
                    dp[i][j] = dp[i-1][j+1] && (s.charAt(i) == s.charAt(j));
                }
                if (i - j >= maxlen && dp[i][j]) {
                    maxlen = i - j + 1;
                    maxleft = j;
                    maxright = i;
                }
            }
        }
        return s.substring(maxleft,maxright+1);
    }
}
