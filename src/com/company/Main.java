package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        int[] a = {1, 3, 1, 2, 0};
        int[] a1 = {3, 1, 5, 5, 1, 4, 2};
        Sort sort = new Sort();

        System.out.println(Arrays.toString(sort.rainbowSortIII(a1,5)));
    }
}
