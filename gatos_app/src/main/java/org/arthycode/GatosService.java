package org.arthycode;

import com.google.gson.Gson;
import com.squareup.okhttp.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class GatosService {

    public static void verGatos() throws IOException {
        // 1. Vamos a traer los datos d ela API

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.thecatapi.com/v1/images/search")
                .method("GET", null).build();
        Response response = client.newCall(request).execute();

        String elJson = response.body().string();

        // Cortar los corchetes
        elJson = elJson.substring(1, elJson.length());
        elJson = elJson.substring(0, elJson.length() -1);

        // Creamos un objeto de la clase Gson
        Gson gson = new Gson();
        Gatos gatos = gson.fromJson(elJson, Gatos.class);

        // Redimensionar imagen
        Image image = null;
        try {
            URL url = new URL(gatos.getUrl());
            image = ImageIO.read(url);
            ImageIcon fondoGato = new ImageIcon(image);

            if (fondoGato.getIconWidth() > 800) {
                // Redimensionamos
                Image fondo = fondoGato.getImage();
                Image modificada = fondo.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                fondoGato =  new ImageIcon(modificada);
            }

            String menu = "Opciones: \n"
                    + " 1. Ver otra imagen \n"
                    + " 2. Marcar el gato como favorito \n"
                    + " 3. Volver \n";
            String[] botones = {"ver otra imagen", "favorito", "volver"};
            String id_gato = gatos.getId();
            String opcion = (String) (String) JOptionPane.showInputDialog(null, menu,
                    id_gato, JOptionPane.INFORMATION_MESSAGE, fondoGato, botones, botones[0]);

            int seleccion =  -1;
            for (int i = 0; i < botones.length; i++) {
                if (opcion.equals(botones[i])) {
                    seleccion = i;
                }
            }
            switch (seleccion) {
                case 0:
                    verGatos();
                    break;
                case 1:
                    favoritoGato(gatos);
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void favoritoGato(Gatos gato) {
        try {
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\r\n    \"image_id\":\"" + gato.getId() +"\"\r\n}");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", gato.getApikey())
                    .build();
            Response response = client.newCall(request).execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void verFavoritos(String apyKey) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/favourites")
                .get()
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", apyKey)
                .build();
        Response response = client.newCall(request).execute();
        String elJson = response.body().string();

        // Creamos el objeto gson
        Gson gson = new Gson();
        GatosFav[] gatosArray = gson.fromJson(elJson, GatosFav[].class);

        if (gatosArray.length > 0) {
            int min = 1;
            int max = gatosArray.length;
            int aleatorio = (int) (Math.random() * ((max - min) + 1 )) + min;
            int indice  = aleatorio - 1;

            GatosFav gatoFav = gatosArray[indice];

                    // Redimensionar imagen
                    Image image = null;
                    try {
                        URL url = new URL(gatoFav.image.getUrl());
                        image = ImageIO.read(url);
                        ImageIcon fondoGato = new ImageIcon(image);

                        if (fondoGato.getIconWidth() > 800) {
                            // Redimensionamos
                            Image fondo = fondoGato.getImage();
                            Image modificada = fondo.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                            fondoGato =  new ImageIcon(modificada);
                        }

                        String menu = "Opciones: \n"
                                + " 1. Ver otra imagen \n"
                                + " 2. Eliminar favorito \n"
                                + " 3. Volver \n";
                        String[] botones = {"ver otra imagen", "eliminar favorito", "volver"};
                        String id_gato = gatoFav.getId();
                        String opcion = (String) (String) JOptionPane.showInputDialog(null, menu,
                                id_gato, JOptionPane.INFORMATION_MESSAGE, fondoGato, botones, botones[0]);

                        int seleccion =  -1;
                        for (int i = 0; i < botones.length; i++) {
                            if (opcion.equals(botones[i])) {
                                seleccion = i;
                            }
                        }
                        switch (seleccion) {
                            case 0:
                                verFavoritos(apyKey);
                                break;
                            case 1:
                                borrarFavorito(gatoFav);
                                break;
                            default:
                                break;
                        }
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
        }
    }

    public static void borrarFavorito(GatosFav gatoFav) {
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites/" + gatoFav.getId())
                    .delete(null)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", gatoFav.getApiKey())
                    .build();
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
