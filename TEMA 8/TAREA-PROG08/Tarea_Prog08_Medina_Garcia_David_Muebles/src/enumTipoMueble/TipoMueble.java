package enumTipoMueble;

/**
 *
 * @author David Medina Garcia
 */
// Enumeraci�n que representa diferentes tipos de muebles
public enum TipoMueble {

    // Enumerados con valores y descripciones asociadas
    H("HOGAR"), // Tipo de mueble para uso en el hogar
    D("DESPACHO"), // Tipo de mueble para uso en despachos
    C("COLEGIOS");  // Tipo de mueble para uso en colegios

    // Atributo para almacenar la descripci�n del tipo de mueble
    private final String tipoMueble;

    // Constructor privado para asignar la descripci�n al tipo de mueble
    private TipoMueble(String tipoMueble) {
        this.tipoMueble = tipoMueble;
    }

    // M�todo para obtener la descripci�n completa del tipo de mueble
    public String getTipoMuebleCompleto() {
        return this.tipoMueble;
    }
}
