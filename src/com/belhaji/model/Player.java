package com.belhaji.model;

import com.belhaji.db.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static sun.audio.AudioPlayer.player;

/**
 * Created by adil on 11/17/16.
 */
public class Player {
    private int id;
    private String name;
    private String email;
    private String password;
    private int level;
    private int score;

    public Player(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.level = 1;
        this.score = 0;
    }

    public Player() {
        this.level = 1;
        this.score = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static List<Player> getAll() {
        List<Player> players = new ArrayList<>();
        try {
            PreparedStatement statement = DBHelper.getConnection().prepareStatement("SELECT * FROM player");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Player player = new Player();
                player.setId(rs.getInt("id"));
                player.setName(rs.getString("name"));
                player.setEmail(rs.getString("email"));
                player.setPassword(rs.getString("password"));
                player.setLevel(rs.getInt("level"));
                player.setScore(rs.getInt("score"));
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return players;
    }

    public static Player getById(int id) {
        Player player = null;
        try {
            PreparedStatement statement = DBHelper.getConnection().prepareStatement("SELECT * FROM player WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            player = parsePlayer(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
    }

    public boolean save() {
        String insertSql = "INSERT INTO player (name, email, password, level, score) VALUES (?, ?, ?, ?, ?)";
        String updateSql = "UPDATE player SET name = ?, email = ?, password = ?, level = ?, score = ?";
        boolean ret = false;
        try {
            PreparedStatement statement;
            if (this.id == 0)
                statement = DBHelper.getConnection().prepareStatement(insertSql);
            else
                statement = DBHelper.getConnection().prepareStatement(updateSql);
            statement.setString(1,this.name);
            statement.setString(2,this.email);
            statement.setString(3,this.password);
            statement.setInt(4,this.level);
            statement.setInt(5,this.score);
            if (statement.executeUpdate() == 1) ret = true;
            this.id = statement.getGeneratedKeys().getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static boolean delete(int id){
        try {
            PreparedStatement statement = DBHelper.getConnection().prepareStatement("DELETE FROM player WHERE id = ?");
            statement.setInt(1, id);
            if (statement.executeUpdate() == 1) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Player getByEmail(String email){
        Player player = null;
        try {
            PreparedStatement statement = DBHelper.getConnection().prepareStatement("SELECT * FROM player WHERE email = ?");
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            player = parsePlayer(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
    }

    private static Player parsePlayer(ResultSet rs) throws SQLException {
        Player player = null;
        if (rs.next()) {
            player = new Player();
            player.setId(rs.getInt("id"));
            player.setName(rs.getString("name"));
            player.setEmail(rs.getString("email"));
            player.setPassword(rs.getString("password"));
            player.setLevel(rs.getInt("level"));
            player.setScore(rs.getInt("score"));
        }
        return player;
    }
}
