module com.example.superviseur {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires java.json;


    opens com.example.superviseur to javafx.fxml;
    exports com.example.superviseur;
}