package com.oop.fmradiostation.Elora.finance_officer;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AddOrModifiedAdvertiseSceneController
{
    @javafx.fxml.FXML
    private TextField companyNameField;
    @javafx.fxml.FXML
    private TextArea notesTextArea;
    @javafx.fxml.FXML
    private TextField advertiseIdField;
    @javafx.fxml.FXML
    private CheckBox mondayCheckBox;
    @javafx.fxml.FXML
    private CheckBox sundayCheckBox;
    @javafx.fxml.FXML
    private TextField advertiseTitleField;
    @javafx.fxml.FXML
    private CheckBox saturdayCheckBox;
    @javafx.fxml.FXML
    private CheckBox wednesdayCheckBox;
    @javafx.fxml.FXML
    private DatePicker startDatePicker;
    @javafx.fxml.FXML
    private ComboBox<String> timeSlotComboBox;
    @javafx.fxml.FXML
    private CheckBox fridayCheckBox;
    @javafx.fxml.FXML
    private CheckBox thursdayCheckBox;
    @javafx.fxml.FXML
    private RadioButton newAdRadioButton;
    @javafx.fxml.FXML
    private ComboBox<String> adTypeComboBox;
    @javafx.fxml.FXML
    private CheckBox tuesdayCheckBox;
    @javafx.fxml.FXML
    private TextField budgetField;
    @javafx.fxml.FXML
    private DatePicker endDatePicker;
    @javafx.fxml.FXML
    private ComboBox<String> statusComboBox;
    @javafx.fxml.FXML
    private TextField durationField;
    @javafx.fxml.FXML
    private RadioButton modifyAdRadioButton;

    private ToggleGroup modeToggleGroup;

    private static final Map<String, AdvertisementCampaign> ADVERTISEMENT_STORE = new LinkedHashMap<>();

    @javafx.fxml.FXML
    public void initialize() {
        adTypeComboBox.getItems().setAll("Spot Ad", "Sponsored Segment", "Jingle", "Interview Mention");
        timeSlotComboBox.getItems().setAll("Morning (6 AM - 10 AM)", "Noon (10 AM - 2 PM)",
                "Evening Drive (4 PM - 8 PM)", "Night (8 PM - 12 AM)");
        statusComboBox.getItems().setAll("Pending", "Active", "Paused", "Completed");

        modeToggleGroup = new ToggleGroup();
        newAdRadioButton.setToggleGroup(modeToggleGroup);
        modifyAdRadioButton.setToggleGroup(modeToggleGroup);
        newAdRadioButton.setSelected(true);

        startDatePicker.setValue(LocalDate.now());
        endDatePicker.setValue(LocalDate.now().plusDays(7));
        seedDummyDataIfNeeded();
    }

    @javafx.fxml.FXML
    public void loadButtonOnClick(ActionEvent actionEvent) {
        String adId = getTrimmedValue(advertiseIdField);
        if (adId.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing ID", "Please enter Advertisement ID to load.");
            return;
        }

        AdvertisementCampaign campaign = ADVERTISEMENT_STORE.get(adId);
        if (campaign == null) {
            showAlert(Alert.AlertType.INFORMATION, "Not Found", "No advertisement found for ID: " + adId);
            return;
        }

        populateForm(campaign);
        modifyAdRadioButton.setSelected(true);
        showAlert(Alert.AlertType.INFORMATION, "Loaded", "Advertisement loaded successfully.");
    }

    @javafx.fxml.FXML
    public void clearButtonOnClick(ActionEvent actionEvent) {
        advertiseIdField.clear();
        companyNameField.clear();
        advertiseTitleField.clear();
        durationField.clear();
        budgetField.clear();
        notesTextArea.clear();

        adTypeComboBox.getSelectionModel().clearSelection();
        timeSlotComboBox.getSelectionModel().clearSelection();
        statusComboBox.getSelectionModel().clearSelection();

        startDatePicker.setValue(LocalDate.now());
        endDatePicker.setValue(LocalDate.now().plusDays(7));
        setAllDaySelections(false);
        newAdRadioButton.setSelected(true);
    }

    @javafx.fxml.FXML
    public void saveButtonOnClick(ActionEvent actionEvent) {
        if (!newAdRadioButton.isSelected()) {
            showAlert(Alert.AlertType.WARNING, "Mode Mismatch", "Switch mode to 'New' before saving a new ad.");
            return;
        }

        AdvertisementCampaign campaign = buildCampaignFromForm();
        if (campaign == null) {
            return;
        }

        if (ADVERTISEMENT_STORE.containsKey(campaign.getAdvertisementId())) {
            showAlert(Alert.AlertType.ERROR, "Duplicate ID", "Advertisement ID already exists. Use Update instead.");
            return;
        }

        ADVERTISEMENT_STORE.put(campaign.getAdvertisementId(), campaign);
        showAlert(Alert.AlertType.INFORMATION, "Saved", "New advertisement saved successfully.");
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
        if (!modifyAdRadioButton.isSelected()) {
            showAlert(Alert.AlertType.WARNING, "Mode Mismatch", "Switch mode to 'Modify' before updating.");
            return;
        }

        AdvertisementCampaign campaign = buildCampaignFromForm();
        if (campaign == null) {
            return;
        }

        if (!ADVERTISEMENT_STORE.containsKey(campaign.getAdvertisementId())) {
            showAlert(Alert.AlertType.ERROR, "Not Found", "Advertisement ID does not exist. Use Save for new ads.");
            return;
        }

        ADVERTISEMENT_STORE.put(campaign.getAdvertisementId(), campaign);
        showAlert(Alert.AlertType.INFORMATION, "Updated", "Advertisement updated successfully.");
    }

    private AdvertisementCampaign buildCampaignFromForm() {
        String adId = getTrimmedValue(advertiseIdField);
        String company = getTrimmedValue(companyNameField);
        String title = getTrimmedValue(advertiseTitleField);
        String durationText = getTrimmedValue(durationField);
        String budgetText = getTrimmedValue(budgetField);
        String adType = adTypeComboBox.getValue();
        String timeSlot = timeSlotComboBox.getValue();
        String status = statusComboBox.getValue();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (adId.isEmpty() || company.isEmpty() || title.isEmpty() || durationText.isEmpty() || budgetText.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing Fields", "Please fill all required text fields.");
            return null;
        }
        if (adType == null || timeSlot == null || status == null || startDate == null || endDate == null) {
            showAlert(Alert.AlertType.WARNING, "Missing Selection", "Please select ad type, slot, status and dates.");
            return null;
        }
        if (endDate.isBefore(startDate)) {
            showAlert(Alert.AlertType.ERROR, "Invalid Date", "End date cannot be before start date.");
            return null;
        }

        int duration;
        double budget;
        try {
            duration = Integer.parseInt(durationText);
            budget = Double.parseDouble(budgetText);
        } catch (NumberFormatException ex) {
            showAlert(Alert.AlertType.ERROR, "Invalid Number", "Duration must be integer and budget must be numeric.");
            return null;
        }

        if (duration <= 0 || budget <= 0) {
            showAlert(Alert.AlertType.ERROR, "Invalid Values", "Duration and budget must be greater than 0.");
            return null;
        }

        List<String> broadcastDays = getSelectedBroadcastDays();
        if (broadcastDays.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Days Selected", "Select at least one broadcast day.");
            return null;
        }

        return new AdvertisementCampaign(
                adId,
                company,
                title,
                duration,
                budget,
                adType,
                startDate,
                endDate,
                timeSlot,
                status,
                broadcastDays,
                notesTextArea.getText() == null ? "" : notesTextArea.getText().trim()
        );
    }

    private List<String> getSelectedBroadcastDays() {
        List<String> days = new ArrayList<>();
        if (mondayCheckBox.isSelected()) {
            days.add("Monday");
        }
        if (tuesdayCheckBox.isSelected()) {
            days.add("Tuesday");
        }
        if (wednesdayCheckBox.isSelected()) {
            days.add("Wednesday");
        }
        if (thursdayCheckBox.isSelected()) {
            days.add("Thursday");
        }
        if (fridayCheckBox.isSelected()) {
            days.add("Friday");
        }
        if (saturdayCheckBox.isSelected()) {
            days.add("Saturday");
        }
        if (sundayCheckBox.isSelected()) {
            days.add("Sunday");
        }
        return days;
    }

    private void populateForm(AdvertisementCampaign campaign) {
        advertiseIdField.setText(campaign.getAdvertisementId());
        companyNameField.setText(campaign.getCompanyName());
        advertiseTitleField.setText(campaign.getAdvertisementTitle());
        durationField.setText(String.valueOf(campaign.getDurationSeconds()));
        budgetField.setText(String.format("%.2f", campaign.getBudget()));
        adTypeComboBox.setValue(campaign.getAdType());
        startDatePicker.setValue(campaign.getStartDate());
        endDatePicker.setValue(campaign.getEndDate());
        timeSlotComboBox.setValue(campaign.getTimeSlot());
        statusComboBox.setValue(campaign.getStatus());
        notesTextArea.setText(campaign.getNotes());

        setAllDaySelections(false);
        for (String day : campaign.getBroadcastDays()) {
            switch (day) {
                case "Monday" -> mondayCheckBox.setSelected(true);
                case "Tuesday" -> tuesdayCheckBox.setSelected(true);
                case "Wednesday" -> wednesdayCheckBox.setSelected(true);
                case "Thursday" -> thursdayCheckBox.setSelected(true);
                case "Friday" -> fridayCheckBox.setSelected(true);
                case "Saturday" -> saturdayCheckBox.setSelected(true);
                case "Sunday" -> sundayCheckBox.setSelected(true);
                default -> {
                }
            }
        }
    }

    private void setAllDaySelections(boolean selected) {
        mondayCheckBox.setSelected(selected);
        tuesdayCheckBox.setSelected(selected);
        wednesdayCheckBox.setSelected(selected);
        thursdayCheckBox.setSelected(selected);
        fridayCheckBox.setSelected(selected);
        saturdayCheckBox.setSelected(selected);
        sundayCheckBox.setSelected(selected);
    }

    private String getTrimmedValue(TextField field) {
        return field.getText() == null ? "" : field.getText().trim();
    }

    private void showAlert(Alert.AlertType type, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle("Advertisement Form");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void seedDummyDataIfNeeded() {
        if (!ADVERTISEMENT_STORE.isEmpty()) {
            return;
        }

        ADVERTISEMENT_STORE.put("AD-101", new AdvertisementCampaign(
                "AD-101",
                "FreshMart",
                "Weekend Grocery Deals",
                30,
                9500,
                "Spot Ad",
                LocalDate.now().minusDays(3),
                LocalDate.now().plusDays(20),
                "Morning (6 AM - 10 AM)",
                "Active",
                List.of("Friday", "Saturday", "Sunday"),
                "Play after weather update"
        ));

        ADVERTISEMENT_STORE.put("AD-102", new AdvertisementCampaign(
                "AD-102",
                "Skyline Mobile",
                "New Data Pack Launch",
                45,
                14000,
                "Sponsored Segment",
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(40),
                "Evening Drive (4 PM - 8 PM)",
                "Pending",
                List.of("Monday", "Wednesday", "Thursday"),
                "Host mention required before ad"
        ));
    }
}