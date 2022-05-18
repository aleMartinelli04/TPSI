package verifica_17_maggio;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Magazzino magazzino = new Magazzino(60);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci motocoltivatori presenti (multiplo di 60):");
        String input = scanner.nextLine();

        try {
            int num = Integer.parseInt(input);

            if (num % 60 != 0) {
                throw new Exception("Il numero deve essere un multiplo di 60");
            }

            Thread produttore = new Thread(new Produttore(magazzino, num));
            Thread trasportatore = new Thread(new Trasportatore(magazzino, num));

            trasportatore.setPriority(Thread.MAX_PRIORITY);

            trasportatore.start();
            produttore.start();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
