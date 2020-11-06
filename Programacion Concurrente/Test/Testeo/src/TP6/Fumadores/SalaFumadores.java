package TP6.Fumadores;

import java.util.concurrent.Semaphore;

public class SalaFumadores {
    private int permiso;// quien puede fumar 1,2,3
    private boolean recurso;

    public SalaFumadores() {
        this.permiso = -1;
        this.recurso = false;
    }

    public synchronized void entrafumar(int id) {
        while (id != permiso || !recurso) {
            try {
                System.out.println("Fumador " + id + " debe esperar");
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("Fumador " +id+" está fumando.");
        recurso = false;// consume los recursos
    }

    public synchronized void terminafumar() {

        this.notifyAll();

    }

    public synchronized void colocar(int n) {
        while (recurso) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        System.out.println("****Agente puso los ingredientes para q lo prenda el fumador "+n);
        permiso=n;
        recurso=true; //genera nuevos recursos
        this.notifyAll();
        
    }
}
