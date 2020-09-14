package sincronizacionTP.cuatro;



public class Auto extends Vehiculo implements Runnable {
    

    public Auto(int patente, Surtidor surtidor) {
        super(patente, surtidor);
    }

    public void recorrerUnKm() throws Exception {

        if (this.getTanque() > 0) {

            this.setKms(this.getKms()+1);
            this.setTanque((float)(this.getTanque()-(0.1))); 
            System.out.println("El auto con patatente: " + this.getPatente() + " avanzo un km.");
        } else {
            System.out.println("Patente: " + this.getPatente() + ". No hay nafta suficiente");
            Thread.sleep(1500);
            this.getSurtidor().cargarNafta(this, (float) 0.5);
            System.out.println("Auto con patente: " + this.getPatente() + " Cargado con exito!");
        }

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
        System.out.println("Auto Patente: " + this.getPatente() + " recorrio: " + this.getKms() + " kms. Tanque: " + this.getTanque());
    }
    
    public static void main(String[] args) throws Exception{
        Thread[] autos = new Thread[5];
        Surtidor surtidor = new Surtidor();
        for (int i = 0; i <= 4; i++) {
            autos[i] = new Thread(new Auto(i, surtidor), Integer.toString(i));
            autos[i].start();
        }
        for (int i = 0; i <= 4; i++) {
            autos[i].join();
        }
        System.out.println("Disponible en el surtidor al final: " + surtidor.getDisponible());    

    }
}
