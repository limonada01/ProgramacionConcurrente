package PracticaParcial.Blancanieves;

import java.util.concurrent.Semaphore;

import TP6.Trafico.A.Semaforo;

public class Casa {
    private Semaphore sillas;
    private Semaphore pasea;
    private Semaphore cocinar;
    private Semaphore mutex;
    private boolean paseando = false;
    private int cantServir = 0;

    public Casa(int cantSillas) {
        sillas = new Semaphore(cantSillas);
        cocinar = new Semaphore(0);
        pasea = new Semaphore(0);
        mutex = new Semaphore(1);
    }

    public void sentarseAComer(int id) throws InterruptedException {
        sillas.acquire();
        mutex.acquire();// exclusion Mutua

        System.out.println(" **** Enano " + id + " consigue una silla y avisa que le den de comer");
        cantServir++;
        if (paseando) {
            pasea.release();
        }
        cocinar.release();

        mutex.release();// exclusion Mutua
    }

    public void dejarSilla(int id) throws InterruptedException {
        mutex.acquire();// exclusion Mutua
        System.out.println("Enano " + id + " Libera una silla ***");

        sillas.release();
        mutex.release();// exclusion Mutua
    }

    public void servirComida() throws InterruptedException {
        while (true) {
            if (cantServir == 0) {
                System.out.println("*Blancanieves se va a pasear*");
                paseando = true;
                pasea.acquire();
                paseando=false;
                System.out.println("*Blancanieves vuelve del paseo por peticion de un enano*");
            }
            else {
                cocinar.acquire();
                System.out.println("*Blancanieves sirve comida a un enano *");
                cantServir--;
            }
        }

    }

}
