package Viaje_Bonito;



public class Main {
    public static void main(String[] args) {
        //reloj
        Reloj reloj=new Reloj();
        
        //vuelos
    
        int cantVuelos=9;
        Vuelo[] vuelosA=new Vuelo[cantVuelos];
        Vuelo[] vuelosB=new Vuelo[cantVuelos];
        Vuelo[] vuelosC=new Vuelo[cantVuelos];
        int hora=6;
        int puertaEmbarqueA=1;
        int puertaEmbarqueB=4;
        int puertaEmbarqueC=7;   

        for(int i=0;i< cantVuelos;i++){
            vuelosA[i]=new Vuelo(hora+2,'a',puertaEmbarqueA);
            vuelosB[i]=new Vuelo(hora+2,'b',puertaEmbarqueB);
            vuelosC[i]=new Vuelo(hora+2,'c',puertaEmbarqueC);
            hora+=2;
            puertaEmbarqueA++;
            puertaEmbarqueB++;
            puertaEmbarqueC++;
            if(puertaEmbarqueA==4){
                puertaEmbarqueA=1;
            }
            
            if(puertaEmbarqueB==7){
                puertaEmbarqueA=4;
            }
            
            if(puertaEmbarqueC==10){
                puertaEmbarqueA=7;
            }
        }
        
        int maxFreeShopA=3;
        int maxFreeShopB=3;
        int maxFreeShopC=3;
        Terminal terminalA=new Terminal('a', maxFreeShopA);
        Terminal terminalB=new Terminal('b', maxFreeShopB);
        Terminal terminalC=new Terminal('c', maxFreeShopC);
        
        Terminal[] terminales={terminalA,terminalB,terminalC};

        //aeropuerto
        int max=2;//max personas en los puestos de atencion de cada aerolinea
        Aerolinea aeroA=new Aerolinea(1, vuelosA, max, reloj);
        Aerolinea aeroB=new Aerolinea(2, vuelosB, max, reloj);
        Aerolinea aeroC=new Aerolinea(3, vuelosC, max, reloj);
        Aerolinea[] aerolineas={aeroA,aeroB,aeroC};
        
        int capacidadMaxTren=3;
        Aeropuerto aeropuerto=new Aeropuerto(aerolineas, terminales, capacidadMaxTren);

        //temporizador
        Thread temporizador=new Thread(new Temporizador(aeropuerto));
        temporizador.start();

        //seteo el aeropuerto al reloj
        reloj.setAeropuerto(aeropuerto);

        //tren
        Thread tren=new Thread(new Tren(aeropuerto));
        tren.start();
        //hilo reloj

        Thread hiloReloj=new Thread(reloj);
        hiloReloj.start();


        //pasajeros
        int cantPasajeros=10;
        Thread[] pasajeros=new Thread[cantPasajeros];

        for(int i=0;i<pasajeros.length;i++){
            pasajeros[i]=new Thread(new Pasajero(i+1, reloj,aeropuerto));
            pasajeros[i].start();
        }
    }
}
