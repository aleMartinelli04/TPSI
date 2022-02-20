package threads.intro;

import java.sql.Timestamp;

public class ThreadIntro {
    static int num = 0;

    public static void main(String[] args) {
        new Thread(new ThreadIncrement()).start();
        new Thread(new ThreadDecrement()).start();
    }

    private static class ThreadIncrement implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println(Colors.THR1.color("INCREMENTO di num che vale " + num + " diventa " + ++num));
                System.out.println(Colors.THR1.color("INCREMENTATO a " + num + " al momento " + new Timestamp(System.currentTimeMillis())));
            }
        }
    }

    private static class ThreadDecrement implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println(Colors.THR2.color("DECREMENTATO di num che vale " + num + " diventa " + --num));
                System.out.println(Colors.THR2.color("DECREMENTATO a " + num + " al momento " + new Timestamp(System.currentTimeMillis())));
            }
        }
    }
}