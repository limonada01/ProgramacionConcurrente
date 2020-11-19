package TP6.CentroHemoterapia2;

public class Persona implements Runnable {
    private int id;
    private Centro centro;

    public Persona(int id, Centro centro) {
        this.id = id;
        this.centro = centro;
    }

    @Override
    public void run() {
        try {
            centro.entrar(id);
            Thread.sleep(5000);
            centro.salir(id);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
