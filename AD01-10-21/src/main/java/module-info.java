module com.example.ad011021 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ad011021 to javafx.fxml;
    exports com.example.ad011021;
}