package com.company;

/**
 * Created by wjm-harry on 8/26/17.
 */
public class DP {
    /**Given an array of positive integers representing a stock’s price on each day. On each day you can only make one operation: either buy or sell one unit of stock and you can make at most 1 transaction. Determine the maximum profit you can make.

     Assumptions

     The given array is not null and is length of at least 2.
     Examples

     {2, 3, 2, 1, 4, 5}, the maximum profit you can make is 5 - 1 = 4
     * */
    public int maxProfit(int[] array) {
        // write your solution here
        int profit = 0;
        int min = array[0];

        for (int i = 0; i < array.length; i++) {
            profit = Math.max(profit,array[i]-min);
            if (min > array[i]) {
                min = array[i];
            }
        }
        return profit;
    }


    /**
     * Given an array of positive integers representing a stock’s price on each day. On each day you can only make one operation: either buy or sell one unit of stock, you can make as many transactions you want, but at any time you can only hold at most one unit of stock. Determine the maximum profit you can make.

     Assumptions

     The give array is not null and is length of at least 2
     Examples

     {2, 3, 2, 1, 4, 5}, the maximum profit you can make is (3 - 2) + (5 - 1) = 5*/
    public int maxProfitII(int[] array) {
        // write your solution here
        int profit = 0;
        int min = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                profit += (array[i - 1] - min);
                min = array[i];
            }
        }

        if (array[array.length - 1] > min) {
            profit += (array[array.length - 1] - min);
        }
        return profit;
    }

    /**Given an array of positive integers representing a stock’s price on each day. On each day you can only make one operation: either buy or sell one unit of stock, at any time you can only hold at most one unit of stock, and you can make at most 2 transactions in total. Determine the maximum profit you can make.

     Assumptions

     The give array is not null and is length of at least 2
     Examples

     {2, 3, 2, 1, 4, 5, 2, 11}, the maximum profit you can make is (5 - 1) + (11 - 2) = 13*/

    //time out
    public int maxProfitIII(int[] array) {
        // write your solution here

        int len = array.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            int max = 0;
            for (int j = i + 1; j < len; j++) {
                max = Math.max(max,array[j]);
                dp[i][j] = (max - array[i]);
            }
        }
        int profit = 0;

        for (int i = 0;i < len; i++) {
            profit = Math.max(profit,dp[i][len - 1]);
            for (int j = i + 1; j < len - 1; j++) {
                profit = Math.max(dp[i][j]+dp[j + 1][len -1],profit);
            }
        }

        return profit;
    }

    public int maxProfitIIII(int[] array){
        int K = 2;
        int maxProf = 0;
        int[][] dp = new int[K + 1][array.length];
        for (int kk = 1; kk <= K; kk++) {
            int tempMax = dp[kk-1][0] - array[0];
            for (int ii = 1; ii < array.length; ii++) {
                dp[kk][ii] = Math.max(dp[kk][ii - 1],array[ii] + tempMax);
                tempMax = Math.max(tempMax,dp[kk - 1][ii] - array[ii]);
                maxProf = Math.max(dp[kk][ii],maxProf);
            }
        }
        return maxProf;
    }

}
