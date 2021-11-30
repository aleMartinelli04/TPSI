package martinelli.puzzle.exceptions;

public class PuzzleFinishedException extends PuzzleException {
    public PuzzleFinishedException() {
        super("Puzzle has already been finished!");
    }
}
