package TP6.Fumadores;

import java.util.concurrent.Semaphore;

public class SalaFumadores {
    private Semaphore semCololar;
    private int permiso;// quien puede fumar 1,2,3

    public SalaFumadores() {
        this.semCololar = new Semaphore(0);
        this.permiso=1;
    }

    public synchronized void entrafumar(int id) {
        while (id != permiso) {
            try {
                System.out.println("Fumador " + id + " debe esperar");
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public synchronized void terminafumar() {

        semCololar.release();
        
    }

    public void colocar(int n) {
        try {
            semCololar.acquire();

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("****Agente puso los ingredientes para q lo prenda el fumador "+n);
        permiso=n;
        synchronized(this){
            this.notifyAll();
        }
    }
}
