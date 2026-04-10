package com.oop.fmradiostation.Elora.listener;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class ProgramScheduleSceneController
{
    @javafx.fxml.FXML
    private TableView programListTableView;
    @javafx.fxml.FXML
    private TableColumn endTimeCol;
    @javafx.fxml.FXML
    private TableColumn programNameCol;
    @javafx.fxml.FXML
    private TableColumn startTimeCol;
    @javafx.fxml.FXML
    private TableColumn categoryCol;
    @javafx.fxml.FXML
    private Button backButtonOnClick;
    @javafx.fxml.FXML
    private TableColumn rjNameCol;
    @javafx.fxml.FXML
    private ComboBox selectStationComboBox;
    @javafx.fxml.FXML
    private TextField messageTextField;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void loadSceduleButtonOnClick(ActionEvent actionEvent) {
    }
}