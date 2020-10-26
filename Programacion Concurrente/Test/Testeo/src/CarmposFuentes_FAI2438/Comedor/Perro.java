package CarmposFuentes_FAI2438.Comedor;

public class Perro extends Animal implements Runnable{
    public Perro(int id, C0medor comedor){
        super(id, comedor);
    }
    public void comer() throws InterruptedException {
        this.getComedor().entrarGato();
        
        Thread.sleep(5000);
        System.out.println("El PERRO " + this.getId() + " SALIO del comedor!");
        this.getComedor().salirPerro();
    }

    @Override
    public void run() {
        

    }
    
}
