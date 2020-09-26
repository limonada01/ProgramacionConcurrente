package tp4.nueve;

public class Calle {
    public static void main(String[] args) {
        Taxi taxi=new Taxi();
        Thread tachero=new Thread(new Taxista(0, taxi));
        Thread cliente=new Thread(new Cliente(1, taxi));
        Thread cliente2=new Thread(new Cliente(2, taxi));
        tachero.start();
        cliente.start();
        cliente2.start();
    }
}
