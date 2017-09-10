package com.company;
import java.util.*;

public class TreeSolution {
    /**Find distance between two given keys of a Binary Tree, no parent pointers are given. Distance between two nodes is the minimum number of edges to be traversed to reach one node from other.

     Assumptions:

     There are no duplicate keys in the binary tree.
     The given two keys are guaranteed to be in the binary tree.
     Examples:

     1

     /  \

     2    3

     / \  /  \

     4   5 6   7

     \

     8

     distance(4, 5) = 2

     distance(4, 6) = 4*/
    class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int key) {
            this.key = key;
        }
    }
    public void run(String[] ar) {
        TreeNode root = buildFromLevelOrderTraverse(ar,0);
//        System.out.println(findDistance(root,4,6));
    }

    private TreeNode buildFromLevelOrderTraverse(String[] ar,int index) {
        if (index >= ar.length || ar[index].equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(ar[index]));
        root.left = buildFromLevelOrderTraverse(ar,2 *index + 1);
        root.right = buildFromLevelOrderTraverse(ar,2 * index + 2);
        return root;
    }

//    public int distance(TreeNode root, int k1, int k2) {
        // Write your solution here.
//        return findDistance(root,k1,k2);
//    }

//    private TreeNode findTarget(TreeNode root, int) {
//
//    }
//    private int findDistance (TreeNode root, int k1, int k2) {
//        if (root == null) {
//            return -1;
//        } else if (root.key == k1 || root.key == k2) {
//            int sl = findDistance(root.left,k1,k2);
//            int sr = findDistance(root.right,k1,k2);
//            if (sl == -1 && sr == -1) {
//                return 0;
//            } else if (sl == -1 || sr == -1) {
//                return 1 + (sl == -1 ? sr : sl);
//            } else {
//                return -2;
//            }
//        }
//
//        int left = findDistance(root.left,k1,k2);
//        int right = findDistance(root.right,k1,k2);
//        if (left == -1 && right == -1) {
//            return -1;
//        } else if (left == -1 || right == -1) {
//            return 1 + (left == -1 ? right : left);
//        } else {
//            return 2 + left + right;
//        }
//    }
}
