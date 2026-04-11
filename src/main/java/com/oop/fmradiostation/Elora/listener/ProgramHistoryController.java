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
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProgramHistoryController
{
    @javafx.fxml.FXML
    private TextField programNameField;
    @javafx.fxml.FXML
    private DatePicker fromDatePicker;
    @javafx.fxml.FXML
    private TextField searchProgramTextField;
    @javafx.fxml.FXML
    private TableColumn<ProgramHistoryEntry, LocalDate> airDateColumn;
    @javafx.fxml.FXML
    private TextField totalProgramsTextField;
    @javafx.fxml.FXML
    private TableColumn<ProgramHistoryEntry, String> categoryColumn;
    @javafx.fxml.FXML
    private TextArea summaryTextArea;
    @javafx.fxml.FXML
    private TableColumn<ProgramHistoryEntry, String> programNameColumn;
    @javafx.fxml.FXML
    private TableColumn<ProgramHistoryEntry, String> timeColumn;
    @javafx.fxml.FXML
    private TextField favoriteTypeTextField;
    @javafx.fxml.FXML
    private ComboBox<String> categoryComboBox;
    @javafx.fxml.FXML
    private TableView<ProgramHistoryEntry> programHistoryTableView;
    @javafx.fxml.FXML
    private TableColumn<ProgramHistoryEntry, String> programIdColumn;
    @javafx.fxml.FXML
    private TextField ratingField;
    @javafx.fxml.FXML
    private TextField hostNameField;
    @javafx.fxml.FXML
    private TextField durationField;

    private final List<ProgramHistoryEntry> allPrograms = new ArrayList<>();
    private List<ProgramHistoryEntry> currentViewPrograms = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        programIdColumn.setCellValueFactory(new PropertyValueFactory<>("programId"));
        programNameColumn.setCellValueFactory(new PropertyValueFactory<>("programName"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        airDateColumn.setCellValueFactory(new PropertyValueFactory<>("airDate"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        categoryComboBox.getItems().setAll("All", "Music", "News", "Sports", "Talk Show", "Entertainment");
        categoryComboBox.setValue("All");

        summaryTextArea.setEditable(false);
        seedDummyPrograms();
        renderTableAndStats(allPrograms, "Loaded full program history.");
    }

    @javafx.fxml.FXML
    public void loadButtonOnClick(ActionEvent actionEvent) {
        categoryComboBox.setValue("All");
        searchProgramTextField.clear();
        fromDatePicker.setValue(null);
        
        clearDetails();
        renderTableAndStats(allPrograms, "Loaded full program history.");
    }

    @javafx.fxml.FXML
    public void exportButtonOnClick(ActionEvent actionEvent) {
        if (currentViewPrograms.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Nothing to Export", "There are no records in the current view.");
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Program History Export\n")
                .append("----------------------\n")
                .append("Total Programs: ").append(totalProgramsTextField.getText()).append("\n")
                .append("Favorite Type: ").append(favoriteTypeTextField.getText()).append("\n\n");

        int limit = Math.min(5, currentViewPrograms.size());
        for (int i = 0; i < limit; i++) {
            ProgramHistoryEntry entry = currentViewPrograms.get(i);
            builder.append(entry.getProgramId()).append(" | ")
                    .append(entry.getProgramName()).append(" | ")
                    .append(entry.getCategory()).append(" | ")
                    .append(entry.getAirDate()).append(" | ")
                    .append(entry.getTime()).append("\n");
        }

        summaryTextArea.setText(builder.toString());
        showAlert(Alert.AlertType.INFORMATION, "Export Preview", "Export preview generated in summary field.");
    }

    @javafx.fxml.FXML
    public void clearButtonOnClick(ActionEvent actionEvent) {
        searchProgramTextField.clear();
        fromDatePicker.setValue(null);
        categoryComboBox.setValue("All");
        clearDetails();
        summaryTextArea.clear();
        programHistoryTableView.getSelectionModel().clearSelection();
    }

    @javafx.fxml.FXML
    public void backButtonOnclick(ActionEvent actionEvent) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(
                getClass().getResource("/com/oop/fmradiostation/Elora/listener/listenerDashboardScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Listener Dashboard");
        window.setScene(scene2);
        window.show();
    }

    @javafx.fxml.FXML
    public void viewButtonOnClick(ActionEvent actionEvent) {
        ProgramHistoryEntry selected = programHistoryTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a program row to view details.");
            return;
        }
        populateDetails(selected);
    }

    @javafx.fxml.FXML
    public void searchButtonOnClick(ActionEvent actionEvent) {
        String keyword = searchProgramTextField.getText() == null ? "" : searchProgramTextField.getText().trim().toLowerCase();
        String category = categoryComboBox.getValue() == null ? "All" : categoryComboBox.getValue();
        LocalDate fromDate = fromDatePicker.getValue();

        List<ProgramHistoryEntry> filtered = allPrograms.stream()
                .filter(entry -> "All".equals(category) || entry.getCategory().equalsIgnoreCase(category))
                .filter(entry -> keyword.isEmpty()
                        || entry.getProgramName().toLowerCase().contains(keyword)
                        || entry.getHostName().toLowerCase().contains(keyword)
                        || entry.getSummary().toLowerCase().contains(keyword))
                .filter(entry -> fromDate == null || !entry.getAirDate().isBefore(fromDate))
                .collect(Collectors.toList());

        renderTableAndStats(filtered, filtered.isEmpty()
                ? "No program history found for selected filters."
                : "Loaded " + filtered.size() + " filtered program(s).");

        if (filtered.isEmpty()) {
            clearDetails();
            showAlert(Alert.AlertType.INFORMATION, "No Results", "No program history entries matched your search.");
        }
    }

    private void renderTableAndStats(List<ProgramHistoryEntry> records, String summaryMessage) {
        currentViewPrograms = new ArrayList<>(records);
        programHistoryTableView.getItems().setAll(currentViewPrograms);
        totalProgramsTextField.setText(String.valueOf(currentViewPrograms.size()));
        favoriteTypeTextField.setText(findFavoriteCategory(currentViewPrograms));
        summaryTextArea.setText(summaryMessage);
    }

    private String findFavoriteCategory(List<ProgramHistoryEntry> records) {
        if (records.isEmpty()) {
            return "N/A";
        }

        Map<String, Long> counts = new LinkedHashMap<>();
        for (ProgramHistoryEntry entry : records) {
            counts.put(entry.getCategory(), counts.getOrDefault(entry.getCategory(), 0L) + 1L);
        }

        return counts.entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("N/A");
    }

    private void populateDetails(ProgramHistoryEntry selected) {
        programNameField.setText(selected.getProgramName());
        hostNameField.setText(selected.getHostName());
        durationField.setText(selected.getDuration());
        ratingField.setText(String.format("%.1f", selected.getRating()));
        summaryTextArea.setText(selected.getSummary());
    }

    private void clearDetails() {
        programNameField.clear();
        hostNameField.clear();
        durationField.clear();
        ratingField.clear();
    }

    private void seedDummyPrograms() {
        if (!allPrograms.isEmpty()) {
            return;
        }

        allPrograms.add(new ProgramHistoryEntry("PH-101", "Wake Up Beats", "Music", LocalDate.now().minusDays(1),
                "07:00-09:00", "RJ Neha", "120 mins", 4.7,
                "High-energy songs and listener shout-outs."));
        allPrograms.add(new ProgramHistoryEntry("PH-102", "Traffic Talk", "News", LocalDate.now().minusDays(2),
                "09:00-10:00", "RJ Fahim", "60 mins", 4.2,
                "Traffic alerts and city updates."));
        allPrograms.add(new ProgramHistoryEntry("PH-103", "Lunch Mix", "Entertainment", LocalDate.now().minusDays(3),
                "13:00-14:30", "RJ Tina", "90 mins", 4.4,
                "Midday entertainment and dedication songs."));
        allPrograms.add(new ProgramHistoryEntry("PH-104", "Live Match Review", "Sports", LocalDate.now().minusDays(4),
                "16:00-17:30", "RJ Sadi", "90 mins", 4.6,
                "Match breakdown and fan reactions."));
        allPrograms.add(new ProgramHistoryEntry("PH-105", "Memory Lane", "Talk Show", LocalDate.now().minusDays(5),
                "18:00-20:00", "RJ Rafi", "120 mins", 4.3,
                "Stories and interviews with callers."));
    }

    private void showAlert(Alert.AlertType type, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle("Program History");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static class ProgramHistoryEntry {
        private final String programId;
        private final String programName;
        private final String category;
        private final LocalDate airDate;
        private final String time;
        private final String hostName;
        private final String duration;
        private final double rating;
        private final String summary;

        public ProgramHistoryEntry(String programId, String programName, String category, LocalDate airDate,
                                   String time, String hostName, String duration, double rating, String summary) {
            this.programId = programId;
            this.programName = programName;
            this.category = category;
            this.airDate = airDate;
            this.time = time;
            this.hostName = hostName;
            this.duration = duration;
            this.rating = rating;
            this.summary = summary;
        }

        public String getProgramId() {
            return programId;
        }

        public String getProgramName() {
            return programName;
        }

        public String getCategory() {
            return category;
        }

        public LocalDate getAirDate() {
            return airDate;
        }

        public String getTime() {
            return time;
        }

        public String getHostName() {
            return hostName;
        }

        public String getDuration() {
            return duration;
        }

        public double getRating() {
            return rating;
        }

        public String getSummary() {
            return summary;
        }
    }
}