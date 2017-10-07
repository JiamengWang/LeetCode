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


    /**Given a pattern and a string str, find if str follows the same pattern.

     Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

     Examples:
     pattern = "abba", str = "dog cat cat dog" should return true.
     pattern = "abba", str = "dog cat cat fish" should return false.
     pattern = "aaaa", str = "dog cat cat dog" should return false.
     pattern = "abba", str = "dog dog dog dog" should return false.
     Notes:
     You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.*/
    public boolean wordPattern(String pattern, String str) {
        Map<Character,List<Character>> patternMap = new HashMap<>();
        return patternHelper(patternMap,new HashSet<String>(),0,0,pattern,str);
    }

    private boolean patternHelper(Map<Character,List<Character>> map, Set<String> set,int pindex,int sindex, String pattern, String str) {

        if (pindex == pattern.length() && sindex == str.length()) {
            return true;
        } else if (pindex == pattern.length() || sindex == str.length()) {
            return false;
        }
        char patt = pattern.charAt(pindex);
        List<Character> pattString = map.get(patt);

        if (pattString == null || pattString.size() == 0) {
            /*create pattern - string relation*/
            pattString = new ArrayList<>();
            map.put(patt,pattString);
            for (int i = sindex; i < str.length(); i++) {
                pattString.add(str.charAt(i));
                String newstr = ArrayToString(pattString);
                if (set.add(newstr)) {
                    if (patternHelper(map,set,pindex+1, i + 1,pattern,str)) {
                        return true;
                    }
                    set.remove(newstr);
                }
            }
            map.remove(patt);
            return false;
        } else {
            /*use the existed relation to judge if the string match out pattern*/
            ListIterator<Character> iter = pattString.listIterator();
            int ssindex = sindex;
            while (iter.hasNext()) {
                if (ssindex >= str.length() || !(iter.next() == str.charAt(ssindex))) {
                    return false;
                }
                ssindex++;
            }
            return patternHelper(map,set,pindex + 1,sindex + pattString.size(),pattern,str);
        }
    }

    private String ArrayToString(List<Character> list){
        StringBuilder out = new StringBuilder();
        for (Character c : list) {
            out.append(c);
        }
        return out.toString();
    }


    /**
     * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

     Example 1:
     Input: "Let's take LeetCode contest"
     Output: "s'teL ekat edoCteeL tsetnoc"
     */

    public String reverseWords(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        char[] sar = s.toCharArray();

        int cur = 0;
        int prev = 0;

        while (cur < sar.length) {
            if (sar[cur] != ' ') {
                cur++;
            } else {
                swapHelper(sar,prev,cur - 1);
                while (cur < sar.length && sar[cur] == ' ') {
                    cur++;
                }
                prev = cur;
            }
        }
        swapHelper(sar,prev,cur - 1);
        return new String(sar);
    }

    private void swapHelper(char[] ar, int b, int e) {
        while (b < e) {
            char t = ar[b];
            ar[b] = ar[e];
            ar[e] = t;
            b++;
            e--;
        }
    }

    /**Given an integer, return its base 7 string representation.

     Example 1:
     Input: 100
     Output: "202"
     Example 2:
     Input: -7
     Output: "-10"*/
    public String convertToBase7(int num) {
        StringBuilder out = new StringBuilder();
        if (num < 0) out.append('-');
        num = Math.abs(num);

        int base = 1;

        while (base * 7 <= num) {
            base *= 7;
        }

        while (base > 0) {
            out.append(num / base);
            num = num % base;
            base /= 7;
        }
        return out.toString();
    }


    /** Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

     Example 1:
     Given words = ["bat", "tab", "cat"]
     Return [[0, 1], [1, 0]]
     The palindromes are ["battab", "tabbat"]
     Example 2:
     Given words = ["abcd", "dcba", "lls", "s", "sssll"]
     Return [[0, 1], [1, 0], [3, 2], [2, 4]]
     The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
     */

    // time out
    public List<List<Integer>> palindromePairsI(String[] words) {
        List<List<Integer>> out = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < i;j++) {
                if (isPalidromePair(words[i],words[j])) out.add(Arrays.asList(new Integer[]{i,j}));
                if (isPalidromePair(words[j],words[i])) out.add(Arrays.asList(new Integer[]{j,i}));
            }
        }
        return out;
    }

    private boolean isPalidromePair (String l,String r) {
        if (l == null || r == null) {
            return true;
        }
        int ll = l.length();
        int rl = r.length();
        int tl = ll + rl - 1;
        int left = 0;
        int right = tl;
        if ((tl & 1) != 0) {
             left = tl / 2;
             right = left + 1;
        } else {
             left = tl / 2 - 1;
             right = tl / 2 + 1;
        }
        while (left >= 0 && right <= tl) {
            if (getCharFromTwoString(l,r,left) != getCharFromTwoString(l,r,right)) {
                return false;
            }
            left--;
            right++;
        }
        return true;
    }

    private char getCharFromTwoString(String l, String r,int index){
        if (index >= l.length()) {
            return r.charAt(index - l.length());
        } else {
            return l.charAt(index);
        }
    }

    // use hashmap to accelerate find rever part
