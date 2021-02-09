/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver;

/**
 *
 * @author Matthew
 */
public class StartingNumbers {
    private static int[][] grid = new int[9][9];
    private static Cell[][] cellArray = new Cell[9][9];
    
    public static int[][] RIXX_CHALLENGE()  {
        grid[0][0] = 8;
        grid[1][2] = 3;
        grid[1][3] = 6;
        grid[2][1] = 7;
        grid[2][4] = 9;
        grid[2][6] = 2;
        grid[3][1] = 5;
        grid[3][5] = 7;
        grid[4][4] = 4;
        grid[4][5] = 5;
        grid[4][6] = 7;
        grid[5][3] = 1;
        grid[5][7] = 3;
        grid[6][2] = 1;
        grid[6][7] = 6;
        grid[6][8] = 8;
        grid[7][2] = 8;
        grid[7][3] = 5;
        grid[7][7] = 1;
        grid[8][1] = 9;
        grid[8][6] = 4;
        
        return grid;
    }
    
    public static int[][] PLATE_CHALLENGE()  {
        grid[0][3] = 9;
        grid[0][8] = 7;
        grid[1][1] = 6;
        grid[1][5] = 7;
        grid[1][7] = 5;
        grid[2][4] = 6;
        grid[2][5] = 4;
        grid[2][6] = 8;
        grid[3][0] = 7;
        grid[3][3] = 4;
        grid[3][4] = 1;
        grid[3][8] = 3;
        grid[4][0] = 8;
        grid[4][3] = 7;
        grid[4][6] = 5;
        grid[4][8] = 4;
        grid[5][1] = 5;
        grid[5][3] = 2;
        grid[6][1] = 4;
        grid[6][5] = 2;
        grid[6][6] = 3;
        grid[7][2] = 7;
        grid[7][3] = 6;
        grid[7][7] = 4;
        grid[7][8] = 9;
        grid[8][7] = 7;
        
        return grid;
    }
    
    public static int[][] INVALID_CHALLENGE()  {
        grid[0][3] = 9;
        grid[0][8] = 9;
        grid[1][1] = 9;
        //grid[1][5] = 9;
        //grid[1][7] = 9;
        grid[2][4] = 9;
        //grid[2][5] = 9;
        //grid[2][6] = 9;
        grid[3][0] = 9;
        //grid[3][3] = 9;
        //grid[3][4] = 9;
        //grid[3][8] = 9;
        //grid[4][0] = 9;
        //grid[4][3] = 9;
        grid[4][6] = 9;
        //grid[4][8] = 9;
        //grid[5][1] = 9;
        //grid[5][3] = 9;
        //grid[6][1] = 9;
        grid[6][5] = 9;
        //grid[6][6] = 9;
        //grid[7][2] = 9;
        //grid[7][3] = 9;
        //grid[7][7] = 9;
        grid[7][8] = 9;
        //grid[8][7] = 9;
        
        return grid;
    }
    
    public static int[][] SINGLE_SQUARE_CHALLENGE()  {
        grid[0][0] = 1;
        grid[0][1] = 2;
        grid[0][2] = 3;
        grid[1][0] = 4;
        grid[1][1] = 5;
        grid[1][2] = 6;
        grid[2][0] = 7;
        grid[2][1] = 8;
        grid[2][2] = 9;
        
        return grid;
    }
    
    public static int[][] UNSOLVABLE_CHALLENGE()  {
        grid[0][0] = 2;
        grid[0][3] = 9;
        grid[1][7] = 6;
        grid[2][5] = 1;
        grid[3][0] = 5;
        grid[3][2] = 2;
        grid[3][3] = 6;
        grid[3][6] = 4;
        grid[3][8] = 7;
        grid[4][5] = 4;
        grid[4][6] = 1;
        grid[5][4] = 9;
        grid[5][5] = 8;
        grid[5][7] = 2;
        grid[5][8] = 3;
        grid[6][5] = 3;
        grid[6][7] = 8;
        grid[7][2] = 5;
        grid[7][4] = 1;
        grid[8][2] = 7;
        
        return grid;
    }
    
    public static int[][] MULTIPLE_OUTCOME_CHALLENGE()  {
        grid[0][3] = 8;
        grid[0][5] = 1;
        grid[1][6] = 4;
        grid[1][7] = 3;
        grid[2][0] = 5;
        grid[3][4] = 7;
        grid[3][6] = 8;
        grid[5][1] = 2;
        grid[5][4] = 3;
        grid[6][0] = 6;
        grid[6][7] = 7;
        grid[6][8] = 5;
        grid[7][2] = 3;
        grid[7][3] = 4;
        grid[8][3] = 2;
        grid[8][6] = 6;
        
        return grid;
    }
}


 /*
        // populate grid with starting numbers
        gamestate1.setNumber(0,0,8);
        gamestate1.setNumber(1,2,3);
        gamestate1.setNumber(1,3,6);
        gamestate1.setNumber(2,1,7);
        gamestate1.setNumber(2,4,9);
        gamestate1.setNumber(2,6,2);
        gamestate1.setNumber(3,1,5);
        gamestate1.setNumber(3,5,7);
        gamestate1.setNumber(4,4,4);
        gamestate1.setNumber(4,5,5);
        gamestate1.setNumber(4,6,7);
        gamestate1.setNumber(5,3,1);
        gamestate1.setNumber(5,7,3);
        gamestate1.setNumber(6,2,1);
        gamestate1.setNumber(6,7,6);
        gamestate1.setNumber(6,8,8);
        gamestate1.setNumber(7,2,8);
        gamestate1.setNumber(7,3,5);
        gamestate1.setNumber(7,7,1);
        gamestate1.setNumber(8,1,9);
        gamestate1.setNumber(8,6,4);
         */     
