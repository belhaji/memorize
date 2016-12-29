package com.belhaji;

import com.belhaji.controller.LevelController;
import com.belhaji.controller.LoginController;
import com.belhaji.model.Player;
import com.belhaji.screen.ScreensController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by adil on 11/17/16.
 */
public class App extends Application{
    public static Player currentPlayer;

    @Override
    public void start(Stage primaryStage) throws Exception {


        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(LoginController.SCREEN_NAME, LoginController.SCREEN_FILE);

        mainContainer.setScreen(LoginController.SCREEN_NAME);
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
