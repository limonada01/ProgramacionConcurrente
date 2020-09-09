import java.rmi.server.SocketSecurityException;

import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;

public class App {
    public static void calcularBiseccion(double a, double b, double error) {
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("Error: " + error);
        double r;
        if ((funcion(a) * funcion(b)) < 0) {
            do {
                r = (double)(a + b) / 2;
                System.out.println(r);
                if ((funcion(a) * funcion(r)) < 0) {
                    b = r;
                } else {
                    a = r;
                }

            } while (Math.abs(funcion(r)) > error);
            System.out.println("raiz aprox: " + r);

        } else {
            System.out.println("no cumple con la condicion inicial");
        }

    }

    public static double funcion(double x) {
        double resultado = (double)(Math.PI*Math.pow(x, 2)*(9-x)/3)-30;
        return resultado;

    }

    public static void main(String[] args) {
        calcularBiseccion(1, 3, 0.01);
    }
}
