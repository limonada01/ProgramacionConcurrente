package Viaje_Bonito;

import java.util.Random;

public class Pasajero implements Runnable {
    private int id;
    private int aerolinea;// para test seran 3 aerolineas representadas por un numero del 1 al 3 asignado en el aeropuerto
    private int vuelo;// para test seran 2 vuelos representados por un numero del 1 al 2 asignado en el aeropuerto
    private Aeropuerto aeropuerto;
    private PuestoAtencion puestoAtencion;
    Random random=new Random();
    public Pasajero(int id, Aeropuerto aeropuerto){
        this.id=id;
        this.aeropuerto=aeropuerto; 
        this.aerolinea=random.nextInt(aeropuerto.getNumeroAerolineas())+1;
        this.vuelo=random.nextInt(2)+1;
    }

    @Override
    public void run() {
        try{ 
            this.puestoAtencion=aeropuerto.ingresarPuestoDeInforme(id,aerolinea);//entrar al puesto informe
            Thread.sleep(2000);//simulo tiempo dentro
            aeropuerto.salirPuestoDeInforme(id);//salir del puesto informe
            puestoAtencion.realizarCheckIn(id);//entra a puesto de atencion de la aerolinea
            Thread.sleep(2000);//simulo tiempo dentro
            puestoAtencion.terminarCheckIn(id);//sale del puesto de atencion
       }catch(Exception e){
            e.getStackTrace();

       }
        

    }

    public int getId() {
        return id;
    }

    
    public int getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(int aerolinea) {
        this.aerolinea = aerolinea;
    }

    public int getVuelo() {
        return vuelo;
    }

    public void setVuelo(int vuelo) {
        this.vuelo = vuelo;
    }

    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }

    public PuestoAtencion getPuestoAtencion() {
        return puestoAtencion;
    }

    public void setPuestoAtencion(PuestoAtencion puestoAtencion) {
        this.puestoAtencion = puestoAtencion;
    }

   
}
