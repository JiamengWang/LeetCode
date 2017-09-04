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


    /** cutting wood
     *
     * There is a wooden stick with length L >= 1, we need to cut it into pieces, where the cutting positions are defined in an int array A. The positions are guaranteed to be in ascending order in the range of [1, L - 1]. The cost of each cut is the length of the stick segment being cut. Determine the minimum total cost to cut the stick into the defined pieces.

     Examples

     L = 10, A = {2, 4, 7}, the minimum total cost is 10 + 4 + 6 = 20 (cut at 4 first then cut at 2 and cut at 7)
     * */
    public int minCost(int[] cuts, int length) {
        // Write your solution here.
        if (cuts == null || cuts.length <= 1) {
            return length;
        }

        int[] dp = new int[cuts.length + 2];
        dp[0] = 0;
        dp[dp.length - 1] = length;
        for (int i = 0; i < cuts.length; i++) {
            dp[i + 1] = cuts[i];
        }

        int[][] mincost = new int[dp.length][dp.length];
        for (int i = 1; i < dp.length;i++) {
            for(int j = i - 1; j >= 0; j--) {
                if (j + 1 == i) {
                    mincost[j][i] = 0;
                } else {
                    mincost[j][i] = Integer.MAX_VALUE;
                    for (int k = j + 1;k <= i; k++) {
                        mincost[j][i] = Math.min(mincost[j][i],mincost[j][k] + mincost[k][i]);
                    }
                    mincost[j][i] += dp[i] - dp[j];
                }
            }
        }
        return mincost[0][dp.length - 1];
    }


}
