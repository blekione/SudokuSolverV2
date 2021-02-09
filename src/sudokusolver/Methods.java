/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Matthew
 */
public class Methods {
    // iterate through grid

    public Gamestate iterateGrid(Gamestate gamestate) {

        int steps = 0;
        boolean go = true;

        for (int row_int = 0; row_int < 9; row_int++) {
            for (int col_int = 0; col_int < 9; col_int++) {
                //System.out.println("Cell: " + row_int + " " + col_int + " Value: " + gamestate.getCellArray()[row_int][col_int]);

                Cell activeCell = gamestate.getCellArray()[row_int][col_int];

                // Is Cell Solved?
                //if (!checkIfCellSolved(gamestate.getCellArray()[row_int][col_int])) {
                if (!checkIfCellLocked(gamestate.getCellArray()[row_int][col_int])) {
                    //System.out.println("Cell " + row_int + "|" + col_int + " unsolved...");

                    // Is possibleList Empty?
                    List<Integer> currentCellOptions = findAvailableNumbers(gamestate.getCellArray()[row_int][col_int], gamestate);
                    if (currentCellOptions.isEmpty()) {
                        //bugger.
                        //System.out.println("Bugger. Made a mistake somewhere.");
                        //Back up to last choice made.

                        activeCell = getUnstuck(activeCell, gamestate);
                        row_int = activeCell.getRow();
                        col_int = activeCell.getCol();

                        steps++;
                        if (steps == 100000) {
                            go = false;
                            break;
                        }
                    } else {
                        // Set cell Val to lowest num.
                        gamestate.getCellArray()[row_int][col_int].setVal(getLowestNum(currentCellOptions));

                        //System.out.println("CHECK: Cell " + row_int + "|" + col_int + " " + gamestate.getCellArray()[row_int][col_int].getVal());
                    }

                } else {
                    //System.out.println("Cell " + row_int + "|" + col_int + " already solved!");
                }

                if (!go) {
                    break;
                }
            }

            if (!go) {
                break;
            }
        }
        gamestate.displayCellArray();
        System.out.println("Tries: " + steps);
        return gamestate;
    }

    // retired. using locked status instead.
    private boolean checkIfCellSolved(Cell cell) {

        boolean solved = false;
        if (cell.getVal() != 0) {
            solved = true;
        }
        //System.out.println(unsolved);
        return solved;
    }

    // checks if cell is set as locked from being a starting number.
    private boolean checkIfCellLocked(Cell cell) {
        boolean locked = false;
        locked = cell.isLocked();
        return locked;
    }

    // finds potential answers for this cell
    public List<Integer> findAvailableNumbers(Cell cell, Gamestate gamestate) {
        List<Integer> possibleNums = new ArrayList<Integer>();

        possibleNums = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        //listPossibleNums(possibleNums, cell);
        possibleNums.removeAll(checkRow(cell, gamestate));
        possibleNums.removeAll(checkColumn(cell, gamestate));
        possibleNums.removeAll(checkBlock(cell, gamestate));

        listPossibleNums(possibleNums, cell);

        cell.setList(possibleNums);

        return possibleNums;
    }

    private List<Integer> checkRow(Cell cell, Gamestate gamestate) {
        List<Integer> rowValues = new ArrayList<Integer>();

        for (int i = 0; i < 9; i++) {
            int num = gamestate.getCellArray()[cell.getRow()][i].getVal();
            if (num != 0) {
                rowValues.add(num);
            }
        }
        return rowValues;
    }

    private List<Integer> checkColumn(Cell cell, Gamestate gamestate) {
        List<Integer> columnValues = new ArrayList<Integer>();

        for (int i = 0; i < 9; i++) {
            int num = gamestate.getCellArray()[i][cell.getCol()].getVal();
            if (num != 0) {
                columnValues.add(num);
            }
        }
        return columnValues;
    }

