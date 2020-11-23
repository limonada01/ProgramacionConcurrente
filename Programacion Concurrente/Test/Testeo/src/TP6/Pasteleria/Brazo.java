package TP6.Pasteleria;

import java.util.Random;

public class Brazo implements Runnable {
    private int id;
    private Pasteleria pasteleria;
    private Random random;

    public Brazo(int id, Pasteleria past) {
        this.id = id;
        this.pasteleria = past;
        this.random = new Random();
    }

    public void trabajar() throws InterruptedException {
        while (true) {
            pasteleria.retirarCaja();
            int tiempo = random.nextInt(4) + 2;
            pasteleria.reponerCaja();

        }
    }

    @Override
    public void run() {
        try {
            trabajar();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
