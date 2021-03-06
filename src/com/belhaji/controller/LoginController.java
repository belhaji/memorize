package com.belhaji.controller;

import com.belhaji.App;
import com.belhaji.model.Player;
import com.belhaji.screen.ControlledScreen;
import com.belhaji.screen.ScreensController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by adil on 11/17/16.
 */
public class LoginController implements Initializable, ControlledScreen {

    public static String SCREEN_NAME = "login";
    public static String SCREEN_FILE = "/com/belhaji/view/login.fxml";
    ScreensController screensController;

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void onLogin() {
        String email = this.email.getText();
        String pass = this.password.getText();
        if (email.equals(App.ADMIN_NAME) && pass.equals(App.ADMIN_PASSWORD)) {
            screensController.loadScreen(AdminController.SCREEN_NAME, AdminController.SCREEN_FILE);
            screensController.setScreen(AdminController.SCREEN_NAME);
            screensController.unloadScreen(LoginController.SCREEN_NAME);
        }else{
            Player player = Player.getByEmail(email);
            if (player == null || player.getPassword().compareTo(pass) != 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Email or password incorrect", ButtonType.OK);
                alert.show();
            } else {
                App.currentPlayer = player;
                screensController.loadScreen(LevelController.SCREEN_NAME, LevelController.SCREEN_FILE);
                screensController.setScreen(LevelController.SCREEN_NAME);
                screensController.unloadScreen(LoginController.SCREEN_NAME);
            }
        }


    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.screensController = screenPage;
    }
}
