package modelos;

import controlador.Utilidades;

/**
 *
 * @author David Medina Garcia
 */
public class CapituloSerieInfantil extends Multimedia {

    private int numeroCapitulo;
    private String canalTV;
    private Anuncio anuncio;

    // Constructor con par�metros
    public CapituloSerieInfantil(int numeroCapitulo, String canalTV, Anuncio anuncio) {
        super(); // Llama al constructor de la clase padre (Multimedia) sin argumentos
        this.numeroCapitulo = numeroCapitulo;
        this.canalTV = canalTV;
        this.anuncio = anuncio;
    }

    // Constructor con par�metros que incluye un objeto Multimedia
    public CapituloSerieInfantil(Multimedia multimedia, int numeroCapitulo, String canalTV, Anuncio anuncio) {
        // Llama al constructor de la clase padre con los atributos heredados
        super(multimedia.getTitulo(), multimedia.getAutor(), multimedia.getDuracion(), multimedia.getFormato());
        this.numeroCapitulo = numeroCapitulo;
        this.canalTV = canalTV;
        this.anuncio = anuncio;
    }

    // M�todos getters y setters
    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public int getNumeroCapitulo() {
        return numeroCapitulo;
    }

    public void setNumeroCapitulo(int numeroCapitulo) {
        this.numeroCapitulo = numeroCapitulo;
    }

    public String getCanalTV() {
        return canalTV;
    }

    public void setCanalTV(String canalTV) {
        this.canalTV = canalTV;
    }

    // M�todo toString para representar el cap�tulo como una cadena de texto
    @Override
    public String toString() {
        return super.toString() + ", Numero Capitulo: " + numeroCapitulo + ", Canal TV: " + canalTV + ", Anuncio: " + anuncio;
    }

    // M�todo para reproducir el cap�tulo
    @Override
    public void reproducir() {
        // Inicializaci�n de variables para el tiempo
        int horas = 0;
        int minutos = 0;
        int segundos = 0;
        // Calcula el momento en que se mostrar� el anuncio
        int tiempoPausa = this.duracion / 2;

        // Ciclo que simula la reproducci�n del cap�tulo
        for (int i = 0; i <= this.duracion; this.duracion--) {
            try {
                // Actualizaci�n de las variables de tiempo
                if (segundos > 59) {
                    segundos = 0;
                    minutos++;
                }
                if (minutos > 59) {
                    minutos = 0;
                    horas++;
                }
                // Muestra el tiempo transcurrido
                System.out.printf("%02d:%02d:%02d\n", horas, minutos, segundos);
                Thread.sleep(1000); // Espera de 1 segundo

                // Muestra el anuncio si se ha alcanzado el tiempo de pausa
                if (tiempoPausa == this.duracion) {
                    System.out.println("<Anuncio: \"" + anuncio.getMensajeAnuncio() + "\">");
                    // Simula la duraci�n del anuncio
                    for (int j = 0; j < anuncio.getDuracion(); j++) {
                        System.out.println("...");
                        Thread.sleep(1000); // Espera de 1 segundo
                    }
                }
                segundos++;
            } catch (InterruptedException e) {
                Utilidades.visualizarMensaje("Error al reproducir el multimedia: " + e.getMessage());
            }
        }
        // Muestra un mensaje indicando que la reproducci�n ha terminado
        Utilidades.visualizarMensaje("[Fin]\n");
    }

}
