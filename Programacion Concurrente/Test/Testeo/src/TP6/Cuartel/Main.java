package TP6.Cuartel;

public class Main {
    public static void main(String[] args) {
        int bandejas=20;
        int mostradores=5;
        int mostradoresPostre=3;
        int abridores=10;
        int cantSoldados=150;
        Comedor comedor=new Comedor(bandejas, mostradores, mostradoresPostre, abridores);

        Thread[] soldados=new Thread[cantSoldados];
        
        for(int i=0;i < cantSoldados;i++){
            soldados[i]=new Thread(new Soldado(i+1, comedor));
            soldados[i].start();
        }
    }   
}
