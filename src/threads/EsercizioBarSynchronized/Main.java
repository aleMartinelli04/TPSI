package threads.EsercizioBarSynchronized;

public class Main {

    public static void main (String[] args) {
        // Viene avviato il distributore dei ticket
        DistributoreTicket distributoreTicket = new DistributoreTicket();

        // Viene creata l'istanza di Barista e avviato il suo thread
        Barista barista = new Barista(distributoreTicket);
        Thread b = new Thread(barista);
        b.start();

        // Istanzio e avvio 10 clienti
        for (int i=0; i<10; i++) {
            Cliente cliente = new Cliente(distributoreTicket);
            Thread c = new Thread(cliente);
            c.start();
        }

    }

}
