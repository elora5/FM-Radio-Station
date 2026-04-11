package com.oop.fmradiostation.Das.studio_coordinator;

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

public class StudioCoordinatorDashboardController {

    // This matches the fx:id in the center of your BorderPane
    @FXML
    private AnchorPane contentArea;

    // --- GOAL NAVIGATION METHODS ---

    @FXML
    public void handleAllocateStudio(ActionEvent event) {
        loadSubScene("AllocateStudio.fxml");
    }

    @FXML
    public void handleManageConflicts(ActionEvent event) {
        loadSubScene("ManageConflicts.fxml");
    }

    @FXML
    public void handleTechCoordination(ActionEvent event) {
        loadSubScene("TechCoordinationSC.fxml");
    }

    @FXML
    public void handleStaffAvailability(ActionEvent event) {
        loadSubScene("StaffAvailability.fxml");
    }

    @FXML
    public void handleProgramDelays(ActionEvent event) {
        loadSubScene("ManageDelays.fxml");
    }

    @FXML
    public void handleStudioTransition(ActionEvent event) {
        loadSubScene("StudioTransition.fxml");
    }

    @FXML
    public void handleLogIssues(ActionEvent event) {
        loadSubScene("LogStudioIssues.fxml");
    }

    @FXML
    public void handleCloseStudio(ActionEvent event) {
        loadSubScene("CloseStudio.fxml");
    }

    // --- HELPER METHODS ---

    /**
     * This method dynamically clears the center AnchorPane and loads a new FXML inside it.
     */
    private void loadSubScene(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            AnchorPane pane = loader.load();

            contentArea.getChildren().clear();
            contentArea.getChildren().add(pane);

        } catch (IOException e) {
            showAlert("Work in Progress", "The screen for " + fxmlFileName + " has not been created yet.");
        }
    }

    @FXML
    public void handleLogout(ActionEvent event) {
        try {
            // Modify this path to point exactly to where your group's Login FXML is located
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
