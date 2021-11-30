package martinelli.puzzle.exceptions;

import martinelli.puzzle.Colors;

public class SolutionAlreadyFoundException extends PuzzleException {
    public SolutionAlreadyFoundException(String message) {
        super("Solution already found: " + Colors.getColoredString(message, Colors.RED));
    }
}
