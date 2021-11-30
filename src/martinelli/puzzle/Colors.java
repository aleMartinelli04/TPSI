package martinelli.puzzle;

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
    CHECKED(GREEN.ansiCode),
    UNCHECKED(PURPLE.ansiCode),
    JUST_CHECKED(YELLOW.ansiCode);


    private final String ansiCode;

    Colors(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    public static String getColoredString(char string, Colors color) {
        return getColoredString(String.valueOf(string), color);
    }

    public static String getColoredString(String string, Colors color) {
        return color.ansiCode + string + RESET.ansiCode;
    }
}
