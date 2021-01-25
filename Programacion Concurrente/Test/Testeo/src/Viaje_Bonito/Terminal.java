package Viaje_Bonito;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import TP6.Trafico.A.Semaforo;

public class Terminal {
    private char id;
    
    private int cantPersonasActual=0;
    private int maxPersonas;
    private Semaphore cajas;
    private Lock lockFreeShop;
    private Condition lleno;
    public Terminal(char id, int maxPersonasFreeShop){
        this.id=id;
        this.maxPersonas=maxPersonasFreeShop;
        this.cajas=new Semaphore(2);
        this.lockFreeShop=new ReentrantLock();
        this.lleno=lockFreeShop.newCondition();
        
        
    }

    public void entrarFreeShop(int id) throws InterruptedException {
        lockFreeShop.lock();
        while(cantPersonasActual>=maxPersonas){
            System.out.println("Pasajero "+id+" aun no puede ingresar a la freeshop de la terminal "+this.id);
            lleno.await();
        }
        cantPersonasActual++;
        System.out.println("Pasajero "+id+" entró a la Freeshop de la Terminal "+this.id);
        lockFreeShop.unlock();
    }

    public  void salirFreeShop(int id) throws InterruptedException {
        cajas.acquire();
        lockFreeShop.lock();
        System.out.println("Pasajero "+id+" pasó por caja y salió de la Freeshop de la Terminal "+this.id);
        cantPersonasActual--;
        lockFreeShop.unlock();
        cajas.release();
        
    }
    public void esperarEmbarque(int id){
        System.out.println("Pasajero "+id+" espera en la sala de embaque de la terminal "+this.id);
    }
}
