package com.oop.fmradiostation.Elora.listener;

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

public class RateShowFeedbackController
{
    @javafx.fxml.FXML
    private RadioButton oneStarRadioButton;
    @javafx.fxml.FXML
    private ComboBox<String> showComboBox;
    @javafx.fxml.FXML
    private RadioButton threeStarRadioButton;
    @javafx.fxml.FXML
    private TableColumn<FeedbackEntry, String> showColumn;
    @javafx.fxml.FXML
    private TableColumn<FeedbackEntry, String> feedbackIdColumn;
    @javafx.fxml.FXML
    private CheckBox funnyCheckBox;
    @javafx.fxml.FXML
    private CheckBox boringCheckBox;
    @javafx.fxml.FXML
    private TextField feedbackIdTextField;
    @javafx.fxml.FXML
    private TextArea previewTextArea;
    @javafx.fxml.FXML
    private RadioButton fourStarRadioButton;
    @javafx.fxml.FXML
    private TableColumn<FeedbackEntry, Integer> ratingColumn;
    @javafx.fxml.FXML
    private TableColumn<FeedbackEntry, String> recommendColumn;
    @javafx.fxml.FXML
    private RadioButton twoStarRadioButton;
    @javafx.fxml.FXML
    private RadioButton fiveStarRadioButton;
    @javafx.fxml.FXML
    private RadioButton noRadioButton;
    @javafx.fxml.FXML
    private TextArea feedbackTextArea;
    @javafx.fxml.FXML
    private TableView<FeedbackEntry> feedbackTableView;
    @javafx.fxml.FXML
    private TableColumn<FeedbackEntry, LocalDate> dateColumn;
    @javafx.fxml.FXML
    private CheckBox informativeCheckBox;
    @javafx.fxml.FXML
    private CheckBox entertainingCheckBox;
    @javafx.fxml.FXML
    private DatePicker reviewDatePicker;
    @javafx.fxml.FXML
    private RadioButton yesRadioButton;

