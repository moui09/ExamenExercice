module com.example.consultationresultats {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.examenExercice to javafx.fxml;
    exports com.example.examenExercice;
}
