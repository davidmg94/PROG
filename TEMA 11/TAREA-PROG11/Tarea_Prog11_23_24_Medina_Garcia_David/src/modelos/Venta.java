package modelos;

/**
 *
 * @author David Medina Garcia
 */
// Clase que representa una venta
public class Venta {

    // Atributos de la clase venta
    private int idVenta;
    private String nombreCliente;
    private int unidadesVendidas;
    private int idMueble;

    // Constructor por defecto
    public Venta() {
    }

    // Constructor con parámetros para inicializar los atributos
    public Venta(int idVenta, String nombreCliente, int unidadesVendidas, int idMueble) {
        this.idVenta = idVenta;
        this.nombreCliente = nombreCliente;
        this.unidadesVendidas = unidadesVendidas;
        this.idMueble = idMueble;
    }

    // Métodos getter y setter para acceder y modificar los atributos
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public void setUnidadesVendidas(int unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
    }

      public int getIdMueble() {
        return idMueble;
    }

    public void setIdMueble(int idMueble) {
        this.idMueble = idMueble;
    }

    @Override
    public String toString() {
        return "Venta{" + "idVenta=" + idVenta + ", nombreCliente=" + nombreCliente + ", unidadesVendidas=" + unidadesVendidas + ", mueble=" + idMueble + '}';
    }

}
