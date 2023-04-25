package org.arthycode;

import java.util.Scanner;

public class MensajesService {


    public static void crearMensaje() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe tu mensaje: ");
        String mensaje = sc.nextLine();
        System.out.println("Tu nombre: ");
        String autor = sc.nextLine();

        Mensajes mssg = new Mensajes();
        mssg.setMensaje(mensaje);
        mssg.setAutor_mensaje(autor);
        MensajesDAO.crearMensajeDB(mssg);
    }

    public static void listarMensajes() {
        MensajesDAO.leerMensajesDB();
    }

    public static void borrarMensajes() {
        int id;
        Scanner sc = new Scanner(System.in);
        System.out.println("Digita el id del mensaje que quieres borrar...");
        id = sc.nextInt();
        MensajesDAO.borrarMensajes(id);
    }

    public static void editarMensaje() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe tu nuevo mensaje.");
        String mssg = sc.nextLine();

        System.out.println("Indica el id del mensaje a editar.");
        int id = sc.nextInt();

        Mensajes actualizar = new Mensajes();
        actualizar.setId_mensaje(id);
        actualizar.setMensaje(mssg);
        MensajesDAO.actualizarMensajesDB(actualizar);
    }
}