    private ToggleGroup ratingToggleGroup;
    private ToggleGroup recommendToggleGroup;
    private final List<FeedbackEntry> feedbackStore = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        feedbackIdColumn.setCellValueFactory(new PropertyValueFactory<>("feedbackId"));
        showColumn.setCellValueFactory(new PropertyValueFactory<>("show"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        recommendColumn.setCellValueFactory(new PropertyValueFactory<>("recommend"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        showComboBox.getItems().setAll(
                "Wake Up Beats", "Traffic Talk", "Lunch Mix",
                "Golden Hour", "Retro Request", "Fan Hotline"
        );

        ratingToggleGroup = new ToggleGroup();
        oneStarRadioButton.setToggleGroup(ratingToggleGroup);
        twoStarRadioButton.setToggleGroup(ratingToggleGroup);
        threeStarRadioButton.setToggleGroup(ratingToggleGroup);
        fourStarRadioButton.setToggleGroup(ratingToggleGroup);
        fiveStarRadioButton.setToggleGroup(ratingToggleGroup);
        fourStarRadioButton.setSelected(true);

        recommendToggleGroup = new ToggleGroup();
        yesRadioButton.setToggleGroup(recommendToggleGroup);
        noRadioButton.setToggleGroup(recommendToggleGroup);
        yesRadioButton.setSelected(true);

        reviewDatePicker.setValue(LocalDate.now());
        previewTextArea.setEditable(false);

        seedDummyFeedbacks();
        feedbackTableView.getItems().setAll(feedbackStore);
        if (!feedbackStore.isEmpty()) {
            feedbackTableView.getSelectionModel().selectFirst();
            loadFeedbackIntoForm(feedbackStore.get(0));
        }
    }

    @javafx.fxml.FXML
    public void loadButtonOnClick(ActionEvent actionEvent) {
        FeedbackEntry selected = feedbackTableView.getSelectionModel().getSelectedItem();
        String feedbackId = trim(feedbackIdTextField.getText());

        if (selected == null && feedbackId.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing ID", "Select a row or enter feedback ID to load.");
            return;
        }

        FeedbackEntry found = selected != null ? selected : findById(feedbackId);
        if (found == null) {
            showAlert(Alert.AlertType.INFORMATION, "Not Found", "No feedback found for ID: " + feedbackId);
            return;
        }

        loadFeedbackIntoForm(found);
    }

    @javafx.fxml.FXML
    public void clearButtonOnClick(ActionEvent actionEvent) {
        feedbackIdTextField.clear();
        showComboBox.getSelectionModel().clearSelection();
        reviewDatePicker.setValue(LocalDate.now());
        feedbackTextArea.clear();
        entertainingCheckBox.setSelected(false);
        informativeCheckBox.setSelected(false);
        funnyCheckBox.setSelected(false);
        boringCheckBox.setSelected(false);
        fourStarRadioButton.setSelected(true);
        yesRadioButton.setSelected(true);
        previewTextArea.setText("Selected feedback details...");
        feedbackTableView.getSelectionModel().clearSelection();
    }

    @javafx.fxml.FXML
    public void saveButtonOnClick(ActionEvent actionEvent) {
        FeedbackEntry entry = buildFromForm();
        if (entry == null) {
            return;
        }

        if (findById(entry.getFeedbackId()) != null) {
            showAlert(Alert.AlertType.ERROR, "Duplicate ID", "Feedback ID already exists. Use Update.");
            return;
        }

        feedbackStore.add(entry);
        feedbackTableView.getItems().setAll(feedbackStore);
        feedbackTableView.getSelectionModel().select(entry);
        previewTextArea.setText(buildPreview(entry));
        showAlert(Alert.AlertType.INFORMATION, "Saved", "Feedback saved successfully.");
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

    @javafx.fxml.FXML
    public void updateButtonOnClick(ActionEvent actionEvent) {
        FeedbackEntry entry = buildFromForm();
        if (entry == null) {
            return;
        }

        FeedbackEntry existing = findById(entry.getFeedbackId());
        if (existing == null) {
            showAlert(Alert.AlertType.ERROR, "Not Found", "No feedback exists with this ID. Save it first.");
            return;
        }

        int idx = feedbackStore.indexOf(existing);
        if (idx < 0) {
            showAlert(Alert.AlertType.ERROR, "Update Failed", "Could not locate the feedback record.");
            return;
        }

        feedbackStore.set(idx, entry);
        feedbackTableView.getItems().setAll(feedbackStore);
        feedbackTableView.getSelectionModel().select(entry);
        previewTextArea.setText(buildPreview(entry));
        showAlert(Alert.AlertType.INFORMATION, "Updated", "Feedback updated successfully.");
    }

    private FeedbackEntry buildFromForm() {
        String id = trim(feedbackIdTextField.getText());
        String show = showComboBox.getValue();
        LocalDate date = reviewDatePicker.getValue();
        int rating = getSelectedRating();
        String recommend = yesRadioButton.isSelected() ? "Yes" : (noRadioButton.isSelected() ? "No" : null);
        String feedbackText = feedbackTextArea.getText() == null ? "" : feedbackTextArea.getText().trim();
        List<String> tags = getSelectedTags();

        if (id.isEmpty() || show == null || date == null || rating == 0 || recommend == null || feedbackText.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing Fields", "Please fill all required fields before saving.");
            return null;
        }
        if (tags.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Tags", "Select at least one feedback tag.");
            return null;
        }

        return new FeedbackEntry(id, show, rating, recommend, date, feedbackText, tags);
    }

    private void loadFeedbackIntoForm(FeedbackEntry entry) {
        feedbackIdTextField.setText(entry.getFeedbackId());
        showComboBox.setValue(entry.getShow());
        reviewDatePicker.setValue(entry.getDate());
        feedbackTextArea.setText(entry.getFeedbackText());

        setRating(entry.getRating());
        yesRadioButton.setSelected("Yes".equalsIgnoreCase(entry.getRecommend()));
        noRadioButton.setSelected("No".equalsIgnoreCase(entry.getRecommend()));

        entertainingCheckBox.setSelected(entry.getTags().contains("Entertaining"));
        informativeCheckBox.setSelected(entry.getTags().contains("Informative"));
        funnyCheckBox.setSelected(entry.getTags().contains("Funny"));
        boringCheckBox.setSelected(entry.getTags().contains("Boring"));

        previewTextArea.setText(buildPreview(entry));
    }

    private FeedbackEntry findById(String id) {
        for (FeedbackEntry entry : feedbackStore) {
            if (entry.getFeedbackId().equalsIgnoreCase(id)) {
                return entry;
            }
        }
        return null;
    }

    private int getSelectedRating() {
        if (oneStarRadioButton.isSelected()) {
            return 1;
        }
        if (twoStarRadioButton.isSelected()) {
            return 2;
        }
        if (threeStarRadioButton.isSelected()) {
            return 3;
        }
        if (fourStarRadioButton.isSelected()) {
            return 4;
        }
        if (fiveStarRadioButton.isSelected()) {
            return 5;
        }
        return 0;
    }

    private void setRating(int rating) {
        oneStarRadioButton.setSelected(rating == 1);
        twoStarRadioButton.setSelected(rating == 2);
        threeStarRadioButton.setSelected(rating == 3);
        fourStarRadioButton.setSelected(rating == 4);
        fiveStarRadioButton.setSelected(rating == 5);
    }

    private List<String> getSelectedTags() {
        List<String> tags = new ArrayList<>();
        if (entertainingCheckBox.isSelected()) {
            tags.add("Entertaining");
        }
        if (informativeCheckBox.isSelected()) {
            tags.add("Informative");
        }
        if (funnyCheckBox.isSelected()) {
            tags.add("Funny");
        }
        if (boringCheckBox.isSelected()) {
            tags.add("Boring");
        }
        return tags;
    }

    private String buildPreview(FeedbackEntry entry) {
        return "ID: " + entry.getFeedbackId()
                + "\nShow: " + entry.getShow()
                + "\nDate: " + entry.getDate()
                + "\nRating: " + entry.getRating() + "/5"
                + "\nRecommend: " + entry.getRecommend()
                + "\nTags: " + String.join(", ", entry.getTags())
                + "\nFeedback: " + entry.getFeedbackText();
    }

    private void seedDummyFeedbacks() {
        if (!feedbackStore.isEmpty()) {
            return;
        }

        feedbackStore.add(new FeedbackEntry("FB-101", "Wake Up Beats", 5, "Yes", LocalDate.now().minusDays(1),
                "Very energetic morning show with great song selection.", List.of("Entertaining", "Funny")));
        feedbackStore.add(new FeedbackEntry("FB-102", "Traffic Talk", 4, "Yes", LocalDate.now().minusDays(2),
                "Helpful updates and clear information.", List.of("Informative")));
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }

    private void showAlert(Alert.AlertType type, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle("Rate Show Feedback");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static class FeedbackEntry {
        private final String feedbackId;
        private final String show;
        private final int rating;
        private final String recommend;
        private final LocalDate date;
        private final String feedbackText;
        private final List<String> tags;

        public FeedbackEntry(String feedbackId, String show, int rating, String recommend,
                             LocalDate date, String feedbackText, List<String> tags) {
            this.feedbackId = feedbackId;
            this.show = show;
            this.rating = rating;
            this.recommend = recommend;
            this.date = date;
            this.feedbackText = feedbackText;
            this.tags = new ArrayList<>(tags);
        }

        public String getFeedbackId() {
            return feedbackId;
        }

        public String getShow() {
            return show;
        }

        public int getRating() {
            return rating;
        }

        public String getRecommend() {
            return recommend;
        }

        public LocalDate getDate() {
            return date;
        }

        public String getFeedbackText() {
            return feedbackText;
        }

        public List<String> getTags() {
            return new ArrayList<>(tags);
        }
    }
}