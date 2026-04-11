package com.oop.fmradiostation.Elora.finance_officer;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManageExpenseRequestSceneController {
    @javafx.fxml.FXML
    private TableColumn<ExpenseRequest, String> departmentCol;
    @javafx.fxml.FXML
    private TableColumn<ExpenseRequest, String> amountCol;
    @javafx.fxml.FXML
    private TableColumn<ExpenseRequest, String> statusCol;
    @javafx.fxml.FXML
    private TableColumn<ExpenseRequest, String> reasonCol;
    @javafx.fxml.FXML
    private TableColumn<ExpenseRequest, String> requestIdCol;
    @javafx.fxml.FXML
    private TableView<ExpenseRequest> expenseTable;
    @javafx.fxml.FXML
    private TextArea messageTextField;

    private final List<ExpenseRequest> expenseRequests = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        requestIdCol.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        departmentCol.setCellValueFactory(new PropertyValueFactory<>("department"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        reasonCol.setCellValueFactory(new PropertyValueFactory<>("reason"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        seedDummyRequests();
        expenseTable.getItems().setAll(expenseRequests);

        messageTextField.setEditable(false);
        messageTextField.setText("Select a request to approve or reject.");
    }

    @javafx.fxml.FXML
    public void rejectButtonOnClick(ActionEvent actionEvent) {
        updateStatusForSelected("Rejected");
    }

    @javafx.fxml.FXML
    public void approveButton(ActionEvent actionEvent) {
        updateStatusForSelected("Approved");
    }

    private void updateStatusForSelected(String newStatus) {
        ExpenseRequest selectedRequest = expenseTable.getSelectionModel().getSelectedItem();
        if (selectedRequest == null) {
            messageTextField.setText("Please select a request first.");
            return;
        }

        if (!"Pending".equals(selectedRequest.getStatus())) {
            messageTextField.setText("Request " + selectedRequest.getRequestId() + " is already "
                    + selectedRequest.getStatus() + ".");
            return;
        }

        selectedRequest.setStatus(newStatus);
        expenseTable.refresh();
        messageTextField.setText("Request " + selectedRequest.getRequestId() + " " + newStatus.toLowerCase() + ".");
    }

    private void seedDummyRequests() {
        if (!expenseRequests.isEmpty()) {
            return;
        }

        expenseRequests.add(new ExpenseRequest("REQ-101", "Production", "$450", "Microphone replacement", "Pending"));
        expenseRequests.add(new ExpenseRequest("REQ-102", "Marketing", "$300", "Social media campaign boost", "Pending"));
        expenseRequests.add(new ExpenseRequest("REQ-103", "Sound Engineering", "$220", "Mixer cable upgrade", "Pending"));
        expenseRequests.add(new ExpenseRequest("REQ-104", "Studio", "$150", "Guest refreshments", "Pending"));
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
