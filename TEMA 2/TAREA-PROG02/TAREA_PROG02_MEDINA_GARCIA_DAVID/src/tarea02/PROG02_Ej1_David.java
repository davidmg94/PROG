package tarea02;

import Enum.*;

/**
 * Nombre de clase: PROG02_Ej1_David
 *
 * Descripción: Este programa demuestra el uso de variables de diferentes tipos
 * para representar información sobre un alumno, como si tiene beca, el valor
 * mínimo requerido, el tipo de matrícula, la nota y el mes de nacimiento.
 *
 * Autor: David Medina García
 */
public class PROG02_Ej1_David {

    // Usamos un float para representar el valor mínimo requerido, 
    // ya que puede ser un número decimal y ademas ocupa menos espacio que un double.
    static final float VALOR_MINIMO = 4.5f;

    public static void main(String[] args) {
        // Usamos un booleano para representar si el alumno tiene beca o no, 
        // ya que solo tiene dos estados posibles: true (tiene beca) o false (no tiene beca).
        boolean beca = true;
        // Usamos un enumerado (TipoMatricula) para representar el tipo de matrícula del alumno, 
        // ya que este tipo de dato es adecuado para representar opciones fijas y limitadas.(E, M, P)
        TipoMatricula matricula = TipoMatricula.E;
        // Usamos un float para representar la nota del alumno,
        // ya que las notas suelen ser números decimales y ademas ocupa menos espacio que un double.
        float nota = 6.94566f;
        // Usamos un enumerado (MesesDelAno) para representar el tipo de matrícula del alumno, 
        // ya que este tipo de dato es adecuado para representar opciones fijas y limitadas.
        MesesDelAno mesNacimiento = MesesDelAno.MAYO;
        
        System.out.println("EJERCICIO DE USO DE VARIABLES:");
        System.out.printf("\n\t%s%b", "El alumno tiene beca: ", beca);
        System.out.printf("\n\t%s%.2f", "El valor MINIMO: ", VALOR_MINIMO);
        System.out.printf("\n\t%s%s", "El tipo de matrícula: ", matricula);
        System.out.printf("\n\t%s%.2f", "Nota del alumno: ", nota);
        System.out.printf("\n\t%s%d\n", "Mes de naciminento: ", (mesNacimiento.ordinal() + 1));
    }
}
