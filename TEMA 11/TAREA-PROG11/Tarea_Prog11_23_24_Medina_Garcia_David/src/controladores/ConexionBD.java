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
     * Lee una venta de la base de datos utilizando su ID.
     *
     * @param idVenta
     * @return
     */
    public Venta leerVenta(int idVenta) {
        Venta v = null;
        ResultSet resultado;
        try (PreparedStatement statement = conexion.prepareStatement("SELECT IDVENTA, NOMBRECLI, UNIDADESVEND, IDMUEBLE "
                + "FROM VENTAS "
                + "WHERE IDVENTA=?")) {
            statement.setInt(1, idVenta);
            resultado = statement.executeQuery();
            if (resultado.next()) {
                v = new Venta();
                v.setIdVenta(resultado.getInt(1));
                v.setNombreCliente(resultado.getString(2));
                v.setUnidadesVendidas(resultado.getInt(3));
                v.setIdMueble(resultado.getInt(4));
            }
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
        ResultSet resultado;
        try (PreparedStatement statement = conexion.prepareStatement("SELECT IDMUEBLE, DESCRIPMUEBLE, PRECIOUNITARIO, UNIDADESEXISTENTES "
                + "FROM MUEBLES "
                + "WHERE IDMUEBLE=?")) {
            statement.setInt(1, idMueble);
            resultado = statement.executeQuery();
            if (resultado.next()) {
                m = new Mueble();
                m.setIdMueble(resultado.getInt(1));
                m.setDescripMueble(resultado.getString(2));
                m.setPrecioUnitario(resultado.getFloat(3));
                m.setUnidadesExistentes(resultado.getInt(4));
            }
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
        ResultSet resultado;
        try (PreparedStatement statement = conexion.prepareStatement("SELECT UNIDADESEXISTENTES "
                + "FROM MUEBLES m "
                + "WHERE IDMUEBLE=?")) {
            statement.setInt(1, idMueble);
            resultado = statement.executeQuery();
            if (resultado.next()) {
                unidadesExist = resultado.getInt(1);
            }
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
        String sql = "INSERT INTO VENTAS (IDVENTA, NOMBRECLI, UNIDADESVEND, IDMUEBLE) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, v.getIdVenta());
            statement.setString(2, v.getNombreCliente());
            statement.setInt(3, v.getUnidadesVendidas());
            statement.setInt(4, v.getIdMueble());
            statement.executeUpdate();
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
        // Construir la consulta SQL para insertar el mueble
        String sql = "INSERT INTO MUEBLES (IDMUEBLE, DESCRIPMUEBLE, PRECIOUNITARIO, UNIDADESEXISTENTES) VALUES (?, ?, ?, ?)";
        // Ejecutar la consulta preparada
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, m.getIdMueble());
            statement.setString(2, m.getDescripMueble());
            statement.setFloat(3, m.getPrecioUnitario());
            statement.setInt(4, m.getUnidadesExistentes());
            statement.executeUpdate();
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
        // Construir la consulta SQL para actualizar las unidades existentes del mueble
        String sql = "UPDATE MUEBLES SET UNIDADESEXISTENTES = UNIDADESEXISTENTES - ? WHERE IDMUEBLE = ?";
        // Ejecutar la consulta preparada
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, unidadesVend);
            statement.setInt(2, idMueble);
            statement.executeUpdate();
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
        // Leer información de la venta
        Venta venta = leerVenta(idVenta);

        // Obtener la cantidad actual de unidades vendidas de la venta
        int unidadesVendidasActuales = venta.getUnidadesVendidas();

        // Verificar si se vendieron más unidades o menos unidades
        if (unidadesVend > unidadesVendidasActuales) {
            // Construir la consulta SQL para actualizar las unidades existentes del mueble al vender más unidades
            String sql = "UPDATE MUEBLES SET UNIDADESEXISTENTES = UNIDADESEXISTENTES - ? WHERE IDMUEBLE = ?";
            // Ejecutar la consulta preparada
            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setInt(1, unidadesVend - unidadesVendidasActuales);
                statement.setInt(2, idMueble);
                statement.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (unidadesVend < unidadesVendidasActuales) {
            // Construir la consulta SQL para actualizar las unidades existentes del mueble al vender menos unidades
            String sql = "UPDATE MUEBLES SET UNIDADESEXISTENTES = UNIDADESEXISTENTES + ? WHERE IDMUEBLE = ?";
            // Ejecutar la consulta preparada
            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setInt(1, unidadesVendidasActuales - unidadesVend);
                statement.setInt(2, idMueble);
                statement.executeUpdate();
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
        // Construir la consulta SQL para restar las unidades vendidas del nuevo mueble vendido
        String sql = "UPDATE MUEBLES SET UNIDADESEXISTENTES = UNIDADESEXISTENTES - ? WHERE IDMUEBLE = ?";
        // Ejecutar la consulta preparada para restar las unidades del nuevo mueble vendido
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, unidadesVend);
            statement.setInt(2, idMueble);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        // Construir la consulta SQL para sumar las unidades vendidas al mueble anterior
        String sql2 = "UPDATE MUEBLES SET UNIDADESEXISTENTES = UNIDADESEXISTENTES + ? WHERE IDMUEBLE = ?";
        // Ejecutar la consulta preparada para sumar las unidades al mueble anterior
        try (PreparedStatement statement = conexion.prepareStatement(sql2)) {
            statement.setInt(1, unidadesVendAnterior);
            statement.setInt(2, idMubleAnterior);
            statement.executeUpdate();
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
        // Construir la consulta SQL para actualizar el mueble
        String sql = "UPDATE MUEBLES SET DESCRIPMUEBLE=?, PRECIOUNITARIO=?, UNIDADESEXISTENTES=? WHERE IDMUEBLE=?";
        // Ejecutar la consulta preparada para modificar el mueble
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, descrip);
            statement.setFloat(2, precio);
            statement.setInt(3, unidadesExist);
            statement.setInt(4, idMueble);
            statement.executeUpdate();
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
     * @param idMueble
     */
    public void modificarVenta(int idVenta, String nombreCliente, int unidadesVend, int idMueble) {
        // Construir la consulta SQL para modificar la venta
        String sql = "UPDATE VENTAS SET NOMBRECLI=?, UNIDADESVEND=?, IDMUEBLE=? WHERE IDVENTA=?";
        // Ejecutar la consulta preparada para modificar la venta
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, nombreCliente);
            statement.setInt(2, unidadesVend);
            statement.setInt(3, idMueble);
            statement.setInt(4, idVenta);
            statement.executeUpdate();
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
        // Construir la consulta SQL para eliminar la venta
        String sqlDelete = "DELETE FROM VENTAS WHERE IDVENTA=?";
        // Ejecutar la consulta preparada para eliminar la venta de la base de datos
        try (PreparedStatement statement = conexion.prepareStatement(sqlDelete)) {
            statement.setInt(1, idVenta);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        // Construir la consulta SQL para actualizar las unidades existentes del mueble vendido
        // Al eliminar la venta, sería una cancelación o devolución del pedido,
        // por tanto hay que recuperar las unidades no vendidas
        String sqlUpdate = "UPDATE MUEBLES SET UNIDADESEXISTENTES = UNIDADESEXISTENTES + ? WHERE IDMUEBLE = ?";
        // Ejecutar la consulta preparada para actualizar las unidades existentes del mueble vendido
        try (PreparedStatement statement = conexion.prepareStatement(sqlUpdate)) {
            statement.setInt(1, unidadesVend);
            statement.setInt(2, idMuebleVenta);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Verifica si un mueble está asociado a alguna venta en la base de datos.
     *
     * @param idMueble
     * @return
     */
    public int muebleEnRegistroVentas(int idMueble) {
        int ventasAsociadas = 0;
        try (PreparedStatement statement = conexion.prepareStatement("SELECT COUNT(*) FROM VENTAS WHERE IDMUEBLE=?")) {
            statement.setInt(1, idMueble);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                ventasAsociadas = resultado.getInt(1);
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
        try (PreparedStatement statement = conexion.prepareStatement("DELETE FROM MUEBLES WHERE IDMUEBLE=?")) {
            statement.setInt(1, idMueble);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Método para mostrar por consola los datos de las ventas
    public void mostrarVentasConsola() {
        try (PreparedStatement statement = conexion.prepareStatement("SELECT V.IDVENTA, V.NOMBRECLI, M.DESCRIPMUEBLE, M.PRECIOUNITARIO, V.UNIDADESVEND, (M.PRECIOUNITARIO * V.UNIDADESVEND) AS \"TOTAL VENTA\"\n"
                + "FROM VENTAS V\n"
                + "JOIN MUEBLES M ON M.IDMUEBLE = V.IDMUEBLE ORDER BY V.IDVENTA ASC")) {
            ResultSet resultado = statement.executeQuery();
            System.out.println("ID. VENTA\tNOMBRE CLIENTE\tDESCRIP. MUEBLE\tPRECIO($)\tUNIDADES VEND.\tTOTAL VENTA($)");
            System.out.println("=======\t=============\t=============\t======\t=============\t=============");
            double totalVendido = 0;
            while (resultado.next()) {
                int idVenta = resultado.getInt("IDVENTA");
                String nombreCliente = resultado.getString("NOMBRECLI");
                String descripcionMueble = resultado.getString("DESCRIPMUEBLE");
                float precioUnitario = resultado.getFloat("PRECIOUNITARIO");
                int unidadesVendidas = resultado.getInt("UNIDADESVEND");
                double totalVenta = resultado.getDouble("TOTAL VENTA");
                System.out.printf("%d\t%s\t\t%s\t\t%.2f\t%d\t\t%.2f\n", idVenta, nombreCliente, descripcionMueble, precioUnitario, unidadesVendidas, totalVenta);
                totalVendido += totalVenta;
            }
            String ventasFormat = String.format("%.2f", totalVendido);
            System.out.println("\nTOTAL VENDIDO: " + ventasFormat + "$");
            resultado.close();
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
        try (PreparedStatement statement = conexion.prepareStatement("SELECT V.IDVENTA, V.NOMBRECLI, M.DESCRIPMUEBLE, M.PRECIOUNITARIO, V.UNIDADESVEND, (M.PRECIOUNITARIO * V.UNIDADESVEND) AS \"TOTAL VENTA\"\n"
                + "FROM VENTAS V\n"
                + "JOIN MUEBLES M ON M.IDMUEBLE = V.IDMUEBLE ORDER BY V.IDVENTA ASC")) {
            ResultSet resultado = statement.executeQuery();
            Object[] fila = new Object[6];
            while (resultado.next()) {
                fila[0] = resultado.getInt("IDVENTA");
                fila[1] = resultado.getString("NOMBRECLI");
                fila[2] = resultado.getString("DESCRIPMUEBLE");
                fila[3] = resultado.getFloat("PRECIOUNITARIO");
                fila[4] = resultado.getInt("UNIDADESVEND");
                fila[5] = resultado.getDouble("TOTAL VENTA");
                modelo.addRow(fila);
            }
            resultado.close();
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
        try (PreparedStatement statement = conexion.prepareStatement("SELECT SUM(M.PRECIOUNITARIO * V.UNIDADESVEND) AS \"TOTAL VENTA\" "
                + "FROM VENTAS V "
                + "JOIN MUEBLES M ON M.IDMUEBLE = V.IDMUEBLE")) {
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                totalVendido = resultado.getDouble("TOTAL VENTA");
            }
            resultado.close();
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
