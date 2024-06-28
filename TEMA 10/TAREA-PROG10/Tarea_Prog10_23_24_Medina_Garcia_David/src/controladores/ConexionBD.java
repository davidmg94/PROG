package controladores;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.*;

/**
 * Clase para manejar la conexión y operaciones con la base de datos.
 *
 * @author David Medina Garcia
 */
public class ConexionBD {

    private Connection conexion = null;

    /**
     * Constructor para establecer la conexión con la base de datos.
     *
     * @param sgbd
     * @param host
     * @param bd
     * @param usuario
     * @param clave
     */
    public ConexionBD(String sgbd, String host, String bd, String usuario, String clave) {
        try {
            // Establecer la conexión al crear una instancia de la clase
            setConexion(conectar(sgbd, host, bd, usuario, clave));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Método privado para establecer la conexión con la base de datos.
     *
     * @param sgbd
     * @param host
     * @param bd
     * @param usuario
     * @param clave
     * @return
     */
    private Connection conectar(String sgbd, String host, String bd, String usuario, String clave) throws ClassNotFoundException {
        try {
            switch (sgbd.toLowerCase()) {
                case "mysql":
                    // Conexión para MySQL
                    conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + bd, usuario, clave);
                    break;
                case "oracle":
                    // Conexión para Oracle
                    conexion = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":1521/" + bd, usuario, clave);
                    break;
            }
            System.out.println("Conexion Realizada");
            return conexion;
        } catch (SQLException e) {
            System.out.println("Error en la Conexión: " + e.getMessage());
            return null;
        }

    }

