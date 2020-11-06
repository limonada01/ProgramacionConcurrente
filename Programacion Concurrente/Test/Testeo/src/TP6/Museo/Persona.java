package TP6.Museo;

import java.util.Random;

public class Persona implements Runnable {
    private int id;
    private GestorSala gestor;
    private Random r;

    public Persona(int id, GestorSala gestor) {
        this.id = id;
        this.gestor = gestor;
        this.r = new Random();
    }

    public int getId() {
        return id;
    }

    public GestorSala getGestor() {
        return gestor;
    }

    public Random getR() {
        return r;
    }

    @Override
    public void run() {

        try {
            gestor.entrarSala(id);
            int tiempoDentro=r.nextInt(10)*1000;
            Thread.sleep(tiempoDentro);
            gestor.salirSala(id);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }

    

   
}
