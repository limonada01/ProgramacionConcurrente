package tp5.TrenTuristico;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Estacion {
    private Semaphore semCompra;
    private ReentrantLock mute;//suban, bajen de a 1 pasajero a la vez
    private Semaphore semVenderTicket;
    private Semaphore asientosTren;
    private Semaphore semArrancarTren;
    private Semaphore semBajarPasajero;
    private Semaphore semSubirTren;
    private int cont;
    private int totalAsientos;
    public Estacion(int cantAsientos){
        this.semCompra=new Semaphore(1);
        this.mute=new ReentrantLock();
        this.semVenderTicket=new Semaphore(0);
        this.asientosTren=new Semaphore(cantAsientos);
        this.semBajarPasajero=new Semaphore(0);
        this.semArrancarTren=new Semaphore(0);
        this.semSubirTren=new Semaphore(1);
        this.cont=cantAsientos;
        this.totalAsientos=cantAsientos;
        
    }

    public void comprarTicket(int idPasajero) throws InterruptedException {
        semCompra.acquire();
        System.out.println("El pasajero "+idPasajero+" solicita un TICKET");
        semVenderTicket.release();
    }
    public void venderTicket() throws InterruptedException {
        semVenderTicket.acquire();
        System.out.println("El vendedor Vende un ticket");
        semCompra.release(); 
    }

    public void subirAlTren(int idPasajero) throws InterruptedException {
        semSubirTren.acquire();
        asientosTren.acquire();
        mute.lock();
        cont--;
        mute.unlock();
        System.out.println("El pasajero "+idPasajero+" se subió al TREN!");
        if(cont==0){
            semArrancarTren.release();
        }else{
            semSubirTren.release();
        }
        
    }
    public void recorridoTren() throws InterruptedException {//inicia y termina el recorrido
        
        semArrancarTren.acquire();
        //semSubirTren.acquire();//no permite q nadie mas intente subir al tren
        System.out.println("***El tren se lleno y arranca el recorrido...***");
        Thread.sleep(15000);
        System.out.println("***El tren acaba el recorrido y permite que los pasajero se bajen...***");
        semBajarPasajero.release();
    }

    public void bajarDelTren(int idPasajero) throws InterruptedException {
        
        semBajarPasajero.acquire();
        mute.lock();
        cont++;
        mute.unlock();
        System.out.println("El pasajero "+idPasajero+" se BAJO del TREN!");
        asientosTren.release();//libera un asiento el pasajero
        if(cont!=totalAsientos){
            semBajarPasajero.release();
        }else{
            semSubirTren.release();//permito que entren al tren los pasajeros en espera
        }
        
        
    }

	
}
