package com.oop.fmradiostation.Elora.finance_officer;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecordAdvertisementRevenueSceneController
{
    @javafx.fxml.FXML
    private DatePicker revenueDatePicker;
    @javafx.fxml.FXML
    private TextField adNaneField;
    @javafx.fxml.FXML
    private ComboBox<String> adTypeComboBox;
    @javafx.fxml.FXML
    private TextArea messageLabel;
    @javafx.fxml.FXML
    private TextField amountField;

    private final List<AdvertisementRevenueRecord> savedRevenues = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        adTypeComboBox.getItems().setAll("Spot Ad", "Sponsored Segment", "Banner Promotion", "Prime Time Ad");
        revenueDatePicker.setValue(LocalDate.now());
        messageLabel.setEditable(false);
        messageLabel.setText("Enter advertisement revenue details and click Save Revenue.");
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
    public void saveRevenueButtonOnClick(ActionEvent actionEvent) {
        String adName = adNaneField.getText() == null ? "" : adNaneField.getText().trim();
        String adType = adTypeComboBox.getValue();
        String amountText = amountField.getText() == null ? "" : amountField.getText().trim();
        LocalDate date = revenueDatePicker.getValue();

        if (adName.isEmpty() || adType == null || amountText.isEmpty() || date == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Information");
            alert.setHeaderText("All fields are required");
            alert.setContentText("Please fill all fields before saving revenue.");
            alert.showAndWait();
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Amount");
            alert.setHeaderText("Amount must be numeric");
            alert.setContentText("Please enter a valid number for amount.");
            alert.showAndWait();
            return;
        }

        if (amount <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Amount");
            alert.setHeaderText("Amount must be greater than zero");
            alert.setContentText("Please enter a positive amount.");
            alert.showAndWait();
            return;
        }

        AdvertisementRevenueRecord record = new AdvertisementRevenueRecord(adName, adType, amount, date.toString());
        savedRevenues.add(record);
        messageLabel.setText("Saved revenue for '" + adName + "' ($" + String.format("%.2f", amount)
                + "). Total entries this session: " + savedRevenues.size());
    }

    @javafx.fxml.FXML
    public void resetButtonOnClick(ActionEvent actionEvent) {
        adNaneField.clear();
        amountField.clear();
        adTypeComboBox.getSelectionModel().clearSelection();
        revenueDatePicker.setValue(LocalDate.now());
        messageLabel.clear();
    }
}