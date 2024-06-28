package modelos;

import java.util.Objects;

/**
 *
 * @author David Medina Garcia
 */
public class Anuncio {

    private int duracion; // Duraci�n del anuncio en segundos
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

    // M�todos getters y setters
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

    // M�todo toString para representar el anuncio como una cadena de texto
    @Override
    public String toString() {
        return "Nombre del producto: " + nombreProducto + ", Duraci�n: " + Multimedia.obtenerHorasMinutosSegundos(duracion);
    }

    // M�todo equals para comparar dos objetos Anuncio
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
