package tp4.seis2;

import java.util.concurrent.Semaphore;

public class Turno {
    private Semaphore semA;
    private Semaphore semB;
    private Semaphore semC;

    public Turno() {
        this.semA = new Semaphore(1);
        this.semB = new Semaphore(0);
        this.semC = new Semaphore(0);
    }

    public void mostrarA(int cant) throws InterruptedException {
        this.semA.acquire();
        for (int i = 0; i < cant; i++) {
            System.out.print("A");
        }
        this.semB.release();
    }

    public void mostrarB(int cant) throws InterruptedException {
        this.semB.acquire();
        for (int i = 0; i < cant; i++) {
            System.out.print("B");
        }
        this.semC.release();
    }

    public void mostrarC(int cant) throws InterruptedException {
        this.semC.acquire();
        for (int i = 0; i < cant; i++) {
            System.out.print("C");
        }
        this.semA.release();
    }
}
