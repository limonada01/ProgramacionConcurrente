package ProdConsumidorMonitores;

public class productor implements Runnable {
    private int id;
    private Buffer buffer;

    public productor(int id, Buffer buffer) {
        this.id = id;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while(true){
                buffer.producir(id);
               
                Thread.sleep(1000);
            }
            
        }catch(

    InterruptedException e)
    {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

}

}
