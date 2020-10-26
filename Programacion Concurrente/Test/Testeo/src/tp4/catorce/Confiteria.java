package tp4.catorce;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Confiteria {
    private int sillas;
    private ReentrantLock lockSillas;
    private Semaphore mozo;
    private Semaphore cocinero;
    private Semaphore beber;
    private Semaphore comer;
    private Semaphore mozoAtender;
    private Semaphore cocineroAtender;
    public Confiteria(int sillas){
        this.sillas=sillas;
        this.lockSillas=new ReentrantLock();
        this.mozo=new Semaphore(0);
        this.mozoAtender=new Semaphore(1);
        this.cocinero=new Semaphore(0);
        this.cocineroAtender=new Semaphore(1);
        this.beber=new Semaphore(0);
        this.comer=new Semaphore(0);
    }

    public boolean sentarse(){
        boolean flag=false;
        this.lockSillas.lock();
        if(this.sillas>0){
            this.sillas--;
            flag=true;
        }
        this.lockSillas.unlock();
        return flag;
    }

    public void pedirBebida(int idEmpleado) throws InterruptedException {
        
        System.out.println("Empleado "+idEmpleado+": quiero algo de beber.");
        this.mozoAtender.acquire();
        this.mozo.release();

    }
    public void pedirComida(int idEmpleado) throws InterruptedException {
        
        System.out.println("Empleado "+idEmpleado+": quiero algo de comer.");
        this.cocineroAtender.acquire();
        this.cocinero.release();

    }

    public void servirBebida() throws InterruptedException {
        this.mozo.acquire();
        Thread.sleep(5000);
        System.out.println("Mozo: aquí esta su bebida.");
        this.mozoAtender.release();
        this.beber.release();
        
    }
    public void servirComida() throws InterruptedException {
        this.cocinero.acquire();
        Thread.sleep(5000);
        System.out.println("Cocinero: aquí esta su comida.");
        this.cocineroAtender.release();
        this.comer.release();

    }
    public void beber(int idEmpleado) throws InterruptedException {
        this.beber.acquire();
        System.out.println("El empleado "+idEmpleado+": *BEBIENDO*");
        Thread.sleep(5000);
        
    }
    public void comer(int idEmpleado) throws InterruptedException {
        this.comer.acquire();
        System.out.println("El empleado "+idEmpleado+": *COMIENDO*");
        Thread.sleep(5000);
    }

	public void liberarSilla(){
        this.lockSillas.lock();
        this.sillas++;
        this.lockSillas.unlock();
    }
	
    
}
