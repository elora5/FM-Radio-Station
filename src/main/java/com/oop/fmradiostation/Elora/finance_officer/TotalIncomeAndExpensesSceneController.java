package com.oop.fmradiostation.Elora.finance_officer;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TotalIncomeAndExpensesSceneController
{
    @javafx.fxml.FXML
    private TextField totalIncomeField;
    @javafx.fxml.FXML
    private TableColumn amountCols;
    @javafx.fxml.FXML
    private TableColumn dateCols;
    @javafx.fxml.FXML
    private TableColumn descriptionCols;
    @javafx.fxml.FXML
    private TableView finantialRecordTable;
    @javafx.fxml.FXML
    private TextField totalExpenseField;
    @javafx.fxml.FXML
    private TableColumn incomeOrExpCols;
    @javafx.fxml.FXML
    private TextField netBalanceField;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOnClick(ActionEvent actionEvent) {
    }
}