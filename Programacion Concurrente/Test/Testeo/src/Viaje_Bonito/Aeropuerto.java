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
    private Condition aeropuertoCerrado;
    private Condition trenEspera;
    private int capacidadMaxTren;
    private int cantActualEnTren=0;
    private int[] cantPersonasBajanEn;
    private Semaphore[] bajadasSem;// semaforo bajarA, bajarB, BajarC, etc
    private boolean trenViajando=false;
    private Semaphore trenListoParaSalir;
    private Semaphore esperarBajenTren;//esperar que los pasajeros bajen del tren en una terminal 
    private Semaphore semTemporizadorTren;
    private Aerolinea[] aerolineas;
    private Terminal[] terminales;

    public Aeropuerto(Aerolinea[] aerolineas,Terminal[] terminales, int capacidadMaxTren) {
        this.aerolineas = aerolineas;
        this.terminales=terminales;
        this.lock = new ReentrantLock();
        this.lockTren=new ReentrantLock();//para subir al tren
        this.aeropuertoCerrado = lock.newCondition();
        this.trenEspera=lockTren.newCondition();
        this.capacidadMaxTren=capacidadMaxTren;
        this.trenListoParaSalir=new Semaphore(0);
        this.esperarBajenTren=new Semaphore(0);
        this.semTemporizadorTren=new Semaphore(0);
        this.cantPersonasBajanEn=new int[terminales.length];
        this.bajadasSem=new Semaphore[terminales.length];
        for(int i=0;i<terminales.length;i++){//creo los semaforos para controlar la bajada en cada terminal (a,b,c,etc)
            bajadasSem[i]=new Semaphore(0);
        }
        for(int i=0;i<terminales.length;i++){//inicio todos los valores en 0
            cantPersonasBajanEn[i]=0;
        }

        
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
        retorno = aerolineas[nroAerolinea];// retorna Aerolinea
        return retorno;
    }
    public Vuelo elegirVuelo(int id,Aerolinea aerolinea){
        System.out.println("** El pasajero número "+id+" elige un vuelo **");
        Vuelo vuelo=aerolinea.asignarVuelo();
        return vuelo;
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

    public void subirAlTren(int id,int terminalBajada) throws InterruptedException {
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



    public void anotarBajada(int terminalBajada){
        cantPersonasBajanEn[terminalBajada]++;
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

    public Terminal bajarDelTren(int id, int nroTerminal) throws InterruptedException{
        Terminal retorno=null;
        bajadasSem[nroTerminal].acquire();
        System.out.println("** El pasajero "+id+" baja del tren en la Estacion "+nroTerminal+"! **");
        cantActualEnTren--;
        cantPersonasBajanEn[nroTerminal]--;
        if(cantPersonasBajanEn[nroTerminal]==0){
            esperarBajenTren.release();
        }else{
            bajadasSem[nroTerminal].release();
        }
        retorno=this.terminales[nroTerminal];
        return retorno;
    }
    
    public void esperarQueBajenDelTren() throws InterruptedException {
        this.esperarBajenTren.acquire();
    }

    public void abrirPuertasTren(int nroTerminal){
        System.out.println("Tren abre las puertas en terminal "+nroTerminal+" para que bajen los pasajeros");
        bajadasSem[nroTerminal].release();
    }

    public int getNumeroAerolineas(){
        return this.aerolineas.length;
    }

    public int getBajanEn(int nroTerminal) {
        return cantPersonasBajanEn[nroTerminal];
    }

    public boolean getAbierto(){
        return abierto;
    }
    
    public Aerolinea[] getAerolineas(){
        return this.aerolineas;
    }
    
}
