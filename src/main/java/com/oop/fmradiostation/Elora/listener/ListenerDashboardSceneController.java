package com.oop.fmradiostation.Elora.listener;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ListenerDashboardSceneController
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void tuneLiveButtonOnClick(ActionEvent actionEvent) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("com/oop/fmradiostation/Elora/listener/tuneFmBroadcasstScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Tune FM");
        window.setScene(scene2);
        window.show();
    }

    @javafx.fxml.FXML
    public void programScheduleButtonOnClick(ActionEvent actionEvent) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("com/oop/fmradiostation/Elora/listener/programScheduleScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Program Schedule");
        window.setScene(scene2);
        window.show();
    }

    @javafx.fxml.FXML
    public void setReminderButtonOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void createButtonOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void programButtonOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void poolsButtonOnClick(ActionEvent actionEvent) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("com/oop/fmradiostation/Elora/listener/pollsOrContestScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Pools or Contest");
        window.setScene(scene2);
        window.show();
    }

    @javafx.fxml.FXML
    public void rateShowButtonOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void sendRequestButtonOnClick(ActionEvent actionEvent) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("com/oop/fmradiostation/Elora/listener/sendRequestScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Song Request");
        window.setScene(scene2);
        window.show();
    }

    @javafx.fxml.FXML
    public void logoutButtonOnClick(ActionEvent actionEvent) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/oop/fmradiostation/login/LoginScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Signup");
        window.setScene(scene2);
        window.show();
    }
}