package ejercicio2;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author David Medina Garcia
 */
public class Prog04_Ejercicio2_Ventas_David {

    // Scanner para entrada de datos
    static Scanner sn = new Scanner(System.in);

    // Constantes para valores máximos y tipos de IVA
    static final int MAX_UNIDADES = 999;
    static final byte IVA4 = 4;
    static final byte IVA10 = 10;
    static final byte IVA21 = 21;

    // Variables de la clase para almacenar los datos de la venta
    private static int unidadesVendidas = 0;
    private static float precioArticulo = 0;
    private static byte iva = 0;

    // Método principal
    public static void main(String[] args) {

        // Variable para determinar si introducir mas datos
        String masDatos = "";

        // Variables para acumular el total de las ventas
        float totalVentas = 0;

        // Bucle principal para ingresar datos de ventas
        while (!"n".equals(masDatos)) {
            try {
                // Solicitar y procesar datos de la venta
                System.out.println("Introduzca las unidades de la venta (0-" + MAX_UNIDADES + ")");
                unidadesVendidas = insertarUnidades();
                System.out.println("Introduzca el precio de los productos");
                precioArticulo = insertarPrecios();
                System.out.println("Introduzca el IVA de los productos(4, 10, 21)");
                iva = insertarIVA();

                // Calcular el total bruto y neto de la venta
                float totalBruto = calcularBruto(unidadesVendidas, precioArticulo);
                float totalNeto = calcularNeto(totalBruto, iva);

                // Mostrar los resultados
                System.out.println("El total bruto de las ventas es: " + totalBruto + "$");
                System.out.println("El total neto de las ventas es: " + totalNeto + "$");

                // Acumular el total de las ventas
                totalVentas += totalNeto;

                // Preguntar si se quieren ingresar más datos
                System.out.println("¿Quieres introducir mas datos? (S/N)");
                masDatos = sn.next().toLowerCase();

                // Validar la respuesta del usuario
                while (!"n".equals(masDatos) && !"s".equals(masDatos)) {
                    System.out.println("¡ERROR!. Debe introducir uno de los siguientes valores (S/N)");
                    masDatos = sn.next();
                }
            } catch (InputMismatchException e) {
                // Captura la excepción si el usuario no ingresa un número
                System.out.println("Debe introducir un número válido");
                // Limpia el buffer de entrada
                sn.next();
            } catch (Exception e) {
                // Captura la excepción generica
                System.out.println("ERROR! " + e.getMessage());
                // Limpia el buffer de entrada
                sn.next();
            }
        }

        // Mostrar el total acumulado de las ventas al final
        System.out.println("El total de todas las ventas es: " + String.format("%.2f", totalVentas) + "$");
    }

    // Método para ingresar las unidades de artículos vendidos
    private static int insertarUnidades() {
        boolean salir = false;
        unidadesVendidas = 0;
        while (!salir) {
            try {
                unidadesVendidas = sn.nextInt();
                // Validar que las unidades esten en el rango permitido
                while (unidadesVendidas > MAX_UNIDADES || unidadesVendidas < 0) {
                    System.out.println("Las unidades deben ser entre 0 y " + MAX_UNIDADES);
                    unidadesVendidas = sn.nextInt();
                }
                salir = true;
            } catch (InputMismatchException e) {
                System.out.println("Debe introducir un numero valido");
                sn.next();
            } catch (Exception e) {
                System.out.println("ERROR! " + e.getMessage());
                sn.next();
            }
        }
        return unidadesVendidas;
    }

    // Método para ingresar el precio del artículo
    private static float insertarPrecios() {
        sn.useLocale(Locale.US);
        boolean salir = false;
        precioArticulo = 0;
        while (!salir) {
            try {
                precioArticulo = sn.nextFloat();
                // Comprobar que el precio no sea negativo
                while (precioArticulo < 0) {
                    System.out.println("El precio no puede ser negativo");
                    precioArticulo = sn.nextFloat();
                }
                salir = true;
            } catch (InputMismatchException e) {
                System.out.println("Debe introducir un numero valido");
                sn.next();
            } catch (Exception e) {
                System.out.println("ERROR! " + e.getMessage());
                sn.next();
            }
        }
        return precioArticulo;
    }

    // Método para ingresar el tipo de IVA
    private static byte insertarIVA() {
        boolean salir = false;
        iva = 0;
        while (!salir) {
            try {
                iva = sn.nextByte();
                // Validar los valores del IVA
                while (iva != 4 && iva != 10 && iva != 21) {
                    System.out.println("El IVA debe ser " + IVA4 + ", " + IVA10 + " o " + IVA21);
                    iva = sn.nextByte();
                }
                salir = true;
            } catch (InputMismatchException e) {
                System.out.println("Debe introducir un numero valido");
                sn.next();
            } catch (Exception e) {
                System.out.println("ERROR! " + e.getMessage());
                sn.next();
            }
        }
        return iva;
    }

    // Método para calcular el total bruto de la venta
    private static float calcularBruto(int unidades, float precio) {
        return unidades * precio;
    }

    // Método para calcular el total neto de la venta
    private static float calcularNeto(float bruto, float iva) {
        return bruto * (1 + iva / 100);
    }
}
