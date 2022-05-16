package threads.EsercizioBarSynchronized;

public class Barista implements Runnable {

    DistributoreTicket d;

    public Barista(DistributoreTicket d) {
     this.d = d;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Il barista " + Thread.currentThread().getId() +
                                " sta attendendo di servire un cliente");
            int cliente = d.checkUtentiInAttesa();
            System.out.println("Il barista " + Thread.currentThread().getId() +
                                " sta servendo il cliente " + cliente);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
