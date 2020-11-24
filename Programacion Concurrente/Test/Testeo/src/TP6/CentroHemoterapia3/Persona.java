package TP6.CentroHemoterapia3;

import java.util.Random;

public class Persona implements Runnable {
    private int id;
    private Centro centro;
    private Random random;
    public Persona(int id, Centro centro) {
        this.id = id;
        this.centro = centro;
        this.random=new Random();
    }

    @Override
    public void run() {
        try {
            
            centro.entrar(id);
            int tiempo=random.nextInt(7)+4;
            Thread.sleep(tiempo*1000);
            centro.salir(id);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
