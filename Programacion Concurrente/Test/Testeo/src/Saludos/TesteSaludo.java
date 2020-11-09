package Saludos;

public class TesteSaludo {
    public static void main(String argv[]) {
        String[] nombresEmpleados = { "Pablo", "Luis", "Andrea", "Pedro", "Paula" };
        Saludo hola = new Saludo();
        Thread[] elPersonal = new Thread[6];
        elPersonal[0] = new Thread(new Personal(hola, "JEFE",true));
        for (int i = 1; i < 6; i++)
            elPersonal[i] = new Thread(new Personal(hola, nombresEmpleados[i - 1],false));
        for (int i = 0; i < 6; i++)
            elPersonal[i].start();
        System.out.println("LISTO, ahora que todos han saludado - a trabajar");
    }

}
