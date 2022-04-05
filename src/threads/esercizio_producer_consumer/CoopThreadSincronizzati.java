package threads.esercizio_producer_consumer;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class Buffer {
    private final int[] data;
    private int nr_elem = 10;

    private boolean empty = true;

    public Buffer(int elem) {
        if (elem != -1) {
            data = new int[elem];

            nr_elem = elem;
        } else {
            data = new int[nr_elem];
        }
    }

    public synchronized void write(int ix) throws InterruptedException {
        while (!empty) {
            System.out.printf("%s attendere che CONSUMER legga il dato ...%n", Thread.currentThread().getName());
            wait();
        }

        data[ix] = ix;
        empty = false;
        System.out.printf("%s ha scritto all'indice %d il valore: %d%n", Thread.currentThread().getName(), ix, data[ix]);
        notifyAll();
    }

    public synchronized int read(int ix) throws InterruptedException {
        while (empty) {
            System.out.printf("%s attendere che PRODUCER scriva il dato ...%n", Thread.currentThread().getName());
            wait();
        }

        System.out.printf("%s ha letto all'indice %d il valore %d%n", Thread.currentThread().getName(), data[ix], ix);
        empty = true;
        notifyAll();

        return data[ix];
    }

    public int getBufferElements() {
        return nr_elem;
    }
}

class Producer implements Runnable {
    private Buffer b;

    public Producer(Buffer b) {
        this.b = b;
    }

    @Override
    public void run() {
        for (int i = 0; i < b.getBufferElements(); i++) {
            Random r = new Random();
            int ms = r.nextInt(5_000);

            try {
                Thread.sleep(ms);
                b.write(i);
            } catch (InterruptedException ignored) {
            }
        }
    }
}

class Consumer implements Runnable {
    private Buffer b;

    public Consumer(Buffer b) {
        this.b = b;
    }

    @Override
    public void run() {
        for (int i = 0; i < b.getBufferElements(); i++) {
            int ms = ThreadLocalRandom.current().nextInt(5_000);

            try {
                Thread.sleep(ms);
                b.read(i);
            } catch (InterruptedException ignored) {
            }
        }
    }
}

public class CoopThreadSincronizzati {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(-1);

        Thread t1 = new Thread(new Producer(buffer), "PRODUCER");
        Thread t2 = new Thread(new Consumer(buffer), "CONSUMER");

        t1.start();
        t2.start();
    }
}