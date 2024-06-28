package tarea02;

import java.util.Locale;
import java.util.Scanner;

public class PROG02_Ej2_David {

    static final int PAR = 2;

    static Scanner sn = new Scanner(System.in);

    public static void main(String[] args) {
        sn.useLocale(Locale.US);

        System.out.println("Introduce el primer n�mero");
        float num1 = sn.nextFloat();
        System.out.println("Introduce el segundo n�mero");
        float num2 = sn.nextFloat();
        System.out.println("La suma de los n�mero " + num1 + " y " + num2 + " es: " + (num1 + num2));
        System.out.println("La resta de los n�mero " + num1 + " y " + num2 + " es: " + (num1 - num2));

        // Operador ternario
        // System.out.println((num1 % num2 == 0) ? "El n�mero " + num2 + " es divisor del n�mero " + num1 : "El n�mero " + num2 + " no es divisor del n�mero " + num1);
        
        // if-else
        if (num1 % num2 == 0) {
            System.out.println("El n�mero " + num2 + " es divisor del n�mero " + num1);
        } else {
            System.out.println("El n�mero " + num2 + " no es divisor del n�mero " + num1);
        }

        // Operador ternario
        // System.out.println((num1 % 2 == 0) ? "El n�mero " + num1 + " es PAR" : "El n�mero " + num1 + " es IMPAR");
        
        // if-else
        if (num1 % PAR == 0) {
            System.out.println("El n�mero " + num1 + " es PAR");
        } else {
            System.out.println("El n�mero " + num1 + " es IMPAR");
        }

        // Operador ternario
        // System.out.println((num2 % 2 == 0) ? "El n�mero " + num2 + " es PAR" : "El n�mero " + num2 + " es IMPAR");
        
        // if-else
        if (num2 % PAR == 0) {
            System.out.println("El n�mero " + num2 + " es PAR");
        } else {
            System.out.println("El n�mero " + num2 + " es IMPAR");
        }

        if (num1 > num2) {
            System.out.println("El primer n�mero (" + num1 + ") es mayor que el segundo n�mero (" + num2 + ")");
        } else if (num2 > num1) {
            System.out.println("El segundo n�mero (" + num2 + ") es mayor que el primer n�mero (" + num1 + ")");
        } else {
            System.out.println("Es el mismo n�mero");
        }
    }
}
