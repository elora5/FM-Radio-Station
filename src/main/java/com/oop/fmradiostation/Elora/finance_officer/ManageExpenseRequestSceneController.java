package com.oop.fmradiostation.Elora.finance_officer;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class ManageExpenseRequestSceneController {
    @javafx.fxml.FXML
    private TableColumn departmentCol;
    @javafx.fxml.FXML
    private TableColumn amountCol;
    @javafx.fxml.FXML
    private TableColumn statusCol;
    @javafx.fxml.FXML
    private TableColumn reasonCol;
    @javafx.fxml.FXML
    private TableColumn requestIdCol;
    @javafx.fxml.FXML
    private TableView expenseTable;
    @javafx.fxml.FXML
    private TextArea messageTextField;

    @javafx.fxml.FXML
    public void rejectButtonOnClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void approveButton(ActionEvent actionEvent) {
    }
}
