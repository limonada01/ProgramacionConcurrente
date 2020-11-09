package Saludos;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Saludo {
    // private Object jefe = new Object();
    private boolean notificados = false;
    private ReentrantLock mutex = new ReentrantLock();
    private Semaphore empleados = new Semaphore(0);
    private int llegaron=0;
    private int numEmp=5;

    void saludarJefe(String empleado) {// empleado
        try {
            this.empleados.acquire();
            System.out.println(empleado + "> Buenos dias jefe!");
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
    }

    synchronized void saludoJefe() {// boss
        System.out.println("JEFE> Buenos dias!");
        this.empleados.release(numEmp);
    }

    synchronized void esperar() {// boss
        try {
            this.llegaron++;
            System.out.println("cant llegaro: "+this.llegaron);
            this.wait();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    synchronized void notificarTodos() {// boss
        this.notificados = true;
        this.notifyAll();

    }
    public boolean verificarLlegada(){
        mutex.lock();
        boolean res= this.llegaron < (this.numEmp+1);
        System.out.println("flaaag ***** "+res);
        mutex.unlock();
        return res;
    }

    public boolean getNotificados() {
        mutex.lock();
        boolean res = this.notificados;
        mutex.unlock();
        return res;

    }

    public void setNotificados(boolean valor) {
        mutex.lock();
        this.notificados = valor;
        mutex.unlock();
    }

    public int getLlegaron() {
        mutex.lock();
        int res=this.llegaron;
        mutex.unlock();
        return res;
    }

    
    
    /*
     * void jefeEsperar() {// boss synchronized (jefe) { try { jefe.wait(); } catch
     * (InterruptedException e) { // TODO Auto-generated catch block
     * e.printStackTrace(); } } }
     * 
     * void jefeSaludar() { // empleados
     * 
     * this.notifyAll(); }
     */
}
