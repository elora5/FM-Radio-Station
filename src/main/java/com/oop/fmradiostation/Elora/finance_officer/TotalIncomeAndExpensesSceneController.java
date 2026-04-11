package com.oop.fmradiostation.Elora.finance_officer;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TotalIncomeAndExpensesSceneController
{
    @javafx.fxml.FXML
    private TextField totalIncomeField;
    @javafx.fxml.FXML
    private TableColumn<FinancialRecord, Double> amountCols;
    @javafx.fxml.FXML
    private TableColumn<FinancialRecord, String> dateCols;
    @javafx.fxml.FXML
    private TableColumn<FinancialRecord, String> descriptionCols;
    @javafx.fxml.FXML
    private TableView<FinancialRecord> finantialRecordTable;
    @javafx.fxml.FXML
    private TextField totalExpenseField;
    @javafx.fxml.FXML
    private TableColumn<FinancialRecord, String> incomeOrExpCols;
    @javafx.fxml.FXML
    private TextField netBalanceField;

    private final List<FinancialRecord> records = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        dateCols.setCellValueFactory(new PropertyValueFactory<>("date"));
        descriptionCols.setCellValueFactory(new PropertyValueFactory<>("description"));
        incomeOrExpCols.setCellValueFactory(new PropertyValueFactory<>("incomeOrExpense"));
        amountCols.setCellValueFactory(new PropertyValueFactory<>("amount"));

        seedDummyRecords();
        finantialRecordTable.getItems().setAll(records);

        totalIncomeField.setEditable(false);
        totalExpenseField.setEditable(false);
        netBalanceField.setEditable(false);
        updateSummary();
    }

    @javafx.fxml.FXML
    public void backButtonOnClick(ActionEvent actionEvent) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(
                getClass().getResource("/com/oop/fmradiostation/Elora/finance_officer/financeOfficerDashboardScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Finance Officer Dashboard");
        window.setScene(scene2);
        window.show();
    }

    private void seedDummyRecords() {
        if (!records.isEmpty()) {
            return;
        }

        records.add(new FinancialRecord("2026-04-01", "Morning Show Sponsorship", "Income", 1800));
        records.add(new FinancialRecord("2026-04-03", "Studio Electricity Bill", "Expense", 420));
        records.add(new FinancialRecord("2026-04-05", "Weekend Ad Campaign", "Income", 950));
        records.add(new FinancialRecord("2026-04-07", "Equipment Maintenance", "Expense", 260));
        records.add(new FinancialRecord("2026-04-09", "Prime Time Ad Slot", "Income", 1200));
    }

    private void updateSummary() {
        double totalIncome = 0;
        double totalExpense = 0;

        for (FinancialRecord record : records) {
            if ("Income".equalsIgnoreCase(record.getIncomeOrExpense())) {
                totalIncome += record.getAmount();
            } else {
                totalExpense += record.getAmount();
            }
        }

        double netBalance = totalIncome - totalExpense;
        totalIncomeField.setText(String.format("%.2f", totalIncome));
        totalExpenseField.setText(String.format("%.2f", totalExpense));
        netBalanceField.setText(String.format("%.2f", netBalance));
    }
}