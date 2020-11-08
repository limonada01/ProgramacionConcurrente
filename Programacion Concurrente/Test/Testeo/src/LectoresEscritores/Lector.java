package LectoresEscritores;

public class Lector implements Runnable {
    private int id;
    private Libro libro;

    public Lector(int id,Libro libro){
        this.id=id;
        this.libro=libro;
    }

    @Override
    public void run() {
        try {
            while (true) {
                libro.empezarLeer(id);
                Thread.sleep(3000);
                libro.terminarLeer(id);
                Thread.sleep(7000);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
