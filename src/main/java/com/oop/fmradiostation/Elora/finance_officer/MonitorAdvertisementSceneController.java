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

public class MonitorAdvertisementSceneController
{
    @javafx.fxml.FXML
    private TextField runningAdsTextField;
    @javafx.fxml.FXML
    private DatePicker monitorDatePicker;
    @javafx.fxml.FXML
    private TableColumn<AdvertisementCampaign, String> adIdColumn;
    @javafx.fxml.FXML
    private TextField pendingAdsTextField;
    @javafx.fxml.FXML
    private TableView<AdvertisementCampaign> advertisementTableView;
    @javafx.fxml.FXML
    private TableColumn<AdvertisementCampaign, LocalDate> startDateColumn;
    @javafx.fxml.FXML
    private TableColumn<AdvertisementCampaign, String> companyColumn;
    @javafx.fxml.FXML
    private TableColumn<AdvertisementCampaign, String> typeColumn;
    @javafx.fxml.FXML
    private TableColumn<AdvertisementCampaign, LocalDate> endDateColumn;
    @javafx.fxml.FXML
    private TextField advertisementIdTextField;
    @javafx.fxml.FXML
    private TableColumn<AdvertisementCampaign, String> titleColumn;
    @javafx.fxml.FXML
    private ComboBox<String> statusComboBox;
    @javafx.fxml.FXML
    private TableColumn<AdvertisementCampaign, String> statusColumn;
    @javafx.fxml.FXML
    private TextField totalAdsTextField;
    @javafx.fxml.FXML
    private TextArea remarksTextArea;
    @javafx.fxml.FXML
    private TextField companyTextField;

    private final List<AdvertisementCampaign> allAdvertisements = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        adIdColumn.setCellValueFactory(new PropertyValueFactory<>("advertisementId"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("advertisementTitle"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("adType"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        statusComboBox.getItems().setAll("All", "Pending", "Active", "Paused", "Completed");
        statusComboBox.setValue("All");

        monitorDatePicker.setValue(LocalDate.now());
        remarksTextArea.setEditable(false);

        seedDummyAdvertisements();
        refreshTable(allAdvertisements, "Loaded advertisement monitor data.");
    }

    @javafx.fxml.FXML
    public void clearButtonOnClick(ActionEvent actionEvent) {
        // ...existing code...
        advertisementIdTextField.clear();
        companyTextField.clear();
        statusComboBox.setValue("All");
        monitorDatePicker.setValue(LocalDate.now());
        remarksTextArea.clear();
    }

    @javafx.fxml.FXML
    public void refreshButtonOnClick(ActionEvent actionEvent) {
        // ...existing code...
        advertisementTableView.getSelectionModel().clearSelection();
        refreshTable(allAdvertisements, "Refreshed all advertisement records.");
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
    public void searchButtonOnClick(ActionEvent actionEvent) {
        // ...existing code...
        String adId = getTrimmedText(advertisementIdTextField);
        String company = getTrimmedText(companyTextField);
        String status = statusComboBox.getValue() == null ? "All" : statusComboBox.getValue();
        LocalDate monitorDate = monitorDatePicker.getValue();

        List<AdvertisementCampaign> filtered = allAdvertisements.stream()
                .filter(ad -> adId.isEmpty() || ad.getAdvertisementId().toLowerCase().contains(adId.toLowerCase()))
                .filter(ad -> company.isEmpty() || ad.getCompanyName().toLowerCase().contains(company.toLowerCase()))
                .filter(ad -> "All".equals(status) || ad.getStatus().equalsIgnoreCase(status))
                .filter(ad -> monitorDate == null || (!ad.getStartDate().isAfter(monitorDate) && !ad.getEndDate().isBefore(monitorDate)))
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            refreshTable(filtered, "No advertisements matched the search criteria.");
            showAlert(Alert.AlertType.INFORMATION, "No Results", "No advertisements found for the selected filters.");
            return;
        }

        refreshTable(filtered, buildRemarks(filtered));
    }

    private void refreshTable(List<AdvertisementCampaign> records, String remarks) {
        advertisementTableView.getItems().setAll(records);
        totalAdsTextField.setText(String.valueOf(records.size()));

        long running = records.stream().filter(ad -> "Active".equalsIgnoreCase(ad.getStatus())).count();
        long pending = records.stream().filter(ad -> "Pending".equalsIgnoreCase(ad.getStatus())).count();

        runningAdsTextField.setText(String.valueOf(running));
        pendingAdsTextField.setText(String.valueOf(pending));
        remarksTextArea.setText(remarks);
    }

    private void seedDummyAdvertisements() {
        if (!allAdvertisements.isEmpty()) {
            return;
        }

        allAdvertisements.add(new AdvertisementCampaign(
                "AD-101",
                "FreshMart",
                "Weekend Grocery Deals",
                30,
                9500,
                "Spot Ad",
                LocalDate.now().minusDays(4),
                LocalDate.now().plusDays(10),
                "Morning (6 AM - 10 AM)",
                "Active",
                List.of("Friday", "Saturday", "Sunday"),
                "Currently running in morning slot"
        ));

        allAdvertisements.add(new AdvertisementCampaign(
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
                "Awaiting launch approval"
        ));

        allAdvertisements.add(new AdvertisementCampaign(
                "AD-103",
                "City Clinic",
                "Health Check Offer",
                20,
                6200,
                "Interview Mention",
                LocalDate.now().minusDays(12),
                LocalDate.now().plusDays(6),
                "Noon (10 AM - 2 PM)",
                "Paused",
                List.of("Tuesday", "Friday"),
                "Paused due to campaign review"
        ));

        allAdvertisements.add(new AdvertisementCampaign(
                "AD-104",
                "BrightHome",
                "Festive Home Sale",
                25,
                8300,
                "Banner Promotion",
                LocalDate.now().minusDays(20),
                LocalDate.now().minusDays(1),
                "Night (8 PM - 12 AM)",
                "Completed",
                List.of("Saturday", "Sunday"),
                "Campaign completed successfully"
        ));
    }

    private String buildRemarks(List<AdvertisementCampaign> records) {
        long active = records.stream().filter(ad -> "Active".equalsIgnoreCase(ad.getStatus())).count();
        long pending = records.stream().filter(ad -> "Pending".equalsIgnoreCase(ad.getStatus())).count();
        long paused = records.stream().filter(ad -> "Paused".equalsIgnoreCase(ad.getStatus())).count();
        long completed = records.stream().filter(ad -> "Completed".equalsIgnoreCase(ad.getStatus())).count();
        return "Search applied. Active=" + active + ", Pending=" + pending + ", Paused=" + paused + ", Completed=" + completed;
    }

    private String getTrimmedText(TextField field) {
        return field.getText() == null ? "" : field.getText().trim();
    }

    private void showAlert(Alert.AlertType type, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle("Monitor Advertisement");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}