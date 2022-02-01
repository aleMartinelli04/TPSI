package puzzle;

import puzzle.exceptions.*;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws PuzzleException, IOException {
        PuzzleReader puzzleReader = new PuzzleReader();

        Puzzle puzzle = puzzleReader.readFile();

        System.out.println(puzzle);

        for (String solution : puzzle.getSolutions()) {
            System.out.println("Finding word: " + solution);

            List<Tile> tiles = puzzle.findWord(solution);

            assert tiles != null;
            tiles.forEach(Tile::check);

            System.out.println(puzzle);
        }


        System.out.println(puzzle);

        System.out.println("Solution: " + puzzle.getSolution());
    }

}
