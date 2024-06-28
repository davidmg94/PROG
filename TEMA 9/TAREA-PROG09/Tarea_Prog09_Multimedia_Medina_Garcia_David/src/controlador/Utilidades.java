package controlador;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author David Medina Garcia
 */
public class Utilidades {

    // Creaci�n de un objeto Scanner para entrada de datos desde la consola
    static Scanner sn = new Scanner(System.in);

    // M�todo para mostrar un mensaje en la consola.
    public static void visualizarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // M�todo para leer una opci�n num�rica entre 1 y un l�mite especificado.
    public static int leerOpcion(int limite) {
        int opcion = -1;
        do {
            try {
                // Llamada al m�todo leerEntero para leer una opci�n num�rica
                opcion = leerEntero("Elija una opcion (1-" + limite + ").");
                if (opcion < 1 || opcion > limite) {
                    visualizarMensaje("Debe introducir un numero entero (1-" + limite + ")");
                }
            } catch (NumberFormatException | InputMismatchException e) {
                visualizarMensaje("Formato incorrecto.");
                sn.next();
            }
        } while (opcion < 1 || opcion > limite);
        return opcion;
    }

    // M�todo para esperar a que el usuario pulse enter.
    public static void pulsarTecla() {
        visualizarMensaje("Pulsa enter para continuar...");
        sn.nextLine(); // Consumir el salto de l�nea pendiente
        sn.nextLine(); // Esperar a que el usuario pulse enter
    }

    // M�todo para leer una cadena de caracteres.
    public static String leerString(String mensaje) {
        visualizarMensaje(mensaje);
        return sn.nextLine();
    }

    // M�todo para leer un n�mero entero.
    public static int leerEntero(String mensaje) {
        int numero = 0;
        boolean correcto = false;
        do {
            try {
                visualizarMensaje(mensaje);
                numero = sn.nextInt();
                correcto = true;
            } catch (InputMismatchException | NumberFormatException e) {
                visualizarMensaje("Formato incorrecto de entero.");
                correcto = false;
                sn.next();
            }
        } while (!correcto);
        return numero;
    }

    // M�todo para leer un n�mero de punto flotante.
    public static float leerFloat(String mensaje) {
        // Configuraci�n del Scanner para leer n�meros decimales con '.' como separador decimal
        sn.useLocale(Locale.US);
        float numero = 0;
        boolean correcto;
        do {
            correcto = true;
            try {
                visualizarMensaje(mensaje);
                numero = sn.nextFloat();
            } catch (InputMismatchException | NumberFormatException e) {
                visualizarMensaje("Formato incorrecto de  decimal.");
                correcto = false;
                sn.next();
            }
        } while (!correcto);
        return numero;
    }
}
