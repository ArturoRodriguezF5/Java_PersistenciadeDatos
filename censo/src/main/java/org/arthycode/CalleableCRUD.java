package org.arthycode;

public interface CalleableCRUD {

    // Declaración de métodos-

    void insertarRegistro(Persona persona);
    void verRegistros();
    void eliminarRegistro(String dni);
    void editarRegistro(Persona persona);

}
