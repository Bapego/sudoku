package hu.barath.ctrl;

import hu.barath.data.Cell;
import hu.barath.data.SolveSudoku;
import hu.barath.data.Database;


public class Controller {

    private String easyTable = "src/easy1.sudoku";
    private String regTable = "src/regular1.sudoku";
    private String diffTable = "src/difficult1.sudoku";
    private String extTable = "src/extreme1.sudoku";
    String task, solve;
    private Cell start = new Cell(0, 0);
    SolveSudoku table;
    Database db;


    public Controller() throws Exception {

        //try to read data
        try {
            boolean done = false;

            //Read data from file
            table = new SolveSudoku(extTable);

            //Is table size 9x9?
            try {

                //we check value's count
                if (table.seventeen()) {
                    System.out.println("Numbers's count is less than 17!");
                    done = false;
                }
                else {
                    //we print the start table
                    System.out.println("Start table:");
                    table.printTable();
                    task = table.toString();

                    //we solve the task and done represents is solvable or not
                    done = table.solve(start);
                }
                //try to print the solution
                try {
                    if (done) {
                        System.out.println("Final table:");
                        table.printTable();
                        solve = table.toString();
                        //try to save it into a database
                        try {
                            //task = start table, solve = final table
                            db = new Database(task, solve);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else System.out.println("Task is not solvable or it has more solution");
                } catch (Exception e) {
                    System.out.println(e);
                }

            } catch (Exception e) {
                System.out.println(e);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
