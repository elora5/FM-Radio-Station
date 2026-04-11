package com.oop.fmradiostation.Elora.finance_officer;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SetAdSlotPrisingSceneController
{
    @javafx.fxml.FXML
    private TableColumn<AdSlotPricingRecord, String> timeSlotColumn;
    @javafx.fxml.FXML
    private TextField basePriceTextField;
    @javafx.fxml.FXML
    private TableColumn<AdSlotPricingRecord, String> slotIdColumn;
    @javafx.fxml.FXML
    private Button loadButton;
    @javafx.fxml.FXML
    private ComboBox<String> timeSlotComboBox;
    @javafx.fxml.FXML
    private TextArea noteTextArea;
    @javafx.fxml.FXML
    private DatePicker effectiveDatePicker;
    @javafx.fxml.FXML
    private TableColumn<AdSlotPricingRecord, String> slotTypeColumn;
    @javafx.fxml.FXML
    private TableColumn<AdSlotPricingRecord, LocalDate> effectiveDateColumn;
    @javafx.fxml.FXML
    private Button clearButton;
    @javafx.fxml.FXML
    private ComboBox<String> slotTypeComboBox;
    @javafx.fxml.FXML
    private TextField durationTextField;
    @javafx.fxml.FXML
    private TableColumn<AdSlotPricingRecord, String> statusColumn;
    @javafx.fxml.FXML
    private TextField slotIdTextField;
    @javafx.fxml.FXML
    private Button updateButton;
    @javafx.fxml.FXML
    private Button saveButton;
    @javafx.fxml.FXML
    private TableView<AdSlotPricingRecord> slotPricingTableView;
    @javafx.fxml.FXML
    private TableColumn<AdSlotPricingRecord, Integer> durationColumn;
    @javafx.fxml.FXML
    private TableColumn<AdSlotPricingRecord, Double> priceColumn;

    private final List<AdSlotPricingRecord> slotPricingRecords = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        slotIdColumn.setCellValueFactory(new PropertyValueFactory<>("slotId"));
        slotTypeColumn.setCellValueFactory(new PropertyValueFactory<>("slotType"));
        timeSlotColumn.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("durationSeconds"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("basePrice"));
        effectiveDateColumn.setCellValueFactory(new PropertyValueFactory<>("effectiveDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        slotTypeComboBox.getItems().setAll("Spot Ad", "Sponsored Segment", "Interview Mention", "Banner Promotion");
        timeSlotComboBox.getItems().setAll("Morning (6 AM - 10 AM)", "Noon (10 AM - 2 PM)", "Evening (4 PM - 8 PM)", "Night (8 PM - 12 AM)");

        effectiveDatePicker.setValue(LocalDate.now());
        noteTextArea.setEditable(false);
        noteTextArea.setText("Select a record to load or create a new slot pricing entry.");

        if (clearButton != null) {
            clearButton.setOnAction(this::clearButtonOnClick);
        }

        seedDummyData();
        refreshTable();
    }

    @javafx.fxml.FXML
    public void loadButtonOnClick(ActionEvent actionEvent) {
        AdSlotPricingRecord selected = slotPricingTableView.getSelectionModel().getSelectedItem();
        String slotId = trim(slotIdTextField.getText());

        if (selected == null && slotId.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Nothing to Load", "Select a row or enter a Slot ID first.");
            return;
        }

        AdSlotPricingRecord record = selected != null ? selected : findBySlotId(slotId);
        if (record == null) {
            showAlert(Alert.AlertType.INFORMATION, "Not Found", "No slot pricing record found for the given Slot ID.");
            return;
        }

        populateForm(record);
        noteTextArea.setText(record.getNote());
    }

    @javafx.fxml.FXML
    public void saveButtonOnClick(ActionEvent actionEvent) {
        AdSlotPricingRecord record = buildRecordFromForm();
        if (record == null) {
            return;
        }

        if (findBySlotId(record.getSlotId()) != null) {
            showAlert(Alert.AlertType.ERROR, "Duplicate Slot ID", "This Slot ID already exists. Use Update instead.");
            return;
        }

        slotPricingRecords.add(record);
        refreshTable();
        slotPricingTableView.getSelectionModel().select(record);
        noteTextArea.setText(record.getNote());
        showAlert(Alert.AlertType.INFORMATION, "Saved", "Slot pricing record saved successfully.");
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
    public void updateButtonOnClick(ActionEvent actionEvent) {
        AdSlotPricingRecord existing = slotPricingTableView.getSelectionModel().getSelectedItem();
        AdSlotPricingRecord updated = buildRecordFromForm();
        if (updated == null) {
            return;
        }

        if (existing == null) {
            existing = findBySlotId(updated.getSlotId());
        }

        if (existing == null) {
            showAlert(Alert.AlertType.WARNING, "No Record Selected", "Select a record or load one before updating.");
            return;
        }

        int index = slotPricingRecords.indexOf(existing);
        if (index < 0) {
            showAlert(Alert.AlertType.ERROR, "Update Failed", "The selected record could not be found.");
            return;
        }

        if (!existing.getSlotId().equals(updated.getSlotId()) && findBySlotId(updated.getSlotId()) != null) {
            showAlert(Alert.AlertType.ERROR, "Duplicate Slot ID", "A different record already uses this Slot ID.");
            return;
        }

        slotPricingRecords.set(index, updated);
        refreshTable();
        slotPricingTableView.getSelectionModel().select(updated);
        noteTextArea.setText(updated.getNote());
        showAlert(Alert.AlertType.INFORMATION, "Updated", "Slot pricing record updated successfully.");
    }

    @javafx.fxml.FXML
    public void clearButtonOnClick(ActionEvent actionEvent) {
        slotIdTextField.clear();
        slotTypeComboBox.getSelectionModel().clearSelection();
        timeSlotComboBox.getSelectionModel().clearSelection();
        durationTextField.clear();
        basePriceTextField.clear();
        effectiveDatePicker.setValue(LocalDate.now());
        noteTextArea.clear();
        slotPricingTableView.getSelectionModel().clearSelection();
        noteTextArea.setText("Select a record to load or create a new slot pricing entry.");
    }

    private void refreshTable() {
        slotPricingTableView.getItems().setAll(slotPricingRecords);
    }

    private void populateForm(AdSlotPricingRecord record) {
        slotIdTextField.setText(record.getSlotId());
        slotTypeComboBox.setValue(record.getSlotType());
        timeSlotComboBox.setValue(record.getTimeSlot());
        durationTextField.setText(String.valueOf(record.getDurationSeconds()));
        basePriceTextField.setText(String.format("%.2f", record.getBasePrice()));
        effectiveDatePicker.setValue(record.getEffectiveDate());
        noteTextArea.setText(record.getNote());
    }

    private AdSlotPricingRecord buildRecordFromForm() {
        String slotId = trim(slotIdTextField.getText());
        String slotType = slotTypeComboBox.getValue();
        String timeSlot = timeSlotComboBox.getValue();
        String durationText = trim(durationTextField.getText());
        String priceText = trim(basePriceTextField.getText());
        LocalDate effectiveDate = effectiveDatePicker.getValue();
        String note = noteTextArea.getText() == null ? "" : noteTextArea.getText().trim();

        if (slotId.isEmpty() || slotType == null || timeSlot == null || durationText.isEmpty() || priceText.isEmpty() || effectiveDate == null) {
            showAlert(Alert.AlertType.WARNING, "Missing Fields", "Please complete all required fields.");
            return null;
        }

        int durationSeconds;
        double basePrice;
        try {
            durationSeconds = Integer.parseInt(durationText);
            basePrice = Double.parseDouble(priceText);
        } catch (NumberFormatException ex) {
            showAlert(Alert.AlertType.ERROR, "Invalid Number", "Duration must be an integer and price must be numeric.");
            return null;
        }

        if (durationSeconds <= 0 || basePrice <= 0) {
            showAlert(Alert.AlertType.ERROR, "Invalid Values", "Duration and base price must be greater than zero.");
            return null;
        }

        return new AdSlotPricingRecord(
                slotId,
                slotType,
                timeSlot,
                durationSeconds,
                basePrice,
                effectiveDate,
                deriveStatus(effectiveDate),
                note
        );
    }

    private String deriveStatus(LocalDate effectiveDate) {
        if (effectiveDate.isBefore(LocalDate.now())) {
            return "Expired";
        }
        if (effectiveDate.isEqual(LocalDate.now())) {
            return "Active";
        }
        return "Scheduled";
    }

    private void seedDummyData() {
        if (!slotPricingRecords.isEmpty()) {
            return;
        }

        slotPricingRecords.add(new AdSlotPricingRecord(
                "SL-101", "Spot Ad", "Morning (6 AM - 10 AM)", 30, 1200.0,
                LocalDate.now(), "Active", "Prime morning pricing"
        ));
        slotPricingRecords.add(new AdSlotPricingRecord(
                "SL-102", "Sponsored Segment", "Noon (10 AM - 2 PM)", 45, 1700.0,
                LocalDate.now().plusDays(2), "Scheduled", "Good for lunch audience"
        ));
        slotPricingRecords.add(new AdSlotPricingRecord(
                "SL-103", "Interview Mention", "Evening (4 PM - 8 PM)", 60, 2200.0,
                LocalDate.now().minusDays(1), "Expired", "Old campaign reference"
        ));
    }

    private AdSlotPricingRecord findBySlotId(String slotId) {
        return slotPricingRecords.stream()
                .filter(record -> record.getSlotId().equalsIgnoreCase(slotId))
                .findFirst()
                .orElse(null);
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }

    private void showAlert(Alert.AlertType type, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle("Ad Slot Pricing");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}