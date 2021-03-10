package Viaje_Bonito;

public class Avion implements Runnable{
    private int id;
    private Vuelo vuelo;
    public Avion(int id ,Vuelo vuelo){
        this.id=id;
        this.vuelo=vuelo;
    }

    public void despegar(){
        System.out.println(ConsoleColors.BLUE_BOLD+" ::::::::::::::: AVION "+id+", Aerolinea: "+vuelo.getNroTerminal()+" --> Despegando con todos los pasajeros abordo... :::::::::::::::"+ConsoleColors.RESET);
    }

    @Override
    public void run() {
      
            try { 
                vuelo.avionEsperandoDespegar();
                despegar();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        
        
    }
    


}
