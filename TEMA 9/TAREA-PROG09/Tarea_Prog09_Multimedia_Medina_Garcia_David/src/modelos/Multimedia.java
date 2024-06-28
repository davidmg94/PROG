package modelos;

import controlador.Utilidades;
import java.util.Objects;

/**
 *
 * @author David Medina Garcia
 */
public class Multimedia {

    private String titulo; 
    private String autor; 
    protected int duracion;
    private Formato formato;

    // Constructor sin argumentos
    public Multimedia() {
        this.titulo = "";
        this.autor = "";
        this.duracion = 0;
        this.formato = null;
    }

    // Constructor con argumentos
    public Multimedia(String titulo, String autor, int duracion, Formato formato) {
        this.titulo = titulo;
        this.autor = autor;
        this.duracion = duracion;
        this.formato = formato;
    }

    // Métodos getters y setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Formato getFormato() {
        return formato;
    }

    public void setFormato(Formato formato) {
        this.formato = formato;
    }

    // Método toString para representar el archivo multimedia como una cadena de texto
    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor + ", Duración: " + obtenerHorasMinutosSegundos(duracion) + ", Formato: " + formato.getNombre();
    }

    // Método estático para obtener la representación en horas, minutos y segundos de la duración
    protected static String obtenerHorasMinutosSegundos(int duracion) {
        int horas = duracion / 3600;
        int minutos = (duracion % 3600) / 60;
        int segundos = duracion % 60;

        if (horas == 0 && minutos == 0) {
            return segundos + " segundos";
        } else if (horas == 0) {
            return minutos + " minutos, " + segundos + " segundos";
        } else {
            return horas + " horas, " + minutos + " minutos, " + segundos + " segundos";
        }
    }

    // Método equals para comparar dos archivos multimedia
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
        final Multimedia other = (Multimedia) obj;
        if (this.duracion != other.duracion) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        return Objects.equals(this.autor, other.autor);
    }

    // Método para reproducir el archivo multimedia (simplemente muestra el tiempo)
    public void reproducir() {
        // Inicialización de variables para contar el tiempo
        int horas = 0;
        int minutos = 0;
        int segundos = 0;
        
        // Ciclo para simular la reproducción del multimedia
        for (int i = 0; i <= this.duracion; i++) {
            try {
                // Actualización de las unidades de tiempo
                if (segundos > 59) {
                    segundos = 0;
                    minutos++;
                }
                if (minutos > 59) {
                    minutos = 0;
                    horas++;
                }
                // Muestra el tiempo formateado
                System.out.printf("%02d:%02d:%02d\n", horas, minutos, segundos);
                segundos++;
                Thread.sleep(1000); // Espera de 1 segundo
            } catch (InterruptedException e) {
                // Manejo de excepciones si ocurre un error durante la reproducción
                Utilidades.visualizarMensaje("Error al reproducir el multimedia: " + e.getMessage());
            }
        }
        // Indica que la reproducción ha terminado
        Utilidades.visualizarMensaje("[Fin]\n");
    }

}
