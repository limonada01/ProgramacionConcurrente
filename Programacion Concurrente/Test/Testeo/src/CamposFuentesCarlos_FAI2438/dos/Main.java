package CamposFuentesCarlos_FAI2438.dos;

public class Main {
    public static void main(String[] args) {
        int cantMaximoTransbordador=10;
        int cantAutos=25;
        Control control=new Control(cantMaximoTransbordador);
        Thread trans=new Thread(new Transbordador(control));
        trans.start();
        Thread[] autos=new Thread[cantAutos];

        for(int i=0;i<cantAutos;i++){
            autos[i]=new Thread(new Auto(i+1, control));
            autos[i].start();
        }
    }
}
