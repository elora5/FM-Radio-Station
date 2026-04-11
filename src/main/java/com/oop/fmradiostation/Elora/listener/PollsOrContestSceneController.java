package com.oop.fmradiostation.Elora.listener;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class PollsOrContestSceneController
{
    @javafx.fxml.FXML
    private TextField pollsContestTitleTextField;
    @javafx.fxml.FXML
    private RadioButton noRadioButton;
    @javafx.fxml.FXML
    private TextField pollsContestDescriptionTextField;
    @javafx.fxml.FXML
    private RadioButton yesRadioButton;

    private final ToggleGroup responseToggleGroup = new ToggleGroup();
    private final List<PollsOrContestSubmission> submissions = new ArrayList<>();
    private final List<PollTemplate> pollTemplates = new ArrayList<>();
    private final Random random = new Random();
    private int submissionCounter = 1;
    @javafx.fxml.FXML
    private ComboBox<String> radioStationComboBox;

    @javafx.fxml.FXML
    public void initialize() {
        yesRadioButton.setToggleGroup(responseToggleGroup);
        noRadioButton.setToggleGroup(responseToggleGroup);
        seedStations();
        seedPollTemplates();
        loadRandomPollData();
        clearResponseSelection();
    }

    @javafx.fxml.FXML
    public void submitButtonOnClick(ActionEvent actionEvent) {
        String title = pollsContestTitleTextField.getText() == null ? "" : pollsContestTitleTextField.getText().trim();
        String description = pollsContestDescriptionTextField.getText() == null ? "" : pollsContestDescriptionTextField.getText().trim();
        String station = radioStationComboBox.getValue();

        if (station == null || station.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Station");
            alert.setHeaderText("Please choose a radio station");
            alert.setContentText("Select a station from the list before submitting.");
            alert.showAndWait();
            return;
        }

        if (title.isEmpty() || description.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Information");
            alert.setHeaderText("Title and description are required");
            alert.setContentText("Please fill in both fields before submitting.");
            alert.showAndWait();
            return;
        }

        String response = yesRadioButton.isSelected() ? "Yes" : noRadioButton.isSelected() ? "No" : "";
        if (response.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Response Selected");
            alert.setHeaderText("Please choose Yes or No");
            alert.setContentText("Select one response for this poll/contest.");
            alert.showAndWait();
            return;
        }

        String submissionId = String.format("POL-%03d", submissionCounter++);
        String submittedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        PollsOrContestSubmission submission = new PollsOrContestSubmission(
                submissionId,
                title,
                description,
                response,
                submittedAt
        );
        submissions.add(submission);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Submitted");
        alert.setHeaderText("Poll/Contest response submitted successfully");
        alert.setContentText("ID: " + submission.getSubmissionId()
                + "\nStation: " + station
                + "\nResponse: " + submission.getResponse()
                + "\nTime: " + submission.getSubmittedAt()
                + "\nTotal submissions this session: " + submissions.size());
        alert.showAndWait();

        clearResponseSelection();
    }

    private void loadRandomPollData() {
        if (pollTemplates.isEmpty()) {
            pollsContestTitleTextField.setText("Listener Choice Poll");
            pollsContestDescriptionTextField.setText("Do you want us to add more interactive segments?");
            return;
        }

        PollTemplate selectedTemplate = pollTemplates.get(random.nextInt(pollTemplates.size()));
        pollsContestTitleTextField.setText(selectedTemplate.getTitle());
        pollsContestDescriptionTextField.setText(selectedTemplate.getDescription());
    }

    private void seedPollTemplates() {
        if (!pollTemplates.isEmpty()) {
            return;
        }

        pollTemplates.add(new PollTemplate("Weekend Mega Quiz", "Do you want a live audience quiz segment this Friday night?"));
        pollTemplates.add(new PollTemplate("Morning Show Vote", "Should we add a daily listener shout-out section at 8 AM?"));
        pollTemplates.add(new PollTemplate("Retro Hour", "Would you like a dedicated retro songs program every Thursday?"));
        pollTemplates.add(new PollTemplate("RJ Challenge", "Should listeners challenge RJ with rapid-fire questions this week?"));
        pollTemplates.add(new PollTemplate("Sports Talk Special", "Do you want a post-match fan call-in show tonight?"));
    }

    private void seedStations() {
        radioStationComboBox.getItems().setAll(
                "FM 88.0 - City Beats",
                "FM 91.6 - Pop Pulse",
                "FM 94.2 - Retro Gold",
                "FM 99.4 - Night Vibes"
        );
        radioStationComboBox.getSelectionModel().selectFirst();
    }

    private void clearResponseSelection() {
        responseToggleGroup.selectToggle(null);
    }

    private static class PollTemplate {
        private final String title;
        private final String description;

        private PollTemplate(String title, String description) {
            this.title = title;
            this.description = description;
        }

        private String getTitle() {
            return title;
        }

        private String getDescription() {
            return description;
        }
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
}