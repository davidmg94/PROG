package app;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import modelos.*;
import controlador.Utilidades;

/**
 *
 * @author David Medina Garcia
 */
public class TVApp {

    final static int LIMITE_OPCIONES = 5; // Limite de opciones del menú

    static ArrayList<Multimedia> listaMultimedia = new ArrayList<>(); // Lista para almacenar objetos Multimedia
    static Scanner sn = new Scanner(System.in); // Scanner para la entrada de datos

    // Método principal que inicia la ejecución del programa
    public static void main(String[] args) {
        programa();
    }

    // Método principal que gestiona el flujo del programa
    private static void programa() {
        int opcion = -1;
        do {

            try {
                // Visualización del menú y captura de la opción seleccionada
                visualizarMenu();
                opcion = Utilidades.leerOpcion(LIMITE_OPCIONES);
                // Switch para realizar las operaciones según la opción seleccionada
                switch (opcion) {
                    case 1:
                        // Introducir películas
                        String masPeliculas = "";
                        do {
                            introducirPeliculas();
                            Utilidades.visualizarMensaje("¿Quieres introducir mas peliculas? (S/N)");
                            masPeliculas = sn.nextLine();
                            while (!masPeliculas.equalsIgnoreCase("s") && !masPeliculas.equalsIgnoreCase("n")) {
                                Utilidades.visualizarMensaje("ERROR! Debe pulsar S/N para continuar.");
                                masPeliculas = sn.nextLine();
                            }
                        } while (masPeliculas.equalsIgnoreCase("s"));
                        Utilidades.pulsarTecla();
                        break;
                    case 2:
                        // Introducir Capítulos de Serie Infantil
                        String masCapitulos = "";
                        do {
                            introducirCapitulos();
                            Utilidades.visualizarMensaje("¿Quieres introducir mas capitulos? (S/N)");
                            masCapitulos = sn.nextLine();
                            while (!masCapitulos.equalsIgnoreCase("s") && !masCapitulos.equalsIgnoreCase("n")) {
                                Utilidades.visualizarMensaje("ERROR! Debe pulsar S/N para continuar.");
                                masCapitulos = sn.nextLine();
                            }
                        } while (masCapitulos.equalsIgnoreCase("s"));
                        Utilidades.pulsarTecla();
                        break;
                    case 3:
                        // Emitir todos los multimedia
                        if (listaMultimedia.isEmpty()) {
                            Utilidades.visualizarMensaje("No hay ningun elemento creado");
                        } else {
                            emitirTodos(listaMultimedia);
                        }
                        Utilidades.pulsarTecla();
                        break;
                    case 4:
                        // Comparar elementos multimedia
                        if (listaMultimedia.size() < 2) {
                            Utilidades.visualizarMensaje("No hay suficientes elementos en la lista para compararlos");
                        } else {
                            Utilidades.visualizarMensaje("Elija los objetos a comparar (0-" + (listaMultimedia.size() - 1) + ")\n");
                            Utilidades.visualizarMensaje("Primer objeto a comparar");
                            Multimedia mult1 = elegirObjetoAComparar(listaMultimedia);
                            Utilidades.visualizarMensaje("");// Salto de linea para mejora visual

                            Utilidades.visualizarMensaje("Segundo objeto a comparar");
                            Multimedia mult2 = elegirObjetoAComparar(listaMultimedia);
                            Utilidades.visualizarMensaje("");// Salto de linea para mejora visual

                            comparar(mult1, mult2);
                        }
                        Utilidades.pulsarTecla();
                        break;
                    case 5:
                        // Salir del programa
                        salir();
                        break;
                }
            } catch (NumberFormatException e) {
                Utilidades.visualizarMensaje("Error: Debes introducir un número entero entre 0 y " + LIMITE_OPCIONES + ".");
            }
        } while (opcion != 5);
    }

    // Método para visualizar el menú de la aplicación
    private static void visualizarMenu() {
        Utilidades.visualizarMensaje("----- Menú de App Multimedia -----");
        Utilidades.visualizarMensaje("1. Introducir películas");
        Utilidades.visualizarMensaje("2. Introducir Capítulo de serie infantil");
        Utilidades.visualizarMensaje("3. Emitir todos");
        Utilidades.visualizarMensaje("4. Comparar elementos");
        Utilidades.visualizarMensaje("5. Salir");
    }

