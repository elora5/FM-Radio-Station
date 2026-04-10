package com.oop.fmradiostation.Elora.listener;


import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TuneFmBroadcastSceneController
{
    @javafx.fxml.FXML
    private Button tuneInButtonOnClick;
    @javafx.fxml.FXML
    private TextField broadcastStatusTextField;
    @javafx.fxml.FXML
    private TextField currentProgramTextField;
    @javafx.fxml.FXML
    private TextArea messageTextArea;
    @javafx.fxml.FXML
    private ComboBox stationListComboBox;
    @javafx.fxml.FXML
    private TextField rjNameTextField;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOnClick(ActionEvent actionEvent) {
    }
}