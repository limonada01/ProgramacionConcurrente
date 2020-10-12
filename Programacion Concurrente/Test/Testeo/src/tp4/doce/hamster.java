package tp4.doce;

public class hamster implements Runnable {
    private int id;
    private Jaula jaula;

    public hamster(int id, Jaula jaula) {
        this.id = id;
        this.jaula = jaula;
    }

    @Override
    public void run() {
        try {
            this.jaula.comer(id);
            this.jaula.correr(id);
            this.jaula.descansar(id);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }
    
}
