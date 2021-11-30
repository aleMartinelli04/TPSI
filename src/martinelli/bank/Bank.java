package martinelli.bank;

import martinelli.bank.exceptions.BankException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Bank {
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final Object lock = new Object();

    public void transfer(BankAccount source, BankAccount destination, double amount) throws BankException {
        if (amount <= 0) {
            throw new BankException("Amount must be > 0");
        }
        executor.submit(() -> {
            synchronized (lock) {
                try {
                    source.transferTo(destination, amount);
                } catch (BankException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }
}
