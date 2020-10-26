package CarmposFuentes_FAI2438.Comedor;

public class Gato extends Animal implements Runnable {
    public Gato(int id, C0medor comedor) {
        super(id, comedor);
    }

    public void comer() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            if (this.getComedor().entrarGato(this.getId())) {
                
                Thread.sleep(5000);
                //System.out.println("El GATO " + this.getId() + " SALIO del comedor!");
                this.getComedor().salirGato(this.getId());
                flag = false;
            } else {
                System.out.println("El GATO " + this.getId() + " NO puede entrar AUN!");
                Thread.sleep(6000);
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