    // Método para introducir un objeto Multimedia genérico
    private static Multimedia introducirMultimedia() {
        Formato f = new Formato();
        try {
            // Pide los datos al usuario
            Utilidades.visualizarMensaje("Introduce el título:");
            String titulo = sn.nextLine();
            Utilidades.visualizarMensaje("Introduce el autor:");
            String autor = sn.nextLine();
            Utilidades.visualizarMensaje("Introduce la duración (segundos):");
            int duracion = sn.nextInt();
            sn.nextLine(); // Consumir el salto de línea

            // Obtiene los valores del array de formatos y los muestra
            String[] nombresFormatos = f.obtenerNombresFormatos();
            Utilidades.visualizarMensaje("Elige el formato: (0-" + (nombresFormatos.length - 1) + ")");
            for (int i = 0; i < nombresFormatos.length; i++) {
                Utilidades.visualizarMensaje(i + ". " + nombresFormatos[i]);
            }
            int opcionFormato = sn.nextInt();
            sn.nextLine(); // Consumir el salto de línea

            if (opcionFormato >= 0 && opcionFormato < nombresFormatos.length) {
                // Crear y devolver objeto Multimedia con los detalles ingresados
                f = new Formato(opcionFormato);
                return new Multimedia(titulo, autor, duracion, f);
            } else {
                Utilidades.visualizarMensaje("Opción de formato inválida. No se creará el objeto multimedia.");
                return null;
            }
        } catch (InputMismatchException e) {
            Utilidades.visualizarMensaje("Error: Debes introducir un número entero para la duración y el formato.");
            sn.nextLine(); // Limpiar el buffer del scanner
        } catch (NumberFormatException e) {
            Utilidades.visualizarMensaje("Error: El formato ingresado no es válido.");
            sn.nextLine(); // Limpiar el buffer del scanner
        }
        return null;
    }

    // Método para introducir una película
    private static void introducirPeliculas() {
        try {
            // Crea un objeto multimedia con el que devuelve el metodo introducirMultimedia()
            Multimedia mul = introducirMultimedia();
            // Pide los datos al usuario
            Utilidades.visualizarMensaje("Introducir actor principal:");
            String actor = sn.nextLine();

            Utilidades.visualizarMensaje("Introducir actriz principal:");
            String actriz = sn.nextLine();

            // Crea un objeto pelicula y lo añade a la lista
            Pelicula p = new Pelicula(mul, actor, actriz);
            listaMultimedia.add(p);
            Utilidades.visualizarMensaje("Pelicula añadida a la lista.");
        } catch (NullPointerException e) {
            Utilidades.visualizarMensaje("Error: No se pudo crear la película debido a que no se ingresaron los datos correctamente.");
        }
    }

    // Método para introducir un capítulo de serie infantil
    private static void introducirCapitulos() {
        try {
            // Crea un objeto multimedia con el que devuelve el metodo introducirMultimedia()
            Multimedia mul = introducirMultimedia();
            // Pide los datos al usuario
            Utilidades.visualizarMensaje("Introducir número de capítulo:");
            int nroCap = sn.nextInt();
            sn.nextLine();

            Utilidades.visualizarMensaje("Introducir canal de TV:");
            String canalTV = sn.nextLine();

            Utilidades.visualizarMensaje("Introducir duración del anuncio (segundos):");
            int duracionAnuncio = sn.nextInt();
            sn.nextLine();

            Utilidades.visualizarMensaje("Introducir nombre del producto que se anuncia:");
            String producto = sn.nextLine();

            Utilidades.visualizarMensaje("Introducir mensaje del anuncio:");
            String mensaje = sn.nextLine();

            // Crea un objeto anuncio con los datos introducidos
            Anuncio anuncio = new Anuncio(duracionAnuncio, producto, mensaje);

            // Crea un objeto CapituloSerieInfantil y lo añade a la lista
            CapituloSerieInfantil c = new CapituloSerieInfantil(mul, nroCap, canalTV, anuncio);
            listaMultimedia.add(c);
            Utilidades.visualizarMensaje("Capitulo añadido a la lista.");
        } catch (NullPointerException e) {
            Utilidades.visualizarMensaje("Error: No se pudo crear la película debido a que no se ingresaron los datos correctamente.");
        } catch (InputMismatchException e) {
            Utilidades.visualizarMensaje("Error: Debes introducir un número entero para el número de capítulo y la duración del anuncio.");
            sn.nextLine(); // Limpiar el buffer del scanner
        } catch (NumberFormatException e) {
            Utilidades.visualizarMensaje("Error: El formato ingresado no es válido para el número de capítulo o la duración del anuncio.");
            sn.nextLine(); // Limpiar el buffer del scanner
        }
    }

