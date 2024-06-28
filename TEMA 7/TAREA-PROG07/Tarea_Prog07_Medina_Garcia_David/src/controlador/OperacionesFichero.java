package controlador;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import modelos.Mueble;

/**
 *
 * @author David Medina Garcia
 */
public class OperacionesFichero {

    // objeto para la entrada de texto que se usara en los metodos
    static Scanner sn = new Scanner(System.in);

    // Constantes que establecen el nombre de los ficheros
    public static final String NOMBRE_ARCHIVO = "MUEBLES.DAT";
    private static final String ARCHIVO_TEMPORAL = "TEMPORAL.TMP";

    // Lista que almacena los objetos de Mueble creados
    private static final ArrayList<Mueble> listaMuebles = new ArrayList<>();

    // comprueba si existe el fichero
    public static boolean existeFichero(String nombreFichero) {
        // Crear un objeto File que representa el archivo especificado
        File archivo = new File(nombreFichero);
        // Verificar si el archivo existe
        return archivo.exists();
    }

    // Método para buscar un registro por el ID del mueble
    public static Mueble buscarRegistro(int idMueble) {
        if (!existeFichero(NOMBRE_ARCHIVO)) {
//            System.out.println("El archivo " + NOMBRE_ARCHIVO + " no existe.");
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOMBRE_ARCHIVO))) {
            while (true) {
                Mueble mueble = (Mueble) ois.readObject();
                if (mueble.getIdMueble() == idMueble) {
                    return mueble; // Devuelve el mueble si encuentra el idMueble en el archivo
                }
            }
        } catch (EOFException e) {
            // Llega al final del archivo sin encontrar el idMueble, devuelve null
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            // Si el archivo no existe, devuelve false
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Método para verificar si existe un registro con el ID del mueble dado
    public static boolean existeRegistro(int idMueble) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOMBRE_ARCHIVO))) {
            while (true) {
                Object obj = ois.readObject();
                if (obj instanceof Mueble mueble) {
                    if (mueble.getIdMueble() == idMueble) {
                        return true; // Si encuentra el idMueble en el archivo, devuelve true
                    }
                }
            }
        } catch (EOFException e) {
            // Llega al final del archivo sin encontrar el idMueble, devuelve false
        } catch (FileNotFoundException e) {
            // Si el archivo no existe, devuelve false
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    // Método para grabar un registro de tipo Mueble en un archivo
    public static boolean grabarRegistro(Mueble m) {
        // Crear el flujo de salida de objetos
        ObjectOutputStream oos = null;
        try {
            File archivo = new File(NOMBRE_ARCHIVO);

            if (archivo.exists()) {
                // Si el archivo ya existe, utilizar MiObjectOutputStream para eliminar las cabeceras
                oos = new MiObjectOutputStream(new FileOutputStream(archivo, true));
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(archivo));
            }

            // Escribir el objeto Mueble en el archivo
            oos.writeObject(m);
            oos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un mueble por ID
    public static boolean eliminarRegistro(int idMueble) {

        try {
            // Creamos un fichero temporal para almacenar los registros del fichero original, menos el que se desea eliminar
            File ficheroTemporal = new File(ARCHIVO_TEMPORAL);
            // Crear un objeto File que representa el archivo especificado
            File archivoActual = new File(NOMBRE_ARCHIVO);
            // Inicia un bloque try-catch para asegurar la correcta gestión de recursos
            // Utilizamos ObjectOutputStream y FileOutputStream para guardar objetos en un archivo
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroTemporal)); ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoActual))) {
                // Leemos cada registro del fichero original
                while (true) {
                    try {
                        Mueble mueble = (Mueble) ois.readObject();
                        // Si el registro no corresponde al mueble que se desea eliminar, lo escribimos en el fichero temporal
                        if (mueble.getIdMueble() != idMueble) {
                            oos.writeObject(mueble);
                        }
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            // eliminamos el fichero original para poder renombrar el nuevo
            archivoActual.delete();
            // Renombramos el fichero temporal al nombre original
            ficheroTemporal.renameTo(new File(NOMBRE_ARCHIVO));
            return true;

        } catch (Exception e) {
            System.out.println("Error en la eliminación del mueble: " + e.getMessage());
        }
        return false;
    }

    // Método para eliminar el archivo
    public static boolean eliminarTodos() {
        // Crear un objeto File que representa el archivo especificado
        File archivo = new File(NOMBRE_ARCHIVO);
        if (archivo.exists()) {//si el archivo existe
            if (archivo.delete()) {
                // Si la operación de borrado tiene éxito, se informa al usuario y se limpia la lista 'muebles'
//                System.out.println("El archivo " + NOMBRE_ARCHIVO + " fue eliminado.");
                listaMuebles.clear();
                return true;
            } else {
//                System.out.println("No se pudo borrar el archivo " + NOMBRE_ARCHIVO);
                return false;
            }
        } else {
//            System.out.println("El archivo " + NOMBRE_ARCHIVO + " no existe.");
            return false;
        }
    }

    // Método para listar los muebles ordenados por ID
    public static String listarMuebles() {

        String lista = new String();
        // Inicia un bloque try-catch para asegurar la correcta gestión de recursos
        // Utilizamos ObjectInputStream y FileInputStream para leer objetos de un archivo
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOMBRE_ARCHIVO))) {

            // Leer todos los objetos Mueble del archivo y almacenarlos en la lista
            while (true) {
                try {
                    Mueble mueble = (Mueble) ois.readObject();
                    lista += mueble.toStringCompleto() + "\n";

                } catch (EOFException e) {
                    break;
                }
            }

            return lista;

        } catch (FileNotFoundException e) {
            System.out.println("No existe el fichero.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
