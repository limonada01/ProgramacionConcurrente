package Viaje_Bonito;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Vuelo  {

    private int horario;
    private int nroTerminal;
    private int puertoEmbarque;
    private CountDownLatch condicionParaAbordar;
    private Semaphore semDespegar;

    public Vuelo(int horario, int nroTerminal,int puertoEmbarque){
        this.horario=horario;
        this.nroTerminal=nroTerminal;
        this.puertoEmbarque=puertoEmbarque; 
        this.condicionParaAbordar=new CountDownLatch(1);//para detener a los pasajeros antes de abordar, se les permite continuar 1 hora antes del vuelo
        this.semDespegar=new Semaphore(0);
    }

    

    public int getHorario() {
        return horario;
    }

    public int getNroTerminal() {
        return nroTerminal;
    }

    public int getPuertoEmbarque() {
        return puertoEmbarque;
    }


    public void permitirQuePasajerosAborden(){
        condicionParaAbordar.countDown();
    }

    public void esperarParaAbordar() throws InterruptedException{
        
        condicionParaAbordar.await();
    }
    
    public synchronized void embarcar(int id,int nroAerolinea){
        System.out.println(ConsoleColors.GREEN_BOLD+"El pasajero "+id+" embarca al vuelo con horario: "+this.horario+" horas, puerta embarque: "+this.puertoEmbarque+" de la Aerolinea : "+nroAerolinea+" en la terminal: "+this.nroTerminal+ConsoleColors.RESET);
    }

    public void avionEsperandoDespegar() throws InterruptedException{
        semDespegar.acquire();
    }
    
    public void avionListoParaDespegar(){
        semDespegar.release();
    }
}
