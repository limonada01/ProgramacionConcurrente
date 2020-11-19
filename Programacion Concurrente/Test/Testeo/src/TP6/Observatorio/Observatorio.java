package TP6.Observatorio;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Observatorio {
    private int capacidad; // actual
    private int capacidadTotal;//50
    private int capacidadLimitada;// 30
    private int cantMantenimientoActual;
    private int cantVisitantesActual;
    private boolean hayInvestigadorDentro;
   // private Lock lock;
   // private Condition visitantes;
    //private Condition investigadores;
    //private Condition mantenimiento;
    private boolean investigadorEspera; // hay investigador que quiere entrar
    private boolean visitanteConSilla;// si hay un visitante con silla dentro

    public Observatorio(int capacidadTotal, int capacidadLimitada) {
        this.capacidad = capacidadTotal; //actual
        this.capacidadTotal=capacidadTotal;
        this.capacidadLimitada = capacidadLimitada;
        this.hayInvestigadorDentro = false;
        this.cantMantenimientoActual = 0;
        this.cantVisitantesActual = 0;
        /*this.lock = new ReentrantLock();
        this.visitantes = lock.newCondition();
        this.investigadores = lock.newCondition();
        this.mantenimiento = lock.newCondition();*/
        this.investigadorEspera =false;
        this.visitanteConSilla=false;
    }

    public synchronized void entrarVisitante(int id, boolean silla) throws InterruptedException {
       // lock.lock();
        while (cantVisitantesActual >= capacidad || hayInvestigadorDentro| cantMantenimientoActual != 0 || investigadorEspera || (silla && visitanteConSilla)) {
            if(!silla){
                System.out.println("El visitante " + id + " debe esperar para entrar");
            }else{
                System.out.println("El visitante " + id + " con silla de rueda debe esperar para entrar");
            }
            this.wait();

        }
        cantVisitantesActual++;
        if (!silla) {
            System.out.println("El visitante " + id + " logró entrar!");
        }else{
            System.out.println("El visitante " + id + " con silla de Rueda logró entrar y limita la capacidad total del observatorio!");
            visitanteConSilla=true;
            capacidad=capacidadLimitada;
        }
        //lock.unlock();
    }

    public synchronized void entrarMantenimiento(int id) throws InterruptedException {
       // lock.lock();
        while(cantMantenimientoActual>=capacidad || cantVisitantesActual!=0 || hayInvestigadorDentro || investigadorEspera){
            System.out.println("La persona de Mantenimiento " + id + " debe esperar para entrar");
            this.wait();
        }
        cantMantenimientoActual++;
        System.out.println("La persona de Mantenimiento " + id + " logró entrar!");
       // lock.unlock();
    }

    public synchronized void entrarInvestigador(int id) throws InterruptedException {
        //lock.lock();
        while(hayInvestigadorDentro || cantVisitantesActual!=0 || cantMantenimientoActual!=0 ){
            System.out.println("El investigador " + id + " debe esperar para entrar");
            investigadorEspera=true;
            this.wait();
        }
        hayInvestigadorDentro=true;
        investigadorEspera=false;

        System.out.println("El Investigador " + id + " logró entrar!");
        //lock.unlock();
    }

    public synchronized void salirVisitante(int id,boolean silla) {
       // lock.lock();
        cantVisitantesActual--;
        if(!silla){
            System.out.println("El Visitante " + id + " logró salir deja un lugar!");
        }else{
            System.out.println("El visitante " + id + " con silla de Rueda logró salir y aumenta la capacidad total del observatorio!");
            visitanteConSilla=false;
            capacidad=capacidadTotal;
        }
        this.notifyAll();
       // lock.unlock();
    }

    public synchronized void salirMantenimiento(int id) {
        cantMantenimientoActual--;
        System.out.println("La persona de mantenimiento "+id+" logra salir y deja un lugar");
        this.notifyAll();
    }

    public synchronized void salirInvestigador(int id) {
        hayInvestigadorDentro=false;
        System.out.println("El investigador "+id+" logra salir y deja un lugar");        
        this.notifyAll();
    }   

}
