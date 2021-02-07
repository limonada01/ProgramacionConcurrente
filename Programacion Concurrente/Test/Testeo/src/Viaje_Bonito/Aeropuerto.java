package Viaje_Bonito;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class Aeropuerto {
    private boolean abierto = false;
    private Lock lock;
    private Lock lockTren;
    private Lock bajadaTren;
    private Condition aeropuertoCerrado;
    private Condition trenEspera;
    private Condition terminalA;
    private Condition terminalB;
    private Condition terminalC;
    private int capacidadMaxTren;
    private int cantActualEnTren=0;
    private int bajanEnA=0;
    private int bajanEnB=0;
    private int bajanEnC=0;
    private boolean trenViajando=false;
    private Semaphore trenListoParaSalir;
    private Semaphore bajarA;
    private Semaphore bajarB;
    private Semaphore bajarC;
    private Semaphore esperarBajenTren;//esperar que los pasajeros bajen del tren en una terminal 
    private Semaphore semTemporizadorTren;
    //private Semaphore semTren;
    // private Semaphore semPuestoInforme=new Semaphore(1);
    private Aerolinea[] aerolineas;
    private Terminal[] terminales;

    public Aeropuerto(Aerolinea[] aerolineas,Terminal[] terminales, int capacidadMaxTren) {
        this.aerolineas = aerolineas;
        this.terminales=terminales;
        this.lock = new ReentrantLock();
        this.lockTren=new ReentrantLock();
        this.bajadaTren=new ReentrantLock();
        this.aeropuertoCerrado = lock.newCondition();
        this.trenEspera=lockTren.newCondition();
        this.terminalA=bajadaTren.newCondition();
        this.terminalB=bajadaTren.newCondition();
        this.terminalC=bajadaTren.newCondition();
        this.capacidadMaxTren=capacidadMaxTren;
        this.trenListoParaSalir=new Semaphore(0);
        this.bajarA=new Semaphore(0);
        this.bajarB=new Semaphore(0);
        this.bajarC=new Semaphore(0);
        this.esperarBajenTren=new Semaphore(0);
        this.semTemporizadorTren=new Semaphore(0);
        
    }

    public Aerolinea ingresarPuestoDeInforme(int id, int nroAerolinea) throws InterruptedException {
        lock.lock();
        Aerolinea retorno;
        
        while (!abierto) {// cuando el aeropierto esté cerrado
            System.out.println("** Pasajero " + id
                    + " el Aeropuerto se encuentra cerrado en este momento, abre a las 6:00 AM !** ");
            aeropuertoCerrado.await();
        }
        System.out.println(ConsoleColors.GREEN_BOLD+"*** El pasajero número " + id
                + " ha ingresado al puesto de informe y se le informó sobre su puesto de atención"+ConsoleColors.RESET);
        retorno = aerolineas[nroAerolinea];// retorna Vuelo
        return retorno;
    }

    public void salirPuestoDeInforme(int id) throws InterruptedException {
        System.out.println(ConsoleColors.GREEN_BOLD+"El pasajero número " + id + " ha salido del puesto de informe ***"+ConsoleColors.RESET);
        lock.unlock();
    }

    public void abrirAeropuerto() {
        abierto = true;
        System.out.println(ConsoleColors.BLUE_BOLD+"** Aeropuerto ABIERTO hasta las 22:00 AM ! **"+ConsoleColors.RESET);
        lock.lock();
        aeropuertoCerrado.signalAll();// despierto a todos los pasajeros que estan esperando a que abra el aeropuerto
        lock.unlock();
    }

    public void cerrarAeropuerto() {
        abierto = false;
        System.out.println(ConsoleColors.BLUE_BOLD+"** Aeropuerto CERRADO hasta las 6:00 AM ! **"+ConsoleColors.RESET);
    }

    public void subirAlTren(int id,char terminalBajada) throws InterruptedException {
        lockTren.lock();
 
        while(trenViajando || cantActualEnTren >= capacidadMaxTren){//si el tren está viajando o ya está lleno deberá esperar
            System.out.println("** Pasajero "+id+" espera para subir al Tren ** ");
            trenEspera.await();
            
        }
        cantActualEnTren++;
        if(cantActualEnTren==1){
            
            semTemporizadorTren.release();//activo el temporizador del tren
        }
        anotarBajada(terminalBajada);
        System.out.println("El pasajero "+id+" consigue subir al tren y espera a que parta");
        if(cantActualEnTren == capacidadMaxTren){
            trenListoParaSalir.release();//habilito el metodo comenzarRecorridoTren que ejecuta el hilo Tren
        }
        lockTren.unlock();
    }



    public void anotarBajada(char terminalBajada){
        switch(terminalBajada){
            case 'a': bajanEnA++;break;
            case 'b': bajanEnB++;break;
            case 'c': bajanEnC++;break;
        }
    }

    public void transporteATerminal() throws InterruptedException {
        trenListoParaSalir.acquire();
        this.trenViajando=true;
        System.out.println(ConsoleColors.RED_BRIGHT+"*El Tren comienza su recorrido hacia las Terminales*"+ConsoleColors.RESET);
        
    }

    public void temporizadorON() throws InterruptedException {
        semTemporizadorTren.acquire();
    }

    public void temporizadorShot(){
        lockTren.lock();
        if(trenViajando==false && cantActualEnTren!=0){// si el tren aun no ha salido
            //System.out.println("+++++++++++++ CANT ACTUAL EN TREN: "+cantActualEnTren+"*++++++++++++++");
            System.out.println(ConsoleColors.RED_BRIGHT+" * Tren debe partir por tiempo de espera... * "+ConsoleColors.RESET);
            trenListoParaSalir.release();
        }
        lockTren.unlock();
    }
    
    public void trenListoParaQueSuban(){
        this.trenViajando=false;
        System.out.println(ConsoleColors.RED_BRIGHT+"*Tren esperando Pasajeros*"+ConsoleColors.RESET);
        lockTren.lock();
        trenEspera.signalAll();//despierto a los pasajeros que esperan para subir al tren
        lockTren.unlock();
    }

    public Terminal bajarEnA(int id) throws InterruptedException {
        Terminal retorno=null;
        bajarA.acquire();
        //lockTren.lock();
        System.out.println("** El pasajero "+id+" baja del tren en la Estacion A! **");
        cantActualEnTren--;
        bajanEnA--;
        //lockTren.unlock();
        if(bajanEnA==0){
            esperarBajenTren.release();
        }else{
            bajarA.release();
        }
        retorno=this.terminales[0];//0 terminal A
        
        return retorno;
    }
    public Terminal bajarEnB(int id) throws InterruptedException {
        Terminal retorno=null;
        bajarB.acquire();
        //lockTren.lock();
        System.out.println("** El pasajero "+id+" baja del tren en la Estacion B! **");
        cantActualEnTren--;
        bajanEnB--;
        //lockTren.unlock();
        if(bajanEnB==0){
            esperarBajenTren.release();
        }else{
            bajarB.release();
        }
        retorno=this.terminales[1];// 1 terminal B
        return retorno;
    }
    public Terminal bajarEnC(int id) throws InterruptedException {
        Terminal retorno=null;
        bajarC.acquire();
        //lockTren.lock();
        System.out.println("** El pasajero "+id+" baja del tren en la Estacion C! **");
        cantActualEnTren--;
        bajanEnC--;
        //lockTren.unlock();
        if(bajanEnC==0){
            esperarBajenTren.release();
        }else{
            bajarC.release();
        }
        retorno=this.terminales[2];// 2 terminal C
        return retorno;
    }

    public void esperarQueBajenDelTren() throws InterruptedException {
        this.esperarBajenTren.acquire();
    }

    public void abrirPuertasEnA(){
        System.out.println("Tren abre las puertas en terminal A para que bajen los pasajeros");
        bajarA.release();
    }
    public void abrirPuertasEnB(){
        System.out.println("Tren abre las puertas en terminal B para que bajen los pasajeros");
        bajarB.release();
    }
    public void abrirPuertasEnC(){
        System.out.println("Tren abre las puertas en terminal C para que bajen los pasajeros");
        bajarC.release();
    }

    public int getNumeroAerolineas(){
        return this.aerolineas.length;
    }

    public int getBajanEnA() {
        return bajanEnA;
    }

    public int getBajanEnB() {
        return bajanEnB;
    }

    public int getBajanEnC() {
        return bajanEnC;
    }

    public boolean getAbierto(){
        return abierto;
    }
    
    
}
