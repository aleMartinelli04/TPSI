package puzzle.exceptions;

import puzzle.Colors;

public class SolutionAlreadyFoundException extends PuzzleException {
    public SolutionAlreadyFoundException(String message) {
        super("Solution already found: " + Colors.getColoredString(message, Colors.RED));
    }
}
