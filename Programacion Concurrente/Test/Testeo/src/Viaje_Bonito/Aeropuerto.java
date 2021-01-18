package Viaje_Bonito;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Aeropuerto {
    
    
    private Semaphore semPuestoInforme=new Semaphore(1);
    private Aerolinea[] puestosDeAtencion;
    //private Random random=new Random();
    public Aeropuerto( Aerolinea[] puestos){
        
        this.puestosDeAtencion=puestos;

    }

    public Aerolinea ingresarPuestoDeInforme(int id,int nroAerolinea) throws InterruptedException {
        Aerolinea retorno;
        semPuestoInforme.acquire();
        System.out.println("*** El pasajero número "+id+" ha ingresado al puesto de informe y se le informó sobre su puesto de atención");
        retorno= puestosDeAtencion[nroAerolinea];//retorna Vuelo 
        return retorno;
    }
    public void salirPuestoDeInforme(int id) throws InterruptedException {
        System.out.println("El pasajero número "+id+" ha salido del puesto de informe ***");
        semPuestoInforme.release();
    }




    public int getNumeroAerolineas(){
        return this.puestosDeAtencion.length;
    }
    
    

}
