package TP6.Impresoras;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CentroImpresion {
    //private int NumA;//cantidad impresoras tipoA
    //private int NumB;//cantidad impresoras tipoB
    private Lock buffer;
    private Condition tipoA;
    private Condition tipoB;
    private Condition cualquiera;
    private int maxA;
    private int maxB;
    private int actualA;
    private int actualB;
    


    public CentroImpresion(int numA,int numB){
        this.buffer=new ReentrantLock();
        this.tipoA=buffer.newCondition();
        this.tipoB=buffer.newCondition();
        this.cualquiera=buffer.newCondition();
        this.maxA=numA;
        this.maxB=numB;
        this.actualA=0;
        this.actualB=0;
    }

    public void imprimirA(int id) throws InterruptedException {
        buffer.lock();
        while(actualA >=maxA){
            System.out.println("Usuario "+id+" que imprime A debe esperar!");
            tipoA.await();
        }
        actualA++;
        System.out.println("Usuario "+id+" consigue imprimir A!");

        buffer.unlock(); 
    }

    public void imprimirB(int id) throws InterruptedException {
        buffer.lock();
        while(actualB >=maxB){
            System.out.println("Usuario "+id+" que imprime B debe esperar!");
            tipoB.await();
        }
        actualB++;
        System.out.println("Usuario "+id+" consigue imprimir B!");

        buffer.unlock(); 
    }

    public int imprimir(int id) throws InterruptedException {
        int impresoraUsada=0;//1=A, 2=B
        buffer.lock();
        while(actualA >= maxA && actualB >=maxB){
            System.out.println("** Usuario "+id+" que no le importa el tipo de impresion debe esperar! **");
            cualquiera.await();
        }
        if(actualA<maxA){
            impresoraUsada=1;
            actualA++;
            System.out.println("Usuario "+id+" que no le importa el tipo consiguio imprimir en A!");
        }else{
            impresoraUsada=2;
            actualB++;
            System.out.println("Usuario "+id+" que no le importa el tipo consiguio imprimir en B!");
        }
        buffer.unlock();
        return impresoraUsada;
    }

    public void terminarImpA(int id){
        buffer.lock();
        actualA--;
        System.out.println("Usuario "+id+" termina de imprimir A y sale!");
        tipoA.signal();
        cualquiera.signal();
        buffer.unlock();
    }

    public void terminarImpB(int id){
        buffer.lock();
        actualB--;
        System.out.println("Usuario "+id+" termina de imprimir B y sale!");
        tipoB.signal();
        cualquiera.signal();
        buffer.unlock();
    }
    
    public void terminarImp(int id,int tipo){
        buffer.lock();
        if(tipo==1){//liberar en A
            actualA--;
            System.out.println("Usuario "+id+" que no le importa el tipo termino de imprimir en A!");
            tipoA.signal();
        }else{//liberar en B
            actualB--;
            System.out.println("Usuario "+id+" que no le importa el tipo termino de imprimir en B!");
            tipoB.signal();
        }
        cualquiera.signal();
        buffer.unlock();
    }
}
