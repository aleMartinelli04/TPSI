package verifica_17_maggio;

public class Trasportatore implements Runnable {
    private final Magazzino magazzino;
    private int trasportate;
    private final int daTrasportare;

    public Trasportatore(Magazzino magazzino, int daTrasportare) {
        this.magazzino = magazzino;
        this.trasportate = 0;
        this.daTrasportare = daTrasportare;
    }

    @Override
    public void run() {
        while (trasportate < daTrasportare) {
            synchronized (magazzino) {
                while (!magazzino.pieno()) {
                    try {
                        System.out.println("Trasportatore - n motocoltivatori presenti=" + magazzino.getNumGinko706() + ", Trasportatore in attesa della merce...");

                        magazzino.wait();
                        magazzino.notifyAll();
                    } catch (InterruptedException ignored) {
                    }
                }

                int numGinkoPresenti = magazzino.svuota();
                trasportate += magazzino.getCapienzaMassima();
                System.out.println("Trasportatore - " +
                        "n motocoltivatori presenti=" + numGinkoPresenti + ", " +
                        "n motocoltivatori caricate=" + magazzino.getCapienzaMassima() + ", " +
                        "n tot trasportate=" + trasportate + " di " +  daTrasportare + ", " +
                        "n motocoltivatori aggiornato=" + magazzino.getNumGinko706());

                magazzino.notifyAll();
            }
        }

        System.out.println("Trasportate " + trasportate + " su " + daTrasportare + ". Trasportatore spento.");
    }
}