    /**
     * Obtiene los datos de un mueble asociado a una venta.
     *
     * @param idVenta
     * @return
     */
    private Mueble obtenerMuebleDesdeIdVenta(int idVenta) {
        try {
            // Construir la consulta SQL para obtener los datos del mueble asociado a la venta
            String query = "SELECT M.* FROM MUEBLES_OBJ M JOIN VENTAS_OBJ V ON M.IDMUEBLE = V.MUEBLE.IDMUEBLE WHERE V.IDVENTA=?";
            PreparedStatement pstmt = conexion.prepareStatement(query);
            pstmt.setInt(1, idVenta);

            // Ejecutar la consulta preparada
            try (ResultSet result = pstmt.executeQuery()) {
                if (result.next()) {
                    // Crear un objeto Mueble y asignarle los valores obtenidos de la consulta
                    Mueble mueble = new Mueble();
                    mueble.setIdMueble(result.getInt(1));
                    mueble.setDescripMueble(result.getString(2));
                    mueble.setPrecioUnitario(result.getFloat(3));
                    mueble.setUnidadesExistentes(result.getInt(4));
                    return mueble;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Lee una venta de la base de datos utilizando su ID.
     *
     * @param idVenta
     * @return
     */
    public Venta leerVenta(int idVenta) {
        Venta v = null;
        ResultSet result;
        try {
            // Construir la consulta SQL para obtener los datos de la venta
            String query = "SELECT * FROM VENTAS_OBJ WHERE IDVENTA=?";
            PreparedStatement pstmt = conexion.prepareStatement(query);
            pstmt.setInt(1, idVenta);

            // Ejecutar la consulta preparada
            result = pstmt.executeQuery();

            if (result.next()) {
                // Crear un objeto Venta y asignarle los valores obtenidos de la consulta
                v = new Venta();
                v.setIdVenta(result.getInt(1));
                v.setNombreCliente(result.getString(2));
                v.setUnidadesVendidas(result.getInt(3));
                // Obtener el mueble asociado a la venta
                Mueble mueble = obtenerMuebleDesdeIdVenta(idVenta);
                v.setMueble(mueble);
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return v;
    }

    /**
     * Lee un mueble de la base de datos utilizando su ID.
     *
     * @param idMueble
     * @return
     */
    public Mueble leerMueble(int idMueble) {
        Mueble m = null;
        ResultSet result;
        try {
            // Construir la consulta SQL para obtener los datos del mueble
            String query = "SELECT * FROM MUEBLES_OBJ WHERE IDMUEBLE=?";
            PreparedStatement pstmt = conexion.prepareStatement(query);
            pstmt.setInt(1, idMueble);

            // Ejecutar la consulta preparada
            result = pstmt.executeQuery();

            if (result.next()) {
                // Crear un objeto Mueble y asignarle los valores obtenidos de la consulta
                m = new Mueble();
                m.setIdMueble(result.getInt(1));
                m.setDescripMueble(result.getString(2));
                m.setPrecioUnitario(result.getFloat(3));
                m.setUnidadesExistentes(result.getInt(4));
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return m;
    }

    /**
     * Lee las unidades existentes de un mueble en la base de datos utilizando
     * su ID.
     *
     * @param idMueble
     * @return
     */
    public int leerUnidadesExistentes(int idMueble) {
        int unidadesExist = 0;
        ResultSet result;
        try {
            // Construir la consulta SQL para obtener las unidades existentes del mueble
            String query = "SELECT UNIDADESEXISTENTES FROM MUEBLES_OBJ WHERE IDMUEBLE=?";
            PreparedStatement pstmt = conexion.prepareStatement(query);
            pstmt.setInt(1, idMueble);

            // Ejecutar la consulta preparada
            result = pstmt.executeQuery();

            if (result.next()) {
                unidadesExist = result.getInt(1);
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return unidadesExist;
    }

    /**
     * Inserta una nueva venta en la base de datos.
     *
     * @param v
     */
    public void insertarVenta(Venta v) {
        try {
            // Construir la consulta SQL para insertar la venta
            String sql = "INSERT INTO VENTAS_OBJ SELECT ?, ?, ?, REF(M) FROM MUEBLES_OBJ M WHERE M.IDMUEBLE = ?";
            PreparedStatement pstmt = conexion.prepareStatement(sql);

            // Establecer los valores para los parámetros de la consulta preparada
            pstmt.setInt(1, v.getIdVenta());
            pstmt.setString(2, v.getNombreCliente());
            pstmt.setInt(3, v.getUnidadesVendidas());
            pstmt.setInt(4, v.getMueble().getIdMueble());

            // Ejecutar la consulta preparada
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Inserta un nuevo mueble en la base de datos.
     *
     * @param m
     */
    public void insertarMueble(Mueble m) {
        String sql = "INSERT INTO MUEBLES_OBJ VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, m.getIdMueble());
            pstmt.setString(2, m.getDescripMueble());
            pstmt.setFloat(3, m.getPrecioUnitario());
            pstmt.setInt(4, m.getUnidadesExistentes());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Actualiza las unidades existentes de un mueble al registrar una nueva
     * venta.
     *
     * @param unidadesVend
     * @param idMueble
     */
    public void actualizarUnidadesExistentesDeVentaNueva(int unidadesVend, int idMueble) {
        String sql = "UPDATE MUEBLES_OBJ SET UNIDADESEXISTENTES = UNIDADESEXISTENTES - ? WHERE IDMUEBLE = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, unidadesVend);
            pstmt.setInt(2, idMueble);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Actualiza las unidades existentes de un mueble al modificar una venta
     * registrada.
     *
     * @param unidadesVend
     * @param idMueble
     * @param idVenta
     */
    public void actualizarUnidadesExistentesDeVentaRegistradaMismoMueble(int unidadesVend, int idMueble, int idVenta) {
        Venta venta = leerVenta(idVenta);
        int unidadesVendidasActuales = venta.getUnidadesVendidas();

        if (unidadesVend > unidadesVendidasActuales) {
            String sql = "UPDATE MUEBLES_OBJ SET UNIDADESEXISTENTES = UNIDADESEXISTENTES - ? WHERE IDMUEBLE = ?";
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setInt(1, unidadesVend - unidadesVendidasActuales);
                pstmt.setInt(2, idMueble);
                pstmt.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (unidadesVend < unidadesVendidasActuales) {
            String sql = "UPDATE MUEBLES_OBJ SET UNIDADESEXISTENTES = UNIDADESEXISTENTES + ? WHERE IDMUEBLE = ?";
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setInt(1, unidadesVendidasActuales - unidadesVend);
                pstmt.setInt(2, idMueble);
                pstmt.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Actualiza las unidades existentes de dos muebles distintos después de
     * realizar una venta. Resta las unidades vendidas del nuevo mueble y suma
     * esas unidades al mueble anterior.
     *
     * @param unidadesVend La cantidad de unidades vendidas.
     * @param unidadesVendAnterior La cantidad de unidades vendidas del mueble
     * anterior
     * @param idMueble El ID del nuevo mueble vendido.
     * @param idMubleAnterior El ID del mueble anterior que se reemplaza.
     * @param idVenta El ID de la venta realizada.
     */
    public void actualizarUnidadesExistentesDeVentaRegistradaDistintoMueble(int unidadesVend, int unidadesVendAnterior, int idMueble, int idMubleAnterior, int idVenta) {
        String sql = "UPDATE MUEBLES_OBJ SET UNIDADESEXISTENTES = UNIDADESEXISTENTES - ? WHERE IDMUEBLE = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, unidadesVend);
            pstmt.setInt(2, idMueble);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        String sql2 = "UPDATE MUEBLES_OBJ SET UNIDADESEXISTENTES = UNIDADESEXISTENTES + ? WHERE IDMUEBLE = ?";
        try (PreparedStatement pstmt2 = conexion.prepareStatement(sql2)) {
            pstmt2.setInt(1, unidadesVendAnterior);
            pstmt2.setInt(2, idMubleAnterior);
            pstmt2.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Actualiza la información de un mueble en la base de datos.
     *
     * @param idMueble
     * @param descrip
     * @param precio
     * @param unidadesExist
     */
    public void modificarMueble(int idMueble, String descrip, float precio, int unidadesExist) {
        String sql = "UPDATE MUEBLES_OBJ SET DESCRIPMUEBLE = ?, PRECIOUNITARIO = ?, UNIDADESEXISTENTES = ? WHERE IDMUEBLE = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, descrip);
            pstmt.setFloat(2, precio);
            pstmt.setInt(3, unidadesExist);
            pstmt.setInt(4, idMueble);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Modifica la información de una venta en la base de datos.
     *
     * @param idVenta
     * @param nombreCliente
     * @param unidadesVend
     * @param v
     */
    public void modificarVenta(int idVenta, String nombreCliente, int unidadesVend, Venta v) {
        String sql = "UPDATE VENTAS_OBJ SET NOMBRECLI = ?, UNIDADESVEND = ?, MUEBLE = (SELECT REF(M) FROM MUEBLES_OBJ M WHERE M.IDMUEBLE = ?) WHERE IDVENTA = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nombreCliente);
            pstmt.setInt(2, unidadesVend);
            pstmt.setInt(3, v.getMueble().getIdMueble());
            pstmt.setInt(4, idVenta);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Elimina una venta de la base de datos utilizando su ID.
     *
     * @param idVenta
     * @param idMuebleVenta
     * @param unidadesVend
     */
    public void eliminarVenta(int idVenta, int idMuebleVenta, int unidadesVend) {
        String sqlDelete = "DELETE FROM VENTAS_OBJ WHERE IDVENTA = ?";
        try (PreparedStatement pstmtDelete = conexion.prepareStatement(sqlDelete)) {
            pstmtDelete.setInt(1, idVenta);
            pstmtDelete.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        String sqlUpdate = "UPDATE MUEBLES_OBJ SET UNIDADESEXISTENTES = UNIDADESEXISTENTES + ? WHERE IDMUEBLE = ?";
        try (PreparedStatement pstmtUpdate = conexion.prepareStatement(sqlUpdate)) {
            pstmtUpdate.setInt(1, unidadesVend);
            pstmtUpdate.setInt(2, idMuebleVenta);
            pstmtUpdate.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Verifica si un mueble está asociado a alguna venta en la base de datos.
     *
     * @param idmueble
     * @return
     */
    public int muebleEnRegistroVentas(int idmueble) {
        int ventasAsociadas = 0;
        String sql = "SELECT COUNT(*) FROM VENTAS_OBJ V WHERE V.MUEBLE.IDMUEBLE = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, idmueble);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                ventasAsociadas = result.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ventasAsociadas;
    }

    /**
     * Elimina un mueble de la base de datos utilizando su ID.
     *
     * @param idMueble
     */
    public void eliminarMueble(int idMueble) {
        String sql = "DELETE FROM MUEBLES_OBJ WHERE IDMUEBLE = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, idMueble);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Método para mostrar por consola los datos de las ventas
    public void mostrarVentasConsola() {
        String sql = "SELECT V.IDVENTA, V.NOMBRECLI, M.DESCRIPMUEBLE, M.PRECIOUNITARIO, V.UNIDADESVEND, (M.PRECIOUNITARIO * V.UNIDADESVEND) AS \"TOTAL VENTA\"\n"
                + "FROM VENTAS_OBJ V\n"
                + "JOIN MUEBLES_OBJ M ON M.IDMUEBLE =  V.MUEBLE.IDMUEBLE ORDER BY V.IDVENTA ASC";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql); ResultSet result = pstmt.executeQuery()) {

            // Imprimir encabezado
            System.out.println("ID. VENTA\tNOMBRE CLIENTE\tDESCRIP. MUEBLE\tPRECIO($)\tUNIDADES VEND.\tTOTAL VENTA($)");
            System.out.println("=======\t=============\t=============\t======\t=============\t=============");

            double totalVendido = 0;

            // Recorrer los resultados y mostrar las ventas por consola
            while (result.next()) {
                int idVenta = result.getInt("IDVENTA");
                String nombreCliente = result.getString("NOMBRECLI");
                String descripcionMueble = result.getString("DESCRIPMUEBLE");
                float precioUnitario = result.getFloat("PRECIOUNITARIO");
                int unidadesVendidas = result.getInt("UNIDADESVEND");
                double totalVenta = result.getDouble("TOTAL VENTA");

                // Imprimir cada fila
                System.out.printf("%d\t%s\t\t%s\t\t%.2f\t%d\t\t%.2f\n", idVenta, nombreCliente, descripcionMueble, precioUnitario, unidadesVendidas, totalVenta);

                // Sumar al total vendido
                totalVendido += totalVenta;
            }
            String ventasFormat = String.format("%.2f", totalVendido);
            // Mostrar el total vendido
            System.out.println("\nTOTAL VENDIDO: " + ventasFormat + "$");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Llena una tabla con datos de la base de datos.
     *
     * @param jt
     */
    public void llenarTabla(JTable jt) {
        DefaultTableModel modelo = (DefaultTableModel) jt.getModel();
        String sql = "SELECT V.IDVENTA, V.NOMBRECLI, M.DESCRIPMUEBLE, M.PRECIOUNITARIO, V.UNIDADESVEND, (M.PRECIOUNITARIO * V.UNIDADESVEND) AS \"TOTAL VENTA\"\n"
                + "FROM VENTAS_OBJ V\n"
                + "JOIN MUEBLES_OBJ M ON M.IDMUEBLE =  V.MUEBLE.IDMUEBLE ORDER BY V.IDVENTA ASC";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql); ResultSet result = pstmt.executeQuery()) {

            while (result.next()) {
                Object[] fila = new Object[6];
                fila[0] = result.getInt("IDVENTA");
                fila[1] = result.getString("NOMBRECLI");
                fila[2] = result.getString("DESCRIPMUEBLE");
                fila[3] = result.getFloat("PRECIOUNITARIO");
                fila[4] = result.getInt("UNIDADESVEND");
                fila[5] = result.getDouble("TOTAL VENTA");
                modelo.addRow(fila);
            }

            result.close();
            jt.setModel(modelo);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Calcula el total vendido.
     *
     * @return
     */
    public double totalVendido() {
        double totalVendido = 0;
        String sql = "SELECT SUM(M.PRECIOUNITARIO * V.UNIDADESVEND) AS \"TOTAL VENTA\" "
                + "FROM VENTAS_OBJ V "
                + "JOIN MUEBLES_OBJ M ON M.IDMUEBLE =  V.MUEBLE.IDMUEBLE";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql); ResultSet result = pstmt.executeQuery()) {

            if (result.next()) {
                totalVendido = result.getDouble("TOTAL VENTA");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return totalVendido;
    }

    /**
     * Cierra la conexión con la base de datos.
     */
    public void cerrar() {
        try {
            // Cerrar la conexión
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
