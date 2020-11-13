package Saludos;

public class Personal implements Runnable {
    private String nombre;
    private Saludo saludo;
    private boolean esJefe;

    

    

    Personal(Saludo s, String n,boolean esJefe) {
        this.esJefe = esJefe;
        nombre = n;
        saludo = s;
       
    }

    public void run() {

        while (saludo.verificarLlegada()) {
            System.out.println("fau cat");
            
            saludo.esperar();
        }
        if (!saludo.getNotificados()) {
            System.out.println("fau gato");
            saludo.notificarTodos();
        }

        if (esJefe) {
            //System.out.println("fau gato");
            saludo.saludoJefe();
            
        } else {
            saludo.saludarJefe(nombre);;
        }

        /*
         * System.out.println("(" + nombre + " llega)"); if (esJefe) { while (llegaron <
         * numEmp) {
         * 
         * System.out.println("(Esperando...)");
         * 
         * 
         * } saludo.saludoJefe(); } else { synchronized (this) { llegaron++; }
         * 
         * saludo.esperarJefe(nombre);
         * 
         * 
         * }
         */
    }

}
