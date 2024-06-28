package modelos;

import java.util.Objects;

/**
 *
 * @author David Medina Garcia
 */
public class Anuncio {

    private int duracion; // Duración del anuncio en segundos
    private String nombreProducto; 
    private String mensajeAnuncio; 

    // Constructor sin argumentos
    public Anuncio() {
        this.duracion = 0;
        this.mensajeAnuncio = "";
        this.nombreProducto = "";
    }

    // Constructor con argumentos
    public Anuncio(int duracion, String nombreProducto, String mensajeAnuncio) {
        this.duracion = duracion;
        this.nombreProducto = nombreProducto;
        this.mensajeAnuncio = mensajeAnuncio;
    }

    // Métodos getters y setters
    public String getMensajeAnuncio() {
        return mensajeAnuncio;
    }

    public void setMensajeAnuncio(String mensajeAnuncio) {
        this.mensajeAnuncio = mensajeAnuncio;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    // Método toString para representar el anuncio como una cadena de texto
    @Override
    public String toString() {
        return "Nombre del producto: " + nombreProducto + ", Duración: " + Multimedia.obtenerHorasMinutosSegundos(duracion);
    }

    // Método equals para comparar dos objetos Anuncio
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Anuncio other = (Anuncio) obj;
        if (this.duracion != other.duracion) {
            return false;
        }
        return Objects.equals(this.nombreProducto, other.nombreProducto);
    }

}
