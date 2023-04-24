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
    }

    public static void editarMensaje() {
    }
}
