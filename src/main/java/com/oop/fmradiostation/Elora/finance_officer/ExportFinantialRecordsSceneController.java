package com.oop.fmradiostation.Elora.finance_officer;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ExportFinantialRecordsSceneController
{
    @javafx.fxml.FXML
    private TableView<ExportFinancialRecord> financialRecordsTableView;
    @javafx.fxml.FXML
    private DatePicker fromDatePicker;
    @javafx.fxml.FXML
    private TableColumn<ExportFinancialRecord, String> remarksColumn;
    @javafx.fxml.FXML
    private DatePicker toDatePicker;
    @javafx.fxml.FXML
    private TableColumn<ExportFinancialRecord, String> recordIdColumn;
    @javafx.fxml.FXML
    private TableColumn<ExportFinancialRecord, String> amountColumn;
    @javafx.fxml.FXML
    private TableColumn<ExportFinancialRecord, String> categoryColumn;
    @javafx.fxml.FXML
    private TextArea summaryTextArea;
    @javafx.fxml.FXML
    private ComboBox<String> recordTypeComboBox;
    @javafx.fxml.FXML
    private TextField totalIncomeField;
    @javafx.fxml.FXML
    private BarChart<String, Number> financialBarChart;
    @javafx.fxml.FXML
    private TableColumn<ExportFinancialRecord, String> statusColumn;
    @javafx.fxml.FXML
    private TableColumn<ExportFinancialRecord, String> dateColumn;
    @javafx.fxml.FXML
    private TextField totalExpenseField;
    @javafx.fxml.FXML
    private TableColumn<ExportFinancialRecord, String> sourceColumn;

    private final List<ExportFinancialRecord> allRecords = new ArrayList<>();
    private List<ExportFinancialRecord> currentViewRecords = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        recordIdColumn.setCellValueFactory(new PropertyValueFactory<>("recordId"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        sourceColumn.setCellValueFactory(new PropertyValueFactory<>("source"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        remarksColumn.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        amountColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(String.format("$%.2f", cellData.getValue().getAmount())));

        recordTypeComboBox.getItems().setAll("All", "Income", "Expense");
        recordTypeComboBox.setValue("All");

        summaryTextArea.setEditable(false);
        totalIncomeField.setEditable(false);
        totalExpenseField.setEditable(false);

        seedDummyData();
        loadAllButtonOnClick(null);
    }

    @javafx.fxml.FXML
    public void loadAllButtonOnClick(ActionEvent actionEvent) {
        fromDatePicker.setValue(null);
        toDatePicker.setValue(null);
        recordTypeComboBox.setValue("All");
        applyFilterAndRender(allRecords, "Loaded all records.");
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

    @javafx.fxml.FXML
    public void printButtonOnClick(ActionEvent actionEvent) {
        if (currentViewRecords.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Nothing to Print", "No records available in current view.");
            return;
        }

        StringBuilder printable = new StringBuilder();
        printable.append("Export Financial Records Snapshot\n")
                .append("--------------------------------\n")
                .append("Rows: ").append(currentViewRecords.size()).append("\n")
                .append("Total Income: ").append(totalIncomeField.getText()).append("\n")
                .append("Total Expense: ").append(totalExpenseField.getText()).append("\n\n")
                .append("Preview:\n");

        int previewCount = Math.min(5, currentViewRecords.size());
        for (int i = 0; i < previewCount; i++) {
            ExportFinancialRecord row = currentViewRecords.get(i);
            printable.append(row.getDate()).append(" | ")
                    .append(row.getRecordId()).append(" | ")
                    .append(row.getRecordType()).append(" | $")
                    .append(String.format("%.2f", row.getAmount())).append("\n");
        }

        summaryTextArea.setText(printable.toString());
        showAlert(Alert.AlertType.INFORMATION, "Print Preview Ready", "Summary preview generated in notes box.");
    }

    @javafx.fxml.FXML
    public void filterButtonOnClick(ActionEvent actionEvent) {
        LocalDate from = fromDatePicker.getValue();
        LocalDate to = toDatePicker.getValue();
        String type = recordTypeComboBox.getValue() == null ? "All" : recordTypeComboBox.getValue();

        if (from != null && to != null && from.isAfter(to)) {
            showAlert(Alert.AlertType.ERROR, "Invalid Date Range", "From Date cannot be after To Date.");
            return;
        }

        List<ExportFinancialRecord> filtered = allRecords.stream()
                .filter(record -> {
                    LocalDate rowDate = record.getRecordDate();
                    boolean dateOk = (from == null || !rowDate.isBefore(from)) && (to == null || !rowDate.isAfter(to));
                    boolean typeOk = "All".equals(type) || record.getRecordType().equalsIgnoreCase(type);
                    return dateOk && typeOk;
                })
                .collect(Collectors.toList());

        applyFilterAndRender(filtered, "Applied filters: Type = " + type + ".");

        if (filtered.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "No Results", "No financial records match the selected filters.");
        }
    }

    private void applyFilterAndRender(List<ExportFinancialRecord> records, String messagePrefix) {
        currentViewRecords = new ArrayList<>(records);
        financialRecordsTableView.getItems().setAll(currentViewRecords);

        double totalIncome = 0;
        double totalExpense = 0;
        for (ExportFinancialRecord record : currentViewRecords) {
            if ("Income".equalsIgnoreCase(record.getRecordType())) {
                totalIncome += record.getAmount();
            } else {
                totalExpense += record.getAmount();
            }
        }

        totalIncomeField.setText(String.format("$%.2f", totalIncome));
        totalExpenseField.setText(String.format("$%.2f", totalExpense));

        double net = totalIncome - totalExpense;
        summaryTextArea.setText(messagePrefix + "\nRows: " + currentViewRecords.size()
                + "\nNet Balance: $" + String.format("%.2f", net)
                + "\nStatus: " + (net >= 0 ? "Profit" : "Loss"));

        renderChart(totalIncome, totalExpense);
    }

    private void renderChart(double totalIncome, double totalExpense) {
        financialBarChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Totals");
        series.getData().add(new XYChart.Data<>("Income", totalIncome));
        series.getData().add(new XYChart.Data<>("Expense", totalExpense));

        financialBarChart.getData().add(series);
    }

    private void seedDummyData() {
        if (!allRecords.isEmpty()) {
            return;
        }

        allRecords.add(new ExportFinancialRecord("2026-04-01", "REC-001", "Advertisement", "FreshMart", 2600,
                "Income", "Received", "Weekend campaign payment"));
        allRecords.add(new ExportFinancialRecord("2026-04-03", "REC-002", "Operations", "Studio Utilities", 800,
                "Expense", "Paid", "Electricity and internet bill"));
        allRecords.add(new ExportFinancialRecord("2026-04-05", "REC-003", "Sponsorship", "Skyline Mobile", 1800,
                "Income", "Received", "Sponsored segment settlement"));
        allRecords.add(new ExportFinancialRecord("2026-04-07", "REC-004", "Equipment", "AudioHub Store", 950,
                "Expense", "Paid", "Headphone and cable purchase"));
        allRecords.add(new ExportFinancialRecord("2026-04-10", "REC-005", "Advertisement", "City Clinic", 1200,
                "Income", "Pending", "Awaiting transfer confirmation"));
    }

    private void showAlert(Alert.AlertType type, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle("Export Financial Records");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
