package com.oop.fmradiostation.Elora.listener;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CreateandEditprofileController
{
    @javafx.fxml.FXML
    private RadioButton premiumRadioButton;
    @javafx.fxml.FXML
    private CheckBox talkShowCheckBox;
    @javafx.fxml.FXML
    private TextField phoneTextField;
    @javafx.fxml.FXML
    private TextField emailTextField;
    @javafx.fxml.FXML
    private CheckBox musicCheckBox;
    @javafx.fxml.FXML
    private TextArea addressTextArea;
    @javafx.fxml.FXML
    private CheckBox sportsCheckBox;
    @javafx.fxml.FXML
    private ComboBox<String> favoriteGenreComboBox;
    @javafx.fxml.FXML
    private CheckBox newsCheckBox;
    @javafx.fxml.FXML
    private TextField fullNameTextField;
    @javafx.fxml.FXML
    private ComboBox<String> genderComboBox;
    @javafx.fxml.FXML
    private TextField profileIdTextField;
    @javafx.fxml.FXML
    private DatePicker dobDatePicker;
    @javafx.fxml.FXML
    private RadioButton regularRadioButton;

    private ToggleGroup listenerTypeToggleGroup;
    private static final Map<String, ListenerProfile> PROFILE_STORE = new LinkedHashMap<>();

    @javafx.fxml.FXML
    public void initialize() {
        genderComboBox.getItems().setAll("Male", "Female", "Other", "Prefer not to say");
        favoriteGenreComboBox.getItems().setAll("Pop", "Rock", "Jazz", "News", "Sports", "Talk Show", "Classical");

        listenerTypeToggleGroup = new ToggleGroup();
        regularRadioButton.setToggleGroup(listenerTypeToggleGroup);
        premiumRadioButton.setToggleGroup(listenerTypeToggleGroup);
        regularRadioButton.setSelected(true);

        dobDatePicker.setValue(LocalDate.now().minusYears(20));
        seedDummyProfiles();
        if (!PROFILE_STORE.isEmpty()) {
            loadProfileIntoForm(PROFILE_STORE.values().iterator().next());
        }
    }

    @javafx.fxml.FXML
    public void loadButtonOnClick(ActionEvent actionEvent) {
        String profileId = trim(profileIdTextField.getText());
        if (profileId.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing Profile ID", "Please enter a profile ID to load.");
            return;
        }

        ListenerProfile profile = PROFILE_STORE.get(profileId);
        if (profile == null) {
            showAlert(Alert.AlertType.INFORMATION, "Not Found", "No profile found for ID: " + profileId);
            return;
        }

        loadProfileIntoForm(profile);
        showAlert(Alert.AlertType.INFORMATION, "Loaded", "Profile loaded successfully.");
    }

    @javafx.fxml.FXML
    public void clearButtonOnClick(ActionEvent actionEvent) {
        profileIdTextField.clear();
        fullNameTextField.clear();
        emailTextField.clear();
        phoneTextField.clear();
        addressTextArea.clear();
        dobDatePicker.setValue(LocalDate.now().minusYears(20));
        genderComboBox.getSelectionModel().clearSelection();
        favoriteGenreComboBox.getSelectionModel().clearSelection();
        musicCheckBox.setSelected(false);
        newsCheckBox.setSelected(false);
        sportsCheckBox.setSelected(false);
        talkShowCheckBox.setSelected(false);
        regularRadioButton.setSelected(true);
    }

    @javafx.fxml.FXML
    public void saveButtonOnClick(ActionEvent actionEvent) {
        ListenerProfile profile = buildProfileFromForm();
        if (profile == null) {
            return;
        }

        if (PROFILE_STORE.containsKey(profile.getProfileId())) {
            showAlert(Alert.AlertType.ERROR, "Duplicate ID", "This profile ID already exists. Use Update instead.");
            return;
        }

        PROFILE_STORE.put(profile.getProfileId(), profile);
        showAlert(Alert.AlertType.INFORMATION, "Saved", "Profile saved successfully.");
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
        ListenerProfile profile = buildProfileFromForm();
        if (profile == null) {
            return;
        }

        if (!PROFILE_STORE.containsKey(profile.getProfileId())) {
            showAlert(Alert.AlertType.ERROR, "Not Found", "No saved profile found with this ID. Use Save first.");
            return;
        }

        PROFILE_STORE.put(profile.getProfileId(), profile);
        showAlert(Alert.AlertType.INFORMATION, "Updated", "Profile updated successfully.");
    }

    private void seedDummyProfiles() {
        if (!PROFILE_STORE.isEmpty()) {
            return;
        }

        PROFILE_STORE.put("LP-101", new ListenerProfile(
                "LP-101", "Ayesha Rahman", "ayesha@example.com", "+8801711111111",
                LocalDate.of(2001, 5, 14), "Female", "Dhaka, Bangladesh",
                "Pop", "Premium", List.of("Music", "News")
        ));
        PROFILE_STORE.put("LP-102", new ListenerProfile(
                "LP-102", "Tanvir Hasan", "tanvir@example.com", "+8801811111111",
                LocalDate.of(1999, 11, 2), "Male", "Chattogram, Bangladesh",
                "Sports", "Regular", List.of("Sports", "Talk Show")
        ));
    }

    private ListenerProfile buildProfileFromForm() {
        String profileId = trim(profileIdTextField.getText());
        String fullName = trim(fullNameTextField.getText());
        String email = trim(emailTextField.getText());
        String phone = trim(phoneTextField.getText());
        String gender = genderComboBox.getValue();
        String genre = favoriteGenreComboBox.getValue();
        String listenerType = regularRadioButton.isSelected() ? "Regular" : "Premium";
        LocalDate dob = dobDatePicker.getValue();
        String address = addressTextArea.getText() == null ? "" : addressTextArea.getText().trim();

        if (profileId.isEmpty() || fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || gender == null || genre == null || dob == null) {
            showAlert(Alert.AlertType.WARNING, "Missing Fields", "Please complete all required profile fields.");
            return null;
        }

        if (!email.contains("@") || !email.contains(".")) {
            showAlert(Alert.AlertType.ERROR, "Invalid Email", "Please enter a valid email address.");
            return null;
        }

        if (phone.length() < 7) {
            showAlert(Alert.AlertType.ERROR, "Invalid Phone", "Please enter a valid phone number.");
            return null;
        }

        List<String> interests = new ArrayList<>();
        if (musicCheckBox.isSelected()) {
            interests.add("Music");
        }
        if (newsCheckBox.isSelected()) {
            interests.add("News");
        }
        if (sportsCheckBox.isSelected()) {
            interests.add("Sports");
        }
        if (talkShowCheckBox.isSelected()) {
            interests.add("Talk Show");
        }

        if (interests.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Interests", "Please select at least one interest.");
            return null;
        }

        return new ListenerProfile(profileId, fullName, email, phone, dob, gender, address, genre, listenerType, interests);
    }

    private void loadProfileIntoForm(ListenerProfile profile) {
        profileIdTextField.setText(profile.getProfileId());
        fullNameTextField.setText(profile.getFullName());
        emailTextField.setText(profile.getEmail());
        phoneTextField.setText(profile.getPhone());
        dobDatePicker.setValue(profile.getDob());
        genderComboBox.setValue(profile.getGender());
        addressTextArea.setText(profile.getAddress());
        favoriteGenreComboBox.setValue(profile.getFavoriteGenre());
        regularRadioButton.setSelected("Regular".equalsIgnoreCase(profile.getListenerType()));
        premiumRadioButton.setSelected("Premium".equalsIgnoreCase(profile.getListenerType()));
        musicCheckBox.setSelected(profile.getInterests().contains("Music"));
        newsCheckBox.setSelected(profile.getInterests().contains("News"));
        sportsCheckBox.setSelected(profile.getInterests().contains("Sports"));
        talkShowCheckBox.setSelected(profile.getInterests().contains("Talk Show"));
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }

    private void showAlert(Alert.AlertType type, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle("Listener Profile");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}