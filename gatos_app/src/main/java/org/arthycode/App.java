package org.arthycode;

import javax.swing.*;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {

        int opcion_menu = -1;
        String[] botones = {" 1. Ver gatos", "2. Ver favoritos", "3. Salir"};

        do {
            String opcion = (String) JOptionPane.showInputDialog(null, "Gatitos Java",
                    "Menu principal", JOptionPane.INFORMATION_MESSAGE, null, botones, botones[0]);
            // Validamos que opcion selecciona el usuario
            for (int i = 0; i < botones.length; i++) {
                if (opcion.equals(botones[i])) {
                    opcion_menu = i;
                }
            }

            switch (opcion_menu) {
                case 0:
                    GatosService.verGatos();
                    break;
                case 1:
                    Gatos gato = new Gatos();
                    GatosService.verFavoritos(gato.getApikey());
                default:
                    break;
            }
        } while (opcion_menu != 1);
    }
}
