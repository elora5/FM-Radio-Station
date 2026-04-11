package com.oop.fmradiostation.Elora.finance_officer;

import javafx.scene.chart.BarChart;
import javafx.scene.control.*;

public class ExportFinantialRecordsSceneController
{
    @javafx.fxml.FXML
    private TableView financialRecordsTableView;
    @javafx.fxml.FXML
    private Button loadAllButton;
    @javafx.fxml.FXML
    private DatePicker fromDatePicker;
    @javafx.fxml.FXML
    private TableColumn remarksColumn;
    @javafx.fxml.FXML
    private DatePicker toDatePicker;
    @javafx.fxml.FXML
    private TableColumn recordIdColumn;
    @javafx.fxml.FXML
    private TableColumn amountColumn;
    @javafx.fxml.FXML
    private TableColumn categoryColumn;
    @javafx.fxml.FXML
    private TextArea summaryTextArea;
    @javafx.fxml.FXML
    private ComboBox recordTypeComboBox;
    @javafx.fxml.FXML
    private Button printButton;
    @javafx.fxml.FXML
    private TextField totalIncomeField;
    @javafx.fxml.FXML
    private BarChart financialBarChart;
    @javafx.fxml.FXML
    private TableColumn statusColumn;
    @javafx.fxml.FXML
    private Button backButton;
    @javafx.fxml.FXML
    private TableColumn dateColumn;
    @javafx.fxml.FXML
    private TextField totalExpenseField;
    @javafx.fxml.FXML
    private Button filterButton;
    @javafx.fxml.FXML
    private TableColumn sourceColumn;

    @javafx.fxml.FXML
    public void initialize() {
    }
}
