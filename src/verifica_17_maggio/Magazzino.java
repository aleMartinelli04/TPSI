package verifica_17_maggio;

public class Magazzino {
    private int numGinko706;

    private final int CAPIENZA_MASSIMA;

    public Magazzino(int CAPIENZA_MASSIMA) {
        this.CAPIENZA_MASSIMA = CAPIENZA_MASSIMA;
    }

    public synchronized int getNumGinko706() {
        return numGinko706;
    }

    public synchronized void aggiungiMotocoltivatori(int num) {
        numGinko706 += num;
    }

    public synchronized boolean pieno() {
        return numGinko706 == CAPIENZA_MASSIMA;
    }

    public synchronized int svuota() {
        int numGinko706 = this.numGinko706;

        this.numGinko706 = 0;

        return numGinko706;
    }

    public int getCapienzaMassima() {
        return CAPIENZA_MASSIMA;
    }
}
