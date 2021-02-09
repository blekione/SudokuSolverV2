/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matthew
 */
public class Checker {
    // check grid and confirm the game is complete

    public boolean checkValidBoard(Gamestate gamestate) {
        boolean passed = true;

        if (checkStartingNumbers(gamestate) == false) {
            passed = false;
        } else {
            if (checkBoardHasOptions(gamestate) == false) {
                passed = false;
            }
        }

        return passed;
    }

    public boolean checkStartingNumbers(Gamestate gamestate) {
        boolean passed = true;
        List<Integer> usedNumbers = new ArrayList<Integer>();
        Cell currentCell = new Cell(0);

        //check each row for duplicate numbers
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                currentCell = gamestate.getCellArray()[row][col];
                if (currentCell.getVal() != 0) {
                    if (usedNumbers.contains(currentCell.getVal())) {
                        System.out.println("Number " + currentCell.getVal() + " already exists in row " + row);
                        passed = false;
                        break;
                    } else {
                        usedNumbers.add(currentCell.getVal());
                    }
                }
                if (passed == false) {
                    usedNumbers.clear();
                    break;
                }
            }
            if (passed == false) {
                usedNumbers.clear();
                break;
            }
            usedNumbers.clear();
        }

        //check each column for duplicate numbers
        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                currentCell = gamestate.getCellArray()[row][col];
                if (currentCell.getVal() != 0) {
                    if (usedNumbers.contains(currentCell.getVal())) {
                        System.out.println("Number " + currentCell.getVal() + " already exists in col " + col);
                        passed = false;
                        break;
                    } else {
                        usedNumbers.add(currentCell.getVal());
                    }
                }
                if (passed == false) {
                    usedNumbers.clear();
                    break;
                }
            }
            if (passed == false) {
                usedNumbers.clear();
                break;
            }
            usedNumbers.clear();
        }

        //check each block for duplicate numbers
        for (int blockRow = 0; blockRow < 7; blockRow += 3) {
            for (int blockCol = 0; blockCol < 7; blockCol += 3) {
                usedNumbers.clear();
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        currentCell = gamestate.getCellArray()[row + blockRow][col + blockCol];
                        if (currentCell.getVal() != 0) {
                            if (usedNumbers.contains(currentCell.getVal())) {
                                int block = 0;
                                //System.out.println("Block " + blockRow + "|" + blockCol);
                                switch (blockRow) {
                                    case 0:
                                        switch (blockCol) {
                                            case 0:
                                                block = 0;
                                                break;
                                            case 3:
                                                block = 1;
                                                break;
                                            case 6:
                                                block = 2;
                                                break;
                                        }
                                        break;
                                    case 3:
                                        switch (blockCol) {
                                            case 0:
                                                block = 3;
                                                break;
                                            case 3:
                                                block = 4;
                                                break;
                                            case 6:
                                                block = 5;
                                                break;
                                        }
                                        break;
                                    case 6:
                                        switch (blockCol) {
                                            case 0:
                                                block = 6;
                                                break;
                                            case 3:
                                                block = 7;
                                                break;
                                            case 6:
                                                block = 8;
                                                break;
                                        }
                                        break;
                                }
                                System.out.println("Number " + currentCell.getVal() + " already exists in block " + block);
                                passed = false;
                                break;
                            } else {
                                usedNumbers.add(currentCell.getVal());
                            }
                        }
                        if (passed == false) {
                            break;
                        }
                    }
                    if (passed == false) {
                        break;
                    }
                }
                if (passed == false) {
                    break;
                }
            }
            if (passed == false) {
                break;
            }
        }

        //System.out.println("blockRow = " + blockRow + " blockCol = " + blockCol);
        if (passed == true) {
            //System.out.println("Starting board check found no errors.");
            //System.out.println("");
        }

        return passed;
    }

    public boolean checkBoardHasOptions(Gamestate gamestate) {
        boolean passed = true;

        Methods method = new Methods();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Cell currentCell = gamestate.getCellArray()[row][col];
                if (method.findAvailableNumbers(currentCell, gamestate).isEmpty()) {
                    System.out.println("No valid numbers in cell " + row + "|" + col);
                    passed = false;
                }
            }
            if (passed == false) {
                break;
            } else {

            }
        }

        if (passed == true) {
            //System.out.println("Board looks valid.");
            //System.out.println("");
        }

        return passed;
    }

    public boolean checkGameIsComplete(Gamestate gamestate) {
        boolean passed = true;

        if (checkFullBoard(gamestate) == false) {
            passed = false;
        } else {
            if (checkSumValue(gamestate) == false) {
                passed = false;
            } else {
                //if (checkGameComplete(gamestate) == false) {
//                    passed = false;
//                }
            }
        }

        return passed;
    }

    public boolean checkFullBoard(Gamestate gamestate) {

        boolean passed = true;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Cell currentCell = gamestate.getCellArray()[row][col];
                if (currentCell.getVal() == 0) {
                    // cell is unsolved
                    passed = false;
                    break;
                }
            }
            if (passed == false) {
                break;
            }
        }
        return passed;
    }

    public boolean checkSumValue(Gamestate gamestate) {
        boolean passed = true;
        int sum = 0;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Cell currentCell = gamestate.getCellArray()[row][col];
                sum += currentCell.getVal();
            }
        }
        if (sum == 405) {
            //sum check passed
        } else {
            passed = false;
            //System.out.println("Failed checkSumValue check");
        }
        return passed;
    }

    public boolean checkNumbersCorrect(Gamestate gamestate) {
        boolean complete = false;
            // check row has 1-9
            // check col has 1-9
            // check block has 1-9
        return complete;
    }

}
