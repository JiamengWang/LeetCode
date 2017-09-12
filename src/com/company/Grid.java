package com.company;

public class Grid {
    char[][] grid;
    int len = 3;
    public Grid() {
        grid = new char[len][len];

        // initialize all cell with token '-'
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                grid[i][j] = '-';
            }
        }
    }

    public boolean addToken (int x, int y, char token) {
        if (x >= 3 | y >= 3) {
            return false;
        }

        if (grid[x][y] == '-') {
            grid[x][y] = token;
            return true;
        } else {
            return false;
        }
    }

    public void printGrid () {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                System.out.print(grid[i][j]);
                if (j < len - 1) System.out.print('|');
            }
            System.out.println();
        }
    }

    public boolean isFull () {
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (Character c : grid[i]) {
                if (c != '-') {
                    count++;
                }
            }
        }
        return count == len*len;
    }

    public int getLen () {
        return this.len;
    }

}
