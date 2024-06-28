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
            // Captura del título del libro desde la entrada estándar
            System.out.println("Introduzca el titulo del libro");
            String titulo = leerTeclado();

            // Validación del formato del título
            while (!Libro.correctoTitulo(titulo)) {
                System.out.println("Error: El titulo debe tener entre 1 y 40 caracteres.");
                System.out.println("Introduzca un nuevo titulo.");
                titulo = leerTeclado();
            }

            // Captura del código completo del libro desde la entrada estándar
            System.out.println("Introduzca el codigo completo del libro");
            String CLIBC = leerTeclado();

            // Validación del formato y dígitos de control del CLIBC
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

            // Creación de una instancia de Libro con los datos capturados
            libroActual = new Libro(CLIBC, titulo);

            // Llamada al método que gestiona el menú y las operaciones del programa
            programa();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Método para visualizar el menú de la aplicación
    private static void visualizarMenu() {
        System.out.println("\n----- Menú de Librería -----");
        System.out.println("1. Ver código completo del libro (CLIBC)");
        System.out.println("2. Ver título del libro");
        System.out.println("3. Ver estantería");
        System.out.println("4. Ver balda");
        System.out.println("5. Ver número del libro");
        System.out.println("6. Ver dígitos de control del libro");
        System.out.println("7. Realizar altas de libros");
        System.out.println("8. Realizar ventas de libros");
        System.out.println("9. Consultar unidades totales");
        System.out.println("0. Salir");
        System.out.println("Elija una opción: ");
    }

    // Método principal que gestiona el flujo del programa
    private static void programa() throws IOException {
        int opcion = -1;
        while (opcion != 0) {
            try {
                // Visualización del menú y captura de la opción seleccionada
                visualizarMenu();
                opcion = leerOpcion();

                // Switch para realizar las operaciones según la opción seleccionada
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
                        System.out.println("¿Cuantas unidades de este libro quieres dar de alta?");
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
                                System.out.println("¿Cuantas unidades de este libro se van a vender?");
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
                        salir(); // Llamada al método salir para gestionar la finalización del programa
                        break;
                    default:
                        System.out.println("¡ERROR! Debe introducir una opción válida.");
                        pulsarReturn();
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes introducir un número entero entre 0 y 9.");
                pulsarReturn();
            } catch (IOException e) {
                System.out.println("Error al leer la entrada del teclado");
                pulsarReturn();
            }
        }
    }

    // Método para leer una cadena de texto desde la entrada estándar
    private static String leerTeclado() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new IOException("Error al leer la entrada del teclado", e);
        }
    }

    // Método para leer una opción numérica desde la entrada estándar
    private static int leerOpcion() throws IOException {
        int opcion = -1;
        try {
            opcion = Integer.parseInt(leerTeclado());
        } catch (NumberFormatException e) {
            throw e; // Re-lanza la excepción después de manejarla
        } catch (IOException e) {
            throw new IOException("Error al leer la entrada del teclado", e);
        } catch (Exception e) {
            throw e; // Re-lanza la excepción después de manejarla
        }
        return opcion;
    }

    // Método para esperar la pulsación de la tecla Enter
//    private static void pulsarReturn() throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        reader.readLine();
//    }
    private static void pulsarReturn() {
        Scanner sn = new Scanner(System.in);
        System.out.println("Pulse Enter para continuar...");
        sn.nextLine();
    }

    // Método para gestionar la salida del programa
    private static void salir() {
        boolean salir = false;
        do {
            try {
                // Pregunta al usuario si está seguro de salir del programa
                System.out.println("¿Estás seguro de que deseas salir del programa? (S/N)");
                String opcion = leerTeclado().toLowerCase();

                // Evaluación de la respuesta del usuario
                if ("n".equals(opcion)) {
                    salir = true;
                    programa(); // Vuelve al menú principal si decide no salir
                } else if ("s".equals(opcion)) {
                    System.out.println("Fin del programa. Hasta luego.");
                    salir = true;
                } else {
                    System.out.println("¡ERROR!. Debe introducir uno de los siguientes valores (S/N)");
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (!salir);
    }
}
