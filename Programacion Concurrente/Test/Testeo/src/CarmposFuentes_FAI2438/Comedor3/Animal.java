package CarmposFuentes_FAI2438.Comedor3;



public class Animal  {
    private int id;
    private int especie;
    private Comedor comedor;
    public Animal(int id,int especie, Comedor comedor){
        this.id=id;
        this.especie=especie;
        this.comedor=comedor;

    }

    public int getId() {
        return id;
    }

   

    public int getEspecie() {
        return especie;
    }

  

    public Comedor getComedor() {
        return comedor;
    }

    

    
}
