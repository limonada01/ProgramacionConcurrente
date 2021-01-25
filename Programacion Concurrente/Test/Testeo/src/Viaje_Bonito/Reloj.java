package Viaje_Bonito;

public class Reloj implements Runnable {
    int hora;
    Aeropuerto aeropuerto;

    public Reloj(/*Aeropuerto aeropuerto*/) {
        this.hora = 0000;
        //this.aeropuerto = aeropuerto;
    }

    public void hacerFuncionar() {
        while (true) {
            hora = (hora + 10) % 2400;// avanza de a 1 minuto
            if (hora == 600) {
                aeropuerto.abrirAeropuerto();
            }
            if (hora == 2200) {
                aeropuerto.cerrarAeropuerto();
            }
            try {
                Thread.sleep(500);// medio segundo por minuto
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        hacerFuncionar();

    }
    
    public int getHora(){
        return hora;
    }


    public void setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }
}
