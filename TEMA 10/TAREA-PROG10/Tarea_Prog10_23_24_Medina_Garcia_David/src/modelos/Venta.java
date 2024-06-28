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
    private Mueble mueble;

    // Constructor por defecto
    public Venta() {
    }

    // Constructor con parametros para inicializar los atributos
    public Venta(int idVenta, String nombreCliente, int unidadesVendidas, Mueble mueble) {
        this.idVenta = idVenta;
        this.nombreCliente = nombreCliente;
        this.unidadesVendidas = unidadesVendidas;
        this.mueble = mueble;
    }

    // Metodos getter y setter para acceder y modificar los atributos
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

      public Mueble getMueble() {
        return mueble;
    }

    public void setMueble(Mueble mueble) {
        this.mueble = mueble;
    }

    @Override
    public String toString() {
        return "Venta: idVenta= " + idVenta + ", nombreCliente= " + nombreCliente + ", unidadesVendidas= " + unidadesVendidas + ", mueble= " + mueble;
    }

}
