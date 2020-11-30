package CamposFuentesCarlos_FAI2438.dos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class Control {
    private int cantTotalTransbordador; //cantidad a llenar de autos para arrancar
    private int cantActualTransbordador=0; //contador de autos en el transbordador
    private boolean puedenSubir=true; //indica cuando se pueden subir los autos, se usa para avisar cuando el tranbordador listo para q suban autos
    private boolean puedenBajar=false; //indicar cuando se puede bajar del transbordador
    private Lock lock;
    private Condition esperanSubir;
    private Condition esperanBajar;
    private Condition transbordadorEsperaArrancar;
    private Condition transBordadorEsperaRegresar;
    public Control(int cantTotal){
        this.cantTotalTransbordador=cantTotal;
        this.lock=new ReentrantLock();
        this.esperanSubir=lock.newCondition();
        this.esperanBajar=lock.newCondition();
        this.transbordadorEsperaArrancar=lock.newCondition();
        this.transBordadorEsperaRegresar=lock.newCondition();


    }

    public  void autoSubir(int id) throws InterruptedException {
        lock.lock();
        while(cantActualTransbordador>=cantTotalTransbordador || !puedenSubir){
            esperanSubir.await();
            esperanSubir.signal();
        }
        cantActualTransbordador++;
        System.out.println("*** Auto "+id+" SUBE al transbordador");
        if(cantActualTransbordador>=cantTotalTransbordador){
            transbordadorEsperaArrancar.signal();
        }
        lock.unlock();
    }

    public void autoBajar(int id) throws InterruptedException {
        lock.lock();
        while(!puedenBajar){//esperan hasta que el transbordador avise q puedan bajar
            esperanBajar.await();
            esperanBajar.signal();
        }
        cantActualTransbordador--;
        System.out.println("Auto "+id+" BAJA del transbordador *** ");
        if(cantActualTransbordador==0){
            transBordadorEsperaRegresar.signal();
        }
        lock.unlock();
    }
    public void ir() throws InterruptedException {
        lock.lock();
        while(cantActualTransbordador!=cantTotalTransbordador){
            System.out.println("El transbordador espera a que se llene para arrancar...");
            transbordadorEsperaArrancar.await();
        }
        puedenSubir=false;//ya no permite que nadie suba
        System.out.println("El transbordador ARRANCA...");
        lock.unlock();
    }
    public void destino(){
        lock.lock();
        System.out.println("El transbordador llega al oeste, y permite que se bajen los autos...");
        puedenBajar=true;
        esperanBajar.signal();
        lock.unlock();
    }

    public void volver() throws InterruptedException {
        lock.lock();
        while(cantActualTransbordador!=0){
            System.out.println("El transbordador espera a se vacie para regresar...");
            transBordadorEsperaRegresar.await();
        }
        System.out.println("El transbordador regresa...");
        puedenBajar=false;
        
        lock.unlock();
    }
    public void inicio(){
        lock.lock();
        System.out.println("El transbordador llega al lado este, y permite que suban los autos ");
        puedenSubir=true;
        esperanSubir.signal();
        lock.unlock();
    }
}
