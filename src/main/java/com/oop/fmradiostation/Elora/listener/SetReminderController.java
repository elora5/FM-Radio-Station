package com.oop.fmradiostation.Elora.listener;

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

public class SetReminderController
{
    @javafx.fxml.FXML
    private TableColumn<ReminderEntry, String> programColumn;
    @javafx.fxml.FXML
    private TableView<ReminderEntry> reminderTableView;
    @javafx.fxml.FXML
    private RadioButton weeklyRadioButton;
    @javafx.fxml.FXML
    private TextField reminderIdTextField;
    @javafx.fxml.FXML
    private DatePicker reminderDatePicker;
    @javafx.fxml.FXML
    private ComboBox<String> timeSlotComboBox;
    @javafx.fxml.FXML
    private TextArea noteTextArea;
    @javafx.fxml.FXML
    private RadioButton dailyRadioButton;
    @javafx.fxml.FXML
    private TextArea previewTextArea;
    @javafx.fxml.FXML
    private CheckBox emailCheckBox;
    @javafx.fxml.FXML
    private CheckBox popupCheckBox;
    @javafx.fxml.FXML
    private TableColumn<ReminderEntry, String> timeColumn;
    @javafx.fxml.FXML
    private TableColumn<ReminderEntry, String> repeatColumn;
    @javafx.fxml.FXML
    private ComboBox<String> programComboBox;
    @javafx.fxml.FXML
    private RadioButton onceRadioButton;
    @javafx.fxml.FXML
    private CheckBox soundCheckBox;
    @javafx.fxml.FXML
    private TableColumn<ReminderEntry, LocalDate> dateColumn;
    @javafx.fxml.FXML
    private TableColumn<ReminderEntry, String> idColumn;

