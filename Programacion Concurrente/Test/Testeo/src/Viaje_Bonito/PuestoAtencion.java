package Viaje_Bonito;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PuestoAtencion {
    private int numeroAerolinea;
    private int max;// cantidad maxima de personas dentro del puesto de atencion
    private int cantidadActual=0;
    
    private Condition hallEspera;
    public PuestoAtencion(int nroAerolinea,int max){
        this.numeroAerolinea=nroAerolinea;
        this.max=max;
        
    }

    public synchronized void realizarCheckIn(int id) throws InterruptedException {
        
        while(cantidadActual>= max){
            System.out.println("*** El puesto de atencion de la aerolinea "+numeroAerolinea+" está lleno, el pasajero "+id+" debe esperar para ser atendido...");
            
            this.wait();
        }
        cantidadActual++;
        System.out.println("*** El pasajero "+id+ " ingresó a el puesto de atencion de la aerolinea "+numeroAerolinea+" para realizar el check IN");
        
    }

    public synchronized void terminarCheckIn(int id){
        
        cantidadActual--;
        System.out.println("*** El pasajero "+id+ " salió del puesto de atencion de la aerolinea "+numeroAerolinea+" y deja un lugar." );
        this.notify();
        
    }


}
