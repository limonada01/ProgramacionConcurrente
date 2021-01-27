package Viaje_Bonito;

import java.util.Random;

public class Pasajero implements Runnable {
    private int id;
    private Aeropuerto aeropuerto;
    private Reloj reloj;
    private Aerolinea aerolinea;// para test seran 3 aerolineas representadas por un numero del 1 al 3 asignado en el aeropuerto
    private Vuelo vuelo;// para test seran 2 vuelos representados por un numero del 1 al 2 asignado en el aeropuerto
    private char terminal;
    Random random=new Random();
    
    public Pasajero(int id,Reloj reloj,Aeropuerto aeropuerto){
        this.id=id;
        this.reloj=reloj;
        this.aeropuerto=aeropuerto;
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
            int segSim=random.nextInt(4);
            Thread.sleep(1000*segSim);
            //System.out.println("****** numero aerlineas: "+aeropuerto.getNumeroAerolineas());
            int nroAerolinea=random.nextInt(aeropuerto.getNumeroAerolineas()); 
            this.aerolinea=aeropuerto.ingresarPuestoDeInforme(id,nroAerolinea);//entrar al puesto informe
            Thread.sleep(500);//simulo tiempo dentro -20 mins
            aeropuerto.salirPuestoDeInforme(id);//salir del puesto informe
            vuelo=aerolinea.realizarCheckIn(id);//entra a puesto de atencion de la aerolinea
            Thread.sleep(500);//simulo tiempo dentro -20 mins
            aerolinea.terminarCheckIn(id);//sale del puesto de atencion
            //System.out.println("******************************************************** "+id +" HORA: "+reloj.getHora());
            //System.out.println("TEST::::::::::::::::::::::: ID: "+id+" terminal: "+vuelo.getTerminal());
            aeropuerto.subirAlTren(id,vuelo.getTerminal());
            Terminal terminal=bajarDelTren();
            if(reloj.calculoTiempo(vuelo.getHorario()) && aeropuerto.getAbierto()){//si el pasajero tiene una hora o mas antes del embarque y el aeropuerto está abierto
                terminal.entrarFreeShop(id);
                Thread.sleep(1000);//40 mins simulados
                terminal.salirFreeShop(id);
            }else{
                //esperar embarque
                terminal.esperarEmbarque(id);
            }
            terminal.listoParaEmbarcar(id, vuelo.getPuertoEmbarque());
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
