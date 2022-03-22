package threads.es5;

public class ContoCorrente {
    private double saldo;

    public ContoCorrente(double saldo) {
        this.saldo = saldo;
    }

    public synchronized void preleva(double saldo) throws Exception {
        if (saldo <= 0) {
            throw new Exception("Saldo non valido");
        }

        if (saldo > this.saldo) {
            throw new Exception("Non puoi prelevare " + saldo + " $ perché ne hai solo " + this.saldo);
        }

        this.saldo -= saldo;
    }

    public void deposita(double saldo) {
        if (this.saldo > 0) {
            this.saldo += saldo;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(saldo);
    }
}
