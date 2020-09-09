public class miEjecucionRunnable implements Runnable{
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
class ThreadTesting {
    public static void main(final String[] args) throws InterruptedException {
        final miEjecucionRunnable runable = new miEjecucionRunnable();
        Thread miHilo=new Thread(runable);
        miHilo.start();
        //miHilo.join();
        System.out.println("En el main");
        System.out.println("q ondaaa");
    }

}