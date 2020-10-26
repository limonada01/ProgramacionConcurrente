package CarmposFuentes_FAI2438.Promocion;

public class Main {

    public static void main(String[] args) {
        GestorCruce gestor = new GestorCruce();
        Control control = new Control(gestor);
        Thread cont=new Thread(control);
        cont.start();
        Thread[] coches =new Thread[10];
        for(int i=0;i<10;i++){
            coches[i]=new Thread(new Coche(i,gestor));
            coches[i].start();
        }
    }

}
