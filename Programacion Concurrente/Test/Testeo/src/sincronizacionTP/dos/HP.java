package sincronizacionTP.dos;

public  class HP {
    private int vida;

    public HP(int n){
        this.vida=n;
    }

    public synchronized  int getVida() {
        return vida;
    }

    public synchronized void modificarVida(int cant) {
        this.vida = vida+cant;
        System.out.println("-----> "+getVida());
    }

    
}
