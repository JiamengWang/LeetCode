package com.company;
import java.util.*;

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

    /**Given a string s, partition s such that every substring of the partition is a palindrome.

     Return all possible palindrome partitioning of s.

     For example, given s = "aab",
     Return
     * */
    public List<List<String>> partition(String s) {
        List<List<String>> out = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return out;
        }

        int len = s.length();

        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {

            for (int j = 0; j <= i; j++) {
                if (j == i) {
                    dp[j][i] = true;
                } else if(j == i - 1){
                    dp[j][i] = s.charAt(j) == s.charAt(i);
                } else {
                    dp[j][i] = dp[j + 1][i - 1] && (s.charAt(j) == s.charAt(i));
                }
            }
        }
        PalindromePartationHelper(out,new ArrayList<String>(),s.toCharArray(),0,dp);
        return out;
    }

    private void PalindromePartationHelper(List<List<String>> out, List<String> t,char[] s, int index, boolean[][] dp) {
        if (index >= s.length) {
            out.add(new ArrayList<String>(t));
            return;
        }

        for (int i = index; i < s.length; i++) {
            if (dp[index][i]) {
                t.add(new String(s,index, i - index + 1));
                PalindromePartationHelper(out,t,s,i + 1,dp);
                t.remove(t.size() - 1);
            }
        }
    }

    /**Given a string s, partition s such that every substring of the partition is a palindrome.

     Return the minimum cuts needed for a palindrome partitioning of s.

     For example, given s = "aab",
     Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
     * */

    // this solution timed out
//    public int minCut(String s) {
//        if (s == null || s.length() <= 1) {
//            return 0;
//        }
//
//        int[] out = {s.length() - 1};
//        int len = s.length();
//
//        boolean[][] dp = new boolean[len][len];
//        for (int i = 0; i < len; i++) {
//            for (int j = 0; j <= i; j++) {
//                if (j == i) {
//                    dp[j][i] = true;
//                } else if(j == i - 1){
//                    dp[j][i] = s.charAt(j) == s.charAt(i);
//                } else {
//                    dp[j][i] = dp[j + 1][i - 1] && (s.charAt(j) == s.charAt(i));
//                }
//            }
//        }
//        PalindromePartationMinCutHelper(new ArrayList<>(),s.toCharArray(),0,dp,out);
//        return out[0];
//    }
//
//    private void PalindromePartationMinCutHelper(List<String> t,char[] s, int index, boolean[][] dp,int[] max) {
//        if (index >= s.length) {
//            max[0] = Math.min(max[0],t.size());
//            return;
//        }
//        for (int i = index; i < s.length; i++) {
//            if (dp[index][i]) {
//                t.add(new String(s,index, i - index + 1));
//                PalindromePartationMinCutHelper(t,s,i + 1,dp,max);
//                t.remove(t.size() - 1);
//            }
//        }
//    }


    public int minCut(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }

        int len = s.length();

        int[] dp = new int[len + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = i - 1;
        }

        for (int i = 0; i < dp.length; i++) {

            for (int j = 0;i - j >= 0 && i+j < len && s.charAt(i+j) == s.charAt(i - j);j++) {
                dp[i + j + 1] = Math.min(dp[i + j + 1], 1 + dp[i - j]);
            }

            for (int j = 1; i-j+1 >= 0 && i+j < len && s.charAt(i-j+1) == s.charAt(i + j);j++) {
                dp[i+j+1] = Math.min(dp[i+j+1],1 + dp[i - j + 1]);
            }
        }
        return dp[len];
    }
    /**
     * given a string, figure out the longest palindrome you can form use these characters
     * */
    public int longestPalindromePermutation(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char t = s.charAt(i);
            Integer val = map.get(t);
            if (val == null) {
                map.put(t,1);
            } else {
                map.put(t,val + 1);
            }
        }

        int longest = 0;
        boolean hasOdd = false;
        for (Map.Entry<Character,Integer> entry : map.entrySet()) {
             if ((entry.getValue() & 1) == 1) {
                if (!hasOdd) {
                    longest += entry.getValue();
                    hasOdd = true;
                } else {
                    longest += (entry.getValue() - 1);
                }
             } else {
                 longest += entry.getValue();
             }
        }
        return longest;
    }
}
