package ProdConsumidor;

import java.util.concurrent.RunnableFuture;

public class consumidor implements Runnable {
    private int id;
    private Buffer buffer;

    public consumidor(int id, Buffer buffer) {
        this.id = id;
        this.buffer = buffer;
    }

    @Override
    public void run() {

        try {
            while (true) {
                this.buffer.consumir(id);
                
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
