package org.arthycode;

import java.util.Scanner;

public class MenuOperaciones {

    // Creamos un  objeto de tipo CalleableCRUD.
    public static CalleableCRUD calleableCRUD = new BdCRUD();
    public static void llamarMenu() {
        int op = 0;
        Scanner sc = new Scanner(System.in);

        while (op != 5) {
            System.out.println(":: Operaciones que se pueden hacer ::");
            System.out.println("1: Registrar persona..." + "\n2: Ver registros..." + "\n3: Eliminar registro..." +
                    "\n4: Actualizar registro..." + "\n5: Salir...");
            op = sc.nextInt();
            switch (op) {
                case 1:
                    System.out.println(":: Registrar persona...");
                    System.out.println("Escribe el DNI...");
                    String dni = sc.nextLine();
                    dni = sc.nextLine();
                    System.out.println("Escribe el nombre...");
                    String nombre = sc.nextLine();
                    System.out.println("Escribe la dirección...");
                    String direccion = sc.nextLine();
                    System.out.println("Escriba el telefono...");
                    int telefono = sc.nextInt();
                    calleableCRUD.insertarRegistro(new Persona(dni, nombre, direccion, telefono));
                    break;
                case 2:
                    System.out.println(":: Ver registros ::");
                    calleableCRUD.verRegistros();
                    break;
                case 3:
                    System.out.println(":: Borrar un registro ::");
                    System.out.println("Escribe el DNI del registro que quieres eliminar.");
                    String d = sc.nextLine();
                    d = sc.nextLine();
                    calleableCRUD.eliminarRegistro(d);
                    break;
                case 4:
                    System.out.println(":: Actualizar un registro :: ");
                    System.out.println("Escribe el nombre...");
                    String nombreNuevo = sc.nextLine();
                    nombreNuevo = sc.nextLine();
                    System.out.println("Escribe la dirección...");
                    String direccionNueva = sc.nextLine();
                    System.out.println("Escriba el telefono...");
                    int telefonoNuevo = sc.nextInt();
                    calleableCRUD.editarRegistro(new Persona(nombreNuevo, direccionNueva, telefonoNuevo));
                    break;
                default:
            }
        }
    }
}
