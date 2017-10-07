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

    /**Given a binary tree, return the tilt of the whole tree.

     The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.

     The tilt of the whole tree is defined as the sum of all nodes' tilt.

     Example:
     Input:
     1
     /   \
     2     3
     Output: 1
     Explanation:
     Tilt of node 2 : 0
     Tilt of node 3 : 0
     Tilt of node 1 : |2-3| = 1
     Tilt of binary tree : 0 + 0 + 1 = 1*/
    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] out = new int[1];
        findT(root,out);
        return out[0];
    }

    private int findT(TreeNode root, int[] sum) {
        if (root == null) {
            return 0;
        }

        int left = findT(root.left,sum);
        int right = findT(root.right,sum);
        sum[0] += Math.abs(left - right);
        return left + right + root.key;
    }

    /**Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v at the given depth d. The root node is at depth 1.

     The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1, create two tree nodes with value v as N's left subtree root and right subtree root. And N's original left subtree should be the left subtree of the new left subtree root, its original right subtree should be the right subtree of the new right subtree root. If depth d is 1 that means there is no depth d-1 at all, then create a tree node with value v as the new root of the whole original tree, and the original tree is the new root's left subtree.*/

    public TreeNode addOneRow(TreeNode root, int v, int d) {
        return addOneRowHelper(root,v,d,true);
    }

    private TreeNode addOneRowHelper(TreeNode root, int v, int d,boolean isLeft) {
        if (d == 1) {
            TreeNode newnode = new TreeNode(v);
            if(isLeft) {
                newnode.left = root;
            } else {
                newnode.right = root;
            }
            return newnode;
        } else if (root == null){
            return null;
        }

        root.left = addOneRowHelper(root.left,v,d - 1,true);
        root.right = addOneRowHelper(root.right,v,d - 1,false);
        return root;
    }

    /**Given the root of a tree, you are asked to find the most frequent subtree sum.
     * The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself).
     * So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

     Examples 1
     Input:

     5
     /  \
     2   -3
     return [2, -3, 4], since all the values happen only once, return all of them in any order.
     Examples 2
     Input:

     5
     /  \
     2   -5
     return [2], since 2 happens twice, however -5 only occur once.*/
    public int[] findFrequentTreeSum(TreeNode root) {
        // use hashmap to record the frequency of a sum number
        // iterator the hashmap, find the sum has the most frequency number
        Map<Integer,Integer> freq = new HashMap<>();
        freqHelper(root,freq);

        int maxfreq = 0;
        List<Integer> maxrecord = new ArrayList<>();
        for (Map.Entry<Integer,Integer> entry : freq.entrySet()) {
            if (entry.getValue() > maxfreq) {
                maxfreq = entry.getValue();
                maxrecord = new ArrayList<>();
                maxrecord.add(entry.getKey());
            } else if (entry.getValue() == maxfreq){
                maxrecord.add(entry.getKey());
            }
        }

        return convertToIntArray(maxrecord);
    }
    private int[] convertToIntArray(List<Integer> ar) {
        int[] out = new int[ar.size()];
        int i = 0;
        for (int a : ar) {
            out[i++] = a;
        }
        return out;
    }
    private int freqHelper(TreeNode root, Map<Integer,Integer> map) {
        if (root == null) {
            return 0;
        }

        int left = freqHelper(root.left,map);
        int right = freqHelper(root.right,map);

        int sum = left + right + root.key;
        Integer record = map.get(sum);
        if (record == null) record = 0;
        map.put(sum,record + 1);
        return sum;
    }

    /**Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

     Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

     For example, you may serialize the following tree

     1
     / \
     2   3
     / \
     4   5
     as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
     Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.*/

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {//using bfs to go through whole tree and store information // assumption: root is not equal to null
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerLast(root);
        StringBuilder out = new StringBuilder();

        while (!stack.isEmpty()) {
            TreeNode tempNode = stack.pollLast();
            if (tempNode == null) {
                out.append('#');
            } else {
                out.append(tempNode.key);
                stack.offerLast(tempNode.left);
                stack.offerLast(tempNode.right);
            }
            out.append(',');
        }
        return out.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // assumption data is not null
        if (data == null || data.length() == 0) {
            return null;
        }

        String[] parts = data.split(",");
        return dfsRebuilder(parts,new int[]{0});
    }

    private TreeNode dfsRebuilder (String[] parts, int[] index) {
        if (index[0] >= parts.length) {
            return null;
        } else if (parts[index[0]].equals("#")) {
            index[0]++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(parts[index[0]]));
        index[0]++;
        root.left = dfsRebuilder(parts,index);
        root.right = dfsRebuilder(parts,index);
        return root;
    }


    /**Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s.
     * A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

     Example 1:
     Given tree s:

     3
     / \
     4   5
     / \
     1   2
     Given tree t:
     4
     / \
     1   2
     Return true, because t has the same structure and node values with a subtree of s.
     Example 2:
     Given tree s:

     3
     / \
     4   5
     / \
     1   2
     /
     0
     Given tree t:
     4
     / \
     1   2
     Return false.*/
//    public boolean isSubtree(TreeNode s, TreeNode t) {
//
//    }
//
//    private boolean issub(TreeNode s, TreeNdoe) {}


    /**Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

     According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that
     has both v and w as descendants (where we allow a node to be a descendant of itself).”

     _______3______
     /              \
     ___5__          ___1__
     /      \        /      \
     6      _2       0       8
     /  \
     7   4
     For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant
     of itself according to the LCA definition.*/

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // corner case
        if (root == null || p == null || q == null) {
            return null;
        }

        TreeNode result = findLCA(root,p,q);
        if (result == q && findLCA(result,null,p) == p) {
            return q;
        } else if (result == p && findLCA(result,null,q) == q) {
            return p;
        }
        return result;
    }

    private TreeNode findLCA(TreeNode root,TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return root;
        } else if (root == null) {
            return null;
        }

        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);

        if (left == null && right == null) {
            return null;
        } else if (left == null || right == null) {
            TreeNode find = left == null ? right : left;
            return find;
        } else {
            return root;
        }
    }
}
