package CarmposFuentes_FAI2438.Comedor2;

public class Gato extends Animal implements Runnable {
    public Gato(int id, C0medor comedor) {
        super(id, comedor);
    }

    public void comer() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            if (this.getComedor().entrarGato()) {
                System.out.println("El GATO "+this.getId()+" entro a COMER!");
                Thread.sleep(1000);
                this.getComedor().salirComedero();
                System.out.println("El GATO "+this.getId()+" SALIO DEL COMEDERO!");
                flag = false;
            } else {
                System.out.println("El GATO " + this.getId() + " NO puede entrar AUN!");
                Thread.sleep(100);
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
