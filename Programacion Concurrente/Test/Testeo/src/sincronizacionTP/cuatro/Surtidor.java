package sincronizacionTP.cuatro;

public class Surtidor {
    private float disponible = 50;

    public synchronized void cargarNafta(Auto auto, float cantidad) throws Exception {
        if ((this.disponible - cantidad )>0) {
            System.out.println("Cargando Nafta.." + cantidad + " Litros.");
            Thread.sleep(500);
            this.disponible=this.disponible-cantidad;
            auto.setTanque(cantidad);
            System.out.println("Listo!");
        }else{
            System.out.println("No hay nafta disponible en el surtidor");
        }
    }

    public float getDisponible() {
        return this.disponible;
    }
}