    private ToggleGroup repeatToggleGroup;
    private final List<ReminderEntry> reminderStore = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("reminderId"));
        programColumn.setCellValueFactory(new PropertyValueFactory<>("programName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("reminderDate"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        repeatColumn.setCellValueFactory(new PropertyValueFactory<>("repeatMode"));

        programComboBox.getItems().setAll("Wake Up Beats", "Traffic Talk", "Lunch Mix", "Golden Hour", "Retro Request", "Memory Lane", "Morning Sports Brief", "Live Match Review", "Fan Hotline");
        timeSlotComboBox.getItems().setAll("07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "16:00", "18:00", "20:00");

        repeatToggleGroup = new ToggleGroup();
        onceRadioButton.setToggleGroup(repeatToggleGroup);
        dailyRadioButton.setToggleGroup(repeatToggleGroup);
        weeklyRadioButton.setToggleGroup(repeatToggleGroup);
        onceRadioButton.setSelected(true);

        previewTextArea.setEditable(false);
        seedDummyReminders();
        refreshTable();

        if (!reminderStore.isEmpty()) {
            reminderTableView.getSelectionModel().selectFirst();
            loadSelectedReminderToForm();
        }
    }

    @javafx.fxml.FXML
    public void loadButtonOnClick(ActionEvent actionEvent) {
        ReminderEntry selected = reminderTableView.getSelectionModel().getSelectedItem();
        String reminderId = trim(reminderIdTextField.getText());

        if (selected == null && reminderId.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Nothing to Load", "Select a reminder or enter a reminder ID.");
            return;
        }

        ReminderEntry entry = selected != null ? selected : findByReminderId(reminderId);
        if (entry == null) {
            showAlert(Alert.AlertType.INFORMATION, "Not Found", "No reminder found with that ID.");
            return;
        }

        loadReminderIntoForm(entry);
    }

    @javafx.fxml.FXML
    public void activateButtonOnClick(ActionEvent actionEvent) {
        ReminderEntry entry = getCurrentOrLoadedReminder();
        if (entry == null) {
            return;
        }

        int index = reminderStore.indexOf(entry);
        if (index < 0) {
            showAlert(Alert.AlertType.ERROR, "Activation Failed", "The reminder could not be found.");
            return;
        }

        ReminderEntry activated = entry.withActive(true);
        reminderStore.set(index, activated);
        refreshTable();
        reminderTableView.getSelectionModel().select(activated);
        previewTextArea.setText(buildPreview(activated));
        showAlert(Alert.AlertType.INFORMATION, "Activated", "Reminder marked as active.");
    }

    @javafx.fxml.FXML
    public void clearButtonOnClick(ActionEvent actionEvent) {
        reminderIdTextField.clear();
        programComboBox.getSelectionModel().clearSelection();
        reminderDatePicker.setValue(LocalDate.now());
        timeSlotComboBox.getSelectionModel().clearSelection();
        noteTextArea.clear();
        previewTextArea.setText("Selected reminder preview...");
        popupCheckBox.setSelected(false);
        soundCheckBox.setSelected(false);
        emailCheckBox.setSelected(false);
        onceRadioButton.setSelected(true);
        reminderTableView.getSelectionModel().clearSelection();
    }

    @javafx.fxml.FXML
    public void saveButtonOnClick(ActionEvent actionEvent) {
        ReminderEntry entry = buildReminderFromForm();
        if (entry == null) {
            return;
        }

        if (findByReminderId(entry.getReminderId()) != null) {
            showAlert(Alert.AlertType.ERROR, "Duplicate ID", "Reminder ID already exists. Use Update instead.");
            return;
        }

        reminderStore.add(entry);
        refreshTable();
        reminderTableView.getSelectionModel().select(entry);
        previewTextArea.setText(buildPreview(entry));
        showAlert(Alert.AlertType.INFORMATION, "Saved", "Reminder saved successfully.");
    }

    @javafx.fxml.FXML
    public void backButtonOnClick(ActionEvent actionEvent) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(
                getClass().getResource("/com/oop/fmradiostation/Elora/listener/listenerDashboardScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Listener Dashboard");
        window.setScene(scene2);
        window.show();
    }

    @javafx.fxml.FXML
    public void deleteButtonOnClick(ActionEvent actionEvent) {
        ReminderEntry entry = getCurrentOrLoadedReminder();
        if (entry == null) {
            return;
        }

        reminderStore.removeIf(reminder -> reminder.getReminderId().equals(entry.getReminderId()));
        refreshTable();
        clearButtonOnClick(actionEvent);
        showAlert(Alert.AlertType.INFORMATION, "Deleted", "Reminder deleted successfully.");
    }

    @javafx.fxml.FXML
    public void updateButtonOnClick(ActionEvent actionEvent) {
        ReminderEntry entry = buildReminderFromForm();
        if (entry == null) {
            return;
        }

        ReminderEntry existing = findByReminderId(entry.getReminderId());
        if (existing == null) {
            showAlert(Alert.AlertType.ERROR, "Not Found", "No reminder exists with this ID. Save it first.");
            return;
        }

        int index = reminderStore.indexOf(existing);
        if (index < 0) {
            showAlert(Alert.AlertType.ERROR, "Update Failed", "Could not locate the selected reminder.");
            return;
        }

        reminderStore.set(index, entry);
        refreshTable();
        reminderTableView.getSelectionModel().select(entry);
        previewTextArea.setText(buildPreview(entry));
        showAlert(Alert.AlertType.INFORMATION, "Updated", "Reminder updated successfully.");
    }

    private void seedDummyReminders() {
        if (!reminderStore.isEmpty()) {
            return;
        }

        reminderStore.add(new ReminderEntry("RM-101", "Wake Up Beats", LocalDate.now().plusDays(1), "07:00",
                "Once", List.of("Popup", "Sound"), "Morning show reminder", false));
        reminderStore.add(new ReminderEntry("RM-102", "Retro Request", LocalDate.now().plusDays(2), "11:00",
                "Daily", List.of("Popup", "Email"), "Remember to listen and send a request", true));
        reminderStore.add(new ReminderEntry("RM-103", "Fan Hotline", LocalDate.now().plusDays(3), "20:00",
                "Weekly", List.of("Popup", "Sound", "Email"), "Weekly sports discussion reminder", false));
    }

    private ReminderEntry buildReminderFromForm() {
        String reminderId = trim(reminderIdTextField.getText());
        String program = programComboBox.getValue();
        LocalDate date = reminderDatePicker.getValue();
        String timeSlot = timeSlotComboBox.getValue();
        String repeatMode = getRepeatMode();
        String note = noteTextArea.getText() == null ? "" : noteTextArea.getText().trim();

        if (reminderId.isEmpty() || program == null || date == null || timeSlot == null || repeatMode == null) {
            showAlert(Alert.AlertType.WARNING, "Missing Fields", "Please complete reminder ID, program, date, time and repeat mode.");
            return null;
        }

        List<String> channels = new ArrayList<>();
        if (popupCheckBox.isSelected()) {
            channels.add("Popup");
        }
        if (soundCheckBox.isSelected()) {
            channels.add("Sound");
        }
        if (emailCheckBox.isSelected()) {
            channels.add("Email");
        }

        if (channels.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Notification Channel", "Please select at least one notification method.");
            return null;
        }

        boolean active = previewTextArea.getText() != null && previewTextArea.getText().contains("Active: Yes");
        return new ReminderEntry(reminderId, program, date, timeSlot, repeatMode, channels, note, active);
    }

    private void loadReminderIntoForm(ReminderEntry entry) {
        reminderIdTextField.setText(entry.getReminderId());
        programComboBox.setValue(entry.getProgramName());
        reminderDatePicker.setValue(entry.getReminderDate());
        timeSlotComboBox.setValue(entry.getTimeSlot());
        selectRepeatMode(entry.getRepeatMode());
        popupCheckBox.setSelected(entry.getChannels().contains("Popup"));
        soundCheckBox.setSelected(entry.getChannels().contains("Sound"));
        emailCheckBox.setSelected(entry.getChannels().contains("Email"));
        noteTextArea.setText(entry.getNote());
        previewTextArea.setText(buildPreview(entry));
        reminderTableView.getSelectionModel().select(entry);
    }

    private void loadSelectedReminderToForm() {
        ReminderEntry entry = reminderTableView.getSelectionModel().getSelectedItem();
        if (entry != null) {
            loadReminderIntoForm(entry);
        }
    }

    private ReminderEntry getCurrentOrLoadedReminder() {
        ReminderEntry selected = reminderTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            return selected;
        }
        String reminderId = trim(reminderIdTextField.getText());
        if (reminderId.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Reminder Selected", "Select a reminder row or enter a reminder ID first.");
            return null;
        }
        ReminderEntry entry = findByReminderId(reminderId);
        if (entry == null) {
            showAlert(Alert.AlertType.INFORMATION, "Not Found", "No reminder found with that ID.");
        }
        return entry;
    }

    private void refreshTable() {
        reminderTableView.getItems().setAll(reminderStore);
    }

    private ReminderEntry findByReminderId(String reminderId) {
        return reminderStore.stream()
                .filter(entry -> entry.getReminderId().equalsIgnoreCase(reminderId))
                .findFirst()
                .orElse(null);
    }

    private String getRepeatMode() {
        if (onceRadioButton.isSelected()) {
            return "Once";
        }
        if (dailyRadioButton.isSelected()) {
            return "Daily";
        }
        if (weeklyRadioButton.isSelected()) {
            return "Weekly";
        }
        return null;
    }

    private void selectRepeatMode(String repeatMode) {
        onceRadioButton.setSelected("Once".equalsIgnoreCase(repeatMode));
        dailyRadioButton.setSelected("Daily".equalsIgnoreCase(repeatMode));
        weeklyRadioButton.setSelected("Weekly".equalsIgnoreCase(repeatMode));
    }

    private String buildPreview(ReminderEntry entry) {
        return "Reminder ID: " + entry.getReminderId() + "\n"
                + "Program: " + entry.getProgramName() + "\n"
                + "Date: " + entry.getReminderDate() + "\n"
                + "Time: " + entry.getTimeSlot() + "\n"
                + "Repeat: " + entry.getRepeatMode() + "\n"
                + "Channels: " + String.join(", ", entry.getChannels()) + "\n"
                + "Active: " + (entry.isActive() ? "Yes" : "No") + "\n"
                + "Note: " + entry.getNote();
    }

    private void showAlert(Alert.AlertType type, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle("Set Reminder");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }

    public static class ReminderEntry {
        private final String reminderId;
        private final String programName;
        private final LocalDate reminderDate;
        private final String timeSlot;
        private final String repeatMode;
        private final List<String> channels;
        private final String note;
        private final boolean active;

        public ReminderEntry(String reminderId,
                             String programName,
                             LocalDate reminderDate,
                             String timeSlot,
                             String repeatMode,
                             List<String> channels,
                             String note,
                             boolean active) {
            this.reminderId = reminderId;
            this.programName = programName;
            this.reminderDate = reminderDate;
            this.timeSlot = timeSlot;
            this.repeatMode = repeatMode;
            this.channels = new ArrayList<>(channels);
            this.note = note;
            this.active = active;
        }

        public String getReminderId() {
            return reminderId;
        }

        public String getProgramName() {
            return programName;
        }

        public LocalDate getReminderDate() {
            return reminderDate;
        }

        public String getTimeSlot() {
            return timeSlot;
        }

        public String getRepeatMode() {
            return repeatMode;
        }

        public List<String> getChannels() {
            return new ArrayList<>(channels);
        }

        public String getNote() {
            return note;
        }

        public boolean isActive() {
            return active;
        }

        public ReminderEntry withActive(boolean newActive) {
            return new ReminderEntry(reminderId, programName, reminderDate, timeSlot, repeatMode, channels, note, newActive);
        }
    }
}