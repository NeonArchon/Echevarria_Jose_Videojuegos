package com.example.echevarria_castillo_jose_ignacio_videojuegos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class AdminController {

    @FXML
    private void onCrearVideojuego(ActionEvent event) {
        // Aquí puedes cargar una nueva vista o mostrar una alerta temporal
        mostrarAlerta("Crear Videojuego", "Aquí se podrá crear un videojuego.");
    }

    @FXML
    private void onBorrarVideojuego(ActionEvent event) {
        // Aquí puedes cargar una nueva vista o mostrar una alerta temporal
        mostrarAlerta("Borrar Videojuego", "Aquí se podrá borrar un videojuego.");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
