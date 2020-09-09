package pingpongEjercicio;
public class Dato{
       private int valor;

       synchronized void contar(){
              valor = ++valor;
       }

       int obtenerValor(){
              return valor;
       }
}