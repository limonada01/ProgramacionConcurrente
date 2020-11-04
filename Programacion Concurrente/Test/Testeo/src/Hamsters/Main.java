package Hamsters;

public class Main {

    public static void main(String[] args) {
        int maxComiendo=3;
        int cantHamsters=5;
        Jaula jaula=new Jaula(maxComiendo);
        Thread[] hamsters=new Thread[cantHamsters];

        for(int i=0;i<cantHamsters;i++){
            hamsters[i]=new Thread(new Hamster(jaula,i));
            hamsters[i].start();
        }
     
    }
    

}
