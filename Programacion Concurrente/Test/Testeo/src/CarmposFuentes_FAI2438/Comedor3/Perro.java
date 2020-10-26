package CarmposFuentes_FAI2438.Comedor3;

public class Perro extends Animal implements Runnable {
    public Perro(int id, C0medor comedor) {
        super(id, 1, comedor);
    }

    public void comer() throws InterruptedException {
        boolean comio = false;
        while (!comio) {
            if (this.getComedor().entrarComer(this.getEspecie())) {
                System.out.println("El Perro " + this.getId() + " logro ENTRAR a COMER!");
                Thread.sleep(5000);// comiendo
                System.out.println("EL Perro " + this.getId() + " terminó de comer y SALIO del comedero!");
                this.getComedor().salirComedero();
                comio = true;
            } else {
                System.out.println("El Perro " + this.getId() + " no puede entrar AUN!");
                Thread.sleep(7000);
            }
        }
    }

    @Override
    public void run() {
        try {
            comer();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}
