public class miEjecucion extends Thread{
    public void run(){
       
        ir();
        
    }
    public void ir (){
        hacerMas();
    }
    public void hacerMas(){
        System.out.println("En la pila");
    }
}
class ThreadTesting2{
    public static void main(final String[] args) throws InterruptedException {
        final Thread miHilo = new miEjecucion();
        
        miHilo.start();
        miHilo.join();
        System.out.println("En el main");
        System.out.println("hola");
    }
    
}
