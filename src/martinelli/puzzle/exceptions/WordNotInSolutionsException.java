package martinelli.puzzle.exceptions;

import martinelli.puzzle.Colors;

public class WordNotInSolutionsException extends PuzzleException {
    public WordNotInSolutionsException(String word) {
        super("Word not in solutions: " + Colors.getColoredString(word, Colors.RED));
    }
}
