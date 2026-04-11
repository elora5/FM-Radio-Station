package com.oop.fmradiostation.Elora.finance_officer;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Year;
import java.util.List;
import java.util.Objects;

public class MonthlyReportSceneController {
    @javafx.fxml.FXML
    private ComboBox<String> yearComboBox;
    @javafx.fxml.FXML
    private ComboBox<String> monthComboBox;
    @javafx.fxml.FXML
    private TextArea reportTextField;

    @javafx.fxml.FXML
    public void initialize() {
        int currentYear = Year.now().getValue();
        yearComboBox.getItems().setAll(
                String.valueOf(currentYear - 1),
                String.valueOf(currentYear),
                String.valueOf(currentYear + 1)
        );
        monthComboBox.getItems().setAll(List.of(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        ));

        reportTextField.setEditable(false);
        reportTextField.setText("Select month and year, then click Generate Report.");
    }

    @javafx.fxml.FXML
    public void clearButtonOnClick(ActionEvent actionEvent) {
        monthComboBox.getSelectionModel().clearSelection();
        yearComboBox.getSelectionModel().clearSelection();
        reportTextField.clear();
    }

    @javafx.fxml.FXML
    public void generateReportButtonOnClick(ActionEvent actionEvent) {
        String year = yearComboBox.getValue();
        String month = monthComboBox.getValue();

        if (year == null || month == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Selection");
            alert.setHeaderText("Month and year are required");
            alert.setContentText("Please select both values before generating report.");
            alert.showAndWait();
            return;
        }

        int monthFactor = monthComboBox.getItems().indexOf(month) + 1;
        double totalIncome = 4200 + (monthFactor * 230);
        double totalExpense = 2500 + (monthFactor * 170);
        double adRevenue = 1800 + (monthFactor * 140);
        double netBalance = totalIncome - totalExpense;

        String report = "Financial Report - " + month + " " + year + "\n"
                + "-----------------------------------\n"
                + String.format("Total Income: $%.2f\n", totalIncome)
                + String.format("Total Expense: $%.2f\n", totalExpense)
                + String.format("Advertisement Revenue: $%.2f\n", adRevenue)
                + String.format("Net Balance: $%.2f\n", netBalance)
                + "\nStatus: " + (netBalance >= 0 ? "Profit" : "Loss");
        reportTextField.setText(report);
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
}
