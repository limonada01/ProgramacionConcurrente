package Viaje_Bonito;

public class Reloj implements Runnable {
    int hora;
    int min;
    Aeropuerto aeropuerto;

    public Reloj(/*Aeropuerto aeropuerto*/) {
        this.hora = 5;
        this.min=0;
        //this.aeropuerto = aeropuerto;
    }

    public void on() {
        while (true) {
            min=min+10;
            if(min==60){
                min=0;
                hora=(hora+1)%24;
            }
            if (hora == 6 && min==0) {
                aeropuerto.abrirAeropuerto();
            }
            if (hora == 22 && min ==0 ) {
                aeropuerto.cerrarAeropuerto();
            }
            try {
                Thread.sleep(250);// un cuarto de segundo real por 10 minutos en la simulacion
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        on();

    }
    
    public int getHora(){
        return hora;
    }

    public int getMinutos(){
        return min;
    }    

    public void setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }
}
