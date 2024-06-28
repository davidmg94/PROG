/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import ejercicios.MiObjectOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import modelos.*;

/**
 *
 * @author Isabel
 */
public class Operaciones {

    static final String FICHERO = "EMPLEADOS.DAT";
    // lista que almacena los empleados
    static ArrayList<Empleado> listaEmpleados = new ArrayList<>();

    /**
     *
     * @throws ClassNotFoundException
     */
    public static void listarNominas() throws ClassNotFoundException {
        Empleado e;
        // Declararás la lista listaEmpleados

        // Se abre el fichero en el try para que lo cierreo automáticamente
        try (ObjectInputStream fichero
                = new ObjectInputStream(new FileInputStream(FICHERO));) {
            // lee el archivo y añade empleados a la lista
            while (true) {
                try {
                    e = (Empleado) fichero.readObject();
                    listaEmpleados.add(e);
                } catch (EOFException ex) {
                    break;
                }
            }
            // Aquí va el bucle que irá leyendo los objetos hasta que se acaba el fichero
            // y añadiendolos al ArrayList listaEmpleados
            // No hace falta cerrar el fichero porque se ha abierto en el try
        }// Fin try
        catch (EOFException ex) // Cuando se acaba el fichero pasa por aquí
        {
            // ordena la lista 
            Collections.sort(listaEmpleados, new comparadorEmpleado());
            // Cuando se acabe el fichero, ordenamos el ArrayList a través de la clase compararEmpleado:
            // Una vez esté ordenada visualizaremos el contenido de la lista en consola
            // con el formato que se indica en el examen
        } catch (IOException ex) {

        }
        // Imprime la lista por consola segun el formato dado
        System.out.printf("%-30s\t%-5s\t%-10s\t%-10s\t%-10s\t%-10s",
                "Nombre", "NRO. HIJOS", "TIPO EMP.", "TURNO", "SALARIO", "SLARIO NETO");
        System.out.println("");
        System.out.printf("%-30s\t%-5s\t%-10s\t%-10s\t%-10s\t%-10s",
                "=======", "=======", "======", "======", "=========", "=========");
        // variables para calcular el salario neto y el total de las nominas
        float salarioNeto = 0f;
        float totalNominas = 0f;
        String strSalario = "";
        // bucle que recorre los empleados
        for (Empleado em : listaEmpleados) {
            // se calcula el neto en funcion del numero de hijos
            if (em.getNroHijos() < 2) {
                salarioNeto = (float) (em.getSalario() - (em.getSalario() * 0.15));
                strSalario = String.format("%.2f", salarioNeto);
            } else if (em.getNroHijos() >= 2) {
                salarioNeto = (float) (em.getSalario() - (em.getSalario() * 0.1));
                strSalario = String.format("%.2f", salarioNeto);

            }
            // segun el turno del empleado se almacena una cadena distinta para mostrar por consola
            String turno = "";
            int elegirTurno = em.getTurno();
            switch (elegirTurno) {
                case 1:
                    turno = "Mañana";
                    break;
                case 2:
                    turno = "Tarde";
                    break;
                case 3:
                    turno = "Nocturno";
                    break;
                case 4:
                    turno = "Mitad";
                    break;
                default:
                    throw new AssertionError();
            }
            
            // segun el tipo de empleado se almacena una cadena distinta para mostrar por consola
            String tipoEmp = "";
            if (em.getTipoEmpleado().equalsIgnoreCase("I")) {
                tipoEmp = "Ingeniero";
            } else if (em.getTipoEmpleado().equalsIgnoreCase("V")) {

                tipoEmp = "Vendedor";

            }
            System.out.println("");
            // Imprimir detalles de cada mueble en formato tabular
            System.out.printf("%-30s\t%-5s\t%-10s\t%-10s\t%-10s\t%-10s",
                    em.getNombre(),
                    em.getNroHijos(),
                    tipoEmp,
                    turno,
                    em.getSalario(),
                    strSalario);
            totalNominas += salarioNeto;
        }
        String ventasFormat = String.format("%.2f", totalNominas);
        // Mostrar el total vendido
        System.out.println("\nTOTAL VENDIDO: " + ventasFormat + "$");
    }

