package CarmposFuentes_FAI2438.Promocion;

public class Coche implements Runnable {
    private int id;
    private GestorCruce gestorCruce;

    public Coche(int id, GestorCruce gestorCruce) {
        this.id = id;
        this.gestorCruce = gestorCruce;
    }

    public void andar() throws InterruptedException {
        int opcion = (int) ((Math.random() * 2) + 1);
        switch (opcion) {
            case 1:
                this.gestorCruce.llegaNorte(id);

                ;
                break;
            case 2:
                this.gestorCruce.llegaOeste(id);

                ;
                break;
        }
    }

    @Override
    public void run() {
        try {
            andar();
            this.gestorCruce.sale();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    
}
