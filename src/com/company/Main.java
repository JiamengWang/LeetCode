package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        int[] a = {1,2,2,3,1,3,3,2,1};
        int[] b = {1,2,3,4,5,6};
        int[] a1 = {3, 1, 5, 5, 1, 4, 2};

        int[] c = {10,8,3,0};
        int[] d = {1,4,4,8,14};

        String s1 = "101";
        String s2 = "011";
        String s3 = "dog cat cat dog";
        String[] ss1 = {"abcd","dcba","lls","s","sssll"};
        String[] ss2 = {"a",""};
//        String[] input = {"aaa","bbb"};
//
//
//        int[][] matrix = {
//                {0,0,0},
//                {0,1,0},
//                {0,0,0}
//        };
//        Sort sort = new Sort();
//        DP dp = new DP();
//        Recursion re = new Recursion();
        ArraySolution ar = new ArraySolution();

//        StringSolution ss = new StringSolution();
//        System.out.println(ss.addBinary(s1,s2));
//        LinkedListSolution ls = new LinkedListSolution();
//        TreeSolution tr = new TreeSolution();
//        tr.run(new String[]{"7","3","9","1","5","8","10","#","2","4","6"});
//        brainTestSolution br = new brainTestSolution();
//        br.run(100);
//            Grid grid = new Grid();
//            grid.printGrid();
//            grid.addToken(0,1,'X');
//            grid.printGrid();
//
//            GridAi ai = new GridAi(grid);
//            ai.nextMove();
//            ai.nextMove();
//            grid.printGrid();

//            for (int i = 0; i <= 10; i++) {
//                ai.nextMove();
//            }

//        GridGame game = new GridGame(grid,new GridAi(grid));
//        game.begin();
//        Integer[][] ar1 = {{1,2,3},{0,0,4},{5,6,7}};
//        List<List<Integer>> l1 = new ArrayList<>();
//        for (Integer[] a : ar1){
//            l1.add(Arrays.asList(a));
//        }
//
        System.out.println(ar.majority(a,4));

//        Scanner cin = new Scanner(System.in);
//        int b;
//        while(cin.hasNextInt())
//        {
//            double a = (double)cin.nextInt();
//            b = cin.nextInt();
//            double count = 0;
//            while (b > 0) {
//                count += a;
//                a = Math.sqrt(a);
//                b--;
//            }
//            System.out.println(String.format("%.2f",count));
//        }

//            Scanner cin = new Scanner(System.in);
//            char[] input;
//            while(cin.hasNext())
//            {
//                input = cin.next().toUpperCase().toCharArray();
//
//            }


    }

//    private void process(char[] a,int i) {
//
//        while (i < a.length) {
//            if (a[i] != '.') {
//                if (i == 0) {
//                    System.out.print('_');
//                }
//                int next;
//                if (a[i] >= 'a' && a[i] <= 'z') {
//                    next = next(a,i);
//                    print(a,i,next);
//                    i = next + 1;
//                }
//            } else {
//                System.out.print('_');
//                i++;
//            }
//        }
//    }
//
//    private int next(char[] a, int b) {
//        boolean islowerbegin = (a[b] >= 'a' && a[b] <= 'b');
//
//        for (int i = b + 1; i < a.length;i++) {
//            if (a[i] >= 'A' && a[i] <= 'Z') {
//                if (islowerbegin) return i - 1;
//                continue;
//            } else {
//
//            }
//        }
//        return a.length - 1;
//    }
//
//    private void print(char[] a, int i, int j) {
//        while (i <= j) {
//            if (a[i] >= 'a' && a[i] <= 'z') a[i] = (char)(a[i] - 'a' + 'A');
//            System.out.print(a[i]);
//            i++;
//        }
//    }
}
