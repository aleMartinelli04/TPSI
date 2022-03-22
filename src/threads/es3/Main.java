package threads.es3;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(new IncrementOf(1));
        Thread b = new Thread(new IncrementOf(-1));

        a.start();
        b.start();

        a.join();
        b.join();

        System.out.println("Contatore vale: " + count.get());
    }
}
