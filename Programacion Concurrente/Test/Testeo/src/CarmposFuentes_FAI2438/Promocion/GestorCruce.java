package CarmposFuentes_FAI2438.Promocion;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GestorCruce {
    private Semaphore norte;
    private Semaphore oeste;
    private Semaphore pasar;
    private ReentrantLock lock;//un auto a la vez

    public GestorCruce() {
        this.norte = new Semaphore(1);
        this.oeste = new Semaphore(0);
        this.pasar = new Semaphore(1);
    }

    public void llegaNorte(int id) throws InterruptedException {
        norte.acquire();
        pasar.acquire();
        System.out.println("soy auto "+id+" y vengo del norte");
        Thread.sleep(5000);
        norte.release();
    }

    

    public void llegaOeste(int id) throws InterruptedException {
        oeste.acquire();
        pasar.acquire();
        System.out.println("soy auto "+id+" y vengo del oeste");
        Thread.sleep(5000);
        oeste.release();
    }

    public void sale() {
        pasar.release();
    }

    public void cambiarSemaforo() {
        if(norte.tryAcquire()){
            norte.release();
        }else{
            oeste.release();
        }
    }
}
