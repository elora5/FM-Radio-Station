package com.oop.fmradiostation.Elora.listener;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CustomerDashboardSceneController {
    @javafx.fxml.FXML
    public void reviewButtonOnClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void customerCareButtonOnClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void hotelBookingButtonOnClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void tourBookingButtonOnClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void cancelBookingButtonOnClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void logoutButtonOnClicked(ActionEvent actionEvent)  throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/oop/fmradiostation/login/LoginScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Signup");
        window.setScene(scene2);
        window.show();
    }

    @javafx.fxml.FXML
    public void paymentButtonOnClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void rescheduleBookingButtonOnClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void flightBookingButtonOnClicked(ActionEvent actionEvent) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/oop/fmradiostation/Elora/listener/bookFlightScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Book Flight");
        window.setScene(scene2);
        window.show();
    }
}
