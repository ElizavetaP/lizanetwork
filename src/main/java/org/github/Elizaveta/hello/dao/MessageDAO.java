package org.github.Elizaveta.hello.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    private final DataSource ds;

    public MessageDAO() {
        super();
        ds = DataSourceUtils.getDataSource();
    }

    public void sendMessage(String text, int senderID, int recipientID, String type) {
        try (Connection connection = ds.getConnection()) {
            String insItem = "INSERT INTO messages (message, ID_sender, ID_recipient, date, type) VALUES (?,?,?,?,?);";
            PreparedStatement prepareStatement = connection.prepareStatement(insItem);
            prepareStatement.setString(1, text);
            prepareStatement.setInt(2, senderID);
            prepareStatement.setInt(3, recipientID);
            prepareStatement.setString(4, LocalDate.now().toString());
            prepareStatement.setString(5, type);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Message> getMessage(String type, int userID, int otherUserID) {
        List<Message> messages = new ArrayList<>();
        try (Connection connection = ds.getConnection()) {
            String insItem = "Select ID_sender, ID_recipient, date, ID_message, message  from messages where ((ID_recipient = ? and ID_sender = ?) " +
                    "OR (ID_recipient = ? and ID_sender = ?)) and type = ?;";
            PreparedStatement prepareStatement = connection.prepareStatement(insItem);
            prepareStatement.setInt(1, userID);
            prepareStatement.setInt(2, otherUserID);
            prepareStatement.setInt(3, otherUserID);
            prepareStatement.setInt(4, userID);
            prepareStatement.setString(5, type);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                messages.add(new Message(resultSet.getInt("ID_sender"), resultSet.getInt("ID_recipient"),
                        resultSet.getString("date"), resultSet.getLong("ID_message"), type, resultSet.getString("message")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return messages;
    }
}
