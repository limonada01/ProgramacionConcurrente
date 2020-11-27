package PracticaParcial.Blancanieves;

import java.util.Random;

public class Enano implements Runnable {
    private int id;
    private Casa casa;
    private Random random;

    public Enano(int id, Casa casa) {
        this.id = id;
        this.casa = casa;
        this.random = new Random();
    }

    public void trabajar() {
        System.out.println("Enano " + id + " trabajando duro...");
        int tiempo = random.nextInt(15) + 6;
        try {
            Thread.sleep(tiempo * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void comer() throws InterruptedException {
        casa.sentarseAComer(id);
        int tiempo=random.nextInt(4)+3;
        Thread.sleep(tiempo*1000);
        casa.dejarSilla(id);
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (int i = 0; i < 2; i++) {
                    trabajar();
                    comer();
                }
                System.out.println("zzz...Enano " + id + " duerme...zzz..");
                int tiempo = random.nextInt(15) + 11;
                Thread.sleep(tiempo * 1000);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
