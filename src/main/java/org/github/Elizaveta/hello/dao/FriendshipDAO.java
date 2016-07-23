package org.github.Elizaveta.hello.dao;

import org.github.Elizaveta.hello.Friendship;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendshipDAO {
    private final DataSource ds;

    public FriendshipDAO() {
        super();
        try {
            InitialContext initContext= new InitialContext();
            ds = (DataSource) initContext.lookup("java:comp/env/jdbc_empDS");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }

    public void addFriend(int ID_otheruser, int ID) {
        try (Connection connection = ds.getConnection()) {
            String insItem = "INSERT INTO friendship (ID, ID_otheruser) VALUES (?,?);";
            PreparedStatement prepareStatement = connection.prepareStatement(insItem);
            prepareStatement.setInt(1, ID);
            prepareStatement.setInt(2, ID_otheruser);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isFriend(int ID_otheruser, int ID){
        try (Connection connection = ds.getConnection()) {
            String insItem = "Select ID, ID_otheruser from friendship where ID = ? and ID_otheruser = ?;";
            PreparedStatement prepareStatement = connection.prepareStatement(insItem);
            prepareStatement.setInt(1, ID);
            prepareStatement.setInt(2, ID_otheruser);
            ResultSet resultSet = prepareStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeFriend(int ID_otheruser, int ID) {
        try (Connection connection = ds.getConnection()) {
            String insItem = "delete from friendship where ID = ? and ID_otheruser = ?;";
            PreparedStatement prepareStatement = connection.prepareStatement(insItem);
            prepareStatement.setInt(1, ID);
            prepareStatement.setInt(2, ID_otheruser);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Friendship> getFriendship(int ID){
        List<Friendship> friendships = new ArrayList<>();
        try (Connection connection = ds.getConnection()) {
            String insItem = "Select ID, ID_otheruser from friendship where ID = ?;";
            PreparedStatement prepareStatement = connection.prepareStatement(insItem);
            prepareStatement.setInt(1, ID);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()){
                friendships.add(new Friendship(resultSet.getInt("ID"),resultSet.getInt("ID_otheruser")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return friendships;
    }
}
