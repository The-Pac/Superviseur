module com.example.superviseur {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.superviseur to javafx.fxml;
    exports com.example.superviseur;
}