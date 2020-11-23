package TP6.Pasteleria;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Pasteleria {
    private Cola cola;
    
    private int pesoMaxCaja;
    private Lock mutex;
    private int cajaActual;
    private Condition empaquetador;//espera para tomarPastel
    private Condition empaquetadorCaja;//espera por una nueva caja
    private Condition brazo;//espera para poner una caja
    private boolean cambiarCaja;
    private boolean hayCaja;

    public Pasteleria(int pesoMaxCaja){
        this.cola=new Cola();
        this.pesoMaxCaja=pesoMaxCaja;
        this.mutex=new ReentrantLock();
        this.cajaActual=0;
        this.empaquetador=mutex.newCondition();
        this.brazo=mutex.newCondition();
        this.empaquetadorCaja=mutex.newCondition();
        this.cambiarCaja=false;
        this.hayCaja=true;
    }

    public void ponerPastelEnMostrador(char id,int pastel){//pastel A,B,C (peso)
        mutex.lock();
        System.out.println("Horno "+id+" pone pastel en el mostrador de "+pastel+" grs");
        cola.poner(pastel);
        empaquetador.signal();// aviso a los empaquetadores que hay pasteles
        mutex.unlock();
    }

    public void retirarCaja() throws InterruptedException { // invoca el brazo mecanico
        mutex.lock();
        while(!cambiarCaja){//si no hay q cambiar caja, el brazo espera
            brazo.await();
        }
        cambiarCaja=false;
        System.out.println("El brazo cierra la caja actual ("+cajaActual+" grs.) y la retira");
        hayCaja=false;
        mutex.unlock();
    }

    public void reponerCaja(){ // invoca el brazo mecanico
        mutex.lock();
        System.out.println("El brazo repone una caja vacia");
        cajaActual=0;
        hayCaja=true;
        empaquetadorCaja.signalAll();//avisa a que ya hay una nueva caja vacia
        mutex.unlock();
    }

    public int tomarPastel(int id) throws InterruptedException { // invoca el empaquetador
        int peso=0;
        mutex.lock();
        while( cola.esVacia()){//si no hay pasteles en mostrador, el empaquetador espera
            empaquetador.await();
        }
        peso=(int)cola.obtenerFrente();
        cola.sacar();//saca el pastel del mostador
        System.out.println("*El empaquetador "+id+" toma el pastel (" +peso+" grs. )*");
        mutex.unlock();
        return peso;
    }

    public void soltarPastel(int id,int peso) throws InterruptedException { // invoca el empaquetador
        mutex.lock();
        while((cajaActual+peso)>pesoMaxCaja || !hayCaja){
            System.out.println("***** La caja no tiene sufieciente espacio para el pastel o no hay caja ******");
            cambiarCaja=true;
            brazo.signal();//despierta a un brazo para que ponga una caja nueva
            empaquetadorCaja.await();
        }
        cajaActual+=peso;
        System.out.println("El empaquetador "+id+" pone el pastel de "+peso+" grs. en la caja. Peso actual de la caja:"+cajaActual+" grs.");
        
        mutex.unlock();
    }
}
