package TP6.Trafico.A;

public class Semaforo implements Runnable {
    private Puente puente;
    private int seg;

    public Semaforo(Puente puente,int seg) {
        this.puente = puente;
        this.seg=seg;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(seg*1000);
                puente.cambiarSemaforo();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }

    }
    
}
