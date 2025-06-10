package com.example.echevarria_castillo_jose_ignacio_videojuegos.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.echevarria_castillo_jose_ignacio_videojuegos.modelo.entities.Cliente;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.Util.HibernateUtil;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {

        welcomeText.setText("Welcome to JavaFX Application!");

        // Guardamos un nuevo cliente
        Cliente cliente = new Cliente();
        cliente.setNombreusuario("juan123");
        cliente.setContrasena("clave123");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(cliente);
            tx.commit();

            // Mostramos en el Label los datos guardados
            welcomeText.setText("Cliente guardado:\nUsuario: " + cliente.getNombreusuario());
        } catch (Exception e) {
            welcomeText.setText("Error al guardar cliente: " + e.getMessage());
            e.printStackTrace();
        }

    }
}