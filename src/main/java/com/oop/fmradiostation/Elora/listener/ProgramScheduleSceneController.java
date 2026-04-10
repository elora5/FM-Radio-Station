package com.oop.fmradiostation.Elora.listener;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ProgramScheduleSceneController
{
    @javafx.fxml.FXML
    private TableView<ProgramScheduleEntry> programListTableView;
    @javafx.fxml.FXML
    private TableColumn<ProgramScheduleEntry, String> endTimeCol;
    @javafx.fxml.FXML
    private TableColumn<ProgramScheduleEntry, String> programNameCol;
    @javafx.fxml.FXML
    private TableColumn<ProgramScheduleEntry, String> startTimeCol;
    @javafx.fxml.FXML
    private TableColumn<ProgramScheduleEntry, String> categoryCol;
    @javafx.fxml.FXML
    private TableColumn<ProgramScheduleEntry, String> rjNameCol;
    @javafx.fxml.FXML
    private ComboBox<String> selectStationComboBox;
    @javafx.fxml.FXML
    private TextField messageTextField;

    private final Map<String, List<ProgramScheduleEntry>> stationScheduleMap = new LinkedHashMap<>();

    @javafx.fxml.FXML
    public void initialize() {
        programNameCol.setCellValueFactory(new PropertyValueFactory<>("programName"));
        rjNameCol.setCellValueFactory(new PropertyValueFactory<>("rjName"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        seedDummySchedule();
        selectStationComboBox.getItems().setAll(stationScheduleMap.keySet());

        messageTextField.setEditable(false);
        messageTextField.setText("Select a station and click Load Schedule.");

        if (!selectStationComboBox.getItems().isEmpty()) {
            selectStationComboBox.getSelectionModel().selectFirst();
            loadScheduleForSelectedStation();
        }
    }

    @javafx.fxml.FXML
    public void loadSceduleButtonOnClick(ActionEvent actionEvent) {
        loadScheduleForSelectedStation();
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

    private void loadScheduleForSelectedStation() {
        String selectedStation = selectStationComboBox.getValue();
        if (selectedStation == null || selectedStation.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Station Selected");
            alert.setHeaderText("Please select a station first");
            alert.setContentText("Choose a station from the dropdown and click Load Schedule.");
            alert.showAndWait();
            return;
        }

        List<ProgramScheduleEntry> schedule = stationScheduleMap.get(selectedStation);
        if (schedule == null) {
            programListTableView.getItems().clear();
            messageTextField.setText("No schedule data available.");
            return;
        }

        programListTableView.getItems().setAll(schedule);
        messageTextField.setText("Loaded " + schedule.size() + " programs for " + selectedStation);
    }

    private void seedDummySchedule() {
        List<ProgramScheduleEntry> cityBeats = new ArrayList<>();
        cityBeats.add(new ProgramScheduleEntry("Wake Up Beats", "RJ Neha", "07:00", "09:00", "Music"));
        cityBeats.add(new ProgramScheduleEntry("Traffic Talk", "RJ Fahim", "09:00", "10:00", "News"));
        cityBeats.add(new ProgramScheduleEntry("Lunch Mix", "RJ Tina", "13:00", "14:30", "Entertainment"));

        List<ProgramScheduleEntry> retroWave = new ArrayList<>();
        retroWave.add(new ProgramScheduleEntry("Golden Hour", "RJ Sam", "08:00", "10:00", "Classic Hits"));
        retroWave.add(new ProgramScheduleEntry("Retro Request", "RJ Mahi", "11:00", "12:00", "Requests"));
        retroWave.add(new ProgramScheduleEntry("Memory Lane", "RJ Rafi", "18:00", "20:00", "Talk Show"));

        List<ProgramScheduleEntry> sportsTalk = new ArrayList<>();
        sportsTalk.add(new ProgramScheduleEntry("Morning Sports Brief", "RJ Tuhin", "07:30", "08:30", "Sports News"));
        sportsTalk.add(new ProgramScheduleEntry("Live Match Review", "RJ Sadi", "16:00", "17:30", "Analysis"));
        sportsTalk.add(new ProgramScheduleEntry("Fan Hotline", "RJ Arman", "20:00", "21:00", "Call-in"));

        stationScheduleMap.put("89.2 FM - City Beats", cityBeats);
        stationScheduleMap.put("91.6 FM - Retro Wave", retroWave);
        stationScheduleMap.put("96.4 FM - Sports Talk", sportsTalk);
    }
}