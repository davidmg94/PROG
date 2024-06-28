package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author David Medina Garcia
 */
public class Utilidades {

    // Método para imprimir un mensaje en la consola
    public static void visualizarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // Método para esperar a que el usuario presione Enter para continuar
    public static void pulsarReturn() {
        Scanner sn = new Scanner(System.in);
        visualizarMensaje("Pulse Return para continuar...");
        sn.nextLine();
    }

    // Método para leer una cadena de texto desde la consola con un mensaje
    public static String leerTeclado(String mensaje) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        visualizarMensaje(mensaje);
        return reader.readLine();
    }

    // Método para leer una opción (número entero) desde la consola con un límite
    public static int leerOpcion(int limite) throws IOException {
        int opcion = -1;
        // bucle do-while que controla que la opcion introducida este en un rango especifico
        do {
            try {
                //convierte el String que devuele leerTeclado() en numero
                opcion = Integer.parseInt(leerTeclado("Teclee una opcion (1-" + limite + "):"));
                if (opcion < 1 || opcion > limite) {
                    visualizarMensaje("Error: Debes introducir un número entero entre 1 y " + limite + ".");
                }
            } catch (InputMismatchException e) {
                visualizarMensaje("Error: Debes introducir un número entero entre 1 y " + limite + ".");
            } catch (IOException e) {
                visualizarMensaje("Error al leer la entrada del teclado");
            }
        } while (opcion < 1 || opcion > limite);

        return opcion;
    }
}