    /**
     * buscaEmpleado Busca una empleado en el fichero EMPLEADOS.DAT Si lo
     * encuentra devuelve un objeto Empleado y si no devuelve null
     *
     * @param idEmpleado
     * @return
     */
    public static Empleado buscaEmpleado(int idEmpleado) {
        Empleado e;
        // Se abre el fichero en el try para que lo cierreo automáticamente
        try (ObjectInputStream fichero
                = new ObjectInputStream(new FileInputStream(FICHERO));) {
            while (true) {
                // Lee un registro y lo guarda en el objeto e
                e = (Empleado) fichero.readObject();
                if (e.getIdEmpleado() == idEmpleado) {
                    return e;
                }
            }
            // No hace falta cerrar el fichero porque se ha abierto en el try
        }// Fin try// Fin try
        catch (Exception ex) {
            return null;
        }

    }

    /**
     * existeEmpleado: busca un idEmpleado en el fichero EMPLEADOS.DAT y
     * devuelve true si la encuentra y false si no la encuentra
     *
     * @param idEmpleado
     * @return
     */
    public static boolean existeEmpleado(int idEmpleado) {
        //Declara el fichero y lo conecta 
        Empleado e;
        // Se abre el fichero en el try para que lo cierre automáticamente
        try (ObjectInputStream fichero
                = new ObjectInputStream(new FileInputStream(FICHERO));) {
            // Se va leyendo los objetos hasta que se acaba el fichero
            while (true) {
                e = (Empleado) fichero.readObject();
                if (e.getIdEmpleado() == idEmpleado) {
                    return true;
                }
            }
            // No hace falta cerrarlo porque se ha abierto en el try
        }// Fin try
        catch (Exception ex) {
            return false;
        }
    }

    /**
     * grabarEmpleado: Graba un objeto Empleado en el fichero EMPLEADOS.DAT
     *
     * @param e
     * @return
     */
    public static boolean grabarEmpleado(Empleado e) {
        //Declara el fichero y lo conecta 
        File f = new File(FICHERO);
        ObjectOutputStream oos;
        try {
            // TIENES QUE COMPRIBAR SI EL FICHERO EXISTE
            //
            if (f.exists()) {
                // Se utiliza MiObjectOutputStream para quitar las cabeceras
                // cuando el fichero ya exista
                oos = new MiObjectOutputStream(new FileOutputStream(f, true));
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(f));
            }
            oos.writeObject(e); // graba el objeto 
            oos.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No existe el fichero");

            return false;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de E/S:" + ex);
            return false;
        }
        return true;
    }

    /**
     * eliminarTodos: elimina todo el contenido del fichero
     */
    public static void eliminarTodos() {
        File f = new File(FICHERO);
        f.delete();
    }

    /**
     * abrirLectura: abre el fichero VENTAS.DAT para lectura
     *
     * @return
     */
    public static ObjectInputStream abrirLectura() {
        // Se abre el fichero en el try para que lo cierreo automáticamente
        try {
            return new ObjectInputStream(new FileInputStream(FICHERO));
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     *
     * @param fichVenta
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Empleado leerRegistro(ObjectInputStream fichVenta) throws IOException, ClassNotFoundException {
        return (Empleado) fichVenta.readObject();
    }

    /**
     *
     * @param f
     * @throws IOException
     */
    public static void cerrarFichero(ObjectInputStream f) throws IOException {
        f.close();
    }

    /**
     * correcto: Método para saber si un valor se corresponde con el patrón
     *
     * @param valor
     * @param patron
     * @return
     */
    public static boolean correcto(String valor, Pattern patron) {
        boolean correcto;
        Matcher nValor = patron.matcher(valor);
        if (nValor.matches()) {
            correcto = true;
        } else {
            correcto = false;
        }
        return correcto;
    }
}
