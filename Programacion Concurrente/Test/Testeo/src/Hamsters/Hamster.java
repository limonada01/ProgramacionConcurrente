package Hamsters;

public class Hamster implements Runnable {

    private Jaula jaula;
    private int id;

    public Hamster(Jaula jaula, int id) {
        this.id = id;
        this.jaula = jaula;
    }

    public void run() {
        while (true) {

            try {
                jaula.empezarAComer(id);
                Thread.sleep((long)(Math.random()*10+5)*1000);
                jaula.terminarDeComer(id);
                jaula.rodar(id);
            } catch (InterruptedException ex) {
            }

        }
    }

}
