package patrones;

/**
 *
 * @author David Medina Garcia
 */
// Clase que contiene métodos estáticos para realizar patrones
public class Patron {

    // Patron codigo
    public static boolean patronCodigo(String codigo) {
        return codigo.matches("[A-Za-z]{1,2}-\\d{4}");
    }

    // Patron alfanumerico
    public static boolean patronAlfanumerico(String cadena) {
        return cadena.matches("[A-Za-z\\d\\s]+");
    }

    // Patron alfabetico
    public static boolean patronAlfabetico(String cadena) {
        return cadena.matches("[A-Za-z\\s]+");
    }

    // Patron números enteros
    public static boolean patronNumeroEntero(int num) {
        // Convertir el número a cadena para obtener su longitud
        String snumStr = Integer.toString(num);
        // uno o mas digitos
        return snumStr.matches("\\d+");
    }

    // Patron números reales
    public static boolean patronNumeroReal(float num) {
        // Convertir el número a cadena para obtener su longitud
        String snumStr = Float.toString(num);
        // uno o mas digitos seguido de punto y uno o tres decimales
        return snumStr.matches("\\d+(\\.\\d{1,3})?");
    }
}
