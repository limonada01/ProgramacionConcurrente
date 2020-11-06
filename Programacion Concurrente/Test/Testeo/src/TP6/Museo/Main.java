package TP6.Museo;

public class Main {
    public static void main(String[] args) {
        int limiteTemperatura=30;
        int totalPeronasLimitado=5;
        int totalPersonasNormal=10;
        int periodo=10;//en segundos
        int cantJubilados=10;
        int cantPersonas=40;
        
        GestorSala gestor=new GestorSala(limiteTemperatura, totalPeronasLimitado, totalPersonasNormal);  
        Thread termometro=new Thread(new Termometro(1, gestor, periodo));
        termometro.start();
        Thread[] jubilados=new Thread[cantJubilados];  
        Thread[] personas=new Thread[cantPersonas];

        for(int i=0;i<cantPersonas;i++){
            personas[i]=new Thread(new Persona(i, gestor));
            personas[i].start();
        }
        for(int i=0;i<cantJubilados;i++){
            jubilados[i]=new Thread(new Jubilado(i+50, gestor));
            jubilados[i].start();
        }
    }
}
