package com.belhaji.controller;

import com.belhaji.App;
import com.belhaji.screen.ControlledScreen;
import com.belhaji.screen.ScreensController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by adil on 11/17/16.
 */
public class LevelController implements Initializable, ControlledScreen {

    public static String SCREEN_NAME = "level";
    public static String SCREEN_FILE = "/com/belhaji/view/level.fxml";
    ScreensController screensController;


    @FXML
    private Button levelOne;
    @FXML
    private Button levelTwo;
    @FXML
    private Button quitButton;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label levelLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcomeLabel.setText("Welcome "+ App.currentPlayer.getName());
        scoreLabel.setText("Score : "+ App.currentPlayer.getScore());
        levelLabel.setText("Current Level : " + App.currentPlayer.getLevel());
        if (App.currentPlayer.getLevel() == 2){
            levelOne.setDisable(true);
        }else if (App.currentPlayer.getLevel() > 2){
            levelOne.setDisable(true);
            levelTwo.setDisable(true);
            levelLabel.setText("Congras You Win !!!");
        }
    }

    @FXML
    public void onLevelOne() {
        screensController.loadScreen(LevelOneController.SCREEN_NAME,LevelOneController.SCREEN_FILE);
        screensController.setScreen(LevelOneController.SCREEN_NAME);
        screensController.unloadScreen(LevelController.SCREEN_NAME);
    }

    @FXML
    public void onLevelTwo() {
        screensController.loadScreen(LevelTwoController.SCREEN_NAME,LevelTwoController.SCREEN_FILE);
        screensController.setScreen(LevelTwoController.SCREEN_NAME);
        screensController.unloadScreen(LevelController.SCREEN_NAME);
    }

    @FXML
    public void onQuit(){
        Platform.exit();
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.screensController = screenPage;
    }
}
