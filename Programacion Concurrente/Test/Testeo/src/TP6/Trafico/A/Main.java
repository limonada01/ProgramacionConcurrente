package TP6.Trafico.A;

public class Main {
    public static void main(String[] args) {
        
        int maxCapacidadPuente=2;
        int segCambioSemaforo=3;
        int autosNorte=4;
        int autosSur=4;

        Puente puente=new Puente(maxCapacidadPuente);
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
