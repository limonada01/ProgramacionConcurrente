package Viaje_Bonito;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Aerolinea {
    private int numeroAerolinea;
    private Vuelo[] vuelos;
    private int max;// cantidad maxima de personas dentro del puesto de atencion
    private int cantidadActual=0;
    private Cola ordenDeLlegada;

    //private Condition hallEspera;
    public Aerolinea(int nroAerolinea,Vuelo[] vuelos,int max ){
        this.numeroAerolinea=nroAerolinea;
        this.vuelos=vuelos;
        this.max=max;
        this.ordenDeLlegada=new Cola();
    }

    public synchronized Vuelo realizarCheckIn(int id) throws InterruptedException {
        Vuelo retorno;
        ordenDeLlegada.poner(id);
        while(cantidadActual>= max ){
            System.out.println("*** El puesto de atencion de la aerolinea "+numeroAerolinea+" está lleno, el pasajero "+id+" debe esperar para ser atendido...");
            while(cantidadActual>=max || (int)ordenDeLlegada.obtenerFrente()==id){
                this.wait();
                this.notify();//en cadena
            }
        }
        ordenDeLlegada.sacar();//quito de la cola de orden de llegada al que logró ingresar
        cantidadActual++;
        System.out.println("*** El pasajero "+id+ " ingresó a el puesto de atencion de la aerolinea "+numeroAerolinea+" para realizar el check IN");
        retorno=asignarVuelo();//asigno vuelo al pasajero
        return retorno;
    }

    public synchronized void terminarCheckIn(int id){
        
        cantidadActual--;
        System.out.println("*** El pasajero "+id+ " salió del puesto de atencion de la aerolinea "+numeroAerolinea+" y deja un lugar." );
        this.notify();
        
    }

    public Vuelo asignarVuelo(){//el vuelo debe estar sujeto a la hora actual y horario de salida del vuelo
        Random random=new Random();
        return vuelos[random.nextInt(vuelos.length)];
        // METODO TEMPORAL, DEBE SER ARREGLADO!!
    }

}
