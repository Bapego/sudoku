package hu.barath.data;

import java.io.BufferedReader;
import java.io.FileReader;
public class SolveSudoku {

    //Global variables

    private int SIZE = 9;
    private Integer[][] sudokuTable;
    private String fileName;
    private int numb = 0;
    int row;
    int col;

    //Constructors
    public SolveSudoku() throws Exception {
    }

    /**
     * It reads data into the matrix from file and examines the accuracy of the data
     * @param fileName
     * @throws Exception
     */
    public SolveSudoku(String fileName) throws Exception {
        this.fileName = fileName;

        sudokuTable = new Integer[SIZE][SIZE];
        FileReader reader = new FileReader(fileName);
        BufferedReader buff = new BufferedReader(reader);

        // row = first row
        String row = buff.readLine();

        //we fill sudokuTable by characters from this row
        try {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {


                    Character elem = row.charAt(j);

                    if (elem != ' ') {
                        //Put the number into a correct cell
                        sudokuTable[i][j] = Character.getNumericValue(elem);
                        numb++;
                    }
                    else{
                        //if it's space, the value change zero
                        sudokuTable[i][j] = 0;
                    }

                }
                //row = next row
                row = buff.readLine();

            }
        }catch (Exception e) {
            System.out.println("Error with datas");
            e.printStackTrace();
        }

        buff.close();
        reader.close();
    }

    //Getters, setters

    /**
     * 
     * @return
     */
    public int getSIZE() {
        return SIZE;
    }

    /**
     * 
     * @return
     */
    public Integer[][] getSudokuTable() {
        return sudokuTable;
    }

    /**
     * It solves the sudoku
     * @param current
     * @return
     * @throws Exception
     */
    public Boolean solve(Cell current) throws Exception {

        if (current == null)
            return true;

        if (sudokuTable[current.row][current.col] != 0) {
            //If it is not null, we step to next cell
            return solve(next(current));
        }

        for (int i = 1; i <= 9; i++) {
            //It checks this value is valid or not
            boolean ok = isOk(current, i);

            //if it is valid
            if (ok)
            {
                //Put this value into cell
                sudokuTable[current.row][current.col] = i;

                //Next cell
                boolean solved = solve(next(current));

                //if solved is true, return is true and Controll class will check this
                if (solved) return true;

                //if solved is false, current cell's value is zero
                else sudokuTable[current.row][current.col] = 0;

            }


        }
        return false;
    }

    /**
     * 
     * @param current
     * @return
     */
    private Cell next(Cell current) {
        int row = current.row;
        int col = current.col;

        //next value in the row
        col++;

        //if it is the last value in row, we step on the next column's first value
        if (col > 8) {
            col = 0;
            row++;
        }

        //if it is the last value, it returns null and Controll class will chech this
        if (row > 8) return null;


        Cell next = new Cell(row, col);
        //it returns next cell
        return next;
    }

    /**
     * it checks the value is valid
     * @param cell
     * @param value
     * @return
     */
    private Boolean isOk(Cell cell, int value) {


        //if cell's column has same value it returns false
        for (int c = 0; c < 9; c++) {
            if (sudokuTable[cell.row][c] == value)
                return false;
        }

        // if cell's row has same value it returns false
        for (int r = 0; r < 9; r++) {
            if (sudokuTable[r][cell.col] == value)
                return false;
        }

        //if cell's block has same value it returns false
        int a = 3 * (cell.row / 3);
        int b = 3 * (cell.col / 3);
        int a1 = a + 2;
        int a2 = b + 2;

        for (int x = a; x <= a1; x++)
            for (int y = b; y <= a2; y++)
                if (sudokuTable[x][y] == value)
                    return false;


        return true;
    }

    /**
     * It checks the start table's number of items and this umber is less than 17, it will returns false
     */
    
    public Boolean seventeen() {
            if (numb <= 16) return true;
            return false;
    }

    /**
     * it prints the table
     */
    public void printTable() {

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    System.out.print(sudokuTable[i][j] + "| ");

                }
                System.out.println();

            }
    }

    /**
     * It change the matrix into a string line
     * @return
     */
    public String toString()
    {
        String table = "";
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                table += sudokuTable[i][j].toString();

            }
        }
        return table;
    }

}