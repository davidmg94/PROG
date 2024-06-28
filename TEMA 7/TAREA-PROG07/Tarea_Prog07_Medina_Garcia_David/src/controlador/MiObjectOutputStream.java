package controlador;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
/**
 *
 * @author DAVID MEDINA GARCIA
 */
public class MiObjectOutputStream extends ObjectOutputStream {

    // Constructor que recibe un objeto OutputStream
    public MiObjectOutputStream(OutputStream out) throws IOException {
        super(out); // Llama al constructor de la clase padre (ObjectOutputStream) con el OutputStream proporcionado
    }

    // Constructor por defecto que lanza IOException y SecurityException
    public MiObjectOutputStream() throws IOException, SecurityException {
    }

    // Método para escribir la cabecera del flujo de salida
    @Override
    protected void writeStreamHeader() throws IOException {
        reset(); // Resetea las cabeceras del flujo de salida
    }
}
