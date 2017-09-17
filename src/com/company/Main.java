package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        int[] a = {7,2,3,4};
        int[] b = {1,2,3,4,5,6};
        int[] a1 = {3, 1, 5, 5, 1, 4, 2};

        int[] c = {10,8,3,0};
        int[] d = {1,4,4,8,14};

        String s1 = "abba";
        String s2 = "ababad";
        String s3 = "dog cat cat dog";
        String[] input = {"aaa","bbb"};


        int[][] matrix = {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };
//        Sort sort = new Sort();
        DP dp = new DP();
//        Recursion re = new Recursion();
//        ArraySolution ar = new ArraySolution();
//        StringSolution ss = new StringSolution();
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

        System.out.println(dp.numDistinct(s2,s1));
    }
}
