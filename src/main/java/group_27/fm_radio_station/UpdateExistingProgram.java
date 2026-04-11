package group_27.fm_radio_station;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UpdateExistingProgram {
    @FXML private TextField searchField;
    @FXML private TableView<?> scheduleTableView;
    @FXML private TextField editNameField;
    @FXML private TextField editTimeField;
    @FXML private TextField editDurationField;

    @javafx.fxml.FXML
    public void initialize() {
        if (scheduleTableView != null){
            scheduleTableView.setPlaceholder(new Label("Search and Select a program to edit."));
        }
    }
    @javafx.fxml.FXML
    void handleSearch(ActionEvent event) {
        // if (searchField == null) return;
        String query = searchField.getText();
        if (query.isEmpty()){
            System.out.println("Search field is empty");
            return;
        }
        System.out.println("Searching for :" + query + "(Event :" + event.getEventType() +")");
    }
    @javafx.fxml.FXML
    void handleUpdateSchedule(ActionEvent event){
        String updatedTitle = editNameField.getText();
        String updatedTime = editTimeField.getText();
        String updatedDuration = editDurationField.getText();

        if (updatedTitle.isEmpty() || updatedTime.isEmpty() || updatedDuration.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Fill all fields");
            alert.show();
            return;
        }
        if (!updatedTime.contains(":")){
            System.out.println("Invalid time format.");
            return;
        }
        System.out.println("Updating database for"+updatedTitle);
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Update Successful");
        success.setHeaderText("Schedule Program Updated");
        success.setContentText("System saves changes and shows confirmation text");
        success.show();
    }
    @javafx.fxml.FXML
    void handleEditSchedule(ActionEvent event){
        if (event.getSource()!=null){
            System.out.println("Loading selected row into workspace.");
        }

    }
}
