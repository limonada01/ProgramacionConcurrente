package ProdConsumidorMonitores;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

import javax.management.monitor.Monitor;

public class Buffer {
    private int actual;//buffer
    private int tamFinal;//buffer
    private Object consumidor=new Object();//para sincronizar consumidores 
    private Object productor=new Object();//para sincronizar productores
    public Buffer(int tam){
        this.actual=tam;
        this.tamFinal=tam;
    }

    public synchronized void producir(int id) throws InterruptedException {
        synchronized(productor){
            while(actual==tamFinal){
                productor.wait();
            }
            actual++;
            System.out.println("*****Productor "+id+" PUDO PRODUCIR!");
            Thread.sleep(1000);//simulacion para producir
            consumidor.notify();
            
           
        }  
    }
    public synchronized void consumir(int id) throws InterruptedException {
        synchronized(consumidor){
            while(actual==0){
                consumidor.wait();
            }
            actual--;
            System.out.println("Consumidor "+id+" pudo CONSUMIR!*****");
            Thread.sleep(1000); //simula tiempo de consumo
            productor.notify();
        }   
    }
}
