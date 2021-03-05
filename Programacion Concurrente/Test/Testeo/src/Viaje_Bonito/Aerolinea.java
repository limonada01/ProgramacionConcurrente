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
    private static Reloj reloj;

    //private Condition hallEspera;
    public Aerolinea(int nroAerolinea,Vuelo[] vuelos,int max, Reloj reloj){
        this.numeroAerolinea=nroAerolinea;
        this.vuelos=vuelos;
        this.max=max;
        this.ordenDeLlegada=new Cola();
        this.reloj=reloj;
    }

    public synchronized Vuelo realizarCheckIn(int id) throws InterruptedException {
        Vuelo retorno;
        ordenDeLlegada.poner(id);
        while(cantidadActual>= max ){
            System.out.println("*** El puesto de atencion de la aerolinea "+numeroAerolinea+" está lleno, el pasajero "+id+" debe esperar para ser atendido...");
            while(cantidadActual>=max || (int)ordenDeLlegada.obtenerFrente()!=id){//si aun esta lleno o no es su turno, espera
                this.wait();
            }
        }
        ordenDeLlegada.sacar();//quito de la cola de orden de llegada al que logró ingresar
        cantidadActual++;
        System.out.println(ConsoleColors.PURPLE_BOLD+"*** El pasajero "+id+ " ingresó a el puesto de atencion de la aerolinea "+numeroAerolinea+" para realizar el check IN"+ConsoleColors.RESET);
        retorno=asignarVuelo();//asigno vuelo al pasajero
        return retorno;
    }

    public synchronized void terminarCheckIn(int id){
        cantidadActual--;
        System.out.println(ConsoleColors.PURPLE_BOLD+"*** El pasajero "+id+ " salió del puesto de atencion de la aerolinea "+numeroAerolinea+" y deja un lugar."+ConsoleColors.RESET );
        this.notifyAll();
    }
//vuelo: asignar en puesto de informe
    public Vuelo asignarVuelo(){//el vuelo debe estar sujeto a la hora actual y horario de salida del vuelo
        
        Vuelo vuelo=null;
        int cantVuelos=vuelos.length-1;
        boolean buscando=true;
        int i=0;
        while(buscando && cantVuelos!=i){
            if(reloj.calcularVuelo(vuelos[i].getHorario())){
                //si el horario del vuelo es en 1 hora y 30 min o mas, se lo asigno al pasajero, siempre debe haber un VUELO sino el programa se ROMPERA!
                vuelo=vuelos[i];
                buscando=false;
            }
            i++;
        }
        return vuelo;

    }

}
