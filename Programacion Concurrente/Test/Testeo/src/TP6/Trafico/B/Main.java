package TP6.Trafico.B;

public class Main {
    public static void main(String[] args) {
        

        int segCambioSemaforo=3;
        int autosNorte=20;
        int autosSur=17;

        GestionaTrafico puente=new GestionaTrafico();
        Thread semaforo=new Thread(new Semaforo(puente,segCambioSemaforo));
        semaforo.start();

        Thread[] autosN=new Thread[autosNorte];
        Thread[] autosS=new Thread[autosSur];

        for(int i=0;i<autosNorte;i++){
            autosN[i]=new Thread(new AutoNorte(i+1, puente));
            autosN[i].start();
        }
        for(int i=0;i<autosSur;i++){
            autosS[i]=new Thread(new AutoSur(i+1, puente));
            autosS[i].start();
        }
        
    }
}
