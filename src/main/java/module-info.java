module com.example.echevarria_castillo_jose_ignacio_videojuegos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires mysql.connector.j;
    requires java.naming;

    opens com.example.echevarria_castillo_jose_ignacio_videojuegos to javafx.fxml;
    //exports com.example.echevarria_castillo_jose_ignacio_videojuegos;
    exports com.example.echevarria_castillo_jose_ignacio_videojuegos.app;
    opens com.example.echevarria_castillo_jose_ignacio_videojuegos.app to javafx.fxml;
    exports com.example.echevarria_castillo_jose_ignacio_videojuegos.controller;
    opens com.example.echevarria_castillo_jose_ignacio_videojuegos.controller to javafx.fxml;

}