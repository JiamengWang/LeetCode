package com.company;

public class GridAi {

    Grid grid;
    char token = 'O';

    public GridAi (Grid grid) {
        this.grid = grid;
    }


    // throw runtime exception, if the move is illegal
    public boolean nextMove () {
        if (grid.isFull()) {
            throw new RuntimeException();
        }
        for (int i = 0; i < grid.getLen(); i++) {
            for (int j = 0; j < grid.getLen(); j++) {
                if (grid.addToken(i,j,token)) {
                    return true;
                }
            }
        }
        return false;
    }

}
