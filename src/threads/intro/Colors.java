package threads.intro;

public enum Colors {
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),
    THR1(GREEN.ansiCode),
    THR2(PURPLE.ansiCode);

    private final String ansiCode;

    Colors(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    public String color(String string) {
        return ansiCode + string + RESET.ansiCode;
    }
}
