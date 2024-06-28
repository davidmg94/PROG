package ejercicio1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author David Medina Garcia
 */
public class Prog04_Ejercicio1_Numeros_David {

    static Scanner sn = new Scanner(System.in);

    // Declaraci�n de constantes para valores m�nimos, m�ximos y un divisor
    static final int MIN_NUM = 2;
    static final int MAX_NUM = 300;
    static final int DIVISOR = 7;

    public static void main(String[] args) {
        programa();
    }

    // M�todo que muestra el men� y gestiona las opciones
    private static void menu() {
        // Muestra las opciones del men�
        System.out.println("/-------------------------------------------------");
        System.out.println("1. Visualizar suma de los n�meros que hay entre numero1 y numero2 ");
        System.out.println("2. Visualizar divisores de " + DIVISOR + " que hay entre numero1 y numero2");
        System.out.println("3. Fin del programa.");
        System.out.println("Elija una opcion");
    }

    // M�todo para iniciar el programa
    private static void programa() {
        // Solicita dos n�meros al usuario
        System.out.println("Introduce el primer numero (entre 2 y 300)");
        int num1 = introducirNumero();
        System.out.println("Introduce el segundo numero (entre 2 y 300)");
        int num2 = introducirNumero();

        // Intercambio de valores si el segundo n�mero es menor que el primero
        if (num2 < num1) {
            int temp = num2;
            num2 = num1;
            num1 = temp;
        }

        // Bucle principal del men�
        boolean salirBucle = false;
        do {

            try {
                // llamada al metodo menu() para visualizarlo
                menu();
                // Lee la opci�n del usuario
                int opcion = sn.nextInt();

                // Realiza acciones seg�n la opci�n seleccionada
                switch (opcion) {
                    case 1:
                        // Llamada al m�todo para sumar n�meros en un rango
                        sumarNumerosFor(num1, num2);
                        // Pregunta al usuario si desea realizar otra operaci�n
                        salirBucle = elegirOtraOperacion();
                        // Sale del programa si el usuario decide no realizar otra operaci�n
                        if (salirBucle) {
                            salir();
                        }
                        break;
                    case 2:
                        // Llamada al m�todo para encontrar divisores de 7 en un rango
                        divisoresDe7(num1, num2);
                        // Pregunta al usuario si desea realizar otra operaci�n
                        salirBucle = elegirOtraOperacion();
                        // Sale del programa si el usuario decide no realizar otra operaci�n
                        if (salirBucle) {
                            salir();
                        }
                        break;
                    case 3:
                        // Llamada al m�todo para salir del programa
                        salirBucle = true;
                        salir();
                        break;
                    default:
                        // Mensaje en caso de que el usuario ingrese una opci�n no v�lida
                        System.out.println("Introduzca una opcion valida");
                }
            } catch (InputMismatchException e) {
                // Captura la excepci�n si el usuario no ingresa un n�mero
                System.out.println("Debe introducir un n�mero v�lido");
                // Limpia el buffer de entrada
                sn.next();
            } catch (Exception e) {
                // Captura la excepci�n generica
                System.out.println("ERROR! " + e.getMessage());
                // Limpia el buffer de entrada
                sn.next();
            }
        } while (!salirBucle);

    }

    // M�todo para preguntar al usuario si desea realizar otra operaci�n
    private static boolean elegirOtraOperacion() {

        boolean salir = false;

        // Bucle para preguntar al usuario si est� seguro de salir del programa
        do {
            System.out.println("�Desea realizar otra operaci�n? (S/N)");
            // Lee la respuesta del usuario y la convierte a min�sculas
            String opcion = sn.next().toLowerCase();

            // Realiza acciones seg�n la respuesta del usuario
            if ("n".equals(opcion)) {
                return true;
            } else if ("s".equals(opcion)) {
                return false; // Sale del bucle
            } else {
                // Mensaje en caso de que el usuario ingrese una respuesta no v�lida
                System.out.println("�ERROR!. Debe introducir uno de los siguientes valores (S, N)");
            }
        } while (!salir);
        return false;
    }

    // M�todo para validar la entrada de un n�mero dentro de un rango
    private static int introducirNumero() {
        boolean salir = false;

        // Bucle para solicitar un n�mero v�lido al usuario
        do {
            try {
                // Lee un n�mero del usuario
                int num = sn.nextInt();

                // Valida que el n�mero est� en el rango permitido
                while (num < MIN_NUM || num > MAX_NUM) {
                    System.out.println("El n�mero debe estar en el intervalo " + MIN_NUM + " y " + MAX_NUM);
                    // Lee un nuevo n�mero si el anterior no est� en el rango
                    num = sn.nextInt();
                }
                salir = true; // Sale del bucle si la entrada es v�lida
                return num;
            } catch (InputMismatchException e) {
                // Captura la excepci�n si el usuario no ingresa un n�mero
                System.out.println("Debe introducir un n�mero v�lido");
                // Limpia el buffer de entrada
                sn.next();
            }
        } while (!salir);

        return -1; // En caso de error, retorna -1
    }

    // M�todo para sumar los n�meros en un rango usando un bucle for
    private static void sumarNumerosFor(int num1, int num2) {
        int suma = 0;
        // Bucle for que suma los n�meros en el rango (excluyendo num1 y num2)
        for (int i = num1 + 1; i < num2; i++) {
            suma += i;
        }
        // Retorna la suma obtenida
        System.out.println("La suma de los numeros entre " + num1 + " y " + num2 + " es: " + suma);
    }

    // M�todo para encontrar divisores de 7 en un rango usando un bucle while
    private static void divisoresDe7(int num1, int num2) {
        // Inicializa la cadena que almacenar� los divisores
        String divisores = "";

        // Bucle while que encuentra divisores de 7 en el rango
        while (num1 < num2) {
            ++num1;
            // Verifica si el n�mero actual es divisor de 7
            if (num1 % DIVISOR == 0) {
                // Agrega el divisor a la cadena, separado por comas si ya hay otros divisores
                if (divisores.length() > 0) {
                    divisores += ", ";
                }
                divisores += num1;
            }
        }
        // Retorna la cadena con los divisores encontrados
        if (divisores.length() == 0) {
            System.out.println("No existe divisores de " + DIVISOR + " entre los numeros " + num1 + " y " + num2);
        } else {
            System.out.println("Los divisores de " + DIVISOR + " en los numeros " + num1 + " y " + num2 + " son:\n" + divisores);
        }
    }

    // M�todo para gestionar la opci�n de salir del programa
    public static void salir() {
        boolean salir = false;

        // Bucle para preguntar al usuario si est� seguro de salir del programa
        do {
            System.out.println("�Est�s seguro que deseas salir del programa? (S/N)");
            // Lee la respuesta del usuario y la convierte a min�sculas
            String opcion = sn.next().toLowerCase();

            // Realiza acciones seg�n la respuesta del usuario
            if ("n".equals(opcion)) {
                // Llamada al m�todo para reiniciar el programa
                programa();
                salir = true; // Sale del bucle
            } else if ("s".equals(opcion)) {
                // Muestra mensaje de despedida y sale del programa
                System.out.println("Fin del programa. Hasta luego.");
                salir = true; // Sale del bucle
            } else {
                // Mensaje en caso de que el usuario ingrese una respuesta no v�lida
                System.out.println("�ERROR!. Debe introducir uno de los siguientes valores (S, N)");
            }
        } while (!salir);

    }
}
