package com.oop.fmradiostation.Elora.listener;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.util.Objects;

public class BookFlightSceneController {

    @FXML
    private TableColumn<Flight, String> arriveCols;

    @FXML
    private TableColumn<Flight, String> baseFareCols;

    @FXML
    private TableColumn<Flight, String> checkInCols;

    @FXML
    private ComboBox<String> classComboBox;

    @FXML
    private TableColumn<Flight, String> departCols;

    @FXML
    private TableView<Flight> flightTable;

    @FXML
    private TableColumn<Flight, String> fromCols;

    @FXML
    private ComboBox<String> fromComboBox;

    @FXML
    private DatePicker journeyDatePicker;

    @FXML
    private TableColumn<Flight, String> operatedByCols;

    @FXML
    private TableColumn<Flight, String> taxCols;

    @FXML
    private TableColumn<Flight, String> toCols;

    @FXML
    private ComboBox<String> toComboBox;
    @FXML
    private TextField travellerTextField;


    ArrayList<Flight> flights;
    // generate some dummy flight details
    private void generateDummyFlights() {
        flights.add(new Flight("Dhaka", "Chittagong", "08:00", "10:00", "07:00", "$100", "$20", "US Bangla "));
        flights.add(new Flight("Sylhet", "Cox's Bazar", "09:00", "11:30", "08:00", "$150", "$25", "Air Astra "));
        flights.add(new Flight("Barishal", "Jessore", "10:00", "12:00", "09:00", "$80", "$15", "NovoAir "));
        flights.add(new Flight("Saidpur", "Delhi", "11:00", "13:00", "10:00", "$200", "$30", "Biman Bangladesh"));
        flights.add(new Flight("Mumbai", "Kolkata", "12:00", "14:00", "11:00", "$250", "$35", "US Bangla"));
        flights.add(new Flight("Chennai", "Sylhet", "13:00", "15:00", "12:00", "$300", "$40", "Indigo "));
        flights.add(new Flight("Dhaka", "Cox's Bazar", "14:00", "16:00", "13:00", "$100", "$20", "Air Astra "));
        flights.add(new Flight("Cox's Bazar", "Dhaka", "15:00", "17:00", "14:00", "$100", "$20", "US Bangla "));

    }

    @FXML
    public void initialize(){
        fromComboBox.getItems().addAll("Dhaka","Chittagong","Sylhet","Barishal","Cox's Bazar",
                                            "Jessore","Saidpur","Delhi","Mumbai","Kolkata","Chennai");
        toComboBox.getItems().addAll("Dhaka","Chittagong","Sylhet","Barishal","Cox's Bazar",
                "Jessore","Saidpur","Delhi","Mumbai","Kolkata","Chennai");
        classComboBox.getItems().addAll("Economy","Business","First Class");
        journeyDatePicker.setValue(LocalDate.now());


        fromCols.setCellValueFactory(new PropertyValueFactory<>("from"));
        toCols.setCellValueFactory(new PropertyValueFactory<>("to"));
        departCols.setCellValueFactory(new PropertyValueFactory<>("depart"));
        arriveCols.setCellValueFactory(new PropertyValueFactory<>("arrive"));
        checkInCols.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        baseFareCols.setCellValueFactory(new PropertyValueFactory<>("baseFare"));
        taxCols.setCellValueFactory(new PropertyValueFactory<>("tax"));
        operatedByCols.setCellValueFactory(new PropertyValueFactory<>("operatedBy"));


        flights = new ArrayList<>();
        generateDummyFlights();
        flightTable.getItems().setAll(flights);
    }

    @FXML
    void backButton(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/oop/fmradiostation/Elora/customerDashboardScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Customer Dashboard");
        window.setScene(scene2);
        window.show();
    }

    @FXML
    void searchButton(ActionEvent event) {
        // Check if all inputs are provided
        if (fromComboBox.getValue() == null || toComboBox.getValue() == null ||
                classComboBox.getValue() == null || travellerTextField.getText().isEmpty()) {

            // Show error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields before searching for flight.");
            alert.showAndWait();
        } else {
            // Initialize a new list to hold filtered flights
            ArrayList<Flight> filteredFlights = new ArrayList<>();

            // Manually iterate over flights to find matches
            for (Flight flight : flights) {
                if (flight.getFrom().equals(fromComboBox.getValue()) &&
                        flight.getTo().equals(toComboBox.getValue())) {
                    // Assuming class and number of travelers are not used for filtering in this example
                    filteredFlights.add(flight);
                }
            }

            // Check if any flights match the search criteria
            if (filteredFlights.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No Flights Found");
                alert.setHeaderText(null);
                alert.setContentText("No flights match your search criteria.");
                alert.showAndWait();
            } else {
                // Display the filtered flights in the table
                flightTable.getItems().clear();
                flightTable.getItems().setAll(filteredFlights);
            }
        }
    }

}
