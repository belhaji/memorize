package com.belhaji.controller;

import com.belhaji.App;
import com.belhaji.model.Player;
import com.belhaji.model.PlayerData;
import com.belhaji.screen.ControlledScreen;
import com.belhaji.screen.ScreensController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by adil on 11/17/16.
 */
public class AdminEditController implements Initializable, ControlledScreen {

    public static String SCREEN_NAME = "adminEdit";
    public static String SCREEN_FILE = "/com/belhaji/view/adminEdit.fxml";
    ScreensController screensController;


    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField level;
    @FXML
    private TextField score;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (App.player != null){
            name.setText(App.player.getName());
            email.setText(App.player.getEmail());
            password.setText(App.player.getPassword());
            level.setText(String.valueOf(App.player.getLevel()));
            score.setText(String.valueOf(App.player.getScore()));
        }
    }

    @FXML
    public void onSave() {
        Player player = new Player();
        player.setName(name.getText());
        player.setEmail(email.getText());
        player.setPassword(password.getText());
        try {
            player.setLevel(Integer.parseInt(level.getText()));
            player.setScore(Integer.parseInt(score.getText()));
        }catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Input error recheck !!");
            alert.show();
            return;
        }
        if (App.player != null){
            player.setId(App.player.getId());
        }
        player.save();
        App.player = null;
        screensController.loadScreen(AdminController.SCREEN_NAME, AdminController.SCREEN_FILE);
        screensController.setScreen(AdminController.SCREEN_NAME);
        screensController.unloadScreen(AdminEditController.SCREEN_NAME);
    }

    @FXML
    public void onCancel() {
        App.player = null;
        screensController.loadScreen(AdminController.SCREEN_NAME, AdminController.SCREEN_FILE);
        screensController.setScreen(AdminController.SCREEN_NAME);
        screensController.unloadScreen(AdminEditController.SCREEN_NAME);
    }


    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.screensController = screenPage;
    }
}
