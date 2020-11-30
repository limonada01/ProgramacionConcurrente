package CamposFuentesCarlos_FAI2438.uno;

import java.util.Random;

public class Extractor implements Runnable {
    private int id;
    private Buffer buffer;
    private Random random;
    public Extractor(int id, Buffer buffer) {
        this.id = id;
        this.buffer = buffer;
        this.random=new Random();
    }

    @Override
    public void run() {
        while (true) {
            try {
                 Thread.sleep(7000); // para test
                buffer.extraer(id);
                int tiempo=random.nextInt(10)+4;
                Thread.sleep(tiempo*1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
