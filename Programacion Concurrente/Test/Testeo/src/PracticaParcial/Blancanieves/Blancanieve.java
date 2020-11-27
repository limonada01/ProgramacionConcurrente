package PracticaParcial.Blancanieves;

import tp4.seis.B;

public class Blancanieve implements Runnable {
    private int id;
    private Casa casa;

    public Blancanieve(int id, Casa casa) {
        this.id = id;
        this.casa = casa;
    }

    @Override
    public void run() {
        try {
            casa.servirComida();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
