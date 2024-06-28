package modelos;

import enumTipoMueble.TipoMueble;
import java.util.Objects;

/**
 *
 * @author David Medina Garcia
 */
// Clase que representa un mueble y que implementa la interfaz Serializable
public class Mueble implements Comparable<Object> {

    // Atributos de la clase Mueble
    private String codigoMueble;
    private String descripMueble;
    private float precioUnitario;
    private int unidadesAlmacen;
    private int unidadesMinimas;
    private TipoMueble tipoMueble;

    // Constructor por defecto
    public Mueble() {
    }

    // Constructor con parámetros para inicializar los atributos
    public Mueble(String codigoMueble, String descripMueble, float precioUnario, int unidadesAlmacen, int unidadesMinimas, TipoMueble tipoMueble) {
        this.codigoMueble = codigoMueble;
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

    public String getCodigoMueble() {
        return codigoMueble;
    }

    public void setCodigoMueble(String codigoMueble) {
        this.codigoMueble = codigoMueble;
    }

    public String getDescripMueble() {
        return descripMueble;
    }

    public void setDescripMueble(String descripMueble) {
        this.descripMueble = descripMueble;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
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

    public float getTotalAlmacen() {
        return (this.precioUnitario * this.unidadesAlmacen);
    }

    // Método toString para obtener una representación en cadena del objeto Mueble
    @Override
    public String toString() {
        return "Mueble: " + codigoMueble + ", Descripcion: " + descripMueble + ", Precio: " + precioUnitario + ", Unidades en almacen: " + unidadesAlmacen + ", Unidades minimas: " + unidadesMinimas + ", Tipo de mueble: " + tipoMueble;
    }

    @Override
    public boolean equals(Object obj) {
        // Verificamos si el objeto actual es el mismo que el objeto pasado como argumento
        if (this == obj) {
            return true; // Si son el mismo objeto, retornamos true
        }
        // Verificamos si el objeto pasado como argumento es nulo
        if (obj == null) {
            return false; // Si es nulo, retornamos false
        }
        // Verificamos si los objetos pertenecen a la misma clase
        if (getClass() != obj.getClass()) {
            return false; // Si no pertenecen a la misma clase, retornamos false
        }
        // Convertimos el objeto pasado como argumento a un objeto de tipo Mueble
        final Mueble other = (Mueble) obj;
        // Comparamos los códigos de mueble de ambos objetos y retornamos el resultado de la comparación
        return Objects.equals(this.codigoMueble, other.codigoMueble);
    }

    @Override
    public int compareTo(Object o) {
        // Verificamos si el objeto pasado como argumento es una instancia de la clase Mueble
        if (o instanceof Mueble) {
            // Convertimos el objeto pasado como argumento a un objeto de tipo Mueble
            Mueble otroMueble = (Mueble) o;
            // Comparamos los códigos de mueble de ambos objetos y retornamos el resultado de la comparación
            return this.codigoMueble.compareTo(otroMueble.getCodigoMueble());
        }
        // Si el objeto pasado como argumento no es una instancia de la clase Mueble, lanzamos una excepción
        throw new IllegalArgumentException("Se esperaba un objeto de tipo Mueble");
    }

}
