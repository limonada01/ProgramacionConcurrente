package PracticaParcial.Blancanieves;

public class Main {
    public static void main(String[] args) {
        int cantSillas=4;
        int cantEnanos=4;
        Casa casa=new Casa(cantSillas);
        Thread bn=new Thread(new Blancanieve(1, casa));
        bn.start();
        Thread[] enanos=new Thread[cantEnanos];
        for(int i=0;i<cantEnanos;i++){
            enanos[i]=new Thread(new Enano(i+1, casa));
            enanos[i].start();
        }
    
    }
}
