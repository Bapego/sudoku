package hu.barath.data;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database {
    public Database(String task, String solved) throws Exception {
        createTable();
        insert(task, solved);

    }

    /**
     * we insert start and final table into Database
     *
     * @param task   - Start table(string)
     * @param solved - Final table(string)
     * @throws Exception
     */
    private void insert(String task, String solved) throws Exception {

        //try to insert
        try {
            //connect to server
            Connection con = connect();

            //Insert start and final table
            PreparedStatement posted = con.prepareStatement("INSERT INTO sudoku_table (exercise, solve) VALUES ('" + task + "','" + solved + "')");

            posted.executeUpdate();
            System.out.println("Insert Completed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return - a Connection
     * @throws Exception
     */
    private Connection connect() throws Exception {
        try {
            //we set a driver and we connect mysql server
            String driver = "com.mysql.cj.jdbc.Driver";
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sudoku?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

            Class.forName(driver);
            System.out.println("Connection is done.");

            return conn;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Create a sudoku table
     *
     * @throws Exception
     */

    private void createTable() throws Exception {
        try {
            //connection
            Connection conn = connect();

            //we create a new table if it is not exist
            PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS sudoku_table(id INT NOT NULL AUTO_INCREMENT, exercise varchar(81), solve varchar(81), PRIMARY KEY(id))");
            create.executeUpdate();

            System.out.println("Table is created");
        } catch (Exception e) {
            System.out.println(e);
        }


    }


}
