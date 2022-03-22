package threads.es6;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ContoCorrenteThread extends Thread {
    private final Cointestatari utente;

    private final static Random random = ThreadLocalRandom.current();

    public ContoCorrenteThread(Cointestatari utente) {
        this.utente = utente;
    }

    @Override
    public void run() {
        for (int i = 0; i < 15; i++) {
            if (i % 2 == 0) {
                int rand = random.nextInt(200);

                try {
                    utente.preleva(rand);
                    System.out.println(utente + " preleva " + rand + " -  ci sono ancora " + Cointestatari.contoCorrente + "$");
                } catch (Exception e) {
                    System.err.println(utente + ": " + e.getMessage());
                }

            } else {
                int rand = random.nextInt(20);

                Cointestatari.contoCorrente.deposita(rand);
                System.out.println(utente + " deposita " + rand + " - ci sono ancora " + Cointestatari.contoCorrente + "$");
            }
        }
    }
}