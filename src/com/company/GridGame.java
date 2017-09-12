package com.company;
import java.util.Scanner;


public class GridGame {

    Grid grid;
    GridAi ai;



    public GridGame (Grid grid, GridAi ai) {
        this.grid = grid;
        this.ai = ai;
    }

    public void begin() {
        Scanner input = new Scanner(System.in);

        //TODO: in the future, we should use isWin() instead
        while (!grid.isFull()) {
            System.out.println("Please Insert X: ");
            int x = input.nextInt();
            System.out.println("Please Insert Y: ");
            int y = input.nextInt();

            if (grid.addToken(x,y,'O')) {
                ai.nextMove();
            }

        }
    }

}
