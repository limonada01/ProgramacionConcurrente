package sincronizacionTP.cuatro;

import org.graalvm.compiler.lir.amd64.vector.AMD64VectorShuffle.ExtractShortOp;

public class Auto implements Runnable {
    private int patente;
    private int kms;
    private float tanque;
    private Surtidor surtidor;

    public Auto(int patente, int kms, float tanque, Surtidor surtidor) {
        this.patente = patente;
        this.kms = kms;
        this.tanque = tanque;
        this.surtidor = surtidor;
    }

    public void recorrerUnKm() throws Exception {

        if (tanque > 0) {

            this.kms++;
            this.tanque = (float) (this.tanque - (0.1));
            System.out.println("El auto con patatente: " + this.patente + " avanzo un km.");
        } else {
            System.out.println("Patente: "+this.patente+". No hay nafta suficiente");
            Thread.sleep(1500);
            this.surtidor.cargarNafta(this, (float) 0.5);
            System.out.println("Auto con patente: " + this.patente + " Cargado con exito!");
        }

    }

    public void setTanque(float nafta) {
        this.tanque = nafta;
    }

    public void run() {
        for (int i = 0; i < 7; i++) {
            try {
                recorrerUnKm();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("Auto Patente: "+this.patente+" recorrio: "+ this.kms +" kms. Tanque: "+this.tanque );
    }

    public static void main(String[] args) throws Exception{
        Surtidor surtidor = new Surtidor();
        Auto uno = new Auto(1, 0, (float) 0.5, surtidor);
        Auto dos = new Auto(2, 0, (float) 0.5, surtidor);
        Auto tres = new Auto(3, 0, (float) 0.5, surtidor);
        Auto cuatro = new Auto(4, 0, (float) 0.5, surtidor);
        Auto cinco = new Auto(5, 0, (float) 0.5, surtidor);
        Thread one = new Thread(uno, "1");
        Thread two = new Thread(dos, "2");
        Thread three = new Thread(tres, "3");
        Thread four = new Thread(cuatro, "4");
        Thread five = new Thread(cinco, "5");
        one.start();
        two.start();
        three.start();
        four.start();
        five.start();
        one.join();
        two.join();
        three.join();
        four.join();
        five.join();
        System.out.println("Disponible en el surtidor al final: "+surtidor.getDisponible());

    }
}
