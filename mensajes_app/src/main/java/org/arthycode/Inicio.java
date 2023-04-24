package org.arthycode;

import java.sql.Connection;
import java.util.Scanner;

public class Inicio {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int op = 0;

        do {
            System.out.println(":: Menú de opciones: " + "\n1: Crear mensaje..." + "\n2: Listar mensajes..." +
                    "\n3: Borrar mensaje..." + "\n4: Editar mensaje..." + "\n5: Salir...");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    MensajesService.crearMensaje();
                    break;
                case 2:
                    MensajesService.listarMensajes();
                    break;
                case 3:
                    MensajesService.borrarMensajes();
                    break;
                case 4:
                    MensajesService.editarMensaje();
                    break;
                default:
            }
        } while(op != 5);

    }
}
