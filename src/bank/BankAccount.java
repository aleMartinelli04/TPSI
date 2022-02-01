package bank;

import bank.exceptions.BankException;

import java.util.UUID;

public class BankAccount {
    private final UUID uuid;
    private final String firstName;
    private final String lastName;
    private double balance;

    public BankAccount(String firstName, String lastName, double balance) {
        this.uuid = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public double getBalance() {
        return balance;
    }

    private boolean cannotPay(double balance) {
        return balance > this.balance;
    }

    private void setBalance(double balance) throws BankException {
        if (cannotPay(balance)) {
            throw new BankException("Balance can't be < 0");
        }

        this.balance = balance;
    }

    public void transferTo(BankAccount receiver, double amount) throws BankException {
        if (receiver == null) {
            throw new BankException("Receiver is null!");
        }

        if (cannotPay(amount)) {
            throw new BankException("You don't have enough money!");
        }

        balance -= amount;
        receiver.setBalance(receiver.getBalance() + balance);
    }

    public void withdraw(double amount) throws BankException {
        if (cannotPay(amount)) {
            throw new BankException("You don't have enough money!");
        }

        balance -= amount;
    }
}
