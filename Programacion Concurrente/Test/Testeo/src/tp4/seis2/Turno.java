package tp4.seis2;

import java.util.concurrent.Semaphore;

public class Turno {
    private Semaphore semA;
    private Semaphore semB;
    private Semaphore semC;

    public Turno() {
        this.semA=new Semaphore(1);
        this.semB=new Semaphore(0);
        this.semC=new Semaphore(0);
    }

    
    public void mostrarA() throws InterruptedException{
        this.semA.acquire();
        System.out.print("A");
        this.semB.release();
    }
    public void mostrarB() throws InterruptedException {
        this.semB.acquire();
        System.out.print("BB");
        this.semC.release();
    }
    public void mostrarC() throws InterruptedException {
        this.semC.acquire();
        System.out.print("CCC");
        this.semA.release();
    }
}
