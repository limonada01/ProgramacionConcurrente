package CamposFuentesCarlos_FAI2438.uno;

import java.util.Random;

public class Insertor implements Runnable {
    private int id;
    private Buffer buffer;
    private Object producto;
    private Random random;

    public Insertor(int id, Buffer buffer) {
        this.id = id;
        this.buffer = buffer;
        this.producto = new Object();
        this.random=new Random();
    }

    @Override
    public void run() {
        while (true) {
            try {
                buffer.insertar(id, producto);
                int tiempo=random.nextInt(6)+3;
                Thread.sleep(tiempo*1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
