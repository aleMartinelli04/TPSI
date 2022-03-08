package threads.es2;

public class Main {
    public static int count = 0;

    public static void main(String[] args) {
        new Thread(new IncrementOf(1)).start();
        new Thread(new IncrementOf(-1)).start();
    }
}
