package LectoresEscritores;

import java.util.concurrent.RunnableFuture;

public class Escritor implements Runnable {
    private int id;
    private Libro libro;

    public Escritor(int id, Libro libro) {
        this.id = id;
        this.libro = libro;
    }

    @Override
    public void run() {
        try {
            
            while (!libro.finalizado()) {
                libro.empezarEscribir(id);
                Thread.sleep(3000);
                libro.terminarEscribir(id);
                Thread.sleep(15000);
            }

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
