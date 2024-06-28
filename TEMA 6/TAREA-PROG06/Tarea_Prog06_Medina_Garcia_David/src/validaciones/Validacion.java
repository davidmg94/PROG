package validaciones;

import controlador.Utilidades;

/**
 *
 * @author David Medina Garcia
 */
// Clase que contiene métodos estáticos para realizar validaciones
public class Validacion {

    // Método para validar números
    public static boolean validacionNumeros(int num) {
        // Convertir el número a cadena para obtener su longitud
        String snumStr = Integer.toString(num);
        // Validar la longitud, que sea positivo y no esté vacío
        if (snumStr.length() > 5 || num < 0 || snumStr.isBlank()) {
//        if (!snumStr.matches("[1-9]\\d{0,4}")) {
            Utilidades.visualizarMensaje("¡ERROR! Debe introducir un número entero positivo de 5 dígitos como máximo.");
            return false;
        }
        return true;
    }

    // Método para validar precios
    public static boolean validacionPrecio(float precio) {
        // Validar que el precio sea mayor que 0 y no esté vacío
        if (precio <= 0 || Float.toString(precio).isBlank()) {
            Utilidades.visualizarMensaje("ERROR! El precio no puede estar vacío y debe ser mayor que 0.");
            return false;
        }
        return true;
    }

    // Método para validar cadenas
    public static boolean validacionCadena(String cadena) {
        // Validar la longitud y que no esté vacía
        if (cadena.length() > 30 || cadena.isBlank()) {
            Utilidades.visualizarMensaje("¡ERROR! La descripción no puede estar vacía y no debe exceder los 30 caracteres.");
            return false;
        }
        return true;
    }

    // Método para validar el tipo de mueble
    public static boolean validacionTipoMueble(String tipoMueble) {
        // Validar que el tipo de mueble sea uno de los correctos
        if (!"h".equalsIgnoreCase(tipoMueble) && !"c".equalsIgnoreCase(tipoMueble) && !"d".equalsIgnoreCase(tipoMueble)) {
            Utilidades.visualizarMensaje("¡ERROR! Debe introducir tipos correctos ('H', 'C', 'D').");
            return false;
        }
        return true;
    }

}
