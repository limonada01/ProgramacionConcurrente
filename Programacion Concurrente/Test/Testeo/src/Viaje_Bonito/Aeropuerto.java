package Viaje_Bonito;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Aeropuerto {
    private boolean abierto=false;
    private Lock lock;
    private Condition aeropuertoCerrado;
    //private Semaphore semPuestoInforme=new Semaphore(1);
    private Aerolinea[] aerolineas;
    
    public Aeropuerto( Aerolinea[] aerolineas){
        this.lock=new ReentrantLock();
        this.aeropuertoCerrado=lock.newCondition();
        this.aerolineas=aerolineas;
        
    }

    public Aerolinea ingresarPuestoDeInforme(int id,int nroAerolinea) throws InterruptedException {
        lock.lock();
        Aerolinea retorno;
        //semPuestoInforme.acquire();
        while(!abierto){//cuando el aeropierto esté cerrado 
            System.out.println("** Pasajero "+id+" el Aeropuerto se encuentra cerrado en este momento, abre a las 6:00 AM !** ");
            aeropuertoCerrado.await();
        }
        System.out.println("*** El pasajero número "+id+" ha ingresado al puesto de informe y se le informó sobre su puesto de atención");
        retorno= aerolineas[nroAerolinea];//retorna Vuelo 
        return retorno;
    }
    public void salirPuestoDeInforme(int id) throws InterruptedException {
        System.out.println("El pasajero número "+id+" ha salido del puesto de informe ***");
        //semPuestoInforme.release();
        lock.unlock();
    }




    public int getNumeroAerolineas(){
        return this.aerolineas.length;
    }
    
    public void abrirAeropuerto(){
        abierto=true;
        System.out.println("** Aeropuerto ABIERTO hasta las 22:00 AM ! **");
        aeropuertoCerrado.signalAll();// despierto a todos los pasajeros que estan esperando a que abra el aeropuerto
    }
    
    public void cerrarAeropuerto(){
        abierto=false;
        System.out.println("** Aeropuerto CERRADO hasta las 6:00 AM ! **");
    }
    

}
