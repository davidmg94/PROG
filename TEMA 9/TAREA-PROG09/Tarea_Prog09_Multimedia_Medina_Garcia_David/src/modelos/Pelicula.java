package modelos;

/**
 *
 * @author David Medina Garcia
 */
public class Pelicula extends Multimedia {

    private String actorPrincipal;
    private String actrizPrincipal;

    // Constructor sin argumentos
    public Pelicula() {
        super(); // Llama al constructor de la clase padre
        this.actorPrincipal = "";
        this.actrizPrincipal = "";
    }

    // Constructor con argumentos
    public Pelicula(Multimedia multimedia, String actorPrincipal, String actrizPrincipal) {
        // Llama al constructor de la clase padre con los atributos heredados
        super(multimedia.getTitulo(), multimedia.getAutor(), multimedia.getDuracion(), multimedia.getFormato());
        this.actorPrincipal = actorPrincipal;
        this.actrizPrincipal = actrizPrincipal;
    }

    // Métodos getters y setters
    public String getActrizPrincipal() {
        return actrizPrincipal;
    }

    public void setActrizPrincipal(String actrizPrincipal) {
        this.actrizPrincipal = actrizPrincipal;
    }

    public String getActorPrincipal() {
        return actorPrincipal;
    }

    public void setActorPrincipal(String actorPrincipal) {
        this.actorPrincipal = actorPrincipal;
    }

    // Método toString para representar la película como una cadena de texto
    @Override
    public String toString() {
        return super.toString() + ", Actor Principal: " + actorPrincipal + ", Actriz Principal: " + actrizPrincipal;
    }
}
