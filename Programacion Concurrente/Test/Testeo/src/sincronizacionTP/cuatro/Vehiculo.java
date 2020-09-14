package sincronizacionTP.cuatro;

public class Vehiculo {

    
    private int patente ;
    private int kms ;
    private float tanque;
    private Surtidor surtidor ;

    public Vehiculo(int patente,Surtidor surtidor){
        this.patente=patente;
        this.kms=0;
        this.tanque=(float)0.5;
        this.surtidor=surtidor;

    }

    public int getPatente() {
        return patente;
    }

   

    public int getKms() {
        return kms;
    }

    public void setKms(int kms) {
        this.kms = kms;
    }

    public float getTanque() {
        return tanque;
    }

    public void setTanque(float tanque) {
        this.tanque = tanque;
    }

    public Surtidor getSurtidor() {
        return surtidor;
    }

    public void setSurtidor(Surtidor surtidor) {
        this.surtidor = surtidor;
    }
    
    
}
