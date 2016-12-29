package com.belhaji.controller;

import com.belhaji.App;
import com.belhaji.screen.ControlledScreen;
import com.belhaji.screen.ScreensController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.*;

/**
 * Created by adil on 11/17/16.
 */
public class LevelOneController implements Initializable, ControlledScreen {

    public static String SCREEN_NAME = "levelOne";
    public static String SCREEN_FILE = "/com/belhaji/view/levelOne.fxml";
    ScreensController screensController;
    int clickNumber = 0;
    int validatedNumber = 0;
    String selectedImageName = null;
    int counter = 60;
    Thread thread;

    @FXML
    private Label scoreLabel;
    @FXML
    private Label counterLabel;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    private ImageView selectedImage = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> imageNames = Arrays.asList("dog", "dog", "cat", "cat");
        Collections.shuffle(imageNames);
        Collections.shuffle(imageNames);
        Collections.shuffle(imageNames);
        img1.setImage(new Image("/com/belhaji/image/" + imageNames.get(0) + ".png"));
        img2.setImage(new Image("/com/belhaji/image/" + imageNames.get(1) + ".png"));
        img3.setImage(new Image("/com/belhaji/image/" + imageNames.get(2) + ".png"));
        img4.setImage(new Image("/com/belhaji/image/" + imageNames.get(3) + ".png"));

        img1.setId(imageNames.get(0));
        img2.setId(imageNames.get(1));
        img3.setId(imageNames.get(2));
        img4.setId(imageNames.get(3));

        counter = 60;
        scoreLabel.setText("Score : " + App.currentPlayer.getScore());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                hideImages();
            }
        }, 4000);

        counterLabel.setText("Counter : 00:" + counter);

        if (thread != null && thread.isAlive()) thread.interrupt();
        thread = new Thread(() -> {
            while (counter > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (counter == 0) {
                    Platform.runLater(() -> {
                        thread.interrupt();
                        new Alert(Alert.AlertType.WARNING, "Timeout Sorry try again !!", ButtonType.OK)
                                .show();
                        initialize(null, null);

                    });
                }
                Platform.runLater(() -> {
                    counter--;
                    System.out.println(counter);
                    counterLabel.setText("Counter : 00:" + counter);
                });

            }
        });
        thread.start();
    }


    private void handleClick(ImageView img, String id) {
        img.setImage(new Image("/com/belhaji/image/" + id + ".png"));
        if (clickNumber == 0) {
            selectedImageName = id;
            this.selectedImage = img;
            clickNumber++;
        } else {
            if (selectedImage != img) {
                clickNumber = 0;
                if (id.compareTo(selectedImageName) == 0) {
                    validatedNumber++;
                    if (validatedNumber == 2) {
                        int score = counter * 10;
                        App.currentPlayer.setScore(App.currentPlayer.getScore() + score);
                        App.currentPlayer.setLevel(2);
                        scoreLabel.setText("Score : " + score);
                        if (thread != null && thread.isAlive()) thread.stop();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Congrats you passed the level one", ButtonType.OK);
                        alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                            @Override
                            public void handle(DialogEvent event) {
                                App.currentPlayer.save();
                                screensController.loadScreen(LevelController.SCREEN_NAME, LevelController.SCREEN_FILE);
                                screensController.setScreen(LevelController.SCREEN_NAME);
                                screensController.unloadScreen(LevelOneController.SCREEN_NAME);
                            }
                        });
                        alert.show();
                    }
                } else {
                    if (validatedNumber == 0)
                        hideImages();
                    else {
                        img.setImage(new Image("/com/belhaji/image/back.png"));
                        selectedImage.setImage(new Image("/com/belhaji/image/back.png"));
                    }
                }

            }
        }
    }

    @FXML
    public void onImg1() {
        System.out.println("on image 1 :" + img1.getId());
        handleClick(img1, img1.getId());
    }

    @FXML
    public void onImg2() {
        System.out.println("on image 2 :" + img2.getId());
        handleClick(img2, img2.getId());
    }

    @FXML
    public void onImg3() {
        System.out.println("on image 3 :" + img3.getId());
        handleClick(img3, img3.getId());
    }

    @FXML
    public void onImg4() {
        System.out.println("on image 4 :" + img4.getId());
        handleClick(img4, img4.getId());
    }

    public void hideImages() {
        img1.setImage(new Image("/com/belhaji/image/back.png"));
        img2.setImage(new Image("/com/belhaji/image/back.png"));
        img3.setImage(new Image("/com/belhaji/image/back.png"));
        img4.setImage(new Image("/com/belhaji/image/back.png"));
    }


    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.screensController = screenPage;
    }
}
