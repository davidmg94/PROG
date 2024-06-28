package patrones;

/**
 *
 * @author David Medina Garcia
 */
// Clase que contiene métodos estáticos para realizar patrones
public class Patron {

    // Patron alfanumerico (Descripcion mueble)
    public static boolean patronAlfanumerico(String cadena) {
        return cadena.matches("[A-Za-z]+([\\s-][A-Za-z\\d]+)*");
    }

    // Patron alfabetico (nombre cliente)
    public static boolean patronAlfabetico(String cadena) {
        return cadena.matches("[A-Za-z]+([\\s-][A-Za-z]+)?");
    }

    // Patron números enteros (para idMueble, unidades vendidas y unidades existentes)
    public static boolean patronNumeroEnteroLongitud4(String num) {
        // 1 o mas digitos mayores que 0
        // con este patron tambien evitamos los numeros negativos al no aceptar guiones
        return num.matches("\\d{1,4}");
    }

    // Patron números enteros (para idVenta)
    public static boolean patronNumeroEnteroLongitud6(String num) {
        // 1 o mas digitos mayores que 0
        // con este patron tambien evitamos los numeros negativos al no aceptar guiones
        return num.matches("\\d{1,6}");
    }

    // Patron números reales (precio unitario)
    public static boolean patronNumeroReal(String num) {
        // de 1 a 6 digitos seguido de punto y 1 o 2 decimales
        // con este patron tambien evitamos los numeros negativos al no aceptar guiones

        return num.matches("\\d{1,6}(\\.\\d{1,2})?");// con este patron tambien evitamos los numeros negativos al no aceptar guiones
    }
}
