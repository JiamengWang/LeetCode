package com.company;
import java.util.*;
public class brainTestSolution {
    /**generating a list of primes using sieve of  eratosthenes
     * */

    public void run(int max) {
        boolean[] output = sieveOfEratosthenes(max);
        for (int i = 0; i < output.length;i ++) {
            if (output[i]) System.out.println(i);
        }
    }

    public boolean[] sieveOfEratosthenes (int max) {
        boolean[] flag = new boolean[max + 1];

        /*initial flag, set all elements to true except 0 and 1*/
        for (int i = 2; i < flag.length; i++) {
            flag[i] = true;
        }

        int prime = 2;
        while (prime <= Math.sqrt(max)) {

             crossOff(flag,prime);

             prime = nextPrime(flag,prime);
        }


        return flag;
    }

    private void crossOff(boolean[] flag, int prime) {
        for (int i = prime * prime;i < flag.length; i+= prime) {
            flag[i] = false;
        }
    }

    private int nextPrime (boolean[] flag, int prime) {
        int next =  prime + 1;
        while (next < flag.length && !flag[next]) {
            next++;
        }
        return next;
    }


    /**Given a digit string, return all possible letter combinations that the number could represent.

     A mapping of digit to letters (just like on the telephone buttons) is given below.
     Input:Digit string "23"
     Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     */
    public List<String> letterCombinations(String digits) {
        List<String> out =  new ArrayList<>();
        char[][] dic = {
                {'a','b','c'},
                {'d','e','f'},
                {'g','h','i'},
                {'j','k','l'},
                {'m','n','o'},
                {'p','q','r','s'},
                {'t','u','v'},
                {'w','x','y','z'}
        };
        letterCombineDfs(out,dic,new char[digits.length()],0,digits);
        return out;
    }

    private void letterCombineDfs(List<String> out,char[][] dic,char[] result, int index, String digit) {
        if (index >= result.length) {
            out.add(new String(result));
            return;
        }
        int d = digit.charAt(index) - '0';
        if (d >= 2) {
            for (Character c : dic[d - 2]) {
                result[index] = c;
                letterCombineDfs(out,dic,result,index + 1, digit);
            }
        } else {
            letterCombineDfs(out,dic,result,index + 1, digit);
        }
    }

    private void swap(char[] c, int i, int j) {
        char t = c[i];
        c[i] = c[j];
        c[j] = t;
    }


    public boolean judgeSquareSum(int c) {
        if (c <= 1) {
            return true;
        }

        int upbound = (int)Math.sqrt(c);

        for (int i = 1; i <= upbound / 2; i++) {
            double remaining = Math.sqrt(c - i*i);
            if (remaining == (int)remaining) {
                return true;
            }
        }
        return false;
    }
}
