package PracticaParcial.Trafico;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;





public class Puente {
    private int maxCapacidadPuente; //capacidad maxima de vehiculos que pueden estar en el puente de manera simultanea
    private int cantAutosPasaron=0; //cantidad de autos que pasaron en un ciclo
    private int cantAutosEnPuente=0;    //cantidad de autos que hay en el puente al mismo tiempo
    private char semaforo='0'; // N norte, S sur
    private Lock lock;
    private Condition sur;  //grupo de espera para autos que vienen del sur
    private Condition norte;    //grupo de espera para autos que vienen del norte
    private Condition salidaOrden; //grupo de espera para que salgan en orden del puente
    private Cola cola; //para llevar el orden de salida del puente

    private boolean hayAutoDireccionOpuesta=false;
    private boolean quedanAutosEnDireccionActual=false;
    //private boolean cambiarSemaforo=false;
    public Puente(int maxPuente){
        this.maxCapacidadPuente=maxPuente;
        this.lock=new ReentrantLock();
        this.sur=lock.newCondition();
        this.norte=lock.newCondition();
        this.salidaOrden=lock.newCondition();
        this.cola=new Cola();
    }

    public void entrarAlPuente(int id,char direccion) throws InterruptedException {
        lock.lock();
        if(semaforo=='0'){// el que llega primero al puente la primera vez setea el semaforo
            semaforo=direccion;
        }
        while(semaforo!=direccion || cantAutosPasaron>=maxCapacidadPuente ){
            if(semaforo!=direccion){
                hayAutoDireccionOpuesta=true;
            }else{
                quedanAutosEnDireccionActual=true;
            }
            
            if(direccion=='N'){
                norte.await();
            }else{
                sur.await();
            }
        }
        cola.poner(id);
        cantAutosEnPuente++;
        cantAutosPasaron++;
        
        System.out.println("****** El auto "+id+" con direccion "+direccion+" ingresó al PUENTE ");

        lock.unlock();
    }

    public void salirDelPuente(int id,char direccion) throws InterruptedException {
        lock.lock();
        while((int)cola.obtenerFrente()!=id){
            salidaOrden.await();
            salidaOrden.signal();//signal en cadena TEST, sino cambiar a signalAll() abajo!
        }
        cola.sacar();
        cantAutosEnPuente--;
        System.out.println("El auto "+id+" con direccion "+direccion+" salió del PUENTE ****** ");
        salidaOrden.signal();
       
        if((cantAutosPasaron>=maxCapacidadPuente || !quedanAutosEnDireccionActual ) && hayAutoDireccionOpuesta &&cantAutosEnPuente==0 ){
            //si (ya pasaron los 10 o ya no quedan mas autos en esa direccion ) y el puente está vacio
            cambiarSemaforo();
        }else if(cantAutosPasaron>=maxCapacidadPuente && quedanAutosEnDireccionActual && !hayAutoDireccionOpuesta)
            cantAutosPasaron--;
            if(semaforo=='N'){
                norte.signal();
            }else{
                sur.signal();
            }

        lock.unlock();
    }

    public void cambiarSemaforo(){
        lock.lock();
        if(semaforo=='N'){//está en verde para los que vienen del norte
            System.out.println("*Semaforo en verde para el SUR*");
            semaforo='S';
            sur.signalAll();
        }else{//está en verde para los que vienen del sur

            System.out.println("*Semaforo en verde para el NORTE*");
            semaforo='N';
            norte.signalAll();
        }
        cantAutosPasaron=0;
        quedanAutosEnDireccionActual=false;
        hayAutoDireccionOpuesta=false;
        //cambiarSemaforo=false;
        lock.unlock();
    }
}
