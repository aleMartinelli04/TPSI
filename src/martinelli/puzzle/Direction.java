package martinelli.puzzle;

public enum Direction {
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST;

    public static Coordinate getNextCoordinate(Direction direction, int row, int column) {
        return switch (direction) {
            case NORTH -> new Coordinate(row - 1, column);
            case NORTH_EAST -> new Coordinate(row - 1, column + 1);
            case EAST -> new Coordinate(row, column + 1);
            case SOUTH_EAST -> new Coordinate(row + 1, column + 1);
            case SOUTH -> new Coordinate(row + 1, column);
            case SOUTH_WEST -> new Coordinate(row + 1, column - 1);
            case WEST -> new Coordinate(row, column - 1);
            case NORTH_WEST -> new Coordinate(row - 1, column - 1);
        };
    }

    public static Coordinate getNextCoordinate(Direction direction, Coordinate coordinate) {
        return getNextCoordinate(direction, coordinate.getRow(), coordinate.getColumn());
    }
}
