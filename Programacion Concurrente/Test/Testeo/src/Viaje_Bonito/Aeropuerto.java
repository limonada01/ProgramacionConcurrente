package Viaje_Bonito;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Aeropuerto {
    private boolean abierto = false;
    private Lock lock;
    private Lock lockTren;
    private Condition aeropuertoCerrado;
    private Condition trenEspera;
    private int capacidadMaxTren;
    private int cantActualEnTren=0;
    private boolean trenViajando=false;
    //private Semaphore semTren;

    // private Semaphore semPuestoInforme=new Semaphore(1);
    private Aerolinea[] aerolineas;

    public Aeropuerto(Aerolinea[] aerolineas, int capacidadMaxTren) {
        this.lock = new ReentrantLock();
        this.lockTren=new ReentrantLock();
        this.aeropuertoCerrado = lock.newCondition();
        this.trenEspera=lockTren.newCondition();
        this.aerolineas = aerolineas;
        this.capacidadMaxTren=capacidadMaxTren;
        
        //this.semTren = new Semaphore(1);
    }

    public Aerolinea ingresarPuestoDeInforme(int id, int nroAerolinea) throws InterruptedException {
        lock.lock();
        Aerolinea retorno;
        // semPuestoInforme.acquire();
        while (!abierto) {// cuando el aeropierto esté cerrado
            System.out.println("** Pasajero " + id
                    + " el Aeropuerto se encuentra cerrado en este momento, abre a las 6:00 AM !** ");
            aeropuertoCerrado.await();
        }
        System.out.println("*** El pasajero número " + id
                + " ha ingresado al puesto de informe y se le informó sobre su puesto de atención");
        retorno = aerolineas[nroAerolinea];// retorna Vuelo
        return retorno;
    }

    public void salirPuestoDeInforme(int id) throws InterruptedException {
        System.out.println("El pasajero número " + id + " ha salido del puesto de informe ***");
        // semPuestoInforme.release();
        lock.unlock();
    }

    public void abrirAeropuerto() {
        abierto = true;
        System.out.println("** Aeropuerto ABIERTO hasta las 22:00 AM ! **");
        aeropuertoCerrado.signalAll();// despierto a todos los pasajeros que estan esperando a que abra el aeropuerto
    }

    public void cerrarAeropuerto() {
        abierto = false;
        System.out.println("** Aeropuerto CERRADO hasta las 6:00 AM ! **");
    }

    public void subirAlTren(int id) throws InterruptedException, BrokenBarrierException {
        lockTren.lock();
 
        while(trenViajando || cantActualEnTren >= capacidadMaxTren){//si el tren está viajando o ya está lleno deberá esperar
            System.out.println("** Pasajero "+id+" espera para subir al Tren ** ");
            trenEspera.await();
            trenEspera.signal();//en cadena
        }
        cantActualEnTren++;
        System.out.println("El pasajero "+id+" consigue subir al tren y espera a que parta");
        if(cantActualEnTren == capacidadMaxTren){// falta hacer que arranque desp de determinado tiempo si aun no se llena!!
            comenzarRecorridoTren();//comienza el viaje
        }
        lockTren.unlock();
    }

    public int getNumeroAerolineas(){
        return this.aerolineas.length;
    }

    public void comenzarRecorridoTren(){
        this.trenViajando=true;
        // aca debo darle la funcionalidad al tren cuando ya está lleno y listo para arrancar
    }

    
    public void trenListoParaQueSuban(){
        this.trenViajando=false;
    }

    public void bajarDelTren(int id){
        System.out.println("** El pasajero "+id+" baja del tren! **");
        cantActualEnTren--;

    }
}
