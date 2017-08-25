package com.company;

/**
 * Created by wjm-harry on 8/25/17.
 */
public class Sort {

    public int[] rainbowSortII(int[] array) {
        // Write your solution here.
        if(array == null || array.length <= 1) {
            return array;
        }

        int r = 0;
        int g = 0;
        int b = array.length - 1;
        int l = 0;
        //since the order is red->green->blue->black
        //r < g < l < b
        while (l <= b) {
            if (array[l] == 0) {
                swap(array,l,g);
                swap(array,g,r++);
                if (r > g) {
                    g = r;
                }
                if (g > l) {
                    l = g;
                }
            } else if (array[l] == 1) {
                swap(array,l,g++);
                if (g > l) {
                    l = g;
                }
            } else if (array[l] == 2) {
                l++;
            } else {
                swap(array,l,b--);
            }
        }
        return array;
    }

    /**Given an array of balls with k different colors denoted by numbers 1- k, sort the balls.
     *
     * Examples

     k=1, {1} is sorted to {1}
     k=3, {1, 3, 2, 1, 2} is sorted to {1, 1, 2, 2, 3}
     k=5, {3, 1, 5, 5, 1, 4, 2} is sorted to {1, 1, 2, 3, 4, 5, 5}
     Assumptions

     The input array is not null.
     k is guaranteed to be >= 1.
     k << logn.
     * */
    public int[] rainbowSortIII(int[] array, int k) {
        if (array.length <= 1) {
            return array;
        }

        int[] index = new int[k];
        index[k-1] = array.length - 1;

        while (index[k - 2] <= index[k - 1]) {
            int temp = array[index[k - 2]];
            if (temp == k) {
                swap(array,index[k - 2],index[k - 1]);
                index[k - 1] -= 1;
            } else if (temp == (k - 1)){
                index[k - 2]++;
            } else {
                nswap(array,index,temp - 1,k - 2);
                index[temp - 1]++;
                IncreaseReorder(index,temp - 1,k - 2);
            }
        }
        return array;
    }

    private void nswap(int[] a,int[] index, int b, int e) {
        for (int i = e; i > b; i--) {
            swap(a,index[i],index[i - 1]);
        }
    }

    private void IncreaseReorder (int[] index, int b, int e) {
        for (int i = b; i < e; i++) {
            if (index[i] > index[i + 1]) {
                index[i + 1] = index[i];
            }
        }
    }



    //utility functions
    private void swap(int[] arr,int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
