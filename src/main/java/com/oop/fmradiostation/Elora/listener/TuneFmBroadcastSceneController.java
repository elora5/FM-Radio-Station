package com.oop.fmradiostation.Elora.listener;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class TuneFmBroadcastSceneController
{
    @javafx.fxml.FXML
    private TextField broadcastStatusTextField;
    @javafx.fxml.FXML
    private TextField currentProgramTextField;
    @javafx.fxml.FXML
    private TextArea messageTextArea;
    @javafx.fxml.FXML
    private ComboBox<String> stationListComboBox;
    @javafx.fxml.FXML
    private TextField rjNameTextField;

    private final Map<String, BroadcastInfo> stationData = new LinkedHashMap<>();

    @javafx.fxml.FXML
    public void initialize() {
        loadDummyStationData();
        stationListComboBox.getItems().setAll(stationData.keySet());

        currentProgramTextField.setEditable(false);
        rjNameTextField.setEditable(false);
        broadcastStatusTextField.setEditable(false);
        messageTextArea.setEditable(false);

        if (!stationListComboBox.getItems().isEmpty()) {
            stationListComboBox.getSelectionModel().selectFirst();
            updateBroadcastInfo(stationListComboBox.getValue());
        }
    }

    @javafx.fxml.FXML
    public void backButtonOnClick(ActionEvent actionEvent) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/oop/fmradiostation/Elora/listener/listenerDashboardScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Listener Dashboard");
        window.setScene(scene2);
        window.show();
    }

    @javafx.fxml.FXML
    public void tuneInButtonOnClick(ActionEvent actionEvent) {
        String station = stationListComboBox.getValue();
        if (station == null || station.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Station Selected");
            alert.setHeaderText("Please select a station first");
            alert.setContentText("Choose a radio frequency from the dropdown and press Tune In.");
            alert.showAndWait();
            return;
        }

        updateBroadcastInfo(station);
    }

    private void loadDummyStationData() {
        stationData.put("89.2 FM - City Beats", new BroadcastInfo(
                "Morning Wake Up Show",
                "RJ Neha",
                "Live",
                "Good morning listeners! Today's top hits are coming up next."
        ));

        stationData.put("91.6 FM - Retro Wave", new BroadcastInfo(
                "Golden Classics",
                "RJ Sam",
                "Live",
                "Now playing timeless classics from the 80s and 90s."
        ));

        stationData.put("96.4 FM - Sports Talk", new BroadcastInfo(
                "Matchday Hotline",
                "RJ Tuhin",
                "Live",
                "Call in with your predictions for tonight's big match."
        ));

        stationData.put("102.8 FM - Night Vibes", new BroadcastInfo(
                "Chill Sessions",
                "RJ Anika",
                "Scheduled",
                "Night session starts at 9 PM with smooth acoustic tracks."
        ));
    }

    private void updateBroadcastInfo(String station) {
        BroadcastInfo info = stationData.get(station);
        if (info == null) {
            currentProgramTextField.clear();
            rjNameTextField.clear();
            broadcastStatusTextField.setText("Unavailable");
            messageTextArea.setText("No broadcast information available for the selected station.");
            return;
        }

        currentProgramTextField.setText(info.getCurrentProgram());
        rjNameTextField.setText(info.getRjName());
        broadcastStatusTextField.setText(info.getBroadcastStatus());
        messageTextArea.setText(info.getMessage());
    }
}