package org.github.Elizaveta.hello.dao;

import org.github.Elizaveta.hello.Friendship;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendshipDAO {
    private final DataSource ds;

    public FriendshipDAO() {
        super();
        ds = DataSourceUtils.getDataSource();
    }

    public void addFriend(int otherUserID, int id) {
        try (Connection connection = ds.getConnection()) {
            String insItem = "INSERT INTO friendship (ID, ID_otheruser) VALUES (?,?);";
            PreparedStatement prepareStatement = connection.prepareStatement(insItem);
            prepareStatement.setInt(1, id);
            prepareStatement.setInt(2, otherUserID);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isFriend(int otherUserID, int id) {
        try (Connection connection = ds.getConnection()) {
            String insItem = "Select ID, ID_otheruser from friendship where ID = ? and ID_otheruser = ?;";
            PreparedStatement prepareStatement = connection.prepareStatement(insItem);
            prepareStatement.setInt(1, id);
            prepareStatement.setInt(2, otherUserID);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeFriend(int otherUserID, int id) {
        try (Connection connection = ds.getConnection()) {
            String insItem = "delete from friendship where ID = ? and ID_otheruser = ?;";
            PreparedStatement prepareStatement = connection.prepareStatement(insItem);
            prepareStatement.setInt(1, id);
            prepareStatement.setInt(2, otherUserID);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Friendship> getFriendship(int id) {
        List<Friendship> friendships = new ArrayList<>();
        try (Connection connection = ds.getConnection()) {
            String insItem = "Select ID, ID_otheruser from friendship where ID = ?;";
            PreparedStatement prepareStatement = connection.prepareStatement(insItem);
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                friendships.add(new Friendship(resultSet.getInt("ID"), resultSet.getInt("ID_otheruser")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return friendships;
    }
}
