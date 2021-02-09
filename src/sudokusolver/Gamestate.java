/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver;

import java.util.HashSet;

/**
 *
 * @author Matthew
 */
public class Gamestate {

    private int[][] grid = new int[9][9];
    private Cell[][] cellArray = new Cell[9][9];

    public Gamestate() {
        //System.out.println("setting up");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Cell cell = new Cell(0);
                cellArray[j][i] = cell;
                cell.setRow(j);
                cell.setCol(i);
            }
        }
        //System.out.println("setup complete");

    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public void displayGrid() {
        for (int[] line : grid) {
            for (int cell : line) {
                System.out.print(cell + " ");

            }
            System.out.println("");
        }
        System.out.println("");
    }

    public void setNumber(int x, int y, int val) {
        grid[x][y] = val;
    }

    public Cell[][] getCellArray() {
        return cellArray;
    }

    public void setCellArray(Cell[][] cellArray) {
        this.cellArray = cellArray;
    }

    public void displayCellArray() {
        for (Cell[] line : cellArray) {
            for (Cell cell : line) {
                System.out.print(cell + " ");

            }
            System.out.println("");
        }
        System.out.println("");
    }

    public void displayLockedCellArray() {
        for (Cell[] line : cellArray) {
            for (Cell cell : line) {
                System.out.print(cell.isLocked() + " ");

            }
            System.out.println("");
        }
        System.out.println("");
    }

    public void setCellNumber(int row, int column, int value) {
        cellArray[row][column].setVal(value);
    }

    public void setStartingState(int[][] startingState) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int cell = startingState[i][j];
                cellArray[i][j].setVal(cell);
                if (cellArray[i][j].getVal() > 0) {
                    cellArray[i][j].setLocked(true);
                }
            }
        }
    }

}
