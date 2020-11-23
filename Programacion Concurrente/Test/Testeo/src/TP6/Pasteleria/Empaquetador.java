package TP6.Pasteleria;

import java.util.Random;

public class Empaquetador implements Runnable {
    private int id;
    private Pasteleria pasteleria;
    private Random random;

    public Empaquetador(int id, Pasteleria past) {
        this.id = id;
        this.pasteleria = past;
        this.random = new Random();
    }

    public void trabajar() throws InterruptedException {
        int peso = 0;
        while (true) {
            peso = pasteleria.tomarPastel(id);
            int tiempo = random.nextInt(2) + 1;
            Thread.sleep(tiempo * 1000);
            pasteleria.soltarPastel(id, peso);

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
