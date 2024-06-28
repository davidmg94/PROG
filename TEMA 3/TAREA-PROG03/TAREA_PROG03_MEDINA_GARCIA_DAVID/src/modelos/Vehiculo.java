package modelos;

public class Vehiculo {

    private String matricula;
    private String dni;
    private String nombre;
    private String apellidos;
    private String color;
    private char tipoSeguro;
    private float franquicia;
    private double precioObligatorio;
    private int numeroAccidentes;

    static final String COLOR_DEFECTO = "Blanco";
    static final char TIPOSEGURO_DEFECTO = 'O';
    static final float FRANQUICIA_DEFECTO = 0;
    static final double PRECIOOBLIGATORIO_DEFECTO = 100;

    public Vehiculo() {
        this.matricula = null;
        this.dni = null;
        this.nombre = null;
        this.apellidos = null;
        this.color = null;
        this.tipoSeguro = '\0';
        this.franquicia = 0;
        this.precioObligatorio = 0;
        this.numeroAccidentes = 0;
    }

    public Vehiculo(String matricula, String dni, String nombre, String apellidos) {
        this.matricula = matricula;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.color = COLOR_DEFECTO;
        this.tipoSeguro = TIPOSEGURO_DEFECTO;
        this.franquicia = FRANQUICIA_DEFECTO;
        this.precioObligatorio = PRECIOOBLIGATORIO_DEFECTO;
        this.numeroAccidentes = 0;

    }

    public Vehiculo(String matricula, String dni, String nombre, String apellidos, String color, char tipoSeguro, float franquicia, double precioObligatorio) {
        this.matricula = matricula;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.color = color;
        this.tipoSeguro = tipoSeguro;
        this.franquicia = franquicia;
        this.precioObligatorio = precioObligatorio;
        this.numeroAccidentes = 0;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public char getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(char tipoSeguro) {
        //Convertimos el parametro introducido a mayusculas 
        //asi no tendremos problemas si se pasan tanto minusculas como mayusculas
        char tipoSeguroUpper = Character.toUpperCase(tipoSeguro);

        if (tipoSeguroUpper == 'O' || tipoSeguroUpper == 'T' || tipoSeguroUpper == 'F') {
            this.tipoSeguro = tipoSeguroUpper;
        } else {
            this.tipoSeguro = TIPOSEGURO_DEFECTO;
        }
    }

    public float getFranquicia() {
        return franquicia;
    }

    public void setFranquicia(float franquicia) {
        this.franquicia = franquicia;
    }

    public double getPrecioObligatorio() {
        return precioObligatorio;
    }

    public void setPrecioObligatorio(double precioObligatorio) {
        this.precioObligatorio = precioObligatorio;
    }

    public int getNumeroAccidentes() {
        return numeroAccidentes;
    }

    public void setNumeroAccidentes(int numeroAccidentes) {
        this.numeroAccidentes = numeroAccidentes;
    }

    public String getNombreCompleto() {
        return this.apellidos + ", " + this.nombre;
    }

    public double getTotalSeguro() {
        setTipoSeguro(this.tipoSeguro);
        switch (this.tipoSeguro) {
            case 'O':
                return 200 + this.precioObligatorio;
            case 'F':
                return 300 + this.precioObligatorio;
            case 'T':
                return 600 + this.precioObligatorio;
            default:
                throw new AssertionError();
        }
    }

    public int sumarAccidentes(int numeroAccidentes) {
        return this.numeroAccidentes += numeroAccidentes;
    }

    @Override
    public String toString() {
        return "Matricula=" + matricula + " DNI=" + dni + " Nombre completo=" + this.getNombreCompleto() + " Color=" + color + " Tipo de seguro=" + tipoSeguro + " Franquicia=" + franquicia + " Precio obligatorio=" + precioObligatorio + " Número de accidentes=" + numeroAccidentes;
    }
}
