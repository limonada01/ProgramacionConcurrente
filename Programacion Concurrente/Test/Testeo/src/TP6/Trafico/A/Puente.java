package TP6.Trafico.A;

import java.util.concurrent.Semaphore;

public class Puente {
    private int capacidadPuente;
    private Semaphore maxNorte;
    private Semaphore maxSur;
    private boolean semaforoNorte;
    private boolean semaforoSur;
    private Semaphore flag;
  
    

    public Puente(int maxSimultaneo){
        this.capacidadPuente=maxSimultaneo;
        this.maxNorte=new Semaphore(maxSimultaneo);
        this.maxSur=new Semaphore(maxSimultaneo);
        this.semaforoNorte=false;
        this.semaforoSur=true;//el semaforo arranca en verde para los del Sur
        this.flag=new Semaphore(0);
    }

    public boolean pasarNorte(int id) throws InterruptedException {
        boolean exito=false;
   
        if(semaforoNorte && maxNorte.tryAcquire()){
            
            System.out.println("Auto NORTE "+id+" entra al puente");
            exito=true;
            Thread.sleep(3000);//tarda 3 seg en pasar
            System.out.println("Auto NORTE "+id+" sale del puente");
            maxNorte.release();
            if(!semaforoNorte && maxNorte.availablePermits()==capacidadPuente){

                flag.release();
            }
        }else{
            System.out.println("Auto NORTE "+id+" no consigue entrar al puente aun!");
        }
        
        return exito;
    }

    public boolean pasarSur(int id) throws InterruptedException {
        boolean exito=false;
  
        if( semaforoSur && maxSur.tryAcquire() ){
            System.out.println("Auto SUR "+id+" entra al puente");
            exito=true;
            Thread.sleep(3000);//tarda 3 seg en pasar
            System.out.println("Auto SUR "+id+" sale del puente");
            maxSur.release();
            if(!semaforoSur && maxSur.availablePermits()==capacidadPuente){

                flag.release();
            }
        }else{
            System.out.println("Auto SUR "+id+" no consigue entrar al puente aun!");
        }
        
        return exito;
    }


    public void cambiarSemaforo() throws InterruptedException {
        if(this.semaforoSur){
            this.semaforoSur=false;
            System.out.println("* SEMAFORO EN ROJO PARA LOS DEL SUR *");
            if(maxSur.availablePermits()!=capacidadPuente){
                this.flag.acquire();
            }
            System.out.println("* SEMAFORO EN VERDE PARA LOS DEL NORTE *");
            this.semaforoNorte=true;//recien cuando sale el ultimo que estaba pasando 
        }else{
            semaforoNorte=false;
            System.out.println("* SEMAFORO EN ROJO PARA LOS DEL NORTE *");
            if(maxNorte.availablePermits()!=capacidadPuente){
                this.flag.acquire();
            }
            System.out.println("* SEMAFORO EN VERDE PARA LOS DEL SUR *");
            this.semaforoSur=true;//recien cuando sale el ultimo que estaba pasando 
        }
    }
}
