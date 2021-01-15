package LectoresEscritoresMod;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Libro {
    private int cantPaginasEscritas;
    private final int maxPaginas;
    private ReadWriteLock lock;
    
    public Libro(int maxPaginas,int cantPaginasEscritas){
        this.cantPaginasEscritas=cantPaginasEscritas;
        this.maxPaginas=maxPaginas;
        this.lock=new ReentrantReadWriteLock();
    }
   
    public void empezarLeer(int id) throws InterruptedException {
        lock.readLock().lock();
        System.out.println("** Lector "+id+" empieza a leer! **" );
    }

    public boolean empezarEscribir(int id) throws InterruptedException {
        boolean exito=false;
        lock.writeLock().lock();
        if(cantPaginasEscritas<maxPaginas){
            exito=true;
            System.out.println("** Escritor "+id+" empieza a escribir! **" );
        }else{
            System.out.println("** Escritor "+id+": EL libro está completo, no se puede escribir mas! **" );
            exito=false;
            lock.writeLock().unlock();
        }
        return exito;
    }

    public  void terminarLeer(int id){
        System.out.println("** Lector "+id+" Termina de leer! **" );
        lock.readLock().unlock();
    }

    public  void terminarEscribir(int id){
        cantPaginasEscritas++;
        System.out.println("** Escritor "+id+" Termina de escribir! **" );
        lock.writeLock().unlock();
        
    }
}
