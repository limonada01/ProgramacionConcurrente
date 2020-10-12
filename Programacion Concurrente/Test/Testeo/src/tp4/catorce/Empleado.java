package tp4.catorce;

public class Empleado extends Persona implements Runnable {
    public Empleado(int id, Confiteria confiteria) {
        super(id, confiteria);
    }
    public void pedir() throws InterruptedException {
        int opcion=(int)((Math.random()*3)+1);
        switch(opcion){
            case 1: this.getConfiteria().pedirBebida(this.getId());
                    this.getConfiteria().beber(this.getId());
                    break;
            case 2: this.getConfiteria().pedirComida(this.getId());
                    this.getConfiteria().comer(this.getId());
                    break;
            case 3: this.getConfiteria().pedirBebida(this.getId());
                    this.getConfiteria().beber(this.getId());
                    this.getConfiteria().pedirComida(this.getId());
                    this.getConfiteria().comer(this.getId());
                    break;
        }
    }
    @Override
    public void run() {
        try {
            boolean flag = true;
            while (flag) {
                if (this.getConfiteria().sentarse()) {
                    System.out.println("Empleado "+this.getId()+": Conseguí sentarme!");
                    pedir();
                    
                    System.out.println("Empleado "+this.getId()+": Muchas gracias por el servicio, regreso a trabajar");
                    this.getConfiteria().liberarSilla();
                    flag = false;
                } else {
                    System.out.println("Empleado "+this.getId()+": No hay sillas libres, de vuelta al laburo");
                    Thread.sleep(10000);
                    System.out.println("Empleado "+this.getId()+": Voy a ver si hay una silla disponible en la confiteria");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
