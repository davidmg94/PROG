package app;

import java.util.Scanner;
import modelos.Vehiculo;

/**
 *
 * @author DAVID MEDINA GARCIA
 */
public class GestionVehiculos {

    static Scanner sn = new Scanner(System.in);

    public static void main(String[] args) {
        //declaracion de variables
        String matriculaV;
        String dniV;
        String nombreV;
        String apellidosV;

        //Recoger datos por teclado
        System.out.println("Introduzca los datos del Vehículo Tercero");
        System.out.println("//-------------------//");
        System.out.println("Introduzca la matrícula del vehículo");
        matriculaV = sn.nextLine();
        System.out.println("Introduzca el dni del titular");
        dniV = sn.nextLine();
        System.out.println("Introduzca el nombre del titular");
        nombreV = sn.nextLine();
        System.out.println("Introduzca los apellidos del titular");
        apellidosV = sn.nextLine();

        //Declaracion de objetos con didstintos constructores
        Vehiculo vehiculoPrimero = new Vehiculo();
        Vehiculo vehiculoSegundo = new Vehiculo("1111-ABC", "12345678B", "María", "Mora Gutiérrez", "Rojo", 't', 0, 80);
        Vehiculo vehiculoTercero = new Vehiculo(matriculaV, dniV, nombreV, apellidosV);

        //Recoger datos por teclado
        System.out.println("\nIntroduzca los datos del Vehículo Primero");
        System.out.println("//-------------------//");
        System.out.println("Introduzca la matrícula del vehículo");
        matriculaV = sn.nextLine();
        System.out.println("Introduzca el dni del titular");
        dniV = sn.nextLine();
        System.out.println("Introduzca el nombre del titular");
        nombreV = sn.nextLine();
        System.out.println("Introduzca los apellidos del titular");
        apellidosV = sn.nextLine();

        //Asignar los nuevos valores al objeto vehiculoPrimero
        vehiculoPrimero.setMatricula(matriculaV);
        vehiculoPrimero.setDni(dniV);
        vehiculoPrimero.setNombre(nombreV);
        vehiculoPrimero.setApellidos(apellidosV);

        //Llamada al metodo para sumar accidentes
        vehiculoSegundo.sumarAccidentes(2);
        vehiculoTercero.sumarAccidentes(1);

        //Variables que almacenan el precio total de los seguros
        double totalSeguroVehiculoPrimero = vehiculoPrimero.getTotalSeguro();
        double totalSeguroVehiculoSegundo = vehiculoSegundo.getTotalSeguro();
        double totalSeguroVehiculoTercero = vehiculoTercero.getTotalSeguro();

        //Mostramos los datos por consola
        System.out.println("");
        System.out.println(vehiculoPrimero.toString() + " Precio total del seguro: " + totalSeguroVehiculoPrimero);
        System.out.println(vehiculoSegundo.toString() + " Precio total del seguro: " + totalSeguroVehiculoSegundo);
        System.out.println(vehiculoTercero.toString() + " Precio total del seguro: " + totalSeguroVehiculoTercero);

        //cerramos el Scanner
        sn.close();
    }

}
