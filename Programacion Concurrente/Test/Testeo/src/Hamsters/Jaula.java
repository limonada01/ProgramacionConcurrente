package Hamsters;

import java.util.concurrent.Semaphore;

public class Jaula {
    private int maxComiendo;
    private int comiendo;
    private Semaphore semRueda;

    public Jaula(int maxComiendo) {
        this.maxComiendo = maxComiendo;
        this.comiendo = 0;
        this.semRueda = new Semaphore(1);
    }

    public synchronized void empezarAComer(int id) {
        try {
            while (comiendo >= maxComiendo) {
                System.out.println(id + " debe esperar para comer");
                this.wait();
            }
        } catch (InterruptedException ex) {
        }
        System.out.println(id + " empieza a comer");
        comiendo++;
    }

    public synchronized void terminarDeComer(int id) {
        System.out.println(id + " termino de comer");
        comiendo--;
        this.notify();
    }

    public void rodar(int id) throws InterruptedException {
        semRueda.acquire();
        System.out.println(id + " se pone a rodar");
        try {
            Thread.sleep((long) Math.random() * 1500);
        } catch (InterruptedException ex) {
        }
        semRueda.release();
    }

}
