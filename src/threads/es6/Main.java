package threads.es6;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Thread mario = new ContoCorrenteThread(Cointestatari.MARIO);
        Thread lucia = new ContoCorrenteThread(Cointestatari.LUCIA);

        mario.start();
        lucia.start();

        mario.join();
        lucia.join();

        System.out.println("Sul conto corrente ci sono ancora " + Cointestatari.contoCorrente + "$");
    }
}