    // Método para emitir todos los elementos multimedia en la lista
    private static void emitirTodos(ArrayList<Multimedia> lista) {
        // Itera sobre cada elemento multimedia en la lista
        for (Multimedia multimedia : lista) {
            // Verifica si el elemento multimedia es una instancia de Pelicula
            if (multimedia instanceof Pelicula) {
                // Imprime un mensaje indicando que se está emitiendo una película y su información
                Utilidades.visualizarMensaje("Emitiendo película: " + multimedia.toString());
                multimedia.reproducir();
            }
            // Verifica si el elemento multimedia es una instancia de CapituloSerieInfantil
            if (multimedia instanceof CapituloSerieInfantil) {
                // Imprime un mensaje indicando que se está emitiendo un capítulo y su información
                Utilidades.visualizarMensaje("Emitiendo capítulo: " + multimedia.toString());
                multimedia.reproducir();
            }
            try {
                // Pausa la ejecución del programa por 1 segundo
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Utilidades.visualizarMensaje(ex.getMessage());
            }
        }
    }

    // Método para elegir un objeto multimedia de la lista
    private static Multimedia elegirObjetoAComparar(ArrayList<Multimedia> lista) {
        try {
            int indice = 0;
            // Itera sobre cada objeto multimedia en la lista y muestra su información junto con un índice
            for (Multimedia multimedia : lista) {
                Utilidades.visualizarMensaje(indice + ". " + multimedia.toString());
                indice++;
            }
            // Lee la opción ingresada por el usuario
            int eleccion = sn.nextInt();
            // Verifica si la opción ingresada es válida
            if (eleccion >= 0 && eleccion < lista.size()) {
                // Obtiene el objeto multimedia correspondiente a la opción elegida
                Multimedia mult = lista.get(eleccion);
                sn.nextLine(); // Limpia el buffer del scanner
                return mult;
            } else {
                Utilidades.visualizarMensaje("Error: Debes introducir un número entero válido entre 0 y " + (lista.size() - 1) + ".");
                sn.nextLine(); // Limpia el buffer del scanner
            }
        } catch (InputMismatchException e) {
            Utilidades.visualizarMensaje("Error: Debes introducir un número entero válido entre 0 y " + (lista.size() - 1) + ".");
            sn.nextLine(); // Limpia el buffer del scanner
        } catch (Exception e) {
            Utilidades.visualizarMensaje("Error: Ocurrió un error inesperado.");
            sn.nextLine(); // Limpia el buffer del scanner
        }
        return null;
    }

    // Método para comparar dos objetos multimedia
    private static void comparar(Multimedia mult1, Multimedia mult2) {
        try {
            // Verifica que los objetos multimedia no sean nulos
            if (mult1 != null && mult2 != null) {
                // Compara los objetos multimedia utilizando el método equals
                if (mult1.equals(mult2)) {
                    Utilidades.visualizarMensaje("Los objetos son iguales");
                } else {
                    Utilidades.visualizarMensaje("Los objetos no son iguales");
                }
            } else {
                Utilidades.visualizarMensaje("ERROR: Al menos uno de los objetos es nulo");
            }
        } catch (Exception e) {
            Utilidades.visualizarMensaje("ERROR: Ocurrió un error durante la comparación de objetos.");
        }
    }

    // Método para salir del programa
    private static void salir() {
        boolean salir = false;
        do {
            try {
                // Pregunta al usuario si está seguro de salir del programa
                Utilidades.visualizarMensaje("¿Estás seguro de que deseas salir del programa? (S/N)");
                String opcion = sn.next().toLowerCase();

                // Evaluacion de la respuesta del usuario
                if ("n".equals(opcion)) {
                    salir = true;
                    programa(); // Vuelve al menu principal si decide no salir
                } else if ("s".equals(opcion)) {
                    Utilidades.visualizarMensaje("Fin del programa. Hasta luego.");
                    salir = true;
                } else {
                    Utilidades.visualizarMensaje("ERROR! Debe pulsar S/N para continuar.");
                }
            } catch (Exception e) {
                Utilidades.visualizarMensaje("Error: " + e.getMessage());
            }
        } while (!salir);
    }
}
