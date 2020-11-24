package TP6.Trafico.B;

public class Semaforo implements Runnable {
    private GestionaTrafico puente;
    private int seg;

    public Semaforo(GestionaTrafico puente,int seg) {
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