    private List<Integer> checkBlock(Cell cell, Gamestate gamestate) {
        List<Integer> blockValues = new ArrayList<Integer>();

        int blockRow = 0;
        int blockCol = 0;

        if (cell.getRow() > 5) {
            blockRow = 6;
        } else if (cell.getRow() > 2) {
            blockRow = 3;
        }

        if (cell.getCol() > 5) {
            blockCol = 6;
        } else if (cell.getCol() > 2) {
            blockCol = 3;
        }

        //System.out.println("blockRow = " + blockRow + " blockCol = " + blockCol);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = gamestate.getCellArray()[i + blockRow][j + blockCol].getVal();
                //System.out.println("Cell Value: " + num + " Row: " + (row_int + blockRow) + " Col: " + (col_int + blockCol));
                //System.out.println("row_int = " + row_int + " col_int = " + col_int + " blockRow = " + blockRow + " blockCol = " + blockCol);
                if (num != 0) {
                    blockValues.add(num);
                }
            }
        }
        return blockValues;
    }

    private void listPossibleNums(List<Integer> possibleNums, Cell cell) {
        //System.out.print("Possible Nums " + cell.getRow() + "|" + cell.getCol() + ": ");
        for (int num : possibleNums) {
            //System.out.print(num + " ");
        }
        //System.out.println("");
    }

    private int getLowestNum(List<Integer> currentCellOptions) {
        int lowestNum = 0;

        currentCellOptions.sort(null);
        lowestNum = currentCellOptions.get(0);

        //System.out.println("Assigning cell: " + lowestNum);

        return lowestNum;
    }

    private Cell getPreviousCell(Cell currentCell, Gamestate gamestate) {
        int currentRow = currentCell.getRow();
        int currentCol = currentCell.getCol();
        //System.out.println("Current Row " + currentRow);
        //System.out.println("Current Col " + currentCol);
        int previousRow = 0;
        int previousCol = 0;
        if ((currentCol == 0) && (currentRow == 0)) {
            System.out.println("Stuck at 0|0, something has gone wrong.");
            return currentCell;
        } else {
            if (currentCol == 0) {
                previousCol = 8;
                previousRow = (currentRow - 1);
            } else {
                previousCol = (currentCol - 1);
                previousRow = currentRow;
            }
            //System.out.println("Switching back to Cell " + previousRow + "|" + previousCol + " from Cell " + currentRow + "|" + currentCol);
        }
        Cell previousCell = gamestate.getCellArray()[previousRow][previousCol];
        return previousCell;
    }

    /*
    private Cell reverseIterateGrid(Cell cell, Gamestate gamestate) {

        // take cell location that got stuck.
        int startRow = cell.getRow();
        int startCol = cell.getCol();

        System.out.println("Reverse Iterating from " + startRow + "|" + startCol);

        System.out.println("Working on partial row");
        // reverse partial line starting at stuck cell.
        for (int k = (startCol - 1); k >= 0; k--) {

            System.out.println("Looking at cell " + startRow + "|" + k);

            Cell currentCell = gamestate.getCellArray()[startRow][k];
            // check if cell is locked
            if (!currentCell.isLocked()) {
                // if not locked set val = 0
                //currentCell.setVal(0);
                setCurrentCellValToZero(currentCell);
                gamestate.getCellArray()[startRow][k].setVal(0);
                List<Integer> currentList = currentCell.getList();

                System.out.println("List: " + currentList);

                //System.out.println("Val: " + gamestate.getCellArray()[startRow][k].getVal());
                // check if cell has getList() > 1
                if (currentList.size() > 1) {
                    // if > 1 option, discard index 0, setList
                    currentList.remove(0);
                    gamestate.getCellArray()[startRow][k].setList(currentList);
                    // assign val to new index 0
                    gamestate.getCellArray()[startRow][k].setVal(currentList.get(0));
                    // update iterateGrid to new row_int|col_int coordinate
                    cell.setCol(k);
                    System.out.println("Finished from partial row. Changed cell " + startRow + "|" + k + " to " + currentList.get(0));
                    gamestate.displayCellArray();
                    return cell;
                }
                setCurrentCellValToZero(currentCell);
                System.out.println("no alternatives");
                // if only 1 option back up again
                
            }
            if (currentCell.isLocked()) {
                System.out.println("cell is locked");
            }
            // if locked, back up again, check for lock
        }

        System.out.println("Couldn't fix in partial row.");

        // run reverse loop of remaining full rows
        for (int row_int = (startRow - 1); row_int >= 0; row_int--) {
            for (int col_int = 8; col_int >= 0; col_int--) {

                System.out.println("Looking at cell " + row_int + "|" + col_int);

                Cell currentCell = gamestate.getCellArray()[row_int][col_int];
                // check if cell is locked
                if (currentCell.isLocked()) {
                    // if not locked set val = 0
                    //currentCell.setVal(0);
                    setCurrentCellValToZero(currentCell);
                    gamestate.getCellArray()[row_int][col_int].setVal(0);

                    List<Integer> currentList = currentCell.getList();

                    System.out.println("List size " + currentList.size() + ". Content " + currentList);
                    // check if cell has getList() > 1
                    if (currentList.size() > 1) {

                        // if > 1 option, discard index 0, setList
                        currentList.remove(0);
                        gamestate.getCellArray()[row_int][col_int].setList(currentList);
                        // assign val to new index 0
                        gamestate.getCellArray()[row_int][col_int].setVal(currentList.get(0));
                        // update iterateGrid to new row_int|col_int coordinate
                        cell.setRow(row_int);
                        cell.setCol(col_int);
                        return cell;
                    }
                    setCurrentCellValToZero(currentCell);
                    System.out.println("no alternatives");
                    // if only 1 option back up again
                }
                if (currentCell.isLocked()) {
                    System.out.println("cell is locked");
                }
                // if locked, back up again, check for lock
            }
        }

        // back up cell
        // check if cell is locked
        // if locked, back up again, check for lock
        // if not locked set val = 0
        // check if cell has getList() > 1
        // if only 1 option back up again
        // if > 1 option, discard index 0, setList
        // assign val to new index 0
        // update iterateGrid to new row_int|col_int coordinate
        gamestate.displayCellArray();
        return cell;
    }
     */
    private void setCurrentCellValToZero(Cell cell) {
        if (!cell.isLocked()) {
            cell.setVal(0);
            //System.out.println("Cell " + cell.getRow() + "|" + cell.getCol() + " value set to O.");
        } else {
            System.out.println("Error. Cannot change cell val. Cell is locked!");
        }

    }

    private boolean isCellOptionListAboveOne(Cell cell) {
        boolean answer = false;

        if (cell.getList().size() > 1) {
            answer = true;
            //System.out.println("Cell " + cell.getRow() + "|" + cell.getCol() + " options are > 1");
        } else {
            //System.out.println("Cell " + cell.getRow() + "|" + cell.getCol() + " options are !> 1");
        }

        return answer;
    }

    private Cell cycleAndAssignNextOptionToVal(Cell cell) {

        if (cell.getList().size() > 1) {

            // if > 1 option, discard index 0, setList
            cell.getList().remove(0);
            // assign val to new index 0
            cell.setVal(cell.getList().get(0));
            // update iterateGrid to new row_int|col_int coordinate
            //System.out.println("Assigned Cell " + cell.getRow() + "|" + cell.getCol() + " value " + cell.getList().get(0));
        } else {
            System.out.println("Error: Cell options list !> 1");
        }

        return cell;
    }

    private Cell assignFirstOptionToVal(Cell cell) {
        if (cell.getList().size() > 0) {
            // assign val to index 0
            cell.setVal(cell.getList().get(0));
            // update iterateGrid to new row_int|col_int coordinate
            System.out.println("Assigned Cell " + cell.getRow() + "|" + cell.getCol() + " value " + cell.getList().get(0));
        } else {
            System.out.println("Error: Cell options list is empty!");
        }

        return cell;
    }

    private Cell getUnstuck(Cell cell, Gamestate gamestate) {
        //System.out.println("Trying to get unstuck");
        // take cell location that got stuck.
        // run a reversed loop, starting at stuck cell.
        // back up cell
        // check if cell is locked
        // if locked, back up again, check for lock
        // if not locked set val = 0
        // check if cell has getList() > 1
        // if only 1 option back up again
        // if > 1 option, discard index 0, setList
        // assign val to new index 0
        // update iterateGrid to new row_int|col_int coordinate

        boolean fixed = false;
        Cell activeCell = cell;

        while (!fixed) {
            
            activeCell = getPreviousCell(activeCell, gamestate);
            
            if (activeCell.isLocked()) {
                //skip to next cell
                //System.out.println("Cell " + cell.getRow() + "|" + cell.getCol() + " is locked.");
            } else {
                setCurrentCellValToZero(activeCell);
                if (isCellOptionListAboveOne(activeCell)) {
                    activeCell = cycleAndAssignNextOptionToVal(activeCell);
                    fixed = true;
                } else {
                    //no other option to take, skip.
                    //System.out.println("Cell " + cell.getRow() + "|" + cell.getCol() + " has no other options.");
                }
            }
        }
        return activeCell;
    }
}
