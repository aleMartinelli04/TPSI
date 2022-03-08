package threads.intro_v2;

public class IncrementOf implements Runnable {
    int amount;

    public IncrementOf(int amount) {
        this.amount = amount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Valore della variabile impostato da " + Main.count + " a " + (amount + Main.count));
            Main.count += amount;
        }
    }
}