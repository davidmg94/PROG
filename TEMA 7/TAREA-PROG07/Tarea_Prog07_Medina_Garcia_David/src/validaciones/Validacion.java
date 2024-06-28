package validaciones;

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
        // Validar la longitud, que sea positivo
        if (!snumStr.matches("\\d{1,5}")) {
            return false;
        }
        return true;
    }

    // M�todo para validar precios
    public static boolean validacionPrecio(double precio) {
        // Validar que el precio sea mayor que 0, con 7 cifras, dos de ellas decimales
        String sPrecio = Double.toString(precio);
        if (!sPrecio.matches("\\d{1,5}([.,]\\d{1,2})?")) {
            return false;
        }
        return true;
    }

    // M�todo para validar cadenas
    public static boolean validacionCadena(String cadena) {
        // Validar la longitud y que no est� vac�a
        if (cadena.length() > 15 || cadena.isEmpty()) {
            return false;
        }
        return true;
    }

    //Metodo para comprobar que las unidades en almacen son superiores o iguales a las unidades minimas
    public static boolean validacionUnidades(int almacen, int minimas) {
        return minimas < almacen;
    }
}
