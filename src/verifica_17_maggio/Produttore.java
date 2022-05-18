package verifica_17_maggio;

import java.util.concurrent.ThreadLocalRandom;

public class Produttore implements Runnable {
    private final Magazzino magazzino;
    private int prodotte;
    private final int daProdurre;

    public Produttore(Magazzino magazzino, int daProdurre) {
        this.magazzino = magazzino;
        this.prodotte = 0;
        this.daProdurre = daProdurre;
    }

    @Override
    public void run() {
        while (prodotte < daProdurre) {
            synchronized (magazzino) {
                while (magazzino.pieno()) {
                    try {
                        System.out.println("Produttore - n motocoltivatori presenti=" + magazzino.getNumGinko706() + ". Produzione momentaneamente ferma...");

                        magazzino.notifyAll();
                        magazzino.wait();
                    } catch (Exception ignored) {
                    }
                }

                magazzino.aggiungiMotocoltivatori(30);
                prodotte += 30;
                System.out.println("Produttore - " +
                        "n motocoltivatori presenti=" + magazzino.getNumGinko706() + ", " +
                        "n motocoltivatori prodotte=" + magazzino.getCapienzaMassima() + ", " +
                        "n tot prodotte=" + prodotte + " di " + daProdurre + ", " +
                        "n motocoltivatori aggiornato=" + magazzino.getNumGinko706());

                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1500));
                } catch (InterruptedException ignored) {
                }
            }
        }

        System.out.println("Prodotti tutti i motocoltivatori!");
    }
}