//    public List<List<Integer>> palindromePairs(String[] words){
//        List<List<Integer>> out = new ArrayList<>();
//        Map<String,Integer> map = new HashMap<>();
//
//        for (int i = 0; i <  words.length; i++) {
//            map.put(words[i],i);
//        }
//
//        for (int i = 0; i < words.length; i++) {
////            char[] warr = words[i].toCharArray();
//            for (int j = 0; j <= words[i].length();j++) {
//                String str1 = words[i].substring(0, j);
//                String str2 = words[i].substring(j);
//                if (isPalidrome(warr,0,j - 1)) {
//                    Integer index = map.get(new StringBuilder(new String(warr,j, warr.length - j)).reverse().toString());
//                    if (index != null && index != i) {
//                        List<Integer> ou = new ArrayList<>();
//                        ou.add(index);
//                        ou.add(i);
//                        out.add(ou);
//                    }
//                }
//
//                if (isPalidrome(warr,j,warr.length - 1)) {
//                    Integer index = map.get(new StringBuilder(new String(warr,0, j)).reverse().toString());
//                    if (index != null && index != i) {
//                        List<Integer> ou = new ArrayList<>();
//                        ou.add(i);
//                        ou.add(index);
//                        out.add(ou);
//                    }
//                }
//            }
//        }
//        return out;
//    }
//
//    private boolean isPalidrome(char[] s,int b, int e) {
//        if (b > e || b < 0 || e >= s.length) return false;
//        int low = b;
//        int high = e;
//        while (low <= high) {
//            if (s[low] == s[high]) {
//                low++;
//                high--;
//                continue;
//            }
//            return false;
//        }
//        return true;
//    }



        public String addBinary(String a, String b) {
            if (a == null || b == null) {
                return a == null ? b : a;
            } else if (a.length() == 0) {
                return b;
            } else if (b.length() == 0) {
                return a;
            }

            int max = Math.max(a.length(),b.length());
            char[] out = new char[max + 1];

            int maxIndex = out.length - 1;
            int aIndex = a.length() - 1;
            int bIndex = b.length() - 1;
            int carry = 0;
            while (aIndex >= 0 && bIndex >= 0) {
                int sum = carry + (a.charAt(aIndex--) - '0') + (b.charAt(bIndex--) - '0');
                if (sum >= 2) {
                    sum = 0;
                    carry = 1;
                } else {
                    carry = 0;
                }
                out[maxIndex--] = (char)('0'+sum);
            }

            while (aIndex >= 0 && maxIndex >= 0) {
                int sum = carry + (a.charAt(aIndex--) - '0');
                if (sum >= 2) {
                    sum = 0;
                    carry = 1;
                } else {
                    carry = 0;
                }
                out[maxIndex--] = (char)('0'+sum);
            }

            while (bIndex >= 0 && maxIndex >= 0) {
                int sum = carry + (b.charAt(bIndex--) - '0');
                if (sum >= 2) {
                    sum = 0;
                    carry = 1;
                } else {
                    carry = 0;
                }
                out[maxIndex--] = (char)('0'+sum);
            }

            if (carry == 1) {
                out[maxIndex--] = '1';

            }
            return new String(out,maxIndex + 1,out.length - maxIndex - 1);
        }


    /**Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

     Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

     The order of output does not matter.

     Example 1:

     Input:
     s: "cbaebabacd" p: "abc"

     Output:
     [0, 6]

     Explanation:
     The substring with start index = 0 is "cba", which is an anagram of "abc".
     The substring with start index = 6 is "bac", which is an anagram of "abc".
     Example 2:

     Input:
     s: "abab" p: "ab"

     Output:
     [0, 1, 2]

     Explanation:
     The substring with start index = 0 is "ab", which is an anagram of "ab".
     The substring with start index = 1 is "ba", which is an anagram of "ab".
     The substring with start index = 2 is "ab", which is an anagram of "ab".*/
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> out = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return out;
        }

        // count appearance of each characters in p
//        Map<Character,Integer> map = new HashMap<>();
        int[] counts = new int[26];
        for (int i = 0; i < p.length(); i++) {
            char temp = p.charAt(i);
            counts[temp - 'a'] += 1;
        }

        for (int j = 0; j <= s.length() - p.length(); j++) {
            char temp = s.charAt(j);
            if (counts[temp - 'a'] != 0) {
                findana(out,copyintArray(counts),s,j,p.length());
            }
        }
        return out;
    }

    private int[] copyintArray(int[] a) {
        int[] out = new int[a.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = a[i];
        }
        return out;
    }

    private void findana(List<Integer> out,int[] counts, String s, int index,int offset) {
        for (int i = index; i <= index + offset; i++) {
            char temp = s.charAt(i);
            if (counts[temp - 'a'] != 0) {
                counts[temp - 'a'] -= 1;
            } else {
                return;
            }
        }
        out.add(index);
    }
}
