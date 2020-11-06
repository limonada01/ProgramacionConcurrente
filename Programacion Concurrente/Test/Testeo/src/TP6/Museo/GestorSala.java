package TP6.Museo;

public class GestorSala {
    private int limiteTemperatura;//tUmbral = 30
    //private int temperaturaActual;
    private int cantPersonasActual;
    private int totalPersonasLimitado;
    private int totalPersonasNormal;
    private int limiteActual;//limite actual de personas que pueden entrar ahora en la sala
    private int jubiladosEsperando;//si hay un jubilado esperando para entrar (para dar prioridad a los jubilados cuando sea posible entrar a la sala)
    public GestorSala(int limiteTemperatura,int totalPersonasLimitado,int totalPersonasNormal){//30,35,50
        this.limiteTemperatura=limiteTemperatura;
        this.limiteActual=-1;
        this.cantPersonasActual=0;
        this.totalPersonasLimitado=totalPersonasLimitado;
        this.totalPersonasNormal=totalPersonasNormal;
        this.jubiladosEsperando=0;
    }

    public synchronized void entrarSala(int id) throws InterruptedException {
        while(cantPersonasActual>= limiteActual || jubiladosEsperando!=0){
            System.out.println("Persona "+id+" a esperar!");
            this.wait();
        }
        this.cantPersonasActual++;
        System.out.println("**** Persona "+id+" pudo entrar a la sala ");
    }

    public synchronized void entrarSalaJubilado(int id) throws InterruptedException {
        jubiladosEsperando++;//un jubilado se pone en espera de prioridad
        while(cantPersonasActual>= limiteActual){
            System.out.println("Jubilado "+id+" a esperar!");
            
            this.wait();
        }
        jubiladosEsperando--;//pudo acceder
        this.cantPersonasActual++;
        System.out.println("**** Jubilado "+id+" pudo entrar a la sala ");
    }

    public synchronized void salirSala(int id){
        this.cantPersonasActual--;
        System.out.println("Persona o Jubilado "+id+" sale de la sala ****");
        notifyAll();
    }


    public void notificarTemperatura(int temperatura){
        
        synchronized(this){
            System.out.println("Temperatura actual: "+temperatura+"°");
            if(temperatura>limiteTemperatura){
                limiteActual=totalPersonasLimitado;
            }else{
                limiteActual=totalPersonasNormal;
            }
            System.out.println("El total permitido en la sala ahora es de "+limiteActual+" personas o jubilados");
            notifyAll();
        }
    }
}
