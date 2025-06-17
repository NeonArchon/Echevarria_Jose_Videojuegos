package com.example.echevarria_castillo_jose_ignacio_videojuegos.controller;

import com.example.echevarria_castillo_jose_ignacio_videojuegos.Util.HibernateUtil;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO.CategoriaDAO;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO.JuegoDAO;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Categoria;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Juego;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AdminController {

    @FXML private TableView<Juego> juegosTable;
    @FXML private TextField busquedaField;
    @FXML private Label mensajeLabel;


    private Session session;


    private JuegoDAO juegoDAO;
    private ObservableList<Juego> juegosList = FXCollections.observableArrayList();



    // Se ejecuta al cargar la vista
    @FXML
    public void initialize() {
        session = HibernateUtil.getSessionFactory().openSession();
        juegoDAO = new JuegoDAO(session);
        cargarTodosLosJuegos();

        juegosTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    // Cargar todos los juegos en la tabla
    private void cargarTodosLosJuegos() {
        try {
            List<Juego> juegos = juegoDAO.buscarJuegos();
            juegosList.setAll(juegos);
            juegosTable.setItems(juegosList);
            mensajeLabel.setText("");
        } catch (Exception e) {
            mensajeLabel.setText("Error al cargar los juegos");
            e.printStackTrace();
        }
    }

    // Buscar juegos por palabra clave
    @FXML
    private void onBuscarJuegos() {
        String keyword = busquedaField.getText().trim();
        if (keyword.isEmpty()) {
            cargarTodosLosJuegos();
        } else {
            List<Juego> resultados = juegoDAO.buscarJuego(keyword);
            juegosList.setAll(resultados);
            mensajeLabel.setText(resultados.isEmpty() ? "No se encontraron resultados" : "");
        }
    }

    // Abrir la ventana de creación de videojuegos
    @FXML
    private void onCrearVideojuego(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/example/echevarria_castillo_jose_ignacio_videojuegos/plantilla-juego-view.fxml"));
            Parent root = loader.load();

            PlantillaJuegoController controller = loader.getController();
            controller.setSession(session); // pasar la sesión a la nueva vista

            Stage stage = new Stage();
            stage.setTitle("Añadir Nuevo Juego");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            cargarTodosLosJuegos(); // refrescar la tabla tras cerrar el modal
        } catch (IOException e) {
            mensajeLabel.setText("Error al cargar el formulario de creación");
            e.printStackTrace();
        }
    }

    @FXML
    private void onEditarVideojuego(ActionEvent event) {
        Juego seleccionado = juegosTable.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mensajeLabel.setText("Por favor, selecciona un juego para editar");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/example/echevarria_castillo_jose_ignacio_videojuegos/plantilla-juego-view.fxml"));
            Parent root = loader.load();

            PlantillaJuegoController controller = loader.getController();
            controller.setSession(session);
            controller.setJuegoEditar(seleccionado);

            Stage stage = new Stage();
            stage.setTitle("Editar Juego");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            cargarTodosLosJuegos();
        } catch (IOException e) {
            mensajeLabel.setText("Error al cargar el formulario de edición");
            e.printStackTrace();
        }
    }

    // Eliminar los juegos seleccionados
    @FXML
    private void onBorrarVideojuego(ActionEvent event) {
        ObservableList<Juego> seleccionados = juegosTable.getSelectionModel().getSelectedItems();

        if (seleccionados.isEmpty()) {
            mensajeLabel.setText("Por favor, seleccione al menos un juego para eliminar");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Está seguro de eliminar los juegos seleccionados?");
        confirmacion.setContentText("Esta acción no se puede deshacer.");

        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            try {
                for (Juego juego : seleccionados) {
                    juegoDAO.borrarJuego(juego);
                }
                mensajeLabel.setText("Se eliminaron " + seleccionados.size() + " juegos correctamente");
                cargarTodosLosJuegos();
            } catch (Exception e) {
                mensajeLabel.setText("Error al eliminar los juegos");
                e.printStackTrace();
            }
        }
    }

    // Cerrar sesión y volver al login
    @FXML
    private void onCerrarSesion(ActionEvent event) {
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/example/echevarria_castillo_jose_ignacio_videojuegos/hello-view.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("Inicio de Sesión");
            loginStage.setScene(new Scene(root));
            loginStage.show();
        } catch (IOException e) {
            System.err.println("Error al cargar la pantalla de login: " + e.getMessage());
            Platform.exit(); // cerrar la aplicación si falla la carga del login
        }
    }

    // Alerta genérica reutilizable
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
