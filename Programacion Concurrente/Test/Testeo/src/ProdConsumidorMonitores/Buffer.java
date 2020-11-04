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
        
        synchronized(productor){
            
            while(actual==tamFinal){
                System.out.println("PRODUCTOR A DORMIR!!!");
                mutex.unlock();
                productor.wait();
            }
            
            mutex.lock();
            
            actual++;
            mutex.unlock(); 
            System.out.println("*****Productor "+id+" PUDO PRODUCIR!");
              
        }
            synchronized(consumidor){
            consumidor.notify(); 
            }
            
            
        
        
    }
    public  void consumir(int id) throws InterruptedException {
        
        synchronized(consumidor){
            
            while(actual==0){
                System.out.println("CONSUMIDOR A DORMIR!!!");
                
                consumidor.wait();
            }
           
            mutex.lock();
            actual--;
            mutex.unlock();
            System.out.println("Consumidor "+id+" pudo CONSUMIR!*****");
            
        }
            synchronized(productor){    
            productor.notify();
            }
            
            
           
        
    }
}
