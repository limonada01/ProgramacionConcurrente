package Viaje_Bonito;

public class Main {
    public static void main(String[] args) {
        //reloj
        Reloj reloj=new Reloj();
        
        //vuelos
        int cantVuelosA=3;
        int cantVuelosB=3;
        int cantVuelosC=3;

        Vuelo[] vuelosA=new Vuelo[cantVuelosA];
        Vuelo[] vuelosB=new Vuelo[cantVuelosB];
        Vuelo[] vuelosC=new Vuelo[cantVuelosC];
        int hora=600;
        int puertaEmbarque=1;
        for(int i=0;i< vuelosA.length;i++){
            vuelosA[i]=new Vuelo(hora+200,'a',puertaEmbarque);
            hora+=200;
            puertaEmbarque++;
        }
        for(int i=0;i< vuelosB.length;i++){
            vuelosA[i]=new Vuelo(hora+200,'b',puertaEmbarque);
            hora+=200;
            puertaEmbarque++;
        }
        for(int i=0;i< vuelosC.length;i++){
            vuelosA[i]=new Vuelo(hora+200,'c',puertaEmbarque);
            hora+=200;
            puertaEmbarque++;
        }
        // terminales
        int maxFreeShopA=10;
        int maxFreeShopB=10;
        int maxFreeShopC=10;
        Terminal terminalA=new Terminal('a', maxFreeShopA);
        Terminal terminalB=new Terminal('b', maxFreeShopB);
        Terminal terminalC=new Terminal('c', maxFreeShopC);
        
        Terminal[] terminales={terminalA,terminalB,terminalC};

        //aeropuerto
        int max=3;//max personas en los puestos de atencion de cada aerolinea
        Aerolinea aeroA=new Aerolinea(1, vuelosA, max, reloj);
        Aerolinea aeroB=new Aerolinea(2, vuelosB, max, reloj);
        Aerolinea aeroC=new Aerolinea(3, vuelosC, max, reloj);
        Aerolinea[] aerolineas={aeroA,aeroB,aeroC};
        int capacidadMaxTren=10;
        Aeropuerto aeropuerto=new Aeropuerto(aerolineas, terminales, capacidadMaxTren);

        //seteo el aeropuerto al reloj
        reloj.setAeropuerto(aeropuerto);


        

        
    }
}
