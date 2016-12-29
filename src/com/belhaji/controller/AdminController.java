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
public class AdminController implements Initializable, ControlledScreen {

    public static String SCREEN_NAME = "admin";
    public static String SCREEN_FILE = "/com/belhaji/view/admin.fxml";
    ScreensController screensController;

    private ObservableList<PlayerData> data = FXCollections.observableArrayList();

    @FXML
    private TableView<PlayerData> tableView;
    @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button delete;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        for (Player player : Player.getAll()) {
            data.add(new PlayerData(
                    player.getId(),
                    player.getName(),
                    player.getEmail(),
                    player.getPassword(),
                    player.getLevel(),
                    player.getScore()));
        }

        TableColumn<PlayerData, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<PlayerData, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(100);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<PlayerData, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setMinWidth(100);
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<PlayerData, String> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setMinWidth(100);
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        TableColumn<PlayerData, String> levelColumn = new TableColumn<>("Level");
        levelColumn.setMinWidth(100);
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));


        tableView.getColumns().addAll(nameColumn, emailColumn, passwordColumn, levelColumn, scoreColumn);
        tableView.setItems(data);

    }

    @FXML
    public void onAdd() {
        screensController.loadScreen(AdminEditController.SCREEN_NAME, AdminEditController.SCREEN_FILE);
        screensController.setScreen(AdminEditController.SCREEN_NAME);
        screensController.unloadScreen(AdminController.SCREEN_NAME);
    }

    @FXML
    public void onEdit() {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You must Select an item", ButtonType.OK);
            alert.show();
        } else {
            App.player = data.get(selectedIndex);
            screensController.loadScreen(AdminEditController.SCREEN_NAME, AdminEditController.SCREEN_FILE);
            screensController.setScreen(AdminEditController.SCREEN_NAME);
            screensController.unloadScreen(AdminController.SCREEN_NAME);
        }

    }

    @FXML
    public void onDelete() {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You must Select an item", ButtonType.OK);
            alert.show();
        } else {
            Player.delete(data.get(selectedIndex).getId());
            data.remove(selectedIndex);
        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.screensController = screenPage;
    }
}
