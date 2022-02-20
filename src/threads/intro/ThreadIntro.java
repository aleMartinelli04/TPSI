package threads.intro;

import java.sql.Timestamp;

public class ThreadIntro {
    static int num = 0;

    public static void main(String[] args) {
        Thread increment = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("INCREMENTO di num che vale " + num + " diventa " + ++num);
                System.out.println("INCREMENTATO a " + num + " al momento " + new Timestamp(System.currentTimeMillis()));
            }
        });

        Thread decrement = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("DECREMENTO di num che vale   " + num + " diventa " + --num);
                System.out.println("DECREMENTATO a " + num + " al momento " + new Timestamp(System.currentTimeMillis()));
            }
        });

        increment.start();
        decrement.start();
    }
}