package modelos;

/**
 *
 * @author David Medina Garcia
 */
// Clase que representa un mueble
public class Mueble {

    // Atributos de la clase Mueble
    private int idMueble;
    private String descripMueble;
    private float precioUnitario;
    private int unidadesExistentes;

    // Constructor por defecto
    public Mueble() {
    }

    // Constructor con parámetros para inicializar los atributos
    public Mueble(int idMueble, String descripMueble, float precioUnario, int unidadesExistentes) {
        this.idMueble = idMueble;
        this.descripMueble = descripMueble;
        this.precioUnitario = precioUnario;
        this.unidadesExistentes = unidadesExistentes;
    }

    // Métodos getter y setter para acceder y modificar los atributos
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

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getUnidadesExistentes() {
        return unidadesExistentes;
    }

    public void setUnidadesExistentes(int unidadesExistentes) {
        this.unidadesExistentes = unidadesExistentes;
    }

    @Override
    public String toString() {
        return "Mueble{" + "idMueble=" + idMueble + ", descripMueble=" + descripMueble + ", precioUnitario=" + precioUnitario + ", unidadesExistentes=" + unidadesExistentes + '}';
    }

}
