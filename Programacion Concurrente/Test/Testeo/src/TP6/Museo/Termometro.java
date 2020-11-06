package TP6.Museo;

import java.util.Random;

public class Termometro implements Runnable {
    private int id;
    private GestorSala gestor;
    private int periodoActualizacion;
    private Random r;

    public Termometro(int id, GestorSala gestor, int periodo) {
        this.id = id;
        this.gestor = gestor;
        this.periodoActualizacion = periodo;
        this.r = new Random();
    }

    private void cambiarTemperatura() throws InterruptedException {
        while (true) {
            Thread.sleep(periodoActualizacion * 1000);
            int temp = r.nextInt(30) + 15;// temperatura entre 15 y 45 grados
            gestor.notificarTemperatura(temp);
        }
    }

    @Override
    public void run() {
        try {
            cambiarTemperatura();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    
}
