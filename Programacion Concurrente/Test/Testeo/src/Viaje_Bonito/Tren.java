package Viaje_Bonito;

import java.awt.Color;

public class Tren implements Runnable {
    private Aeropuerto aeropuerto;
    private char lugarActual='x';// x : es el aeropuerto, a: terminal A, b: terminal B, c: terminal C
    
    public Tren(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;

    }

    public void irTerminalC() throws InterruptedException {
        Thread.sleep(500);
        System.out.println(ConsoleColors.RED_BRIGHT+"*Tren en Terminal C*"+ConsoleColors.RESET);
        lugarActual='c';
    }

    public void irTerminalB() throws InterruptedException {
        Thread.sleep(500);
        System.out.println(ConsoleColors.RED_BRIGHT+"*Tren en Terminal B*"+ConsoleColors.RESET);
        lugarActual='b';
    }

    public void irTerminalA() throws InterruptedException {
        Thread.sleep(500);
        System.out.println(ConsoleColors.RED_BRIGHT+"*Tren en Terminal A*"+ConsoleColors.RESET);
        lugarActual='a';
    }

    
    @Override
    public void run() {
        try {
            while(true){
                aeropuerto.trenListoParaQueSuban();//avisa que no está viajando y está listo para que los pasajero suban
                aeropuerto.transporteATerminal();//comienza el recorrido del tren
                irTerminalC();
                if(aeropuerto.getBajanEnC()>0){
                    aeropuerto.abrirPuertasEnC();
                    aeropuerto.esperarQueBajenDelTren();
                }
                irTerminalB();
                if(aeropuerto.getBajanEnB()>0){
                    aeropuerto.abrirPuertasEnB();
                    aeropuerto.esperarQueBajenDelTren();
                }
                irTerminalA();
                if(aeropuerto.getBajanEnA()>0){
                    aeropuerto.abrirPuertasEnA();
                    aeropuerto.esperarQueBajenDelTren();
                }
                System.out.println(ConsoleColors.RED_BRIGHT+"*Tren de regreso a por nuevos pasajeros*"+ConsoleColors.RESET);
                Thread.sleep(5000);//tiempo que tarda en regresar al aeropuerto
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}
