package Viaje_Bonito;

import java.awt.Color;

public class Tren implements Runnable {
    private Aeropuerto aeropuerto;
    private int nroTerminalActual = -1;// -1 : es el aeropuerto, 0: terminal A, 1: terminal B, 2: terminal C
    private int cantTerminales;

    public Tren(Aeropuerto aeropuerto, int cantTerminales) {
        this.aeropuerto = aeropuerto;
        this.cantTerminales = cantTerminales ;// de 0 a cantTerminales
    }

    
    public void siguienteTerminal() throws InterruptedException {
        Thread.sleep(50);// 2 mins sim
        nroTerminalActual = (nroTerminalActual + 1) % cantTerminales;
        System.out.println(
                ConsoleColors.RED_BRIGHT + "*Tren en Terminal " + nroTerminalActual + "*" + ConsoleColors.RESET);

    }

    @Override
    public void run() {
        try {
            while (true) {
                aeropuerto.trenListoParaQueSuban();// avisa que no está viajando y está listo para que los pasajero suban
                aeropuerto.transporteATerminal();// comienza el recorrido del tren
                
                for (int i = 0; i < cantTerminales; i++) {
                    siguienteTerminal();
                    if (aeropuerto.getBajanEn(nroTerminalActual) > 0) {
                        aeropuerto.abrirPuertasTren(nroTerminalActual);
                        aeropuerto.esperarQueBajenDelTren();
                    }
                }
                nroTerminalActual=-1;//tren en aeropuerto

                System.out.println(ConsoleColors.RED_BRIGHT + "*Tren de regreso a por nuevos pasajeros*" + ConsoleColors.RESET);
                Thread.sleep(150);// tiempo que tarda en regresar al aeropuerto
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
