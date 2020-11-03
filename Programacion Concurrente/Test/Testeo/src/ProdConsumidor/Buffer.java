package ProdConsumidor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;



public class Buffer {
    private Semaphore tamaño;//buffer
    private ReentrantLock mutex;
    private int total;//total de permisos
    private int actual;//permisos actuales libres para consumir
    private Semaphore prod;
    public Buffer(int tam){
        this.tamaño=new Semaphore(tam);
        this.mutex=new ReentrantLock();
        this.total=tam;
        this.actual=tam;
        this.prod=new Semaphore(0); 
    }

    public void producir(int id) throws InterruptedException {
        if(actual==total){
            System.out.println("----> intento de producir!  ++++++++++++BUFFER LLENOO++++++++++++++");
            prod.acquire();    
        }
        tamaño.release();//doy un permiso para consumir
        System.out.println("*****Productor "+id+" PUDO PRODUCIR!");
        mutex.lock();
        this.actual++;
        mutex.unlock(); 
    };
    public void consumir(int id) throws InterruptedException {
        tamaño.acquire();
        System.out.println("Consumidor "+id+" pudo CONSUMIR!*****"); 
        mutex.lock();
        if(actual==total){
            System.out.println("++++++++++++BUFFER LIBERADO PARA PRODUCIR++++++++++++++");
            prod.release();
        }
        this.actual--;
        mutex.unlock();
    };
}
