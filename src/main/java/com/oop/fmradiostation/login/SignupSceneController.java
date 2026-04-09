package com.oop.fmradiostation.login;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SignupSceneController {
    @javafx.fxml.FXML
    private PasswordField passwordTextField;
    @javafx.fxml.FXML
    private ComboBox<String> countryCodeComboBox;
    @javafx.fxml.FXML
    private TextField emailTextField;
    @javafx.fxml.FXML
    private CheckBox checkBox;
    @javafx.fxml.FXML
    private TextField mobileTextField;
    @javafx.fxml.FXML
     void initialize() {
        countryCodeComboBox.setValue("Select Country Code");
        countryCodeComboBox.getItems().addAll("Bangladesh +880","India +91",
                                            "Pakistan +92","Sri Lanka +94",
                                                "Nepal +977","Bhutan +975",
                                                "Maldives +960","Afghanistan +93");
    }

    @javafx.fxml.FXML
    public void signupButtonOnClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void signInButtonOnClicked(ActionEvent actionEvent) throws IOException {
        Parent scene2Parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/oop/fmradiostation/login/LoginScene.fxml")));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Signup");
        window.setScene(scene2);
        window.show();
    }
}
