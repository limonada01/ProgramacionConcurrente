package ProdConsumidorMonitores;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

import javax.management.monitor.Monitor;

public class Buffer {
    private int actual;//buffer
    private int tamFinal;//buffer
    private Object consumidor=new Object();//para sincronizar consumidores 
    private Object productor=new Object();//para sincronizar productores
    private ReentrantLock mutex;
    public Buffer(int tam){
        this.actual=tam;
        this.tamFinal=tam;
        this.mutex=new ReentrantLock();
    }

    public  void producir(int id) throws InterruptedException {
        mutex.lock();
        synchronized(productor){
            while(actual==tamFinal){
                System.out.println("PRODUCTOR A DORMIR!!!");
                productor.wait();
            }
            actual++;
            System.out.println("*****Productor "+id+" PUDO PRODUCIR!");
            Thread.sleep(1000);//simulacion para producir
            synchronized(consumidor){
            consumidor.notify(); 
            }
            System.out.println("****");   
        }
        mutex.unlock(); 
    }
    public void consumir(int id) throws InterruptedException {
        mutex.lock();
        synchronized(consumidor){

            while(actual==0){
                System.out.println("CONSUMIDOR A DORMIR!!!");
                consumidor.wait();
            }
            actual--;
            System.out.println("Consumidor "+id+" pudo CONSUMIR!*****");
            Thread.sleep(1000); //simula tiempo de consumo
            synchronized(productor){
            productor.notify();
            }
            System.out.println("****");
        }   
        mutex.unlock();
    }
}
