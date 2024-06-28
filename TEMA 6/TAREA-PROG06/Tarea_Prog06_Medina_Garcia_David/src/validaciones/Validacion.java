package validaciones;

import controlador.Utilidades;

/**
 *
 * @author David Medina Garcia
 */
// Clase que contiene m�todos est�ticos para realizar validaciones
public class Validacion {

    // M�todo para validar n�meros
    public static boolean validacionNumeros(int num) {
        // Convertir el n�mero a cadena para obtener su longitud
        String snumStr = Integer.toString(num);
        // Validar la longitud, que sea positivo y no est� vac�o
        if (snumStr.length() > 5 || num < 0 || snumStr.isBlank()) {
//        if (!snumStr.matches("[1-9]\\d{0,4}")) {
            Utilidades.visualizarMensaje("�ERROR! Debe introducir un n�mero entero positivo de 5 d�gitos como m�ximo.");
            return false;
        }
        return true;
    }

    // M�todo para validar precios
    public static boolean validacionPrecio(float precio) {
        // Validar que el precio sea mayor que 0 y no est� vac�o
        if (precio <= 0 || Float.toString(precio).isBlank()) {
            Utilidades.visualizarMensaje("ERROR! El precio no puede estar vac�o y debe ser mayor que 0.");
            return false;
        }
        return true;
    }

    // M�todo para validar cadenas
    public static boolean validacionCadena(String cadena) {
        // Validar la longitud y que no est� vac�a
        if (cadena.length() > 30 || cadena.isBlank()) {
            Utilidades.visualizarMensaje("�ERROR! La descripci�n no puede estar vac�a y no debe exceder los 30 caracteres.");
            return false;
        }
        return true;
    }

    // M�todo para validar el tipo de mueble
    public static boolean validacionTipoMueble(String tipoMueble) {
        // Validar que el tipo de mueble sea uno de los correctos
        if (!"h".equalsIgnoreCase(tipoMueble) && !"c".equalsIgnoreCase(tipoMueble) && !"d".equalsIgnoreCase(tipoMueble)) {
            Utilidades.visualizarMensaje("�ERROR! Debe introducir tipos correctos ('H', 'C', 'D').");
            return false;
        }
        return true;
    }

}
