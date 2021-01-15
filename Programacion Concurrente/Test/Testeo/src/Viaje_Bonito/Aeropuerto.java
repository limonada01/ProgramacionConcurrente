package Viaje_Bonito;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Aeropuerto {
    
    private int numeroAerolineas;
    private Semaphore semPuestoInforme=new Semaphore(1);
    private PuestoAtencion[] puestosDeAtencion;

    public Aeropuerto(int numeroAerolineas, PuestoAtencion[] puestos){
        this.numeroAerolineas=numeroAerolineas;
        this.puestosDeAtencion=puestos;

    }

    public PuestoAtencion ingresarPuestoDeInforme(int id,int aerolinea) throws InterruptedException {
        PuestoAtencion retorno;
        semPuestoInforme.acquire();
        System.out.println("*** El pasajero número "+id+" ha ingresado al puesto de informe y se le informó sobre su puesto de atención");
        retorno= puestosDeAtencion[aerolinea];
        return retorno;
    }
    public void salirPuestoDeInforme(int id) throws InterruptedException {
        System.out.println("El pasajero número "+id+" ha salido del puesto de informe ***");
        semPuestoInforme.release();
    }




    public int getNumeroAerolineas(){
        return this.numeroAerolineas;
    }
    


}
