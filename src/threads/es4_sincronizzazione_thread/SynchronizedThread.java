package threads.es4_sincronizzazione_thread;

public class SynchronizedThread {
    public static void main(String[] args) {

    }
}

class MakeOperations {
    private int data;

    public void doOp(int v) {
        System.out.printf("Il valore del dato dal thread %s è", Thread.currentThread().getName());

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);

                data += v;

                System.out.println(getRes() + i == 4 ? "\n" : " ");

            } catch (InterruptedException e) {
                System.out.println("Il thread si è interrotto");
            }
        }
    }

    private int getRes() {
        return data;
    }

}

class RunThread1 implements Runnable {
    private final MakeOperations mop;

    public RunThread1(MakeOperations mop) {
        this.mop = mop;
    }

    @Override
    public void run() {
        mop.doOp(10);
    }
}

class RunThread2 implements Runnable {
    private final MakeOperations mop;

    public RunThread2(MakeOperations mop) {
        this.mop = mop;
    }

    @Override
    public void run() {
        mop.doOp(-10);
    }
}

