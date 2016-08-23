package org.github.Elizaveta.hello.dao;

import org.github.Elizaveta.hello.Photo;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhotoDAO {
    private final DataSource ds;

    public PhotoDAO() {
        super();
        ds = DataSourceUtils.getDataSource();
    }

    public void setAvatar(int id, String avatarname) {

        try (Connection connection = ds.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM PHOTOS where ID = ?;");
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                String insItem = "update PHOTOS set photo_name = '?' " +
                        "where ID = ? and album_id is null;";
                prepareStatement.setString(1, avatarname);
                prepareStatement.setString(2, insItem);
                prepareStatement.executeUpdate();
            } else {
                String insItem = "INSERT INTO PHOTOS (album_id, photo_name, ID) VALUES (NULL,'?', ?);";
                prepareStatement.setString(1, avatarname);
                prepareStatement.setString(2, insItem);
                prepareStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAvatar(int ID) {
        String image;
        try (Connection connection = ds.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT photo_name FROM PHOTOS where ID =" + ID + ";");
            if (resultSet.next()) {
                image = resultSet.getString(1);
            } else {
                image = "qwerty";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    public Map<Integer, String> getAllAvatar() {
        Map<Integer, String> avatars = new HashMap<>();
        try (Connection connection = ds.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT photo_name, ID FROM PHOTOS");
            while (resultSet.next()) {
                avatars.put(resultSet.getInt("ID"), resultSet.getString("photo_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return avatars;
    }

    public List<Photo> getAllPhotos() {
        List<Photo> photos = new ArrayList<>();
        try (Connection connection = ds.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT photo_name, ID, album_id, photo_id FROM PHOTOS");
            while (resultSet.next()) {
                photos.add(new Photo(resultSet.getInt("photo_id"), resultSet.getInt("album_id"),
                        resultSet.getString("photo_name"), resultSet.getInt("ID")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return photos;
    }
}
