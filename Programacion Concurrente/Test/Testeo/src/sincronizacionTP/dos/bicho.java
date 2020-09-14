package sincronizacionTP.dos;

public class bicho implements Runnable {
    private HP vida;
    private int cantidad;

    public bicho(HP hp, int n) {

        this.vida = hp;
        this.cantidad = n;
    }

    public void run() {

        int vidaaa = this.vida.getVida();
        int suma = vidaaa + cantidad;
        this.vida.setVida(suma);

    }

    public static void main(String[] args) throws InterruptedException {
        HP vida = new HP(10);
        bicho Orc = new bicho(vida, -3);
        bicho Cur = new bicho(vida, 3);
        Thread orco = new Thread(Orc, "Orco");
        Thread curandero = new Thread(Cur, "Curandero");
        orco.start();
        curandero.start();
        //orco.join();
        //curandero.join();
        System.out.println("Vida final: "+vida.getVida());

    }

}
