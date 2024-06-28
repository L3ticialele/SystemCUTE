module com.aerospace.gui3d {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.aerospace.gui3d to javafx.fxml;
    opens com.aerospace.gui3d.controllers to javafx.fxml; // Adicionando o pacote controllers

    exports com.aerospace.gui3d;
}
