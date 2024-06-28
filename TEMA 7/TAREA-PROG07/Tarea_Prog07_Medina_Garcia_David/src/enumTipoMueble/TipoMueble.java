package enumTipoMueble;

/**
 *
 * @author David Medina Garcia
 */
// Enumeración que representa diferentes tipos de muebles
public enum TipoMueble {

    // Enumerados con valores y descripciones asociadas
    H("Hogar"), // Tipo de mueble para uso en el hogar
    D("Despacho"), // Tipo de mueble para uso en despachos
    C("Colegios");  // Tipo de mueble para uso en colegios

    // Atributo para almacenar la descripción del tipo de mueble
    private final String tipoMueble;

    // Constructor privado para asignar la descripción al tipo de mueble
    private TipoMueble(String tipoMueble) {
        this.tipoMueble = tipoMueble;
    }

    // Método para obtener la descripción completa del tipo de mueble
    public String getTipoMuebleCompleto() {
        return this.tipoMueble;
    }
}
