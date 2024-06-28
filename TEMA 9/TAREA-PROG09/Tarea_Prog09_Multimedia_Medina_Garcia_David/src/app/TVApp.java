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

    final static int LIMITE_OPCIONES = 5; // Limite de opciones del men�

    static ArrayList<Multimedia> listaMultimedia = new ArrayList<>(); // Lista para almacenar objetos Multimedia
    static Scanner sn = new Scanner(System.in); // Scanner para la entrada de datos

    // M�todo principal que inicia la ejecuci�n del programa
    public static void main(String[] args) {
        programa();
    }

    // M�todo principal que gestiona el flujo del programa
    private static void programa() {
        int opcion = -1;
        do {

            try {
                // Visualizaci�n del men� y captura de la opci�n seleccionada
                visualizarMenu();
                opcion = Utilidades.leerOpcion(LIMITE_OPCIONES);
                // Switch para realizar las operaciones seg�n la opci�n seleccionada
                switch (opcion) {
                    case 1:
                        // Introducir pel�culas
                        String masPeliculas = "";
                        do {
                            introducirPeliculas();
                            Utilidades.visualizarMensaje("�Quieres introducir mas peliculas? (S/N)");
                            masPeliculas = sn.nextLine();
                            while (!masPeliculas.equalsIgnoreCase("s") && !masPeliculas.equalsIgnoreCase("n")) {
                                Utilidades.visualizarMensaje("ERROR! Debe pulsar S/N para continuar.");
                                masPeliculas = sn.nextLine();
                            }
                        } while (masPeliculas.equalsIgnoreCase("s"));
                        Utilidades.pulsarTecla();
                        break;
                    case 2:
                        // Introducir Cap�tulos de Serie Infantil
                        String masCapitulos = "";
                        do {
                            introducirCapitulos();
                            Utilidades.visualizarMensaje("�Quieres introducir mas capitulos? (S/N)");
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
                Utilidades.visualizarMensaje("Error: Debes introducir un n�mero entero entre 0 y " + LIMITE_OPCIONES + ".");
            }
        } while (opcion != 5);
    }

    // M�todo para visualizar el men� de la aplicaci�n
    private static void visualizarMenu() {
        Utilidades.visualizarMensaje("----- Men� de App Multimedia -----");
        Utilidades.visualizarMensaje("1. Introducir pel�culas");
        Utilidades.visualizarMensaje("2. Introducir Cap�tulo de serie infantil");
        Utilidades.visualizarMensaje("3. Emitir todos");
        Utilidades.visualizarMensaje("4. Comparar elementos");
        Utilidades.visualizarMensaje("5. Salir");
    }

    // M�todo para introducir un objeto Multimedia gen�rico
    private static Multimedia introducirMultimedia() {
        Formato f = new Formato();
        try {
            // Pide los datos al usuario
            Utilidades.visualizarMensaje("Introduce el t�tulo:");
            String titulo = sn.nextLine();
            Utilidades.visualizarMensaje("Introduce el autor:");
            String autor = sn.nextLine();
            Utilidades.visualizarMensaje("Introduce la duraci�n (segundos):");
            int duracion = sn.nextInt();
            sn.nextLine(); // Consumir el salto de l�nea

            // Obtiene los valores del array de formatos y los muestra
            String[] nombresFormatos = f.obtenerNombresFormatos();
            Utilidades.visualizarMensaje("Elige el formato: (0-" + (nombresFormatos.length - 1) + ")");
            for (int i = 0; i < nombresFormatos.length; i++) {
                Utilidades.visualizarMensaje(i + ". " + nombresFormatos[i]);
            }
            int opcionFormato = sn.nextInt();
            sn.nextLine(); // Consumir el salto de l�nea

            if (opcionFormato >= 0 && opcionFormato < nombresFormatos.length) {
                // Crear y devolver objeto Multimedia con los detalles ingresados
                f = new Formato(opcionFormato);
                return new Multimedia(titulo, autor, duracion, f);
            } else {
                Utilidades.visualizarMensaje("Opci�n de formato inv�lida. No se crear� el objeto multimedia.");
                return null;
            }
        } catch (InputMismatchException e) {
            Utilidades.visualizarMensaje("Error: Debes introducir un n�mero entero para la duraci�n y el formato.");
            sn.nextLine(); // Limpiar el buffer del scanner
        } catch (NumberFormatException e) {
            Utilidades.visualizarMensaje("Error: El formato ingresado no es v�lido.");
            sn.nextLine(); // Limpiar el buffer del scanner
        }
        return null;
    }

    // M�todo para introducir una pel�cula
    private static void introducirPeliculas() {
        try {
            // Crea un objeto multimedia con el que devuelve el metodo introducirMultimedia()
            Multimedia mul = introducirMultimedia();
            // Pide los datos al usuario
            Utilidades.visualizarMensaje("Introducir actor principal:");
            String actor = sn.nextLine();

            Utilidades.visualizarMensaje("Introducir actriz principal:");
            String actriz = sn.nextLine();

            // Crea un objeto pelicula y lo a�ade a la lista
            Pelicula p = new Pelicula(mul, actor, actriz);
            listaMultimedia.add(p);
            Utilidades.visualizarMensaje("Pelicula a�adida a la lista.");
        } catch (NullPointerException e) {
            Utilidades.visualizarMensaje("Error: No se pudo crear la pel�cula debido a que no se ingresaron los datos correctamente.");
        }
    }

    // M�todo para introducir un cap�tulo de serie infantil
    private static void introducirCapitulos() {
        try {
            // Crea un objeto multimedia con el que devuelve el metodo introducirMultimedia()
            Multimedia mul = introducirMultimedia();
            // Pide los datos al usuario
            Utilidades.visualizarMensaje("Introducir n�mero de cap�tulo:");
            int nroCap = sn.nextInt();
            sn.nextLine();

            Utilidades.visualizarMensaje("Introducir canal de TV:");
            String canalTV = sn.nextLine();

            Utilidades.visualizarMensaje("Introducir duraci�n del anuncio (segundos):");
            int duracionAnuncio = sn.nextInt();
            sn.nextLine();

            Utilidades.visualizarMensaje("Introducir nombre del producto que se anuncia:");
            String producto = sn.nextLine();

            Utilidades.visualizarMensaje("Introducir mensaje del anuncio:");
            String mensaje = sn.nextLine();

            // Crea un objeto anuncio con los datos introducidos
            Anuncio anuncio = new Anuncio(duracionAnuncio, producto, mensaje);

            // Crea un objeto CapituloSerieInfantil y lo a�ade a la lista
            CapituloSerieInfantil c = new CapituloSerieInfantil(mul, nroCap, canalTV, anuncio);
            listaMultimedia.add(c);
            Utilidades.visualizarMensaje("Capitulo a�adido a la lista.");
        } catch (NullPointerException e) {
            Utilidades.visualizarMensaje("Error: No se pudo crear la pel�cula debido a que no se ingresaron los datos correctamente.");
        } catch (InputMismatchException e) {
            Utilidades.visualizarMensaje("Error: Debes introducir un n�mero entero para el n�mero de cap�tulo y la duraci�n del anuncio.");
            sn.nextLine(); // Limpiar el buffer del scanner
        } catch (NumberFormatException e) {
            Utilidades.visualizarMensaje("Error: El formato ingresado no es v�lido para el n�mero de cap�tulo o la duraci�n del anuncio.");
            sn.nextLine(); // Limpiar el buffer del scanner
        }
    }

    // M�todo para emitir todos los elementos multimedia en la lista
    private static void emitirTodos(ArrayList<Multimedia> lista) {
        // Itera sobre cada elemento multimedia en la lista
        for (Multimedia multimedia : lista) {
            // Verifica si el elemento multimedia es una instancia de Pelicula
            if (multimedia instanceof Pelicula) {
                // Imprime un mensaje indicando que se est� emitiendo una pel�cula y su informaci�n
                Utilidades.visualizarMensaje("Emitiendo pel�cula: " + multimedia.toString());
                multimedia.reproducir();
            }
            // Verifica si el elemento multimedia es una instancia de CapituloSerieInfantil
            if (multimedia instanceof CapituloSerieInfantil) {
                // Imprime un mensaje indicando que se est� emitiendo un cap�tulo y su informaci�n
                Utilidades.visualizarMensaje("Emitiendo cap�tulo: " + multimedia.toString());
                multimedia.reproducir();
            }
            try {
                // Pausa la ejecuci�n del programa por 1 segundo
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Utilidades.visualizarMensaje(ex.getMessage());
            }
        }
    }

    // M�todo para elegir un objeto multimedia de la lista
    private static Multimedia elegirObjetoAComparar(ArrayList<Multimedia> lista) {
        try {
            int indice = 0;
            // Itera sobre cada objeto multimedia en la lista y muestra su informaci�n junto con un �ndice
            for (Multimedia multimedia : lista) {
                Utilidades.visualizarMensaje(indice + ". " + multimedia.toString());
                indice++;
            }
            // Lee la opci�n ingresada por el usuario
            int eleccion = sn.nextInt();
            // Verifica si la opci�n ingresada es v�lida
            if (eleccion >= 0 && eleccion < lista.size()) {
                // Obtiene el objeto multimedia correspondiente a la opci�n elegida
                Multimedia mult = lista.get(eleccion);
                sn.nextLine(); // Limpia el buffer del scanner
                return mult;
            } else {
                Utilidades.visualizarMensaje("Error: Debes introducir un n�mero entero v�lido entre 0 y " + (lista.size() - 1) + ".");
                sn.nextLine(); // Limpia el buffer del scanner
            }
        } catch (InputMismatchException e) {
            Utilidades.visualizarMensaje("Error: Debes introducir un n�mero entero v�lido entre 0 y " + (lista.size() - 1) + ".");
            sn.nextLine(); // Limpia el buffer del scanner
        } catch (Exception e) {
            Utilidades.visualizarMensaje("Error: Ocurri� un error inesperado.");
            sn.nextLine(); // Limpia el buffer del scanner
        }
        return null;
    }

    // M�todo para comparar dos objetos multimedia
    private static void comparar(Multimedia mult1, Multimedia mult2) {
        try {
            // Verifica que los objetos multimedia no sean nulos
            if (mult1 != null && mult2 != null) {
                // Compara los objetos multimedia utilizando el m�todo equals
                if (mult1.equals(mult2)) {
                    Utilidades.visualizarMensaje("Los objetos son iguales");
                } else {
                    Utilidades.visualizarMensaje("Los objetos no son iguales");
                }
            } else {
                Utilidades.visualizarMensaje("ERROR: Al menos uno de los objetos es nulo");
            }
        } catch (Exception e) {
            Utilidades.visualizarMensaje("ERROR: Ocurri� un error durante la comparaci�n de objetos.");
        }
    }

    // M�todo para salir del programa
    private static void salir() {
        boolean salir = false;
        do {
            try {
                // Pregunta al usuario si est� seguro de salir del programa
                Utilidades.visualizarMensaje("�Est�s seguro de que deseas salir del programa? (S/N)");
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
