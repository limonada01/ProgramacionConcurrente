package TP6.Trafico.C;

public class Main {
    public static void main(String[] args) {
        
        int maxAutosPorTurno=10;
        int autosNorte=30;
        int autosSur=3;

        GestionaTrafico puente=new GestionaTrafico(maxAutosPorTurno);


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
