package controlador;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author David Medina Garcia
 */
public class Utilidades {

    // Creación de un objeto Scanner para entrada de datos desde la consola
    static Scanner sn = new Scanner(System.in);

    // Método para mostrar un mensaje en la consola.
    public static void visualizarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // Método para leer una opción numérica entre 1 y un límite especificado.
    public static int leerOpcion(int limite) {
        int opcion = -1;
        do {
            try {
                // Llamada al método leerEntero para leer una opción numérica
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

    // Método para esperar a que el usuario pulse enter.
    public static void pulsarTecla() {
        visualizarMensaje("Pulsa enter para continuar...");
        sn.nextLine(); // Consumir el salto de línea pendiente
        sn.nextLine(); // Esperar a que el usuario pulse enter
    }

    // Método para leer una cadena de caracteres.
    public static String leerString(String mensaje) {
        visualizarMensaje(mensaje);
        return sn.nextLine();
    }

    // Método para leer un número entero.
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

    // Método para leer un número de punto flotante.
    public static float leerFloat(String mensaje) {
        // Configuración del Scanner para leer números decimales con '.' como separador decimal
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
