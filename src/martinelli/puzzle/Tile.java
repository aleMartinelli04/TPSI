package martinelli.puzzle;

public class Tile {
    private final char character;
    private boolean checked;
    private Colors temp;

    public Tile(char character) {
        this.character = Character.toUpperCase(character);
        this.checked = false;
    }

    public char getCharacter() {
        return character;
    }

    public boolean isChecked() {
        return checked;
    }

    public void check() {
        checked = true;
        temp = Colors.JUST_CHECKED;
    }

    public boolean hasCharacter(char character) {
        return this.character == character;
    }

    @Override
    public String toString() {
        Colors color;

        if (temp != null){
            color = temp;
            temp = null;

        } else {
            color = checked ? Colors.CHECKED : Colors.UNCHECKED;
        }

        return Colors.getColoredString(character, color);
    }
}
