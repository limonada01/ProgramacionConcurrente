

package TP6.Trafico.B;


/**
 *
 * @author Limon
 */
public class Cola {
    private Nodo frente;
    private Nodo fin;
    
    public Cola(){
        this.fin=null;
        this.frente=null;
    }
    public boolean esVacia(){
        boolean flag=false;
        if((this.fin==null) && (this.frente==null)){
            flag=true;
        }
        return flag;
    }
    public boolean poner(Object elemento){
        boolean flag=esVacia();
        Nodo nuevo=new Nodo(elemento,null);
        if(flag){
            this.frente=nuevo;
            this.fin=nuevo;
            
        }else{
            this.fin.setEnlace(nuevo);
            this.fin=nuevo;
        }
        return true;   
        
    }
    public boolean sacar(){
        boolean respuesta;
        if(!esVacia()){
            this.frente=frente.getEnlace();
            if(this.frente==null)
                this.fin=null;
            respuesta=true;
        }else respuesta=false;
        
        return respuesta;
    }
    public Object obtenerFrente(){
        Object retorno=null;
        if(!esVacia())
            retorno=this.frente.getElemento();
            
        return retorno;
    }
    
    public void vaciar(){
        this.frente=null;
        this.fin=null;
    }
    public Cola clone(){
        Cola clon=new Cola();
        if(!esVacia()){
            Nodo aux1=this.frente;
            clon.frente= new Nodo(aux1.getElemento(),null);
            aux1=aux1.getEnlace();
            Nodo aux2=clon.frente;
            while(aux1 != null){    
                aux2.setEnlace(new Nodo(aux1.getElemento(),null));

                aux2=aux2.getEnlace();

                aux1=aux1.getEnlace();


            }
            clon.fin=aux2;
        }
        return clon;
    }
    public String toString(){
        String cadena="";
        if(esVacia()){
            cadena="Cola Vacia";
        }else{
        Nodo aux=this.frente;
        
        do{
            cadena+=aux.getElemento().toString()+" ";
            aux=aux.getEnlace();
            }while(aux!=null);
        aux=this.frente;
        }
        
        return cadena;
    }
    public boolean equals(Cola C1){
        boolean resultado=true;
        Nodo aux=this.frente;
        Nodo auxC1=C1.frente;
        if((esVacia() && !C1.esVacia()) ||(!esVacia() && C1.esVacia())){
            resultado=false;
        }else{
            while(aux!=null && auxC1!=null && resultado){

                    resultado=aux.getElemento().equals(auxC1.getElemento());

                    aux=aux.getEnlace();
                    auxC1=auxC1.getEnlace();
                    if((aux!=null && auxC1==null) ||(aux==null && auxC1!=null)){
                        resultado=false;
                    }
            }
        }
        return resultado;
    }
}
