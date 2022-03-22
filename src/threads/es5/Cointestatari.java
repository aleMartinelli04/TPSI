package threads.es5;

public enum Cointestatari {
    MARIO("Mario"),
    LUCIA("Lucia");

    public static final ContoCorrente contoCorrente = new ContoCorrente(1_000);

    private final String nome;

    Cointestatari(String string) {
        this.nome = string;
    }

    public void preleva(double soldi) throws Exception {
        contoCorrente.preleva(soldi);
    }

    public void deposita(double soldi) {
        contoCorrente.deposita(soldi);
    }

    @Override
    public String toString() {
        return nome;
    }
}
