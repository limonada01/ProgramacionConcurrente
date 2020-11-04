package ProdConsumidorMonitores;

public class Main {
    public static void main(String[] args) {
        int tamBuffer=2;
        int cantConsumidores=4;
        int cantProductores=2;
        Buffer buffer=new Buffer(tamBuffer);
        Thread[] consumidores= new Thread[cantConsumidores];
        Thread[] productores= new Thread[cantProductores];
        for(int i=0;i<cantConsumidores;i++){
            consumidores[i]=new Thread(new consumidor(i, buffer),"CONSUMIDOR");
            consumidores[i].start();
        }
        for(int i=0;i<cantProductores;i++){
            productores[i]=new Thread(new productor(i, buffer),"PRODUCTOR");
            productores[i].start();
        }
    }
}
