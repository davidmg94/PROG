//package app;
//
//import controlador.Utilidades;
//import enumTipoMueble.TipoMueble;
//import modelos.Mueble;
//import validaciones.Validacion;
//import java.io.*;
//import java.util.*;
//
///**
// *
// * @author David Medina Garcia
// */
//public class AppGesionMueblesDavid {
//
//    // objeto para la entrada de texto que se usara en los metodos
//    static Scanner sn = new Scanner(System.in);
//
//    // Constantes que establecen el nombre de los ficheros
//    private static final String NOMBRE_ARCHIVO = "MUEBLES.DAT";
//    private static final String ARCHIVO_TEMPORAL = "TEMPORAL.TMP";
//
//    // Lista que almacena los objetos de Mueble creados
//    private static final ArrayList<Mueble> listaMuebles = new ArrayList<>();
//
//    // Constante que establece el limite de las opciones
//    private static final int LIMITE = 6;
//
//    public static void main(String[] args) throws IOException {
//        recuperarFichero(); // Recupera datos del archivo al iniciar la aplicaci�n
//        programa(); // Inicia el flujo principal del programa
//    }
//
//    // M�todo principal que gestiona el flujo del programa
//    private static void programa() throws IOException {
//        int opcion = -1;
//        do {
//            try {
//                // Visualizaci�n del men� y captura de la opci�n seleccionada
//                visualizarMenu();
//                opcion = Utilidades.leerOpcion(LIMITE);
//
//                // Switch para realizar las operaciones seg�n la opci�n seleccionada
//                switch (opcion) {
//                    case 1:
//                        insertarMueble(); // Inserta un nuevo mueble
//                        Utilidades.pulsarReturn();
//                        break;
//                    case 2:
//                        listarMuebles(); // Lista todos los muebles
//                        Utilidades.pulsarReturn();
//                        break;
//                    case 3:
//                        buscarMueble(); // Busca un mueble por su ID
//                        Utilidades.pulsarReturn();
//                        break;
//                    case 4:
//                        eliminarMueble(); // Elimina un mueble por su ID
//                        Utilidades.pulsarReturn();
//                        break;
//                    case 5:
//                        eliminarFichero(); // Elimina todos los datos del fichero
//                        Utilidades.pulsarReturn();
//                        break;
//                    case 6:
//                        salir(); // Llamada al m�todo salir para gestionar la finalizaci�n del programa
//                        break;
//                    default:
//                        Utilidades.visualizarMensaje("Error: Debes introducir un n�mero entero entre 0 y " + LIMITE + ".");
//                        Utilidades.pulsarReturn();
//                }
//            } catch (NumberFormatException e) {
//                Utilidades.visualizarMensaje("Error: Debes introducir un n�mero entero entre 0 y " + LIMITE + ".");
//                Utilidades.pulsarReturn();
//            } catch (IOException e) {
//                Utilidades.visualizarMensaje("Error al leer la entrada del teclado");
//                Utilidades.pulsarReturn();
//            }
//        } while (opcion != LIMITE);
//    }
//
//    // M�todo para visualizar el men� de la aplicaci�n
//    private static void visualizarMenu() {
//        Utilidades.visualizarMensaje("\n----- Gestion de muebles -----");
//        Utilidades.visualizarMensaje("1. A�adir Mueble");
//        Utilidades.visualizarMensaje("2. Listar todos los muebles");
//        Utilidades.visualizarMensaje("3. Buscar un mueble");
//        Utilidades.visualizarMensaje("4. Borrar un mueble");
//        Utilidades.visualizarMensaje("5. Borrar todos los datos del fichero");
//        Utilidades.visualizarMensaje("6. Salir");
//    }
//
//    // M�todo para verificar si existe un registro con el ID del mueble dado
//    private static boolean existeRegistro(int idMueble) {
//        Mueble mueble;
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOMBRE_ARCHIVO))) {
//            while (true) {
//                mueble = (Mueble) ois.readObject();
//                if (mueble.getIdMueble() == idMueble) {
//                    return true; // Si encuentra el idMueble en el archivo, devuelve true
//
//                }
//            }
//        } catch (EOFException e) {
//            // Llega al final del archivo sin encontrar el idMueble, devuelve false
//        } catch (FileNotFoundException e) {
//            // Si el archivo no existe, devuelve false
//        } catch (ClassNotFoundException | IOException e) {
//            Utilidades.visualizarMensaje(e.getMessage());
//        }
//        return false;
//    }
//
//    // M�todo para buscar un registro por el ID del mueble
//    private static Mueble buscarRegistro(int idMueble) {
//        Mueble mueble;
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOMBRE_ARCHIVO))) {
//            while (true) {
//                mueble = (Mueble) ois.readObject();
//                if (mueble.getIdMueble() == idMueble) {
//                    return mueble; // Devuelve el mueble si encuentra el idMueble en el archivo
//                }
//            }
//        } catch (EOFException e) {
//            // Llega al final del archivo sin encontrar el idMueble, devuelve null
//        } catch (FileNotFoundException e) {
//            // Si el archivo no existe, devuelve false
//        } catch (ClassNotFoundException | IOException e) {
//            Utilidades.visualizarMensaje(e.getMessage());
//        }
//        return null;
//    }
//
//    // M�todo para insertar un nuevo mueble en la lista y guardar los datos en el archivo
//    private static void insertarMueble() {
//        // Se obtiene el tama�o actual de la lista de muebles
//        int tamanoArray = listaMuebles.size();
//
//        // Se solicita el ID del mueble y se verifica si ya existe en el archivo
//        int idMueble = introducirIdMueble();
//        while (existeRegistro(idMueble) || idMueble == 0) {
//            if (idMueble == 0) {
//                Utilidades.visualizarMensaje("El ID del Mueble debe estar entre 1-99999.");
//            } else {
//                Utilidades.visualizarMensaje("Ese registro ya existe");
//            }
//            idMueble = introducirIdMueble();
//        }
//        sn.nextLine();  // Consumir la nueva l�nea antes de leer la descripci�n del mueble
//
//        // Se solicitan los detalles del mueble: descripci�n, precio, unidades en almac�n, unidades m�nimas y tipo
//        String descrpMueble = introducirDescripcionMueble();
//        float precio = introducirPrecioMueble();
//        int unidadesAlmacen = introducirUnidadesAlmacenMueble();
//        int unidadesMinimas = introducirUnidadesMinimasMueble();
//
//        // Se verifica que las unidades en el almac�n sean mayores que las unidades m�nimas
//        while (unidadesAlmacen <= unidadesMinimas) {
//            Utilidades.visualizarMensaje("Las unidades disponibles en el almacen deben ser mayores que las unidades minimas");
//            unidadesAlmacen = introducirUnidadesAlmacenMueble();
//            unidadesMinimas = introducirUnidadesMinimasMueble();
//        }
//
//        // Se solicita el tipo de mueble
//        TipoMueble tipoMueble = introducirTipoMueble();
//
//        // Se crea un nuevo objeto Mueble y se a�ade a la lista
//        Mueble mueble = new Mueble(idMueble, descrpMueble, precio, unidadesAlmacen, unidadesMinimas, tipoMueble);
//        listaMuebles.add(mueble);
//        listaMuebles.sort(Comparator.comparingInt(Mueble::getIdMueble));
//
//        // Se verifica si la lista se ha actualizado y se guarda en el archivo si es necesario
//        if (listaMuebles.size() > tamanoArray) {
//            guardarDatos();
//        }
//    }
//
//    // M�todo para introducir el ID de un mueble
//    private static int introducirIdMueble() {
//        int idMueble = -1;
//        do {
//            try {
//                Utilidades.visualizarMensaje("Introduce el ID del mueble (1-99999)");
//                idMueble = sn.nextInt();
//            } catch (InputMismatchException e) {
//                Utilidades.visualizarMensaje("ERROR! Debe introducir un numero valido.");
//                sn.nextLine();
//            }
//            // Bucle para continuar solicitando el ID hasta que se ingrese un valor num�rico v�lido
//        } while (!Validacion.validacionNumeros(idMueble));
//        return idMueble;
//    }
//
//    // M�todo para introducir la descripci�n de un mueble
//    private static String introducirDescripcionMueble() {
//        String descrpMueble = "";
//        do {
//            try {
//                Utilidades.visualizarMensaje("Introduce la descripcion del mueble (max. 30 caracteres)");
//                descrpMueble = sn.nextLine();
//            } catch (Exception e) {
//                Utilidades.visualizarMensaje("Error en la descripcion del producto.");
//                sn.nextLine();
//            }
//        } // Bucle para continuar solicitando la descripci�n hasta que se ingrese una cadena v�lida
//        while (!Validacion.validacionCadena(descrpMueble));
//
//        return descrpMueble;
//    }
//
//    // M�todo para introducir el precio de un mueble
//    private static float introducirPrecioMueble() {
//        sn.useLocale(Locale.US); // Configura el Scanner para usar el separador decimal como punto en lugar de coma
//        float precio = 0;
//        do {
//            try {
//                Utilidades.visualizarMensaje("Introduce el precio del mueble");
//                precio = sn.nextFloat();
//            } catch (InputMismatchException e) {
//                Utilidades.visualizarMensaje("ERROR! Debe introducir un numero valido.");
//                sn.nextLine();
//            }
//            // Bucle para continuar solicitando el precio hasta que se ingrese un n�mero decimal v�lido
//        } while (!Validacion.validacionPrecio(precio));
//        return precio;
//    }
//
//    // M�todo para introducir las unidades de almacenamiento de un mueble
//    private static int introducirUnidadesAlmacenMueble() {
//        int unidadesAlmacen = 0;
//        do {
//            try {
//                Utilidades.visualizarMensaje("Introduce las unidades del mueble en el almacen (1-99999)");
//                unidadesAlmacen = sn.nextInt();
//            } catch (InputMismatchException e) {
//                Utilidades.visualizarMensaje("ERROR! Debe introducir un numero valido.");
//                sn.nextLine();
//            }
//            // Bucle para continuar solicitando las unidades de almacenamiento hasta que se ingrese un n�mero entero v�lido
//        } while (!Validacion.validacionNumeros(unidadesAlmacen));
//        return unidadesAlmacen;
//    }
//
//    // M�todo para introducir las unidades m�nimas de un mueble
//    private static int introducirUnidadesMinimasMueble() {
//        int unidadesMinimas = 0;
//        do {
//            try {
//                Utilidades.visualizarMensaje("Introduce las unidades minimas del mueble (1-99999)");
//                unidadesMinimas = sn.nextInt();
//            } catch (InputMismatchException e) {
//                Utilidades.visualizarMensaje("ERROR! Debe introducir un numero valido.");
//                sn.nextLine();
//            }
//            // Bucle para continuar solicitando las unidades minimas hasta que se ingrese un n�mero entero v�lido
//        } while (!Validacion.validacionNumeros(unidadesMinimas));
//        return unidadesMinimas;
//    }
//
//    // M�todo para introducir el tipo de mueble
//    private static TipoMueble introducirTipoMueble() {
//        TipoMueble tipoMueble = null;
//        String input = "";
//        do {
//            try {
//                Utilidades.visualizarMensaje("Introduce el tipo de mueble:");
//                for (TipoMueble mueble : TipoMueble.values()) {
//                    Utilidades.visualizarMensaje("\t-" + mueble + ", si el tipo de mueble es " + mueble.getTipoMuebleCompleto());
//                }
//                // Obtiene la entrada del usuario y la convierte a may�sculas para hacerla case-insensitive
//                input = sn.next().toUpperCase();
//            } catch (IllegalArgumentException e) {
//                Utilidades.visualizarMensaje("El tipo de mueble debe ser H(Hogar), C(Colegios), o D(Despacho).");
//                sn.nextLine();
//            }
//            // Bucle para continuar solicitando el tipo de mueble hasta que se ingrese una opci�n v�lida
//        } while (!Validacion.validacionTipoMueble(input));
//        // Intenta convertir la entrada en un valor del enum TipoMueble
//        tipoMueble = TipoMueble.valueOf(input);
//        return tipoMueble;
//    }
//
//    private static void guardarDatos() {
//        if (confirmarAccion("�Quieres guardar los datos? (S/N)")) {
//            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOMBRE_ARCHIVO))) {
//                for (Mueble m : listaMuebles) {
//                    oos.writeObject(m);
//                }
//                Utilidades.visualizarMensaje("Fichero guardado con �xito.");
//                if (confirmarAccion("�Quieres realizar otro registro? (S/N)")) {
//                    insertarMueble();
//                }
//            } catch (FileNotFoundException ex) {
//                Utilidades.visualizarMensaje(ex.getMessage());
//            } catch (IOException ex) {
//                Utilidades.visualizarMensaje(ex.getMessage());
//            }
//        } else {
//            Utilidades.visualizarMensaje("No se han guardado los datos");
//        }
//    }
//
////    // M�todo para guardar los datos de la lista de muebles en el archivo
////    private static void guardarDatos() {
////        // boolean salir1 = false;
////        // boolean salir2 = false;
////        String confirmar = "";
////        do {
////            // Se solicita la confirmaci�n para guardar los datos
////            Utilidades.visualizarMensaje("�Quieres guardar los datos? (S/N)");
////            confirmar = sn.next();
////
////            if ("s".equalsIgnoreCase(confirmar)) {
////                // Inicia un bloque try-catch para asegurar la correcta gesti�n de recursos
////                // Utilizamos ObjectOutputStream y FileOutputStream para guardar objetos en un archivo
////                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOMBRE_ARCHIVO))) {
////                    // Se recorre la lista de muebles y se escribe cada objeto en el archivo
////                    for (Mueble m : listaMuebles) {
////                        oos.writeObject(m);
////                    }
////
////                    Utilidades.visualizarMensaje("Fichero guardado con �xito.");
////
////                    String confirmar2 = "";
////                    do {
////                        // Se pregunta si se quiere realizar otro registro
////                        Utilidades.visualizarMensaje("�Quieres realizar otro registro? (S/N)");
////                        confirmar2 = sn.next();
////
////                        if ("s".equalsIgnoreCase(confirmar2)) {
//////                            salir2 = true;
////
////                            insertarMueble();
////                        } else if ("n".equalsIgnoreCase(confirmar2)) {
////                            Utilidades.visualizarMensaje("Volviendo al menu...");
//////                                                        salir2 = true;
////
////                        } else {
////                            Utilidades.visualizarMensaje("ERROR! Debe pulsar S/N para continuar.");
////                        }
////                        // Repite el bucle mientras la respuesta no sea 's' ni 'n'
////                    } while (!"s".equalsIgnoreCase(confirmar2) && !"n".equalsIgnoreCase(confirmar2));
////                    // } while (!salir2);
////
////                    // salir1 = true;
////                } catch (FileNotFoundException ex) {
////                    Utilidades.visualizarMensaje(ex.getMessage());
////                } catch (IOException ex) {
////                    Utilidades.visualizarMensaje(ex.getMessage());
////                }
////            } else if ("n".equalsIgnoreCase(confirmar)) {
////                Utilidades.visualizarMensaje("No se han guardado los datos");
////                // salir1 = true;
////
////            } else {
////                Utilidades.visualizarMensaje("ERROR! Debe pulsar S/N para continuar.");
////            }
////            // Repite el bucle mientras la respuesta no sea 's' ni 'n'
////        } while (!"s".equalsIgnoreCase(confirmar) && !"n".equalsIgnoreCase(confirmar));
////        // } while (!salir1);
////    }
//    // M�todo para recuperar datos del archivo al inicio de la aplicaci�n
//    private static void recuperarFichero() {
//        // Inicia un bloque try-catch para asegurar la correcta gesti�n de recursos
//        // Utilizamos ObjectInputStream y FileInputStream para leer objetos de un archivo
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOMBRE_ARCHIVO))) {
//            while (true) {
//                // Se lee cada objeto Mueble del archivo y se a�ade a la lista
//                Mueble mueble = (Mueble) ois.readObject();
//                listaMuebles.add(mueble);
//            }
//
//        } catch (FileNotFoundException ex) {
//            Utilidades.visualizarMensaje("No existe el fichero.");
//        } catch (EOFException ex) {
//            // Se informa si el archivo estaba vac�o o se han a�adido muebles a la lista
//            if (listaMuebles.isEmpty()) {
//                Utilidades.visualizarMensaje("No existen Muebles.");
//            } else {
//                Utilidades.visualizarMensaje("Muebles a�adidos del fichero");
//            }
//        } catch (IOException | ClassNotFoundException ex) {
//            Utilidades.visualizarMensaje(ex.getMessage());
//        }
//
//        // Se imprime la lista de muebles recuperada del archivo
//        for (Mueble mueble : listaMuebles) {
//            System.out.println(mueble);
//        }
//    }
//
//    // M�todo para listar los muebles ordenados por ID
//    private static void listarMuebles() {
//        if (listaMuebles.isEmpty()) {
//            Utilidades.visualizarMensaje("No hay datos registrados.");
//        } else {
//            System.out.printf("%-5s\t%-30s\t%-10s\t%-15s\t%-15s\t%-10s%n",
//                    "ID MUEBLE", "NOMBRE", "TIPO", "PRECIO UNITARIO", "UNID.ALMACEN", "TOTAL");
//
//            System.out.printf("%-5s\t%-30s%-10s\t%-15s\t%-15s\t%-10s%n",
//                    "=========", "=============", "========", "===============", "=============", "==============");
//
//            for (Mueble mueble : listaMuebles) {
//                float total = mueble.getUnidadesAlmacen() * mueble.getPrecioUnitario();
//
//                // Imprimir detalles de cada mueble en formato tabular
//                System.out.printf("%-5s\t%-30s\t%-10s\t%-15s\t\t%-15s\t\t%-10s%n",
//                        mueble.getIdMueble(),
//                        mueble.getDescripMueble(),
//                        mueble.getTipoMueble().getTipoMuebleCompleto(),
//                        mueble.getPrecioUnitario(),
//                        mueble.getUnidadesAlmacen(),
//                        total);
//            }
//        }
//    }
//
//    // M�todo para buscar un mueble por ID
//    private static void buscarMueble() {
//        if (listaMuebles.isEmpty()) {
//            Utilidades.visualizarMensaje("No hay datos registrados.");
//        } else {
//            Utilidades.visualizarMensaje("Introduce el ID del mueble que deseas buscar (1-99999) o pulsa 0 para salir:");
//            int idMueble = -1; // Inicializa el ID del mueble con un valor no v�lido
//            Mueble mueble = null; // Inicializa el objeto Mueble con null
//            do {
//                idMueble = introducirIdMueble(); // Llama a un m�todo para obtener el ID del usuario
//                mueble = buscarRegistro(idMueble); // Llama a un m�todo para buscar el mueble por ID
//
//                if (idMueble == 0) {
//                    Utilidades.visualizarMensaje("Volviendo al menu...");
//                } else if (mueble == null) {
//                    // Si no se encuentra el mueble, informa al usuario y pide otro ID o salir
//                    Utilidades.visualizarMensaje("No existe el mueble buscado");
//                    Utilidades.visualizarMensaje("Introduce otro ID o pulsa 0 para salir");
//                } else {
//                    // Si se encuentra el mueble, muestra los detalles y pregunta si se quiere buscar otro mueble
//                    Utilidades.visualizarMensaje("Mueble encontrado. Detalles del mueble:");
//                    Utilidades.visualizarMensaje(mueble.toString());
//
//                    if (confirmarAccion("�Quieres buscar otro mueble?(S/N)")) {
//                        buscarMueble();
//                    }
//                }
//                // Repite el bucle mientras el ID no sea 0 y no se haya encontrado el mueble
//            } while (idMueble != 0 && mueble == null);
//        }
//    }
//
//    // M�todo para eliminar un mueble por ID
//    private static void eliminarMueble() {
//        if (listaMuebles.isEmpty()) {
//            Utilidades.visualizarMensaje("No hay datos registrados.");
//        } else {
//            int idMueble = -1;
//            do {
//                try {
//                    Utilidades.visualizarMensaje("Introduce el ID del mueble que deseas borrar (1-99999) o pulsa 0 para salir:");
//                    idMueble = introducirIdMueble();
//                    if (idMueble == 0) {
//                        Utilidades.visualizarMensaje("Volviendo al menu...");
//                    } else {
//                        while (!existeRegistro(idMueble)) {
//                            // Si no existe el mueble, pide otro ID o salir
//                            Utilidades.visualizarMensaje("No existe el mueble que quieres eliminar.");
//                            Utilidades.visualizarMensaje("Introduce otro ID o pulsa 0 para salir");
//                            idMueble = introducirIdMueble();
//                        }
//
//                        sn.nextLine();  // Consumir la nueva l�nea antes de leer la descripci�n del mueble
//
//                        if (confirmarAccion("�Desea eliminar el mueble? (S/N)")) {
//                            // Creamos un fichero temporal para almacenar los registros del fichero original, menos el que se desea eliminar
//                            File ficheroTemporal = new File(ARCHIVO_TEMPORAL);
//                            // Crear un objeto File que representa el archivo especificado
//                            File archivoActual = new File(NOMBRE_ARCHIVO);
//
//                            // Inicia un bloque try-catch para asegurar la correcta gesti�n de recursos
//                            // Utilizamos ObjectOutputStream y FileOutputStream para guardar objetos en un archivo
//                            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroTemporal)); ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoActual))) {
//                                // Leemos cada registro del fichero original
//                                while (true) {
//                                    try {
//                                        Mueble mueble = (Mueble) ois.readObject();
//                                        // Si el registro no corresponde al mueble que se desea eliminar, lo escribimos en el fichero temporal
//                                        if (mueble.getIdMueble() != idMueble) {
//                                            oos.writeObject(mueble);
//                                        }
//                                    } catch (EOFException e) {
//                                        break;
//                                    }
//                                }
//                                Utilidades.visualizarMensaje("El mueble " + idMueble + " fue eliminado.");
//                            } catch (IOException | ClassNotFoundException e) {
//                                Utilidades.visualizarMensaje(e.getMessage());
//                            }
//                            // eliminamos el fichero original para poder renombrar el nuevo
//                            archivoActual.delete();
//                            // Renombramos el fichero temporal al nombre original
//                            ficheroTemporal.renameTo(new File(NOMBRE_ARCHIVO));
//                        }
//                    }
//                } catch (Exception e) {
//                    Utilidades.visualizarMensaje("Error en la eliminaci�n del mueble: " + e.getMessage());
//                }
//            } while (idMueble != 0);
//        }
//    }
//
//    // M�todo para eliminar el archivo
//    private static void eliminarFichero() throws IOException {
//        // Crear un objeto File que representa el archivo especificado
//        File archivo = new File(NOMBRE_ARCHIVO);
//        // Si el archivo existe, se informa al usuario y se solicita confirmaci�n para eliminarlo
//        if (archivo.exists()) {
//            if (confirmarAccion("Se va a eliminar el archivo " + NOMBRE_ARCHIVO + "\n�Quieres continuar?(S/N)")) {
//                eliminarFichero(archivo);
//            }
//        } else {
//            Utilidades.visualizarMensaje("El archivo " + NOMBRE_ARCHIVO + " no existe.");
//        }
//    }
//
//    // M�todo auxiliar para eliminar el archivo
//    private static void eliminarFichero(File archivo) {
//        if (archivo.delete()) {
//            // Si la operaci�n de borrado tiene �xito, se informa al usuario y se limpia la lista 'muebles'
//            Utilidades.visualizarMensaje("El archivo " + NOMBRE_ARCHIVO + " fue borrado exitosamente.");
//            listaMuebles.clear();
//        } else {
//            Utilidades.visualizarMensaje("No se pudo borrar el archivo " + NOMBRE_ARCHIVO);
//        }
//    }
//
//    // M�todo para gestionar la salida del programa
//    private static void salir() throws IOException {
//        if (!confirmarAccion("�Est�s seguro de que deseas salir del programa? (S/N)")) {
//            programa();
//        }
//    }
//
//    private static boolean confirmarAccion(String mensaje) {
//        String confirmar;
//        do {
//            Utilidades.visualizarMensaje(mensaje);
//            confirmar = sn.next();
//            if ("s".equalsIgnoreCase(confirmar)) {
//                return true;
//            } else if ("n".equalsIgnoreCase(confirmar)) {
//                Utilidades.visualizarMensaje("Volviendo al menu...");
//                return false;
//            } else {
//                Utilidades.visualizarMensaje("ERROR! Debe pulsar S/N para continuar.");
//            }
//        } while (true);
//    }
//}
