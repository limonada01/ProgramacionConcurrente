package tp4.doce;

public class Main {
    public static void main(String[] args) {
        Jaula jaula=new Jaula();
        Thread[] hamsters=new Thread[10];
        for(int i=0;i<10;i++){
            hamsters[i]=new Thread(new hamster(i, jaula));
            hamsters[i].start();
        }
    }
}
