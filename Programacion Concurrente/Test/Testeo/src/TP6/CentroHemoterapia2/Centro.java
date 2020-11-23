package TP6.CentroHemoterapia2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Centro {
    private int cantCamillas;//4
    private int camillasActual;//contador de camillas en uso
    private Lock mutex;
    private int cantidadRevistas;
    private int revistasActual;
    //private Condition leyendo;
   // private Condition noticiero;
    private Condition turno;
    private Cola cola;
    public Centro(int cantCamillas,int cantidadRevistas){
        this.cantCamillas=cantCamillas;
        this.camillasActual=0;
        this.cantidadRevistas=cantidadRevistas;
        this.revistasActual=0;
        this.mutex=new ReentrantLock();
       // this.leyendo=this.mutex.newCondition();
       // this.noticiero=this.mutex.newCondition();
        this.turno=this.mutex.newCondition();
        this.cola=new Cola();

    }

    public void entrar(int id) throws InterruptedException {
        mutex.lock();
        cola.poner(id);//pone en la cola la id de la persona que busca camilla
        while(camillasActual>=cantCamillas){

            System.out.println("La persona "+id+" no consigue una camilla");
            while(revistasActual>=cantidadRevistas  && ((int)cola.obtenerFrente()!=id || camillasActual>=cantCamillas)){
                //si no hay revistas y (no es su turno o estan todas las camas ocupadas)
                System.out.println("La persona "+id+" no puede tomar una revista y se pone a ver el noticiero");
                turno.await();//pone a esperar a la persona viendo el noticiero ya que aun no puede tomar una revista
            }
            if((int)cola.obtenerFrente()!=id || camillasActual>=cantCamillas){//si es su turno no toma revista
                revistasActual++;//toma una revista
                System.out.println("La persona "+id+" logra tomar una revista y se pone a leer");
                while(camillasActual>=cantCamillas || (int)cola.obtenerFrente()!=id  ){//mientras no sea su turno o aun estan todas las camas ocupadas, espera con la revista
                    turno.await();//pone a esperar a la persona con la revista
                }
                revistasActual--;//devuelve la revista para que otro pueda tomarla
                System.out.println("La persona "+id+" devuelve la revista para que otro la tome");
                turno.signalAll();//avisa a los que estan viendo el noticiero para que pueda tomar la revista
            }
        }
        cola.sacar();//quita de la cola el id de la persona que consigue camilla
        camillasActual++;
        System.out.println("La persona "+id+" consigue una camilla para donar sangre");
        mutex.unlock();
    }

    public void salir(int id){
        mutex.lock();
        
        camillasActual--;
        System.out.println("La persona "+id+" termina de donar sangre y se va");
        turno.signalAll();
        mutex.unlock();
    }
    
}
