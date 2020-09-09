public class PingPong extends Thread {
        private String cadena;
        private int tiempo;
    
        public PingPong(String cadena, int tiempo) {
            this.cadena = cadena;
            this.tiempo = tiempo;
        }
        
        @Override
        public void run(){ 
            for(int i=1; i<tiempo*10;i++){
                System.out.println(cadena+" ");
                try{
                    Thread.sleep(tiempo);}
                catch(InterruptedException e){
                       
                }
        }
        }  
    }  