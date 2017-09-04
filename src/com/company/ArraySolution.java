package com.company;
import java.util.*;
/**
 * Created by wjm-harry on 9/3/17.
 */
public class ArraySolution {

    /**Given an original unsorted array A of size n that contains all integers from  [1….n] (no duplicated numbers) but we do not know A. Instead, we only know a count-array B, in which B[i] represents the number of elements A[j]  (i < j) that are smaller than A[i]. How can we re-store A based on B?

     Assumptions:

     The given array is not null.
     Examples:

     Given B = { 3, 0, 1, 0 }, then we can restore the original array A = { 4, 1, 3, 2 },
     Requirement:

     time complexity = O(nlogn).*/

    public int[] restore(int[] array) {
        // Write your solution here.
        if (array == null || array.length == 0) {
            return array;
        }

        int[] out = new int[array.length];
        TreeNode root = buildBalancedTree(0,array.length - 1);
        for (int i = 0; i<array.length; i++) {
            root = dfsDelete(root,array[i],-1,out,i);
        }
        return out;
    }
    class TreeNode {
        public int val;
        public int counter;
        public TreeNode left;
        public TreeNode right;

        public TreeNode (int val) {
            this.val = val;
            this.counter = 0;
        }
    }

    private TreeNode dfsDelete(TreeNode root,int key,int val,int[] out, int index) {
        if (root == null) {
            return null;
        } else if (key == -1) {
            if (root.val > val) {
                root.counter--;
                root.left = dfsDelete(root.left,key,val,out,index);
            } else if (root.val < key) {
                root.right = dfsDelete(root.right,key,val,out,index);
            } else {
                if (root.left == null && root.right == null) {
                    return null;
                } else if (root.left == null || root.right == null) {
                    return root.left == null ? root.right : root.left;
                } else {
                    root.val = findmin(root.right);
                    root.right = dfsDelete(root.right,key,root.val,out,index);
                }
            }
        } else if (root.counter > key) {
            root.counter--;
            root.left = dfsDelete(root.left,key,val,out,index);
        } else if (root.counter < key) {
            root.right = dfsDelete(root.right,key - root.counter - 1,val,out,index);
        } else {
            // find the target node
            out[index] = root.val;
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null || root.right == null) {
                return root.left == null ? root.right : root.left;
            } else {
                root.val = findmin(root.right);
                root.right = dfsDelete(root.right,-1,root.val,out,index);
            }
        }
        return root;
    }

    private int findmin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.val;
    }
    private TreeNode buildBalancedTree(int min,int max) {
        if (min > max) {
            return null;
        }
        int mid = min + (max - min) / 2;
        TreeNode root = new TreeNode(mid + 1);
        root.counter = mid - min;
        root.left = buildBalancedTree(min,mid - 1);
        root.right = buildBalancedTree(mid + 1, max);
        return root;
    }



    /**Given a sorted array A, find a pair (i, j) such that A[j] - A[i] is identical to a target number(i != j).

     If there does not exist such pair, return a zero length array.

     Assumptions:

     The given array is not null and has length of at least 2.
     Examples:

     A = {1, 2, 3, 6, 9}, target = 2, return {0, 2} since A[2] - A[0] == 2.
     A = {1, 2, 3, 6, 9}, target = -2, return {2, 0} since A[0] - A[2] == 2.*/
    public int[] twoDiff(int[] array, int target) {
        // Write your solution here.
        int[] out = new int[2];
        for (int i = 0; i < array.length; i++) {
            int index1 = BinarySearchFirst(array,array[i] - target);
            int index2 = BinarySearchFirst(array,target + array[i]);
            if (index2 != -1) {
                if (index2 != i) {
                    out[0] = i;
                    out[1] = index2;
                    return out;
                } else if(index2 + 1 < array.length && array[index2 + 1] == array[index2]){
                    out[0] = i;
                    out[1] = index2 + 1;
                    return out;
                }
            }
            if (index1 != -1) {
                if (index1 != i) {
                    out[1] = i;
                    out[0] = index1;
                    return out;
                } else if(index1 + 1 < array.length && array[index1 + 1] == array[index1]){
                    out[1] = i;
                    out[0] = index1 + 1;
                    return out;
                }
            }

        }
        return new int[0];
    }

    private int BinarySearchFirst(int[] a, int tar) {
        int low = 0;
        int high = a.length - 1;

        while (low < high - 1) {
            int mid = low + (high - low) / 2;
            if (a[mid] < tar) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        if (a[low] == tar) return low;
        return a[high] == tar ? high : -1;
    }
}
