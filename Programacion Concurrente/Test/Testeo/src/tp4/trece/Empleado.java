package tp4.trece;

public class Empleado extends Persona implements Runnable{
    public Empleado(int id, Confiteria confiteria){
        super(id,confiteria);
    }

    @Override
    public void run() {
        try {
            this.getConfiteria().sentarse(this.getId());
            this.getConfiteria().pedirComida(this.getId());
            this.getConfiteria().comer(this.getId());
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
