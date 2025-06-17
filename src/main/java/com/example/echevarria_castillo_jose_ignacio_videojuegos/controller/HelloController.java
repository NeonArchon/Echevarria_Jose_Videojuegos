package com.example.echevarria_castillo_jose_ignacio_videojuegos.controller;

import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO.AdministradorDAO;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO.CategoriaDAO;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO.ClienteDAO;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Administrador;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Categoria;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.hibernate.Transaction;

import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Cliente;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class HelloController {

    @FXML
    private TextField usuarioField;
    @FXML
    private PasswordField contrasenaField;
    @FXML
    private Label mensajeLabel;

    private Session session;
    private ClienteDAO clienteDAO = new ClienteDAO();
    private AdministradorDAO adminDAO = new AdministradorDAO();




    @FXML
    protected void onLoginClick() {
        String username = usuarioField.getText();
        String password = contrasenaField.getText();

        Cliente cliente = clienteDAO.loginCliente(username, password);
        if (cliente != null) {
            mensajeLabel.setText("Bienvenido Cliente: " + cliente.getNombreusuario());
            // Ir a vista de catálogo
            return;
        }


        Administrador admin = adminDAO.loginAdministrador(username, password);
        if (admin != null) {
            mensajeLabel.setText("Bienvenido Administrador: " + admin.getNombreusuario());

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/echevarria_castillo_jose_ignacio_videojuegos/admin-view.fxml"));

                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Panel de Administrador");
                stage.setScene(new Scene(root));
                stage.show();

                // Cierra la ventana actual
                Stage currentStage = (Stage) usuarioField.getScene().getWindow();
                currentStage.close();

            } catch (IOException e) {
                mensajeLabel.setText("Error al cargar la interfaz");
                // Log más detallado
                System.err.println("Error al cargar FXML: " + e.getMessage());
                e.printStackTrace();
            }
            return;
        }

        mensajeLabel.setText("Usuario o contraseña incorrectos.");

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

    @FXML
    private void onExitClick() {
        Platform.exit();
    }

}