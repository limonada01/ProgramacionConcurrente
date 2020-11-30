package LectoresEscritoresMod;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Libro {
    private int cantPaginasEscritas;
    private final int maxPaginas;
    private ReadWriteLock lock;
    
    public Libro(int maxPaginas){
        this.maxPaginas=maxPaginas;
        this.cantPaginasEscritas=0;
        this.lock=new ReentrantReadWriteLock();
    }
   
    public boolean empezarLeer(int id) throws InterruptedException {
        boolean exito=false;
        lock.readLock().lock();
        if(cantPaginasEscritas!=0){
            exito=true;
            System.out.println("** Lector "+id+" empieza a leer! **" );
        }else{
            System.out.println("Lector "+id+": *** NO HAY PAGINAS ESCRITAS! ****");
            lock.readLock().unlock();
        }
        
        return exito;
    }

    public  boolean empezarEscribir(int id) throws InterruptedException {
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
