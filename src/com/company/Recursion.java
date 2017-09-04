package com.company;
import java.util.*;
/**
 * Created by wjm-harry on 9/2/17.
 */
public class Recursion {

    /**Given a set of n integers, divide the set in two subsets of n/2 sizes each such that the difference of the sum of two subsets is as minimum as possible.

     Return the minimum difference(absolute value).

     Assumptions:

     The given integer array is not null and it has length of >= 2.
     Examples:

     {1, 3, 2} can be divided into {1, 2} and {3}, the minimum difference is 0

     * */
    public int minDifference(int[] array) {
        // Write your solution here.
        Arrays.sort(array);
        int sum = 0;
        for (int i : array) {
            sum += i;
        }

        // target is find the subset whose sum is closet to sum / 2;
        int[] out = new int[1];
        out[0] = Integer.MAX_VALUE;
        findClosetSubset(array,0,0,out,sum / 2,0);
        return Math.abs(2 * out[0] - sum);
    }
    private void findClosetSubset(int[] a, int index, int sum, int[] closet, int tar,int num) {
        if (num >= a.length / 2) {
            closet[0] = Math.abs(tar - sum) < Math.abs(tar - closet[0]) ? sum : closet[0];
            return;
        } else if (index >= a.length) {
            return;
        }
        findClosetSubset(a,index + 1,sum + a[index],closet,tar,num + 1);
        findClosetSubset(a,index + 1,sum,closet,tar,num);
    }


    /**Given an array of strings, find if the strings can be concatenated to form a ring. The two strings s1 and s2 can be concatenated iff the the last char of s1 is identical to the first char of s2. The first char of the first string in the array must be identical to the last char of the last string. The ring must contain every string in the input once and only once.

     Assumptions:

     The given array is not null or empty.
     None of the strings in the array is null or empty.
     Examples:

     input = {"aaa", "bbb", "baa", "aab"}, return true since it can be re-arranged to {"aaa", "aab", "bbb"  and "baa"}
     input = {"aaa", "bbb"}, return false*/
    public boolean formRing(String[] input) {
        // Write your solution here.
        if (input == null || input.length < 1) {
            return false;
        }
        char head = input[0].charAt(0);
        char tail = input[0].charAt(input[0].length() - 1);
        if (input.length == 1) return head == tail;
        return canFormRing(input,1,head,tail);
    }

    private boolean canFormRing(String[] a,int index, char head, char tail) {
        if (index == a.length - 1) {
            char nhead = a[index].charAt(0);
            char ntail = a[index].charAt(a[index].length() - 1);
            if (tail == nhead && ntail == head) {
                return true;
            }
            return false;
        }

        for (int i = index; i < a.length; i++) {
            char nhead = a[i].charAt(0);
            char ntail = a[i].charAt(a[i].length() - 1);
            if (tail == nhead) {
                swap(a,i,index);
                if (canFormRing(a,index + 1,head,ntail)) {
                    return true;
                }
                swap(a,i,index);
            }
        }
       return false;
    }
    private void swap(String[] a, int i, int j){
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    };

}
