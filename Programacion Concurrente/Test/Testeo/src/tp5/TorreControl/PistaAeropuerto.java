package tp5.TorreControl;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class PistaAeropuerto {
   // private Semaphore pista;
   // private int flag;// 0 despega | 1 aterriza
    private Semaphore cantAterrizajes;
    private int cantAte;
    //private ReentrantLock mute;
    private Semaphore semDespegar;
    private Semaphore semAterrizar;
    private Semaphore semControl;
    private int prioridad;
    public PistaAeropuerto(int prioridad) {
       // this.pista = new Semaphore(1);
       // this.flag = -1;
        this.cantAterrizajes = new Semaphore(prioridad);
        this.cantAte = 0;
        //this.mute = new ReentrantLock();
        this.prioridad=prioridad;
        this.semDespegar = new Semaphore(0);
        this.semAterrizar = new Semaphore(1);
        this.semControl = new Semaphore(0);
    }

   

    public void despegar(int idAvion) throws InterruptedException {
        semDespegar.acquire();
        System.out.println("***************El avion " + idAvion + " comienza a Despegar");
        Thread.sleep(15000);
        System.out.println("***************El avion " + idAvion + " DESPEGA con exito");
        semControl.release();
        
    }

    public void aterrizar(int idAvion) throws InterruptedException {
        semAterrizar.acquire();
        cantAterrizajes.acquire();
        cantAte++;
        System.out.println("El avion " + idAvion + " comienza a aterrizar**************");
        Thread.sleep(15000);
        System.out.println("El avion " + idAvion + " aterriza con exito***************");
        
        if (cantAte == prioridad) {
            semControl.release();
        } else {
            semAterrizar.release();
        }

    }

    public void control() throws InterruptedException {
        semControl.acquire();
        if(cantAte==prioridad){
            cantAte=0;
            cantAterrizajes.release(prioridad);
            semDespegar.release();
        }else{
            semAterrizar.release();
        }
        
    }
}
