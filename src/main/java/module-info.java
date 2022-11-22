module com.example.semester1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.semester1 to javafx.fxml;
    exports com.example.semester1;
}