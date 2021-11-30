package martinelli.puzzle;

import martinelli.puzzle.exceptions.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle {
    private final Tile[][] board;

    private final int rows;
    private final int columns;

    private final String[] solutions;
    private final List<String> foundSolutions;

    private final String hint;
    private final int[] hintIndexes;

    private boolean finished;


    public Puzzle(int rows, int columns, char[] table, String[] solutions, String hint, int[] hintIndexes) {
        if (table.length != rows * columns) {
            throw new IllegalArgumentException();
        }


        this.board = new Tile[rows][columns];

        this.rows = rows;
        this.columns = columns;

        this.solutions = solutions;
        this.foundSolutions = new ArrayList<>();

        this.hint = hint;
        this.hintIndexes = hintIndexes;

        this.finished = false;


        int index = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new Tile(table[index]);
                index++;
            }
        }
    }


    public List<Tile> findWord(String word) throws PuzzleFinishedException, WordNotInSolutionsException, SolutionAlreadyFoundException {
        if (finished) {
            throw new PuzzleFinishedException();
        }

        if (!hasSolution(word)) {
            throw new WordNotInSolutionsException(word);
        }

        if (solutionAlreadyFound(word)) {
            throw new SolutionAlreadyFoundException(word);
        }

        List<Tile> foundTiles = new ArrayList<>();
        word = word.toUpperCase();

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Tile currentTile;

                try {
                    currentTile = tileIn(row, column);
                } catch (NonExistentTileException e) {
                    continue;
                }

                if (!currentTile.hasCharacter(word.charAt(0))) {
                    continue;
                }

                foundTiles.add(currentTile);

                try {
                    continueToNextChar(word.substring(1), new Coordinate(row, column), null, foundTiles);
                } catch (WordEndedException e) {
                    foundSolutions.add(word);

                    if (foundSolutions.size() == solutions.length) {
                        finished = true;
                    }

                    return foundTiles;
                }
            }
        }

        return new ArrayList<>();
    }

    public String getSolution() throws PuzzleNotEndedException {
        if (!finished) {
            throw new PuzzleNotEndedException();
        }

        StringBuilder solution = new StringBuilder();

        for (Tile[] row : board) {
            for (Tile tile : row) {
                if (tile.isChecked()) {
                    continue;
                }

                solution.append(tile.getCharacter());
            }
        }

        StringBuilder toReturn = new StringBuilder();

        int lastIndex = 0;
        for (int hintIndex : hintIndexes) {
            toReturn.append(solution.substring(lastIndex, hintIndex + lastIndex));
            toReturn.append(" ");

            lastIndex = hintIndex;
        }

        return hint + ": " + Colors.getColoredString(toReturn.toString(), Colors.BLUE);
    }


    public String[] getSolutions() {
        return solutions.clone();
    }


    private boolean hasSolution(String word) {
        return Arrays.stream(solutions)
                .anyMatch(solution -> solution.equalsIgnoreCase(word));
    }

    private boolean solutionAlreadyFound(String word) {
        return foundSolutions.stream()
                .anyMatch(solutions -> solutions.equalsIgnoreCase(word));
    }


    private void continueToNextChar(String word, Coordinate coordinate, Direction direction, List<Tile> foundTiles) throws WordEndedException {
        if (direction == null) {
            for (Direction dir : Direction.values()) {
                Coordinate nextCoordinate = Direction.getNextCoordinate(dir, coordinate);

                Tile nextTile;

                try {
                    nextTile = tileIn(nextCoordinate);
                } catch (NonExistentTileException e) {
                    continue;
                }

                if (!nextTile.hasCharacter(word.charAt(0))) {
                    continue;
                }

                foundTiles.add(nextTile);

                if (word.length() == 1) {
                    throw new WordEndedException();
                }

                continueToNextChar(word.substring(1), nextCoordinate, dir, foundTiles);
            }

            foundTiles.clear();
            return;
        }

        Coordinate nextCoordinate = Direction.getNextCoordinate(direction, coordinate);
        Tile nextTile;
        try {
            nextTile = tileIn(nextCoordinate);
        } catch (NonExistentTileException e) {
            return;
        }

        if (!nextTile.hasCharacter(word.charAt(0))) {
            return;
        }

        foundTiles.add(nextTile);

        if (word.length() == 1) {
            throw new WordEndedException();
        }

        continueToNextChar(word.substring(1), nextCoordinate, direction, foundTiles);
    }


    private Tile tileIn(@NotNull Coordinate coordinate) throws NonExistentTileException {
        return tileIn(coordinate.getRow(), coordinate.getColumn());
    }

    private Tile tileIn(int column, int row) throws NonExistentTileException {
        try {
            return board[column][row];
        } catch (IndexOutOfBoundsException e) {
            throw new NonExistentTileException();
        }
    }


    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();

        for (Tile[] column : board) {
            for (Tile tile : column) {
                boardString.append(tile.toString()).append(" ");
            }

            boardString.append("\n");
        }

        return boardString.toString();
    }
}
