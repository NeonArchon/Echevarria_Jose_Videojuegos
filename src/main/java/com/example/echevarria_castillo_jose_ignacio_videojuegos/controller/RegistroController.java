package com.example.echevarria_castillo_jose_ignacio_videojuegos.controller;

import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO.AdministradorDAO;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO.ClienteDAO;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Administrador;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Cliente;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;


public class RegistroController {

    @FXML private TextField nombreField;
    @FXML private PasswordField contrasenaField;
    @FXML private TextField emailField;
    @FXML private ComboBox<String> tipoUsuarioCombo;
    @FXML private Label mensajeLabel;

    private ClienteDAO clienteDAO = new ClienteDAO();
    private AdministradorDAO adminDAO = new AdministradorDAO();

    @FXML
    private void onGuardarClick() {

        String nombre = nombreField.getText();
        String contrasena = contrasenaField.getText();
        String email = emailField.getText();
        String tipo = tipoUsuarioCombo.getValue();


        if (nombre.isEmpty() || contrasena.isEmpty() || email.isEmpty() || tipo == null) {
            mensajeLabel.setText("Debe rellenar todos los campos.");
            return;
        }

        if (tipo.equals("Cliente")) {
            Cliente cliente = new Cliente();
            cliente.setNombreusuario(nombre);
            cliente.setContrasena(contrasena);
            cliente.setEmail(email);
            clienteDAO.guardarCliente(cliente);
            mensajeLabel.setText("Registrado como Cliente");
        } else if (tipo.equals("Administrador")) {
            Administrador admin = new Administrador();
            admin.setNombreusuario(nombre);
            admin.setContrasena(contrasena);
            admin.setEmail(email);
            adminDAO.guardarAdministrador(admin);
            mensajeLabel.setText("Registrado como Administrador");
        }

        // Esperar un momento y cerrar la ventana
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> ((Stage) mensajeLabel.getScene().getWindow()).close());
        delay.play();

    }


}
