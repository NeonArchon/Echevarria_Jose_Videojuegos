package com.example.echevarria_castillo_jose_ignacio_videojuegos.app;

import com.example.echevarria_castillo_jose_ignacio_videojuegos.Util.HibernateUtil;
import com.example.echevarria_castillo_jose_ignacio_videojuegos.controller.HelloController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // En tu método start() o donde inicializas la aplicación principal:
        Session session = HibernateUtil.getSessionFactory().openSession();


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/echevarria_castillo_jose_ignacio_videojuegos/hello-view.fxml"));
        HelloController controller = fxmlLoader.getController();

        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}