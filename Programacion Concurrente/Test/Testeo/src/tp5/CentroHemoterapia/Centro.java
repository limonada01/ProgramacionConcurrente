package tp5.CentroHemoterapia;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Centro {
    private int cantCamillas;//4
    private int camillasActual;//contador de camillas en uso
    private Lock mutex;
    private int cantidadRevistas;
    private int revistasActual;
    //private Condition camillas;
    private Condition leyendo;
    private Condition noticiero;
    //private boolean tieneRevista;//para verificar si debe soltar una revista cuando pueda tomar una camilla
    public Centro(int cantCamillas,int cantidadRevistas){
        this.cantCamillas=cantCamillas;
        this.camillasActual=0;
        this.cantidadRevistas=cantidadRevistas;
        this.revistasActual=0;
        this.mutex=new ReentrantLock(true);
        //this.camillas=this.mutex.newCondition();
        this.leyendo=this.mutex.newCondition();
        this.noticiero=this.mutex.newCondition();
        //this.tieneRevista=false;
    }

    public void entrar(int id) throws InterruptedException {
        mutex.lock();
        while(camillasActual>=cantCamillas){

            System.out.println("La persona "+id+" no consigue una camilla");
            while(revistasActual>=cantidadRevistas){
                System.out.println("La persona "+id+" no puede tomar una revista y se pone a ver el noticiero");
                noticiero.await();//pone a esperar a la persona viendo el noticiero ya que aun no puede tomar una revista
            }
            revistasActual++;//toma una revista
            System.out.println("La persona "+id+" logra tomar una revista y se pone a leer");
            leyendo.await();//pone a esperar a la persona con la revista
            revistasActual--;//devuelve la revista para que otro pueda tomarla
            System.out.println("La persona "+id+" devuelve la revista para que otro la tome");
            noticiero.signalAll();//avisa a los que estan viendo el noticiero para que pueda tomar la revista
        }
        camillasActual++;
        System.out.println("La persona "+id+" consigue una camilla para donar sangre");
        mutex.unlock();
    }

    public void salir(int id){
        mutex.lock();
        camillasActual--;
        System.out.println("La persona "+id+" termina de donar sangre y se va");
        leyendo.signal();
        mutex.unlock();
    }
    
}
