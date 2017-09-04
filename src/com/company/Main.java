package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        int[] a = {2, 3, 2, 1, 4, 5, 2, 11};
        int[] b = {1,2,3,4,5,6};
        int[] a1 = {3, 1, 5, 5, 1, 4, 2};

        int[] c = {5,-2,-10,3};
        int[] d = {1,4,4,8,14};

        String[] input = {"aaa","bbb"};
//        Sort sort = new Sort();
//        DP dp = new DP();
        Recursion re = new Recursion();
        ArraySolution ar = new ArraySolution();

//        System.out.println(Arrays.toString(ar.twoDiff(d,0)));
        System.out.println(re.formRing(input));
    }
}
