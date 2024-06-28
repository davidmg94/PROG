package modelos;

import java.io.Serializable;
import enumTipoMueble.TipoMueble;

/**
 *
 * @author David Medina Garcia
 */
// Clase que representa un mueble y que implementa la interfaz Serializable
public class Mueble implements Serializable {

    // Atributos de la clase Mueble
    private int idMueble;
    private String descripMueble;
    private double precioUnitario;
    private int unidadesAlmacen;
    private int unidadesMinimas;
    private TipoMueble tipoMueble;

    // Constructor por defecto
    public Mueble() {
    }

    // Constructor con parámetros para inicializar los atributos
    public Mueble(int idMueble, String descripMueble, double precioUnario, int unidadesAlmacen, int unidadesMinimas, TipoMueble tipoMueble) {
        this.idMueble = idMueble;
        this.descripMueble = descripMueble;
        this.precioUnitario = precioUnario;
        this.unidadesAlmacen = unidadesAlmacen;
        this.unidadesMinimas = unidadesMinimas;
        this.tipoMueble = tipoMueble;
    }

    // Métodos getter y setter para acceder y modificar los atributos
    public TipoMueble getTipoMueble() {
        return tipoMueble;
    }

    public void setTipoMueble(TipoMueble tipoMueble) {
        this.tipoMueble = tipoMueble;
    }

    public int getIdMueble() {
        return idMueble;
    }

    public void setIdMueble(int idMueble) {
        this.idMueble = idMueble;
    }

    public String getDescripMueble() {
        return descripMueble;
    }

    public void setDescripMueble(String descripMueble) {
        this.descripMueble = descripMueble;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getUnidadesAlmacen() {
        return unidadesAlmacen;
    }

    public void setUnidadesAlmacen(int unidadesAlmacen) {
        this.unidadesAlmacen = unidadesAlmacen;
    }

    public int getUnidadesMinimas() {
        return unidadesMinimas;
    }

    public void setUnidadesMinimas(int unidadesMinimas) {
        this.unidadesMinimas = unidadesMinimas;
    }

    // Método toString para obtener una representación en cadena del objeto Mueble
    @Override
    public String toString() {
        return "Mueble: " + idMueble + ", Descripcion: " + descripMueble + ", Precio: " + precioUnitario + ", Unidades en almacen: " + unidadesAlmacen + ", Unidades minimas: " + unidadesMinimas + ", Tipo de mueble: " + tipoMueble;
    }
    
    // Método toString para obtener una representación en cadena del objeto Mueble personalizado
    public String toStringCompleto() {
        String listaFortmateada = String.format("%s\t%s\t\t%.2f\t%d\t\t%d\t%s", idMueble, descripMueble, precioUnitario, unidadesAlmacen, unidadesMinimas, "      " + tipoMueble.getTipoMuebleCompleto());
        return listaFortmateada;
    }
}
