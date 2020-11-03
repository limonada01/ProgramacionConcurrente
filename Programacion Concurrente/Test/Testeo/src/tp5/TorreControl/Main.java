package tp5.TorreControl;

public class Main {
    public static void main(String[] args) {
        int cantAviones=10;
        int prioridad=2;
        PistaAeropuerto pista=new PistaAeropuerto(prioridad);

        Thread torre=new Thread(new Torre(1,pista));
        torre.start();
        Thread[] aviones=new Thread[cantAviones];
        for(int i=0;i<cantAviones;i++){
            aviones[i]=new Thread(new Avion(i,pista));
            aviones[i].start();
        }
    }
}
