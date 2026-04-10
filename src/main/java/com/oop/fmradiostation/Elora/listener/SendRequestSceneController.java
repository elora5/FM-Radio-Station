package com.oop.fmradiostation.Elora.listener;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SendRequestSceneController
{
    @javafx.fxml.FXML
    private TextField listenerNameTextField;
    @javafx.fxml.FXML
    private Label requestSongTextArea;
    @javafx.fxml.FXML
    private TextField songNameTextField;
    @javafx.fxml.FXML
    private TextArea messa;

    private final List<SongRequest> submittedRequests = new ArrayList<>();
    private int requestCounter = 1;

    @javafx.fxml.FXML
    public void initialize() {
        listenerNameTextField.clear();
        songNameTextField.clear();
        messa.clear();
        requestSongTextArea.setText("Request Song");
    }

    @javafx.fxml.FXML
    public void clearButtonOnClick(ActionEvent actionEvent) {
        listenerNameTextField.clear();
        songNameTextField.clear();
        messa.clear();
        requestSongTextArea.setText("Request Song");
    }

    @javafx.fxml.FXML
    public void submitButtonOnClick(ActionEvent actionEvent) {
        String listenerName = listenerNameTextField.getText() == null ? "" : listenerNameTextField.getText().trim();
        String songName = songNameTextField.getText() == null ? "" : songNameTextField.getText().trim();
        String message = messa.getText() == null ? "" : messa.getText().trim();

        if (listenerName.isEmpty() || songName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Information");
            alert.setHeaderText("Listener name and song name are required");
            alert.setContentText("Please fill both fields, then submit your request.");
            alert.showAndWait();
            return;
        }

        String requestId = String.format("REQ-%03d", requestCounter++);
        String submittedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        SongRequest request = new SongRequest(requestId, listenerName, songName, message, submittedAt);
        submittedRequests.add(request);

        requestSongTextArea.setText("Submitted: " + request.getRequestId());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Request Submitted");
        alert.setHeaderText("Your request has been submitted successfully");
        alert.setContentText("ID: " + request.getRequestId() + "\nListener: " + request.getListenerName() +
                "\nSong: " + request.getSongName() + "\nTime: " + request.getSubmittedAt() +
                "\nTotal requests this session: " + submittedRequests.size());
        alert.showAndWait();
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
}