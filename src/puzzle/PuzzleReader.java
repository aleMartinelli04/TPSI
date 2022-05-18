package puzzle;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PuzzleReader {
    private final List<String> files;

    public PuzzleReader() throws IOException {
        this.files = getFiles();
    }

    private List<String> getFiles() throws IOException {
        Stream<Path> paths = Files.walk(Paths.get("src/martinelli/puzzle/puzzleFiles"));

        return paths
                .filter(Files::isRegularFile)
                .map(path -> {
                    String[] filename = path.toString().split("/");
                    return filename[filename.length - 1].split(".txt")[0];
                })
                .collect(Collectors.toList());
    }


    private void verifyFile(String filename) {
        if (!files.contains(filename)) {
            throw new IllegalArgumentException();
        }
    }

    private String randomFilename() {
        return files.get(ThreadLocalRandom.current().nextInt(0, files.size()));
    }

    private String getRelativePath(String filename) {
        return "verifica_17_maggio/martinelli/puzzle/puzzleFiles/%s.txt".formatted(filename);
    }


    public Puzzle readFile(String filename) throws FileNotFoundException {
        verifyFile(filename);

        filename = getRelativePath(filename);

        Scanner fileReader = new Scanner(new FileReader(filename));

        int rows = fileReader.nextInt();
        int columns = fileReader.nextInt();

        fileReader.nextLine();

        char[] table = fileReader.nextLine().toCharArray();

        String[] solutions = fileReader.nextLine().split(" ");

        String hint = fileReader.nextLine();

        String[] hintIndexesStr = fileReader.nextLine().split(" ");
        int[] hintIndexes = Arrays.stream(hintIndexesStr).mapToInt(Integer::parseInt).toArray();

        return new Puzzle(rows, columns, table, solutions, hint, hintIndexes);
    }

    public Puzzle readFile() throws FileNotFoundException {
        return readFile(randomFilename());
    }
}