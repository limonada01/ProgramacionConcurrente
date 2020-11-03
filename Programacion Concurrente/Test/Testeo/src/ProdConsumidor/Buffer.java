package ProdConsumidor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;



public class Buffer {
    private Semaphore tamaño;//buffer
    private ReentrantLock mutex;
    private Semaphore prod;//sem para producir
    public Buffer(int tam){
        this.tamaño=new Semaphore(tam);
        this.mutex=new ReentrantLock();
        this.prod=new Semaphore(0); 
    }

    public void producir(int id) throws InterruptedException {
        prod.acquire();//pide permisos para producir ( no los tendra hastá q tenga espacio para producir (buffer no lleno))
        mutex.lock();// exclusion mutua       
        tamaño.release();//doy un permiso para consumir
        Thread.sleep(1000);//simulacion para producir
        System.out.println("*****Productor "+id+" PUDO PRODUCIR!");
        mutex.unlock(); //libero la zona de exclusion para otro hilo
    };
    public void consumir(int id) throws InterruptedException {
        tamaño.acquire();//consumo un producto , en caso de no haber, espera hasta que se pueda tomar 1 permiso ( un productor produzca == libere un permiso)
        mutex.lock();//exclusion mutua
        System.out.println("Consumidor "+id+" pudo CONSUMIR!*****");
        Thread.sleep(1000); //simula tiempo de consumo
        prod.release();//permite q un productor produzca, ( avisa que hay espacio para producir 1 producto)
        mutex.unlock();// libero la zona de exclusion para otro hilo
    };
}
