package threads.EsercizioBarSynchronized;

public class Cliente implements Runnable {

    DistributoreTicket d;

    public Cliente(DistributoreTicket d) {
        this.d = d;
    }

    @Override
    public void run() {
        int mioNumeroTicket = d.ritiraTicket();
        System.out.println("Il cliente " + Thread.currentThread().getId() +
                            " ha ricevuto il ticket con numero " + mioNumeroTicket);
        d.attendiNumeroTicket(mioNumeroTicket);
        System.out.println("Il cliente " + Thread.currentThread().getId() +
                            " sta entrando nel bar");
    }
}
