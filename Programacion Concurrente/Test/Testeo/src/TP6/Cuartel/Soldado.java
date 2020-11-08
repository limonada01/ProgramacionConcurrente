package TP6.Cuartel;

import java.util.Random;

public class Soldado implements Runnable {
    private int id;
    private Comedor comedor;
    private Random random;

    public Soldado(int id, Comedor comedor) {
        this.id = id;
        this.comedor = comedor;
        this.random= new Random();
    }

    @Override
    public void run() {
        try {
            comedor.entrarComedor(id);
            Thread.sleep(1000);
            comedor.tomarBandeja(id);
            Thread.sleep(1000);
            int opcion=random.nextInt(2);
            comedor.tomarBebida(id, opcion);
            Thread.sleep(1000);
            if(random.nextInt(2)==1){
                comedor.buscarPostre(id);
            }
            Thread.sleep(1000*(random.nextInt(12)+5));
            comedor.salirComedor(id);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}
