package TP6.Impresoras;

public class Main {
    public static void main(String[] args) {
        int numA=3;//cantidad impresoras tipo A
        int numB=4;//cantidad impresoras tipo B
        int cantUsuarios=20;
        CentroImpresion centro=new CentroImpresion(numA, numB);

        Thread[] usuarios=new Thread[cantUsuarios];

        for(int i=0;i<cantUsuarios;i++){
            usuarios[0]=new Thread(new Usuario(i+1, centro));
            usuarios[0].start();
        }
    }
}
