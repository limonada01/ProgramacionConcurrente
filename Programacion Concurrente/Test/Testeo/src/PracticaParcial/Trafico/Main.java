package PracticaParcial.Trafico;

public class Main {
    public static void main(String[] args) {
        int cantMaxAutos=10;
        int cantAutosNorte=2;
        int cantAutosSur=4;
        Puente puente=new Puente(cantMaxAutos);
        Thread[] autosN=new Thread[cantAutosNorte];
        Thread[] autosS=new Thread[cantAutosSur];
        for(int i=0;i<cantAutosNorte;i++){
            autosN[i]=new Thread(new Auto(i+1,'N', puente));
            autosN[i].start();
        }
        for(int i=0;i<cantAutosSur;i++){
            autosS[i]=new Thread(new Auto(i+1,'S', puente));
            autosS[i].start();
        }
    }
}
