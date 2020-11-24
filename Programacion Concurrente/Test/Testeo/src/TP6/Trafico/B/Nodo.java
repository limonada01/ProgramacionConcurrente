package TP6.Trafico.B;

/**
 *
 * @author Limon
 */
 class Nodo {
    private Object elemento;
    private Nodo enlace;
    
    public Nodo(Object elem,Nodo enl){
        this.elemento=elem;
        this.enlace=enl;
        
    }
    
    public Object getElemento(){
        return this.elemento;
    }
    public Nodo getEnlace(){
        return this.enlace;
    }
    public void setElemento(Object el){
        this.elemento=el;
    }
    public void setEnlace(Nodo en){
        this.enlace=en;
    }
}
