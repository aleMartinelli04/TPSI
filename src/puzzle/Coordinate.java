package puzzle;

public class Coordinate {
    private final int column;
    private final int row;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "[" + row + ", " + column + "]";
    }
}
