package tarea05;

/*
    @author DAVID MEDINA GARCIA
 */
public class Libro {

    // Constantes para definir la cantidad de d�gitos en cada parte del c�digo.
    final static int DIGITOS_ESTANTERIA = 2;
    final static int DIGITOS_BALDA = 3;
    final static int DIGITOS_NRO_LIBRO = 4;
    final static int DIGITOS_DC = 2;
    static final byte LONGITUD_CLIBC = DIGITOS_ESTANTERIA + DIGITOS_BALDA + DIGITOS_NRO_LIBRO + DIGITOS_DC;

    // Atributos de la clase
    private String estanteria;
    private String balda;
    private String numeroLibro;
    private String titulo;
    private int unidadesTotales;

    // Constructor vacio
    public Libro() {
    }

    // Constructor con par�metros
    public Libro(String CLIBC, String titulo) {
        separarCLIBC(CLIBC);
        setTitulo(titulo);
        this.unidadesTotales = 0;
    }

    // M�todos getters y setters para los atributos
    public String getEstanteria() {
        return estanteria;
    }

    public void setEstanteria(String estanteria) {
        this.estanteria = estanteria;
    }

    public String getBalda() {
        return balda;
    }

    public void setBalda(String balda) {
        this.balda = balda;
    }

    public String getNumeroLibro() {
        return numeroLibro;
    }

    public void setNumeroLibro(String numeroLibro) {
        this.numeroLibro = numeroLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    private void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getUnidadesTotales() {
        return unidadesTotales;
    }

    public void setUnidadesTotales(int unidadesTotales) {
        this.unidadesTotales = unidadesTotales;
    }

    // M�todo para obtener los d�gitos de control del c�digo del libro.
    public String getDigitosControl() {
        int digitosControl = calcularDigitosControl(estanteria, balda, numeroLibro);
        String digitosControlStr = String.format("%0" + DIGITOS_DC + "d", digitosControl);
        return digitosControlStr;
    }

    // M�todo para obtener el c�digo completo del libro (CLIBC)
    public String getCLIBC() {
        int digitosControl = calcularDigitosControl(estanteria, balda, numeroLibro);
        String digitosControlStr = String.format("%0" + DIGITOS_DC + "d", digitosControl);
        return estanteria + balda + numeroLibro + digitosControlStr;
    }

    // M�todo privado para separar el CLIBC en partes (estanter�a, balda, n�mero de libro)
    private void separarCLIBC(String CLIBC) {
        this.estanteria = CLIBC.substring(0, DIGITOS_ESTANTERIA);
        this.balda = CLIBC.substring(DIGITOS_ESTANTERIA, DIGITOS_ESTANTERIA + DIGITOS_BALDA);
        this.numeroLibro = CLIBC.substring(DIGITOS_ESTANTERIA + DIGITOS_BALDA, DIGITOS_ESTANTERIA + DIGITOS_BALDA + DIGITOS_NRO_LIBRO);
    }

    // M�todo est�tico para calcular los d�gitos de control del c�digo del libro.
    public static int calcularDigitosControl(String estanteria, String balda, String numeroLibro) {
        int est = Integer.parseInt(estanteria);
        int bal = Integer.parseInt(balda);
        int num = Integer.parseInt(numeroLibro);
        int digitosControl = (est + bal + num) % 99;
        return digitosControl;
    }

    // M�todo est�tico para validar los d�gitos de control del CLIBC.
    public static boolean correctoDigitosControlCLIBC(String CLIBC) {
        String est = CLIBC.substring(0, DIGITOS_ESTANTERIA);
        String bal = CLIBC.substring(DIGITOS_ESTANTERIA, DIGITOS_ESTANTERIA + DIGITOS_BALDA);
        String cod = CLIBC.substring(DIGITOS_ESTANTERIA + DIGITOS_BALDA, DIGITOS_ESTANTERIA + DIGITOS_BALDA + DIGITOS_NRO_LIBRO);
        int digitosControl = Integer.parseInt(CLIBC.substring(DIGITOS_ESTANTERIA + DIGITOS_BALDA + DIGITOS_NRO_LIBRO));

        int CC = calcularDigitosControl(est, bal, cod);

        return CC == digitosControl;
    }

    // M�todo est�tico para validar el formato del CLIBC.
    public static boolean correctoFormatoCLIBC(String CLIBC) {
        return CLIBC.matches("\\d{" + LONGITUD_CLIBC + "}");
    }

//    public static boolean correctoFormatoCLIBC(String CLIBC) {
//        if (CLIBC.length() != LONGITUD_CLIBC) {
//            return false;
//        }
//        for (int i = 0; i < CLIBC.length(); i++) {
//            if (!Character.isDigit(CLIBC.charAt(i))) {
//                    return false;
//            }
//        }
//        return true;
//    }
    // M�todo est�tico para validar el formato del t�tulo.
    public static boolean correctoTitulo(String titulo) {
        return titulo.matches(".{1,40}");
//        return titulo.matches("[\\S\\s]{1,40}");
        // return titulo.length() > 0 && titulo.length() <= 40;
    }

    // M�todo para dar de alta libros en el inventario.
    public void altaLibros(int unidades) {
        try {
            if (unidades <= 0) {
                throw new Exception("Las unidades deben ser mayores que cero.");
            }
            this.unidadesTotales += unidades;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // M�todo para vender libros del inventario.
    public void ventaLibros(int unidades) {
        try {
            if (unidades <= 0) {
                throw new Exception("Las unidades deben ser mayores a cero.");
            } else if (unidades > this.unidadesTotales) {
                throw new Exception("Las unidades a vender no pueden ser mayores que las unidades totales disponibles.");
            }
            this.unidadesTotales -= unidades;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
