package com.company;
import java.util.*;
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

    /**Follow up for "Unique Paths":

     Now consider if some obstacles are added to the grids. How many unique paths would there be?

     An obstacle and empty space is marked as 1 and 0 respectively in the grid.

     For example,
     There is one obstacle in the middle of a 3x3 grid as illustrated below.

     [
     [0,0,0],
     [0,1,0],
     [0,0,0]
     ]
     The total number of unique paths is 2.
     * */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null) {
            return 0;
        }

        int x = obstacleGrid.length;
        if (x == 0) {
            return 0;
        }

        int y = obstacleGrid[0].length;
        if (y == 0) {
            return 0;
        }

        int[][] dp = new int[x][y];

        for (int i = x - 1; i >= 0; i--) {

            for (int j = y - 1; j >= 0;j--) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                if (i == x - 1 && j < y - 1) {
                    dp[i][j] = dp[i][j + 1];
                } else if (j == y - 1 && i < x - 1) {
                    dp[i][j] = dp[i + 1][j];
                } else if (i < x - 1 && j < y - 1) {
                    dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
                } else {
                    dp[i][j] = 1;
                }
            }
        }
        return dp[0][0];
    }

    /**You are climbing a stair case. It takes n steps to reach to the top.

     Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

     Note: Given n will be a positive integer.

     */

    // this is another fibonicca question
    public int climbStairs(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int prev = 1;
        int cur = 1;

        while (n > 1) {
            int sum = prev + cur;
            prev = cur;
            cur = sum;
            n--;
        }
        return cur;
    }

    /**Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

     You have the following 3 operations permitted on a word:

     a) Insert a character
     b) Delete a character
     c) Replace a character
     */

    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        int len1 = word1.length();
        int len2 = word2.length();

        if (len1 == 0 || len2 == 0) {
            return len1 == 0 ? len2 : len1;
        }

        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i= 0; i < len1 + 1; i++) {
            for (int j = 0; j < len2 + 1; j++) {

                if (i == 0 && j > 0) {
                    dp[i][j] = dp[i][j - 1] + 1;
                } else if (j == 0 && i > 0) {
                    dp[i][j] = dp[i - 1][j] + 1;
                } else if (i > 0 && j > 0) {
                    char c1 = word1.charAt(i - 1);
                    char c2 = word2.charAt(j - 1);
                    if (c1 == c2) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i][j - 1],Math.min(dp[i - 1][j - 1],dp[i -1][j])) + 1;
                    }
                }
            }
        }
        return dp[len1][len2];
    }

    /**Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

     For example, given the following triangle
     [
     [2],
     [3,4],
     [6,5,7],
     [4,1,8,3]
     ]
     The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

     Note:
     Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
     * */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null) {
            return 0;
        }
        ListIterator<List<Integer>> iter = triangle.listIterator();
        Integer[] out = minTotalDfs(iter,iter.next());
        return out[0];
    }

    private Integer[] minTotalDfs(ListIterator<List<Integer>> iter,List<Integer> cur) {
        if (!iter.hasNext()) {
            return cur.toArray(new Integer[0]);
        }

        Integer[] nextLevel = minTotalDfs(iter,iter.next());
        int index = 0;
        for (Integer i : cur) {
            nextLevel[index] = i + Math.min(nextLevel[index],nextLevel[index + 1]);//index < nextLevel.length
            index++;
        }

        return nextLevel;
    }


    /**Say you have an array for which the ith element is the price of a given stock on day i.

     If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

     Example 1:
     Input: [7, 1, 5, 3, 6, 4]
     Output: 5

     max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
     Example 2:
     Input: [7, 6, 4, 3, 1]
     Output: 0

     In this case, no transaction is done, i.e. max profit = 0.
     * */

    public int maxProfitI(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int[] dp = new int[prices.length];
        int min = Integer.MAX_VALUE;

        // dp[i] indicates the min val before index i
        for (int i = 0; i < dp.length; i++) {
            min = Math.min(min,prices[i]);
            dp[i] = min;
        }
        int maxProfit = 0;
        for (int i = 0; i < dp.length; i++) {
            maxProfit = Math.max(maxProfit,prices[i] - dp[i]);
        }
        return maxProfit;
    }

    /**Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.

     For example, given
     s = "leetcode",
     dict = ["leet", "code"].

     Return true because "leetcode" can be segmented as "leet code".*/
    public boolean wordBreakI(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return false;
        }
        Set<String> dict = wordDictSet(wordDict);
        char[] sa = s.toCharArray();
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j <= i;j++) {
                // System.out.println(dp[j - 1] + " " +new String(sa,j - 1,i - j + 1));
                if (dp[j - 1] && dict.contains(new String(sa,j - 1,i - j + 1))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[dp.length - 1];
    }

    private Set<String> wordDictSet(List<String> dic) {
        Set<String> out = new HashSet<>();
        for (String i : dic) {
            out.add(i);
        }
        return out;
    }

    /** Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. You may assume the dictionary does not contain duplicate words.

     Return all such possible sentences.

     For example, given
     s = "catsanddog",
     dict = ["cat", "cats", "and", "sand", "dog"].

     A solution is ["cats and dog", "cat sand dog"].
     * */
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> out = new ArrayList<>();

        if (s == null || s.length() == 0) {
            return out;
        }
        Set<String> dict = wordDictSet(wordDict);
        char[] sa = s.toCharArray();
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j <= i;j++) {
                // System.out.println(dp[j - 1] + " " +new String(sa,j - 1,i - j + 1));
                if (dp[j - 1] && dict.contains(new String(sa,j - 1,i - j + 1))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        if (!dp[dp.length - 1]) {
            return out;
        }

        wordBreakDfs(out,sa,dp,dp.length - 1,new ArrayList<String>());
        return  out;
    }

    private void wordBreakDfs(List<String> out, char[] sa,boolean[] dp, int anchor,List<String> temp) {
        if (anchor < 0) {
            StringBuilder str = new StringBuilder();
            ListIterator<String> iter = temp.listIterator();
            while(iter.hasNext()) {
                str.append(iter.next());
                if (iter.hasNext()) str.append(" ");
            }
            out.add(str.reverse().toString());
            return;
        }

        for (int i = anchor; i > 0; i--) {
            if (dp[i]) {
                temp.add(new String(sa,i - 1, anchor - i + 1));
                wordBreakDfs(out,sa,dp,i - 1,temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    /**
     * After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

     Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.*/

    public int rob(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        } else if (nums.length == 2) {
            return Math.max(nums[0],nums[1]);
        }
        int[] dp = new int[nums.length];
        int head = robHelper(nums,dp,0,dp.length - 2);
        int nohead = robHelper(nums,dp,1,dp.length - 1);
        return Math.max(head,nohead);
    }

    private int robHelper(int[] a, int[] dp,int b, int e) {
        dp[b] = a[b];
        dp[b + 1] = Math.max(a[b],a[b + 1]);
        for (int i = b + 2; i <= e; i++) {
            dp[i] = Math.max(a[i] + dp[i - 2],dp[i - 1]);
        }
        return dp[e];
    }

    /**Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
     * Given matrix = [
     [3, 0, 1, 4, 2],
     [5, 6, 3, 2, 1],
     [1, 2, 0, 1, 5],
     [4, 1, 0, 1, 7],
     [1, 0, 3, 0, 5]
     ]

     sumRegion(2, 1, 4, 3) -> 8
     sumRegion(1, 1, 2, 2) -> 11
     sumRegion(1, 2, 2, 4) -> 12
     * */

    class NumMatrix {
        private int[][] dp;

        public NumMatrix(int[][] matrix) {
            if (matrix == null) {
                dp = null;
                return;
            }

            int x = matrix.length;
            if (x == 0) {
                return;
            }

            int y = matrix[0].length;
            if (y == 0) {
                return;
            }

            dp = new int[x][y];
            initialize(dp,matrix);
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int i = row1; i <= row2; i++) {
                sum += dp[i][col2];
                if (col1 > 0) {
                    sum -= dp[i][col1 - 1];
                }
            }
            return sum;
        }

        private void initialize(int[][] dp,int[][] m) {
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++) {
                    if (j == 0) {
                        dp[i][j] = m[i][j];
                    } else {
                        dp[i][j] = m[i][j] + dp[i][j - 1];
                    }
                }
            }
        }
    }

    /**Write a program to find the n-th ugly number.

     Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

     Note that 1 is typically treated as an ugly number, and n does not exceed 1690.
     * */
    public int nthUglyNumber(int n) {
        if (n == 0) {
            return -1;
        }
        int[] dp = new int[n];
        dp[0]  = 1;

        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;

        for (int i = 1; i < n;i ++) {
            int min = Math.min(Math.min(factor2,factor3),factor5);
            dp[i] = min;
            if (min == factor2) {
                factor2 = 2 * dp[++index2];
            }
            if (min == factor3) {
                factor3 = 3 * dp[++index3];
            }
            if (min == factor5) {
                factor5 = 5 * dp[++index5];
            }
        }
        return dp[n - 1];
    }


    /**The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

     The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

     Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

     In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.


     Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

     For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.*/
    public int calculateMinimumHP(int[][] dungeon) {
        int x = dungeon.length;
        int y = dungeon[0].length;

        int[][] dp = new int[x][y];
        for (int i = x - 1; i >= 0; i--) {
            for (int j = y - 1; j >= 0; j--) {
                if (i == x - 1 && j == y - 1) {
                    dp[i][j] = dungeon[i][j];
                } else if (j == y - 1) {
                    dp[i][j] = dungeon[i][j] + dp[i + 1][j];
                } else if (i == x - 1) {
                    dp[i][j] = dungeon[i][j] + dp[i][j + 1];
                } else {
                    dp[i][j] = dungeon[i][j] + Math.min(dp[i + 1][j],dp[i][j + 1]);
                }
            }
        }
        return dp[0][0];
    }


    /**Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

     For example, given the following matrix:

     1 0 1 0 0
     1 0 1 1 1
     1 1 1 1 1
     1 0 0 1 0
     Return 4.*/
    public int maximalSquare(char[][] matrix) {
        if (matrix == null) {
            return 0;
        }

        int x = matrix.length;
        int y = matrix[0].length;
        int[][] dp = new int[x][y];
        int maxlen = 0;

        for (int i = 0; i < x; i++) {

            for (int j = 0; j < y; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 && j == 0) {
                        dp[i][j] = 1;
                    } else if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j] ,Math.min(dp[i - 1][j - 1],dp[i][j - 1])) + 1;
                    }
                    maxlen = Math.max(maxlen,dp[i][j]);
                }
            }
        }
        return maxlen * maxlen;

    }


    /**Given a string S and a string T, count the number of distinct subsequences of S which equals T.

     A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

     Here is an example:
     S = "rabbbit", T = "rabbit"

     Return 3.*/

    //two pointer sol,DFS
    // solution is correct, but timeout time complixity is O(m^2)
    public int numDistinctI(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return 0;
        }
        return numDistinctHelper(s,0,t,0);
    }

    private int numDistinctHelper(String s, int sindex, String t, int tindex) {
        if (tindex == t.length()) {
            return 1;
        } else if (sindex == s.length()) {
            return 0;
        }
        char tchar = t.charAt(tindex);
        int tempsindex = sindex;
        int out = 0;
        while (tempsindex < s.length()) {
            if (s.charAt(tempsindex) == tchar) {
                out += numDistinctHelper(s,tempsindex + 1,t,tindex + 1);
            }
            tempsindex++;
        }
        return out;
    }

    // dp solution
    public int numDistinct(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return 0;
        } else if (t.length() == 0) {
            return 1;
        }

        int[] premem = new int[s.length() + 1];
        for (int i = 0; i < premem.length; i++) {
            premem[i] = 1;
        }
        int[] curmem = new int[s.length() + 1];

        for (int i = 0; i < t.length(); i++) {
            char tempchar = t.charAt(i);
            for (int j = 0; j < s.length(); j++) {
                if (tempchar == s.charAt(j)) {
                    curmem[j + 1] = curmem[j] + premem[j];
                } else {
                    curmem[j + 1] = curmem[j];
                }
                premem[j] = curmem[j];
                curmem[j] = 0 ;
            }
            premem[premem.length - 1] = curmem[curmem.length - 1];
            curmem[curmem.length - 1] = 0;
        }
        return premem[premem.length - 1];
    }

}
