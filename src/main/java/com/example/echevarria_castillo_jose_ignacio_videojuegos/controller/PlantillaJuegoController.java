package com.example.echevarria_castillo_jose_ignacio_videojuegos.controller;

import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO.CategoriaDAO;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO.IntCategoriaDAO;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Categoria;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Juego;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.DAO.JuegoDAO;
import org.hibernate.Session;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class PlantillaJuegoController {

    @FXML private TextField tituloField;
    @FXML private TextArea descripcionField;

    @FXML private TextField precioField;
    @FXML private TextField rutaImagenField;
    @FXML private Label mensajeLabel;


    private JuegoDAO juegoDAO;
    private Juego juegoExistente = null;

    public void setSession(Session session) {
        this.juegoDAO = new JuegoDAO(session);

    }

    @FXML
    public void initialize() {
        rutaImagenField.setText("src/main/resources/Imagenes/");
    }

    @FXML
    private void onGuardarClick() {
        if (!validarCampos()) return;

            if (juegoExistente == null) {
            Juego nuevoJuego = new Juego();
            nuevoJuego.setTitulo(tituloField.getText().trim());
            nuevoJuego.setDescripcion(descripcionField.getText().trim());
            nuevoJuego.setPrecio(Double.parseDouble(precioField.getText().trim()));
            nuevoJuego.setRutaImagen(rutaImagenField.getText().trim());

            juegoDAO.guardarJuego(nuevoJuego);

                ((Stage) tituloField.getScene().getWindow()).close();

            }else {
                // Editar juego existente
                juegoExistente.setTitulo(tituloField.getText().trim());
                juegoExistente.setDescripcion(descripcionField.getText().trim());
                juegoExistente.setPrecio(Double.parseDouble(precioField.getText().trim()));
                juegoExistente.setRutaImagen(rutaImagenField.getText().trim());

                juegoDAO.actualizarJuego(juegoExistente);

            ((Stage) tituloField.getScene().getWindow()).close();


        }
    }

    private boolean validarCampos() {
        if (tituloField.getText().trim().isEmpty()) {
            mensajeLabel.setText("El título es obligatorio");
            return false;
        }
        if (descripcionField.getText().trim().isEmpty()) {
            mensajeLabel.setText("La descripción es obligatoria");
            return false;
        }
        if (precioField.getText().trim().isEmpty()) {
            mensajeLabel.setText("El precio es obligatorio");
            return false;
        }
        if (!rutaImagenField.getText().trim().startsWith("src/main/resources/Imagenes/")) {
            mensajeLabel.setText("La ruta debe comenzar con src/main/resources/Imagenes/");
            return false;
        }

        try {
            Double.parseDouble(precioField.getText().trim());
        } catch (NumberFormatException e) {
            mensajeLabel.setText("El precio debe ser un número válido");
            return false;
        }

        return true;
    }

    public void setJuegoEditar(Juego juego) {
        this.juegoExistente = juego;

        // Cargar los datos del juego en los campos
        if (juego != null) {
            tituloField.setText(juego.getTitulo());
            descripcionField.setText(juego.getDescripcion());
            precioField.setText(String.valueOf(juego.getPrecio()));
            rutaImagenField.setText(juego.getRutaImagen());
        }
    }

    @FXML
    private void onCancelarClick() {
        ((Stage) tituloField.getScene().getWindow()).close();
    }

}
