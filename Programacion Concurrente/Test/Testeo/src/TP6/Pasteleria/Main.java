package TP6.Pasteleria;

public class Main {
    public static void main(String[] args) {
        int pesoMaximoCaja=500;//grs
        int pastelA=200;
        int pastelB=350;
        int pastelC=100;
        int cantEmpaquedaras=2;
        Pasteleria pasteleria=new Pasteleria(pesoMaximoCaja);
        Thread hornoA=new Thread(new Horno('A', pasteleria, pastelA));
        Thread hornoB=new Thread(new Horno('B', pasteleria, pastelB));
        Thread hornoC=new Thread(new Horno('C', pasteleria, pastelC));
        hornoA.start();
        hornoB.start();
        hornoC.start();
        Thread empaquetadora1=new Thread(new Empaquetador(1, pasteleria));
        Thread empaquetadora2=new Thread(new Empaquetador(2, pasteleria));
        empaquetadora1.start();
        empaquetadora2.start();
        Thread brazo=new Thread(new Brazo(1, pasteleria));
        brazo.start();
    }
}
