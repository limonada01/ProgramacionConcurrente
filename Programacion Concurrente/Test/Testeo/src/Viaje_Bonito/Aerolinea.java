package Viaje_Bonito;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Aerolinea {
    private int numeroAerolinea;
    private boolean atendiendo=false;//bandera para saber si se está atendiendo a un pasajero en el puesto de atencion
    private Vuelo[] vuelos;
    private int max;// cantidad maxima de personas dentro del puesto de atencion
    private int cantidadActual=0;
    private Lock lockPuestoAtencion;
    private Condition esperaHall;
    private Condition esperaSerAtendido;
    private Cola ordenDeLlegada;
    private static Reloj reloj;

    //private Condition hallEspera;
    public Aerolinea(int nroAerolinea,Vuelo[] vuelos,int max, Reloj reloj){
        this.numeroAerolinea=nroAerolinea;
        this.vuelos=vuelos;
        this.max=max;
        this.ordenDeLlegada=new Cola();
        this.reloj=reloj;
        this.lockPuestoAtencion= new ReentrantLock();
        this.esperaSerAtendido= lockPuestoAtencion.newCondition();
        this.esperaHall= lockPuestoAtencion.newCondition();
    }

    public void realizarCheckIn(int id) throws InterruptedException {
        lockPuestoAtencion.lock();
        hallCentral(id);//verifica si tiene q esperar en el hall central
        ordenDeLlegada.poner(id);//se pone en la cola de llegada cuando entra al PUESTO DE ATENCION, NO EN EL HALL!
        cantidadActual++;//sumo 1 a la cantidad de personas actualmente dentro del puesto de atencion
        while(atendiendo || (int)ordenDeLlegada.obtenerFrente()!=id){//si se está atendiendo a una persona o no es su turno segun el orden de llegada , deberá esperar dentro del puesto de atencion hasta ser atendido 
            System.out.println(ConsoleColors.PURPLE_BOLD+" ** Pasajero: "+id+": Hay un pasajero siendo atendido en este momento en el puesto de atencion de la aerolinea "+numeroAerolinea+"... debe esperar en el PUESTO DE ATENCION "+ConsoleColors.RESET);
            esperaSerAtendido.await();
        }
        ordenDeLlegada.sacar();//quito de la cola de orden de llegada al que acaban de atender
        atendiendo=true;//ahora toca que lo atiendan al pasajero que acaba de salir del while en caso de que le haya tocado esperar
        System.out.println(ConsoleColors.PURPLE_BOLD+"*** El pasajero "+id+ " del puesto de atencion de la aerolinea "+numeroAerolinea+" consigue realizar el check IN"+ConsoleColors.RESET);
        lockPuestoAtencion.unlock();
    }

    public void hallCentral(int id) throws InterruptedException {
        
        while(cantidadActual>=max){
            System.out.println(ConsoleColors.PURPLE_BOLD+"*** Puesto de atencion de la aerolinea "+numeroAerolinea+" ESTA LLENO, el pasajero "+id+" debe esperar en el HALL CENTRAL..."+ConsoleColors.RESET );
            esperaHall.await();
            System.out.println(ConsoleColors.PURPLE_BOLD+"Se liberó un lugar en el puesto de atencion y guardia permite entrada al pasajero "+id+ConsoleColors.RESET);
        }
        
        
    }
    public void terminarCheckIn(int id){
        lockPuestoAtencion.lock();
        System.out.println(ConsoleColors.PURPLE_BOLD+"*** El pasajero "+id+ " salió del puesto de atencion de la aerolinea "+numeroAerolinea+" y deja un lugar."+ConsoleColors.RESET );
        cantidadActual--;
        atendiendo=false;//se puede atender al siguiente en la cola
        esperaSerAtendido.signalAll();// notifico a los que esperan a ser atendidos
        esperaHall.signal();//notifico al hall central que hay un lugar disponible dentro del puesto de atencion
        lockPuestoAtencion.unlock();
    }

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
