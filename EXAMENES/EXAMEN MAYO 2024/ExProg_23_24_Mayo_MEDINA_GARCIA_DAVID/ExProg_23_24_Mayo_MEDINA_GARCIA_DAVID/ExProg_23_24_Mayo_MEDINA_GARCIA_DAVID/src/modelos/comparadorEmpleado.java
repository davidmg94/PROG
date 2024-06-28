/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.Comparator;

/**
 *
 * @author Isabel
 *
 * Clase para poder comparar y ordenar las ventas por cliente
 */
public class comparadorEmpleado implements Comparator<Empleado> {

    @Override
    public int compare(Empleado e1, Empleado e2) {
        return e1.nombre.compareTo(e2.nombre);
    }
}
