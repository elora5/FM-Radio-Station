package com.oop.fmradiostation.Das.sound_engineer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SoundEngineerDashboardController {

    // This matches the fx:id in the center of your BorderPane
    @FXML
    private AnchorPane contentArea;

    // --- GOAL NAVIGATION METHODS ---

    @FXML
    public void handleEquipmentTest(ActionEvent event) {
        loadSubScene("EquipmentTest.fxml");
    }

    @FXML
    public void handleUploadAudio(ActionEvent event) {
        loadSubScene("UploadAudio.fxml");
    }

    @FXML
    public void handleTechCoordination(ActionEvent event) {
        loadSubScene("TechCoordination.fxml");
    }

    @FXML
    public void handleBroadcastLevels(ActionEvent event) {
        loadSubScene("BroadcastLevels.fxml");
    }

    @FXML
    public void handleAudioIssues(ActionEvent event) {
        loadSubScene("ResolveAudioIssues.fxml");
    }

    @FXML
    public void handleEquipmentCheckout(ActionEvent event) {
        loadSubScene("EquipmentCheckout.fxml");
    }

    @FXML
    public void handleArchiveAudio(ActionEvent event) {
        loadSubScene("ArchiveAudio.fxml");
    }

    @FXML
    public void handleMaintenanceLog(ActionEvent event) {
        loadSubScene("MaintenanceLog.fxml");
    }

    // --- HELPER METHODS ---

    /**
     * This method dynamically clears the center AnchorPane and loads a new FXML inside it.
     */
    private void loadSubScene(String fxmlFileName) {
        try {
            // Load the specific FXML file for the goal
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            AnchorPane pane = loader.load();

            // Clear the old screen and set the new one
            contentArea.getChildren().clear();
            contentArea.getChildren().add(pane);

        } catch (IOException e) {
            // Since you haven't created these sub-FXML files yet, it will throw an error.
            // This alert lets you know it works, but the file is missing!
            showAlert("Work in Progress", "The screen for " + fxmlFileName + " has not been created yet.");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleLogout(ActionEvent event) {
        try {
            // Assuming your main login screen is called LoginScene.fxml and is in the root directory
            Parent root = FXMLLoader.load(getClass().getResource("/com/oop/fmradiostation/LoginScene.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Error", "Could not load the Login screen.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
