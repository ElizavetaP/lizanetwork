package org.github.Elizaveta.hello.dao;

import org.github.Elizaveta.hello.Photo;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhotoDAO {
    private final DataSource ds;

    public PhotoDAO() {
        super();
        try {
            InitialContext initContext= new InitialContext();
            ds = (DataSource) initContext.lookup("java:comp/env/jdbc_empDS");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }

    public void setAvatar(String ID, String avatarname){

        try (Connection connection = ds.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PHOTOS where ID ="+ID +";");
            if(resultSet.next()){
                String insItem ="update PHOTOS set photo_name = '" +avatarname+"' " +
                        "where ID = "+ID+" and album_id is null;";
                statement.executeUpdate(insItem);
            }else {
                String insItem = "INSERT INTO PHOTOS (album_id, photo_name, ID) VALUES (NULL,'" +
                        avatarname +"', " + ID + ");";
                statement.executeUpdate(insItem);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAvatar(String ID){
        String image;
        try (Connection connection = ds.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT photo_name FROM PHOTOS where ID =" + ID + ";");
            if(resultSet.next()) {
                image = resultSet.getString(1);
            }else {
                image = "qwerty";
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return image;
        }

   /* public Map<String,String> getAllAvatar(){
        Map<String,String> avatars = new HashMap<>();
        try(Connection connection = ds.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT photo_name, ID FROM PHOTOS");
            while (resultSet.next()){
                avatars.put(resultSet.getString("ID"), resultSet.getString("photo_name"));
            }
            }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return avatars;
    }*/

    public List<Photo>  getAllPhotos(){
        List<Photo> photos = new ArrayList<>();
        try(Connection connection = ds.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT photo_name, ID, album_id, photo_id FROM PHOTOS");
            while (resultSet.next()){
                photos.add(new Photo(resultSet.getInt("photo_id"),resultSet.getInt("album_id"),
                        resultSet.getString("photo_name"),resultSet.getInt("ID")));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return photos;
    }
}
