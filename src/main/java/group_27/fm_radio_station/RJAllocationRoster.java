package group_27.fm_radio_station;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class RJAllocationRoster {
    @javafx.fxml.FXML
    private TableView<?>activeProgramsTable;
    @javafx.fxml.FXML
    private ComboBox<String> rjCombobox;
    @javafx.fxml.FXML
    private Label availabilityLabel;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {
        if (rjCombobox != null) {
            rjCombobox.getItems().addAll("RJ Paul","RJ Elora"," RJ Sabbir","RJ Das");
        }
        if (activeProgramsTable !=null){
            System.out.println("Table is initialized.");
        }
    }
    @javafx.fxml.FXML
    public void handleAssignToProgram(ActionEvent actionEvent) {
        if (actionEvent == null) return;
        assignButton.setDisable(false);
        activeProgramsTable.setVisible(true);
        String selectedRJ = rjCombobox.getValue();


        if (selectedRJ== null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Select a Radio Jockey first");
            alert.show();
            return;
        }

        System.out.println(" Assignning RJ :" + selectedRJ);
        availabilityLabel.setText("Availability : Assigned");
        statusLabel.setText("Assigned Successfully : " + selectedRJ);
        statusLabel.setVisible(true);

        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Success");
        success.setHeaderText("RJ Allocated");
        success.setContentText("The RJ has been successfully allocated to the program roster");
        success.show();
