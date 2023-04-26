package org.arthycode;

public interface CalleableCRUD {

    // Declaración de métodos-

    void insertarRegistro(Persona persona);
    void verRegistros();
    void eliminarRegistro(int dni);
    void editarRegistro(Persona persona);

}
