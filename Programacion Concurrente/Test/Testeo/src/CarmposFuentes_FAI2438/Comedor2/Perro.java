package CarmposFuentes_FAI2438.Comedor2;

public class Perro extends Animal implements Runnable {
    public Perro(int id, C0medor comedor) {
        super(id, comedor);
    }

    public void comer() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            if (this.getComedor().entrarPerro()) {
                System.out.println("El PERRO " + this.getId() + " entro a COMER!");
                Thread.sleep(8000);
                this.getComedor().salirComedero();
                System.out.println("El PERRO " + this.getId() + " SALIO DEL COMEDERO!");
                flag = false;
            } else {
                System.out.println("El PERRO " + this.getId() + " NO puede entrar AUN!");
                Thread.sleep(10000);
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
