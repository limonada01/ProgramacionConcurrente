package TP6.ObservatorioMODReadWriteLock;


public class Main {
    public static void main(String[] args) {
        int capacidadTotal=50;
        int capacidadLimitada=30;
        int cantVisitantes=60;
        int cantInvestigadores=5;

        Observatorio obs=new Observatorio(capacidadTotal, capacidadLimitada);

        Thread[] visitante=new Thread[cantVisitantes];
        Thread[] invesitgador=new Thread[cantInvestigadores];

        for(int i=0;i<cantVisitantes;i++){
            visitante[i]=new Thread(new Visitante(i+1, obs));
            visitante[i].start();
        }

        

        for(int i=0;i<cantInvestigadores;i++){
            invesitgador[i]=new Thread(new Investigador(i+1, obs));
            invesitgador[i].start();
        }

    }
}
