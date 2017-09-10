package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        int[] a = {2, 3, 2, 1, 4, 5, 2, 11};
        int[] b = {1,2,3,4,5,6};
        int[] a1 = {3, 1, 5, 5, 1, 4, 2};

        int[] c = {10,8,3,0};
        int[] d = {1,4,4,8,14};

        String s1 = "babad";
        String s2 = "ababad";
        String[] input = {"aaa","bbb"};


        int[][] matrix = {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };
//        Sort sort = new Sort();
//        DP dp = new DP();
//        Recursion re = new Recursion();
//        ArraySolution ar = new ArraySolution();
//        StringSolution ss = new StringSolution();
//            LinkedListSolution ls = new LinkedListSolution();
//            TreeSolution tr = new TreeSolution();
//            tr.run(new String[]{"7","3","9","1","5","8","10","#","2","4","6"});
        brainTestSolution br = new brainTestSolution();
        br.run(100);

//        System.out.println(Arrays.toString(ar.maxSum(c)));
//        System.out.println(tr.run());
//        System.out.println(ss.longestPalindrome(s1));
    }
}
