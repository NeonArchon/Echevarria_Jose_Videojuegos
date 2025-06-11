package com.example.echevarria_castillo_jose_ignacio_videojuegos.controller;

import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO.AdministradorDAO;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO.ClienteDAO;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Administrador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Cliente;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.Util.HibernateUtil;

import java.io.IOException;

public class HelloController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;

    private ClienteDAO clienteDAO = new ClienteDAO();
    private AdministradorDAO adminDAO = new AdministradorDAO();

    @FXML
    protected void onLoginClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        Cliente cliente = clienteDAO.loginCliente(username, password);
        if (cliente != null) {
            messageLabel.setText("Bienvenido Cliente: " + cliente.getNombreusuario());
            // Ir a vista de catálogo
            return;
        }

        Administrador admin = adminDAO.loginAdministrador(username, password);
        if (admin != null) {
            messageLabel.setText("Bienvenido Administrador: " + admin.getNombreusuario());
            // Ir a vista de gestión admin
            return;
        }

        messageLabel.setText("Usuario o contraseña incorrectos.");

    }

    @FXML
    private void onRegistroClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/echevarria_castillo_jose_ignacio_videojuegos/registro-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Registro de Usuario");
        stage.setScene(scene);
        stage.show();
    }



}