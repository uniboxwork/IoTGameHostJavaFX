module com.example.iotgamehostjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.unsupported.desktop;


    opens com.example.iotgamehostjavafx to javafx.fxml;
    exports com.example.iotgamehostjavafx;
}