package Viaje_Bonito;

import java.util.Random;

public class Pasajero implements Runnable {
    private int id;
    private Aerolinea aerolinea;// para test seran 3 aerolineas representadas por un numero del 1 al 3 asignado en el aeropuerto
    private Vuelo vuelo;// para test seran 2 vuelos representados por un numero del 1 al 2 asignado en el aeropuerto
    private Aeropuerto aeropuerto;
    private char terminal;
    private Reloj reloj;
    Random random=new Random();
    
    public Pasajero(int id,Reloj reloj){
        this.id=id;
        this.reloj=reloj;
       
    }

    public Terminal bajarDelTren() throws InterruptedException {
        Terminal retorno=null;
        char terminal= (char) vuelo.getTerminal();
        switch(terminal){
            case 'a':retorno=aeropuerto.bajarEnA(id);break;
            case 'b':retorno=aeropuerto.bajarEnB(id);break;
            case 'c':retorno=aeropuerto.bajarEnC(id);break;
        }
        return retorno;
    }

    @Override
    public void run() {
        try{
            int nroAerolinea=random.nextInt(aeropuerto.getNumeroAerolineas()); 
            this.aerolinea=aeropuerto.ingresarPuestoDeInforme(id,nroAerolinea);//entrar al puesto informe
            Thread.sleep(2000);//simulo tiempo dentro
            aeropuerto.salirPuestoDeInforme(id);//salir del puesto informe
            vuelo=aerolinea.realizarCheckIn(id);//entra a puesto de atencion de la aerolinea
            Thread.sleep(2000);//simulo tiempo dentro
            aerolinea.terminarCheckIn(id);//sale del puesto de atencion
            aeropuerto.subirAlTren(id);
            Terminal terminal=bajarDelTren();
            if(reloj.getHora()-vuelo.getHorario()>=100){//si el pasajero tiene una hora o mas antes del embarque
                terminal.entrarFreeShop(id);
            }else{
                //esperar embarque
            }

       }catch(Exception e){
            e.getStackTrace();

       }
        

    }

    public int getId() {
        return id;
    }

    
    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }
   

   
}
