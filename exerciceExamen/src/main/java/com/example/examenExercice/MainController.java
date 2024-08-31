package com.example.examenExercice;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainController {

    @FXML
    private TextField matriculeField;
    @FXML
    private Button consulterButton;

    private final Map<String, Etudiant> etudiants = new HashMap<>();

    public MainController() {
        // Données valides pour les tests
        etudiants.put("001", new Etudiant("001", "Touré", "Oumar", "1997-02-11", "ISTEMA", 14.5));
        etudiants.put("002", new Etudiant("002", "BOno", "Yaya", "2000-04-12", "CEFIAT", 9.5));
        etudiants.put("003", new Etudiant("003", "Tahé", "Nahé", "2001-07-11", "INPHB", 18.4));
    }

    @FXML
    private void onConsulterButtonClicked() {
        String matricule = matriculeField.getText();
        Etudiant etudiant = etudiants.get(matricule);

        if (etudiant == null) {
            showAlert("Erreur", "Le matricule n'existe pas !");
        } else {
            openResultWindow(etudiant);
        }
    }

    private void openResultWindow(Etudiant etudiant) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("result-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load(), 500, 300);
            stage.setScene(scene);

            ResultController controller = loader.getController();
            if (etudiant.getMoyenne() >= 10) {
                controller.setMessage("Félicitations, vous avez réussi votre Examen !");
                controller.setButtonVisible(true);
                controller.setButtonAction(e -> openDetailWindow(etudiant, "white"));

            } else {
                controller.setMessage("Désolé, vous avez échoué à votre Examen !");
                controller.setButtonVisible(true);
                controller.setButtonAction(e -> openDetailWindow(etudiant, "white"));
                scene.getRoot().setStyle("-fx-background-color: blue;");
            }

            stage.setTitle("Résultat");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDetailWindow(Etudiant etudiant, String color) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("details-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load(), 400, 300); // Taille agrandie
            stage.setScene(scene);

            DetailController controller = loader.getController();
            controller.setEtudiant(etudiant);
            scene.getRoot().setStyle("-fx-background-color: " + color + ";");

            stage.setTitle("Détails de l'Étudiant");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
