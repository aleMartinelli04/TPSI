package bank;

import bank.exceptions.BankException;
import org.junit.Test;

import static org.junit.Assert.*;

public class BankAccountTest {

    public static BankAccount mario = new BankAccount("Mario", "Super", 50_000);
    public static BankAccount luigi = new BankAccount("Luigi", "Pensa", 1_000_000);

    @Test
    public void getFirstName() {
        assertEquals("Mario", mario.getFirstName());
        assertEquals("Waluigi", luigi.getFirstName()); // sbagliato
    }

    @Test
    public void getLastName() {
        assertEquals("Super", mario.getLastName());
        assertEquals("Waluigi", luigi.getLastName()); // sbagliato
    }

    @Test
    public void getFullName() {
        assertEquals("Mario Super", mario.getFullName());
        assertEquals("Waluigi", luigi.getFullName()); // sbagliato
    }

    @Test
    public void getBalance() {
        assertEquals(50_000, mario.getBalance(), 0.1);
        assertEquals(0, luigi.getBalance(), 0.1); // sbagliato
    }

    @Test
    public void transferTo() throws BankException {
        mario.transferTo(luigi, 5_000);

        assertEquals(45_000, mario.getBalance(), 0.1);
        assertEquals(1_000_000, luigi.getBalance(), 0.1); // sbagliato
    }

    @Test
    public void withdraw() throws BankException {
        luigi.withdraw(5_000);

        assertEquals(1_000_000, luigi.getBalance(), 0.1);
    }
}