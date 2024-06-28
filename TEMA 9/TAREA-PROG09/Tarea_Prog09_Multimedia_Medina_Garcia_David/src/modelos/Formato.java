package modelos;

/**
 *
 * @author David Medina Garcia
 */
public class Formato {

    private int codigoFormato;
    final private String[] arrayFormato = {"wav", "mp3", "midi", "mp4", "avi", "mov", "mpg", "cdAudio", "dvd"}; 

    // Constructor sin argumentos
    public Formato() {
        this.codigoFormato = 0; // Por defecto, el formato es el primero en el array
    }

    // Constructor con argumentos
    public Formato(int codigoFromato) {
        this.codigoFormato = codigoFromato;
    }

    // M�todo para obtener el nombre del formato seg�n el c�digo
    public String getNombre() {
        return arrayFormato[codigoFormato];
    }

    // M�todo para obtener todos los nombres de formatos
    public String[] obtenerNombresFormatos() {
        return arrayFormato;
    }

    // M�todos getters y setters
    public int getCodigoFormato() {
        return codigoFormato;
    }

    public void setCodigoFormato(int codigoFormato) {
        this.codigoFormato = codigoFormato;
    }

}
