package sincronizacionTP.dos;

public  class HP {
    private int vida;

    public HP(int n){
        this.vida=n;
    }

    public int getVida() {
        return vida;
    }

    public synchronized void setVida(int vida) {
        this.vida = vida;
        System.out.println("-----> "+getVida());
    }

    
}
