package threads.esercizio_producer_consumer;

public class PizzaioloCliente {
    public static void main(String[] args) {
        Pizzeria pizzeria = new Pizzeria(10, 5_000);

        Thread pizzaiolo = new Thread(new Pizzaiolo(pizzeria), "Pizzaiolo");
        Thread cliente = new Thread(new Cliente(pizzeria), "Cliente");

        pizzaiolo.start();
        cliente.start();
    }
}

class Pizzeria {
    private final int[] pizze;

    private boolean empty;
    private final int SLEEP_TIME;

    public Pizzeria(int size, int sleepTime) {
        this.pizze = new int[size];
        this.empty = true;
        this.SLEEP_TIME = sleepTime;
    }

    public synchronized void aggiungi(int pizza) throws InterruptedException {
        while (!empty) {
            System.out.printf("%s attendere che PIZZAIOLO faccia la pizza... %n", Thread.currentThread().getName());
            wait();
        }

        pizze[pizza] = pizza;
        empty = false;
        System.out.printf("%s ha fatto la pizza: %d%n", Thread.currentThread().getName(), pizza);
        notifyAll();
    }

    public synchronized void togli(int pizza) throws InterruptedException {
        while (empty) {
            System.out.printf("%s attendere che PIZZAIOLO faccia la pizza ...%n", Thread.currentThread().getName());
            wait();
        }

        System.out.printf("%s ha ordinato la pizza %d%n", Thread.currentThread().getName(), pizza);
        empty = true;
        notifyAll();

    }

    public int getSize() {
        return pizze.length;
    }

    public int getSLEEP_TIME() {
        return SLEEP_TIME;
    }
}


class Pizzaiolo implements Runnable {
    private final Pizzeria pizzeria;

    public Pizzaiolo(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }

    @Override
    public void run() {
        for (int i = 0; i < pizzeria.getSize(); i++) {
            try {
                Thread.sleep(pizzeria.getSLEEP_TIME());
                pizzeria.aggiungi(i);
            } catch (InterruptedException ignored) {
            }
        }
    }
}


class Cliente implements Runnable {
    private final Pizzeria pizzeria;

    public Cliente(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }

    @Override
    public void run() {
        for (int i = 0; i < pizzeria.getSize(); i++) {
            try {
                Thread.sleep(pizzeria.getSLEEP_TIME());
                pizzeria.togli(i);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
