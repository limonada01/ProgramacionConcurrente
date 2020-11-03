package tp5.Comedor2;

public class Gato extends Animal implements Runnable {
    public Gato(int id, C0medor comedor) {
        super(id, 0, comedor);
    }

    public void comer() throws InterruptedException {
        boolean comio = false;
        while (!comio) {
            if (this.getComedor().entrarComer(this.getEspecie())) {
                System.out.println("El Gato " + this.getId() + " logro ENTRAR a COMER!");
                Thread.sleep(5000);// comiendo
                System.out.println("EL Gato " + this.getId() + " terminó de comer y SALIO del comedero!");
                this.getComedor().salirComedero();
                comio = true;
            } else {
                System.out.println("El Gato " + this.getId() + " no puede entrar AUN!");
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
