package TP6.Trafico.C;

import java.util.Random;

public class Auto  {
    private int id;
    private GestionaTrafico puente;
    private Random random;

    public Auto(int id,GestionaTrafico puente){
        this.id=id;
        this.puente=puente;
        this.random=new Random();

    }

    public int getId() {
        return id;
    }


    public GestionaTrafico getPuente() {
        return puente;
    }


    public Random getRandom() {
        return random;
    }


   

}
