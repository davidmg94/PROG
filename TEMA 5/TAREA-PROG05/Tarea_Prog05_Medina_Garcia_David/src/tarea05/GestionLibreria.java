package tarea05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/*
 *
    @author DAVID MEDINA GARCIA
 */
public class GestionLibreria {

    // Instancia de la clase Libro para gestionar las operaciones
    static Libro libroActual = new Libro();

    public static void main(String[] args) throws IOException {
        try {
            // Captura del t�tulo del libro desde la entrada est�ndar
            System.out.println("Introduzca el titulo del libro");
            String titulo = leerTeclado();

            // Validaci�n del formato del t�tulo
            while (!Libro.correctoTitulo(titulo)) {
                System.out.println("Error: El titulo debe tener entre 1 y 40 caracteres.");
                System.out.println("Introduzca un nuevo titulo.");
                titulo = leerTeclado();
            }

            // Captura del c�digo completo del libro desde la entrada est�ndar
            System.out.println("Introduzca el codigo completo del libro");
            String CLIBC = leerTeclado();

            // Validaci�n del formato y d�gitos de control del CLIBC
            boolean salirWhile = false;
            while (!salirWhile) {
                if (!Libro.correctoFormatoCLIBC(CLIBC)) {
                    System.out.println("El CLIBC debe tener 11 digitos");
                    System.out.println("Introduzca un nuevo CLIBC.");
                    CLIBC = leerTeclado();
                } else if (!Libro.correctoDigitosControlCLIBC(CLIBC)) {
                    System.out.println("Los digitos de control introducidos (" + Libro.DIGITOS_DC + " ultimos digitos) son erroneos");
                    System.out.println("Introduzca un nuevo CLIBC.");
                    CLIBC = leerTeclado();
                } else {
                    salirWhile = true;
                }
            }

            // Creaci�n de una instancia de Libro con los datos capturados
            libroActual = new Libro(CLIBC, titulo);

            // Llamada al m�todo que gestiona el men� y las operaciones del programa
            programa();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // M�todo para visualizar el men� de la aplicaci�n
    private static void visualizarMenu() {
        System.out.println("\n----- Men� de Librer�a -----");
        System.out.println("1. Ver c�digo completo del libro (CLIBC)");
        System.out.println("2. Ver t�tulo del libro");
        System.out.println("3. Ver estanter�a");
        System.out.println("4. Ver balda");
        System.out.println("5. Ver n�mero del libro");
        System.out.println("6. Ver d�gitos de control del libro");
        System.out.println("7. Realizar altas de libros");
        System.out.println("8. Realizar ventas de libros");
        System.out.println("9. Consultar unidades totales");
        System.out.println("0. Salir");
        System.out.println("Elija una opci�n: ");
    }

    // M�todo principal que gestiona el flujo del programa
    private static void programa() throws IOException {
        int opcion = -1;
        while (opcion != 0) {
            try {
                // Visualizaci�n del men� y captura de la opci�n seleccionada
                visualizarMenu();
                opcion = leerOpcion();

                // Switch para realizar las operaciones seg�n la opci�n seleccionada
                switch (opcion) {
                    case 1:
                        System.out.println("Codigo completo del libro: " + libroActual.getCLIBC());
                        pulsarReturn();
                        break;
                    case 2:
                        System.out.println("Titulo del libro: " + libroActual.getTitulo());
                        pulsarReturn();
                        break;
                    case 3:
                        System.out.println("Estanteria en la que se encuentra el libro: " + libroActual.getEstanteria());
                        pulsarReturn();
                        break;
                    case 4:
                        System.out.println("Balda en la que se encuentra el libro: " + libroActual.getBalda());
                        pulsarReturn();
                        break;
                    case 5:
                        System.out.println("Numero del libro: " + libroActual.getNumeroLibro());
                        pulsarReturn();
                        break;
                    case 6:
                        System.out.println("Digitos de control del libro: " + libroActual.getDigitosControl());
                        pulsarReturn();
                        break;
                    case 7:
                        try {
                        System.out.println("�Cuantas unidades de este libro quieres dar de alta?");
                        int unidadesAlta = leerOpcion();
                        libroActual.altaLibros(unidadesAlta);
                        System.out.println("Se han dado de alta " + unidadesAlta + " libros.");
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR! Debe introducir un numero entero.");
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    pulsarReturn();
                    break;
                    case 8:
                        if (libroActual.getUnidadesTotales() == 0) {
                            System.out.println("""
                                               No hay unidades para vender.
                                               Primero debe dar de alta minimo un libro para poder vender unidades.""");
                        } else {
                            try {
                                System.out.println("�Cuantas unidades de este libro se van a vender?");
                                int unidadesVenta = leerOpcion();
                                libroActual.ventaLibros(unidadesVenta);
                                System.out.println("Se han vendido " + unidadesVenta + " libros.");

                            } catch (NumberFormatException e) {
                                System.out.println("Debe introducir un numero entero.");
                            } catch (IOException e) {
                                System.out.println("Error: " + e.getMessage());
                            }

                        }
                        pulsarReturn();
                        break;
                    case 9:
                        System.out.println("Unidades totales del libro: " + libroActual.getUnidadesTotales());
                        pulsarReturn();
                        break;
                    case 0:
                        salir(); // Llamada al m�todo salir para gestionar la finalizaci�n del programa
                        break;
                    default:
                        System.out.println("�ERROR! Debe introducir una opci�n v�lida.");
                        pulsarReturn();
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes introducir un n�mero entero entre 0 y 9.");
                pulsarReturn();
            } catch (IOException e) {
                System.out.println("Error al leer la entrada del teclado");
                pulsarReturn();
            }
        }
    }

    // M�todo para leer una cadena de texto desde la entrada est�ndar
    private static String leerTeclado() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new IOException("Error al leer la entrada del teclado", e);
        }
    }

    // M�todo para leer una opci�n num�rica desde la entrada est�ndar
    private static int leerOpcion() throws IOException {
        int opcion = -1;
        try {
            opcion = Integer.parseInt(leerTeclado());
        } catch (NumberFormatException e) {
            throw e; // Re-lanza la excepci�n despu�s de manejarla
        } catch (IOException e) {
            throw new IOException("Error al leer la entrada del teclado", e);
        } catch (Exception e) {
            throw e; // Re-lanza la excepci�n despu�s de manejarla
        }
        return opcion;
    }

    // M�todo para esperar la pulsaci�n de la tecla Enter
//    private static void pulsarReturn() throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        reader.readLine();
//    }
    private static void pulsarReturn() {
        Scanner sn = new Scanner(System.in);
        System.out.println("Pulse Enter para continuar...");
        sn.nextLine();
    }

    // M�todo para gestionar la salida del programa
    private static void salir() {
        boolean salir = false;
        do {
            try {
                // Pregunta al usuario si est� seguro de salir del programa
                System.out.println("�Est�s seguro de que deseas salir del programa? (S/N)");
                String opcion = leerTeclado().toLowerCase();

                // Evaluaci�n de la respuesta del usuario
                if ("n".equals(opcion)) {
                    salir = true;
                    programa(); // Vuelve al men� principal si decide no salir
                } else if ("s".equals(opcion)) {
                    System.out.println("Fin del programa. Hasta luego.");
                    salir = true;
                } else {
                    System.out.println("�ERROR!. Debe introducir uno de los siguientes valores (S/N)");
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (!salir);
    }
}
