package com.oop.fmradiostation.Elora.finance_officer;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FinanceOfficerDashboardSceneController {
    @javafx.fxml.FXML
    public void manageExpensesRequestButtonOnClick(ActionEvent actionEvent) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/oop/fmradiostation/Elora/finance_officer/manageExpenseRequestScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Manage Expenses Request");
        window.setScene(scene2);
        window.show();
    }

    @javafx.fxml.FXML
    public void monthlyReportButtonOnClick(ActionEvent actionEvent) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/oop/fmradiostation/Elora/finance_officer/monthlyReportScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Monthly Report");
        window.setScene(scene2);
        window.show();
    }

    @javafx.fxml.FXML
    public void monitorAdvertisementPaymentButtonOnClick(ActionEvent actionEvent) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/oop/fmradiostation/Elora/finance_officer/monitorAdvertisementScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Monitor Advertisement Payment");
        window.setScene(scene2);
        window.show();
    }

    @javafx.fxml.FXML
    public void setAdSlotPrisingButtonOnClick(ActionEvent actionEvent) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/oop/fmradiostation/Elora/finance_officer/setAdSlotPrisingScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Set Ad Slot Prising");
        window.setScene(scene2);
        window.show();
    }

    @javafx.fxml.FXML
    public void addOrModifiedAdvertiseButtonOnClick(ActionEvent actionEvent) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/oop/fmradiostation/Elora/finance_officer/addOrModifiedAdvertiseScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Add or Modified Advertise");
        window.setScene(scene2);
        window.show();
    }

    @javafx.fxml.FXML
    public void totalIncomeAndExpensesButtonOnClick(ActionEvent actionEvent) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/oop/fmradiostation/Elora/finance_officer/totalIncomeAndExpensesScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Total Income and Expenses");
        window.setScene(scene2);
        window.show();
    }

    @javafx.fxml.FXML
    public void recordAdvertisementRevenueButtonOnClick(ActionEvent actionEvent) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/oop/fmradiostation/Elora/finance_officer/recordAdvertisementRevenueScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Record Advertisement Revenue");
        window.setScene(scene2);
        window.show();
    }

    @javafx.fxml.FXML
    public void ExportFinantialRecordsButtonOnClick(ActionEvent actionEvent) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/oop/fmradiostation/Elora/finance_officer/exportFinantialRecordsScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Export Finantial Records");
        window.setScene(scene2);
        window.show();
    }

    @javafx.fxml.FXML
    public void logoutButtonOnClick(ActionEvent actionEvent) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/oop/fmradiostation/login/LoginScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Login");
        window.setScene(scene2);
        window.show();
    }
}
