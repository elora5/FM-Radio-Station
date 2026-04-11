package group_27.fm_radio_station;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PrepareDailySchedule {
    @javafx.fxml.FXML
    private TextField programNameField;
    @javafx.fxml.FXML
    private TextField timeField;
    @javafx.fxml.FXML
    private TextField durationField;
    @javafx.fxml.FXML
    private ComboBox<String> categoryCombobox;
    @javafx.fxml.FXML
    private TableView<?> scheduleTable;
    @javafx.fxml.FXML
    private Button publishButton;

    @javafx.fxml.FXML
    public void initialize() {
        if (categoryCombobox != null) {
            categoryCombobox.getItems().addAll("Music","Talk Show","News","Live Session");
        }
    }
    @javafx.fxml.FXML
    public void handlePublish(ActionEvent actionEvent) {
        if (actionEvent == null) return;
        publishButton.setDisable(false);
        scheduleTable.setVisible(true);
        String name = programNameField.getText();
        String time = timeField.getText();
        String duration = durationField.getText();
        String category = categoryCombobox.getValue();

        if (name.isEmpty() || time.isEmpty() || duration.isEmpty () || category == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Fill all fields");
            alert.show();
            return;
        }

        if (scheduleTable != null){
            System.out.println(" table is ready.");
        }
        System.out.println("Schedule saved for:" + name);
        scheduleTable.setVisible(true);
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Success");
        success.setHeaderText("SchedulePublished");
        success.setContentText("System saves schedule and live for broadcast view");
        success.show();
    }
}