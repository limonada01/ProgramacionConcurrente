package TP6.Impresoras;

import java.util.concurrent.Semaphore;

public class CentroImpresion {
    //private int NumA;//cantidad impresoras tipoA
    //private int NumB;//cantidad impresoras tipoB
    private Semaphore bufferA;
    private Semaphore bufferB;
    public CentroImpresion(int numA,int numB){
        this.bufferA=new Semaphore(numA);
        this.bufferB=new Semaphore(numB);
    }

    public void imprimirA(int id) throws InterruptedException {
        bufferA.acquire();

        
    }

    public void imprimirB(int id) throws InterruptedException {
        bufferB.acquire();
    }

    public void terminarImpA(){
        bufferA.release();
    }

    public void terminarImpB(){
        bufferB.release();
    }
}
