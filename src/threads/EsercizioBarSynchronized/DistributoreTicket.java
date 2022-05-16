package threads.EsercizioBarSynchronized;

public class DistributoreTicket {

    int numeroTicket, numeroUtenteServito;

    public DistributoreTicket() {
        numeroTicket = 0;
        numeroUtenteServito = 0;
    }

    public void setValoriIniziali(int numero) {
        numeroTicket = numero;
        numeroUtenteServito = numero;
    }

    public synchronized int ritiraTicket() {
        numeroTicket++;
        notifyAll();
        return numeroTicket;
    }

    public synchronized void attendiNumeroTicket(int numero) {
        while (numeroTicket != numero) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Controllo se ci sono clienti in attesa di essere serviti
    public synchronized int checkUtentiInAttesa() {
        while (numeroTicket == numeroUtenteServito) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        numeroUtenteServito++;
        notifyAll();
        return numeroUtenteServito;
    }

}
