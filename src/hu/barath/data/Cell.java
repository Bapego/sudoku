package hu.barath.data;

public class Cell {

    int row, col;

    /**
     * @param row
     * @param col
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * @return - the row and column of cell
     */
    public String toString() {
        return "row: " + row + ", column: " + col + "\n";
    }
}
