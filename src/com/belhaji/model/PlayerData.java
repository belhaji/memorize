package com.belhaji.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by adil on 11/17/16.
 */
public class PlayerData {
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty level;
    private SimpleIntegerProperty score;
    private SimpleStringProperty name;
    private SimpleStringProperty email;
    private SimpleStringProperty password;


    public PlayerData(int id, String name, String email, String password, int level, int score) {
        super();
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.level = new SimpleIntegerProperty(level);
        this.score = new SimpleIntegerProperty(score);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public int getLevel() {
        return level.get();
    }

    public void setLevel(int level) {
        this.level.set(level);
    }

    public int getScore() {
        return score.get();
    }

    public void setScore(int score) {
        this.score.set(score);
    }

}
