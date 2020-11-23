package TP6.Pasteleria;

import java.util.Random;

public class Horno implements Runnable {
    private char id;
    private Pasteleria pasteleria;
    private int peso;
    private Random random;

    public Horno(char id, Pasteleria pasteleria, int peso) {
        this.id = id;
        this.pasteleria = pasteleria;
        this.peso = peso;
        this.random = new Random();

    }

    public void cocinarPastel() throws InterruptedException {
        while (true) {
            System.out.println("*** El horno " + id + " comienza a cocinar un pastel");
            int tiempo = random.nextInt(6) + 4;
            Thread.sleep(tiempo * 1000);
            System.out.println("El horno " + id + " termina de cocinar un pastel ***");
            pasteleria.ponerPastelEnMostrador(id, peso);
            Thread.sleep(8000);// espera despues de cocinar un pastel
        }
    }

    @Override
    public void run() {
        try {
            cocinarPastel();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
