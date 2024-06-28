/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Empleado implements Serializable {

    int idEmpleado;
    String nombre;
    Float salario;
    int nroHijos;
    String tipoEmpleado; // Valores posibles  I: Ingeniero, V: Vendedor
    int turno;  // Valores posibles 1: Mañana 2: Tarde 3: Nocturno 4:Mitad

    /**
     *
     */
    public Empleado() {
    }

    /**
     *
     * @param idEmpleado
     * @param nombre
     * @param salario
     * @param nroHijos
     * @param tipoEmpleado
     * @param turno
     */
    public Empleado(int idEmpleado, String nombre, Float salario, int nroHijos, String tipoEmpleado, int turno) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.salario = salario;
        this.nroHijos = nroHijos;
        this.tipoEmpleado = tipoEmpleado;
        this.turno = turno;
    }

    /**
     *
     * @return
     */
    public int getIdEmpleado() {
        return idEmpleado;
    }

    /**
     *
     * @param idEmpleado
     */
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public Float getSalario() {
        return salario;
    }

    /**
     *
     * @param salario
     */
    public void setSalario(Float salario) {
        this.salario = salario;
    }

    /**
     *
     * @return
     */
    public int getNroHijos() {
        return nroHijos;
    }

    /**
     *
     * @param nroHijos
     */
    public void setNroHijos(int nroHijos) {
        this.nroHijos = nroHijos;
    }

    /**
     *
     * @return
     */
    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    /**
     *
     * @param tipoEmpleado
     */
    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    /**
     *
     * @return
     */
    public int getTurno() {
        return turno;
    }

    /**
     *
     * @param turno
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Empleado: idEmpleado=" + idEmpleado + ", nombre=" + nombre + ", salario=" + salario + ", nroHijos=" + nroHijos + ", tipoEmpleado=" + tipoEmpleado + ", turno=" + turno;
    }

}
