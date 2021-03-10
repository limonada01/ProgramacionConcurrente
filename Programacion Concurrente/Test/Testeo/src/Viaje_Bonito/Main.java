package Viaje_Bonito;



public class Main {
    public static void main(String[] args) {
        

        Reloj reloj=new Reloj();
        
        int cantVuelos=9;// cantidad de vuelos por aerolinea:: estan organizados de tal manera que nadie se quede sin vuelo , por lo que no es recomendable cambiar este parametro
        int horaDelPrimerVueloDeLasAerolineas=8; //en horas
        int intervaloDeHorasPorVuelo=2; //cada cuantas horas habrá un vuelo, primer vuelo a las 8AM 
        int cantAerolineas=3; // por cada aerolinea habrá una terminal que le corresponde
        int cantMaxEnPuestoAtencion=2; //cantidad maxima de personas que tendran los puestos de atencion de cada aerolinea
        int cantMaxPersonasEnLasFreeShop=2; //cantidad maxima de personas que pueden estar simultaneamente dentro de las freeshop
        int capacidadMaxTren=3; //capacidad maxima de personas del tren de transporte hacia terminales
        int cantidadPasajeros=70; //cantidad total de pasajeros 
        Aeropuerto aeropuerto=crearAeropuerto(capacidadMaxTren, cantAerolineas, cantMaxEnPuestoAtencion, reloj, cantVuelos, horaDelPrimerVueloDeLasAerolineas, intervaloDeHorasPorVuelo, cantMaxPersonasEnLasFreeShop);
        
        setearReloj(reloj, aeropuerto);

        runReloj(reloj); //lanza el reloj
        crearTren(aeropuerto, cantAerolineas); //crea y lanza el tren
        crearTemporizadorTren(aeropuerto); //crea y lanza el temporizador del tren
        crearPasajeros(cantidadPasajeros,reloj,aeropuerto);

       
    }

    public static Aeropuerto crearAeropuerto(int capacidadMaxTren,int cantAerolineas,int cantMaxEnPuestoAtencion,Reloj reloj, int cantVuelos,int horaDelPrimerVueloDeLasAerolineas,int intervaloDeHorasPorVuelo,int cantMaxPersonasEnLasFreeShop){
        Aeropuerto aeropuerto=new Aeropuerto(crearAerolineas(cantAerolineas, cantMaxEnPuestoAtencion, reloj, cantVuelos, horaDelPrimerVueloDeLasAerolineas, intervaloDeHorasPorVuelo), crearTerminales(cantAerolineas, cantMaxPersonasEnLasFreeShop), capacidadMaxTren);
        return aeropuerto;
    }

    public static Aerolinea[] crearAerolineas(int cantAerolineas,int cantMaxEnPuestoAtencion,Reloj reloj,int cantVuelos,int horaDelPrimerVueloDeLasAerolineas,int intervaloDeHorasPorVuelo){
        Aerolinea[] aerolineas=new Aerolinea[cantAerolineas];
        
        for(int i=0; i<cantAerolineas;i++){
            aerolineas[i]=new Aerolinea(i, crearVuelos(cantVuelos,i,horaDelPrimerVueloDeLasAerolineas,intervaloDeHorasPorVuelo), cantMaxEnPuestoAtencion, reloj);
        }


        return aerolineas;
    }

    public static Vuelo[] crearVuelos(int cantVuelos,int nroTerminal,int horaDelPrimerVueloDeLasAerolineas,int intervaloDeHorasPorVuelo){//el nroTerminal es el mismo que el de la aerolinea
        Vuelo[] vuelos=new Vuelo[cantVuelos];
        Thread[] aviones=new Thread[cantVuelos];
        int puertaEmbarque=0;
        int hora=horaDelPrimerVueloDeLasAerolineas;
        for(int i=0;i<cantVuelos;i++){
            
            vuelos[i]=new Vuelo(hora,nroTerminal,puertaEmbarque+1);//cada Terminal tendra 3 puertas de embarque, 1 - 2 - 3 
            puertaEmbarque=(puertaEmbarque+1)%3;
            hora=hora+intervaloDeHorasPorVuelo;//primer vuelo a las 8, luego cada 2 horas por cada aerolinea, hasta las 12 AM (9 vuelos)
            aviones[i]=new Thread(new Avion(i,vuelos[i]));
            aviones[i].start();
        }
        return vuelos;
    }

    public static Terminal[] crearTerminales(int cantTerminales,int cantMaxPersonasEnLasFreeShop){//cantTerminales == cantAerolineas 
        Terminal[] terminales=new Terminal[cantTerminales];
        for(int i=0;i<cantTerminales;i++){
            terminales[i]=new Terminal(i,cantMaxPersonasEnLasFreeShop);
        }
        return terminales;
    }
    public static void setearReloj(Reloj reloj,Aeropuerto aeropuerto){
        reloj.setAeropuerto(aeropuerto);
    }

    public static void crearTren(Aeropuerto aeropuerto,int cantTerminales){
        Thread tren=new Thread(new Tren(aeropuerto, cantTerminales));
        tren.start();
    }
    
    public static void crearTemporizadorTren(Aeropuerto aeropuerto){
        Thread temporizador=new Thread(new Temporizador(aeropuerto));
        temporizador.start();
    }

    public static void runReloj(Reloj reloj){
        Thread hiloReloj=new Thread(reloj);
        hiloReloj.start();
    }

    public static void crearPasajeros(int cantPasajeros,Reloj reloj,Aeropuerto aeropuerto){
        Thread[] pasajeros=new Thread[cantPasajeros];
        for(int i=0;i<cantPasajeros;i++){
            pasajeros[i]=new Thread(new Pasajero(i, reloj, aeropuerto));
            pasajeros[i].start();
        }
    }
}
