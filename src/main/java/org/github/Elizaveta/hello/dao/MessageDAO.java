package org.github.Elizaveta.hello.dao;

import org.github.Elizaveta.hello.Message;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    private final DataSource ds;

    public MessageDAO() {
        super();
        try {
            InitialContext initContext= new InitialContext();
            ds = (DataSource) initContext.lookup("java:comp/env/jdbc_empDS");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendMessage(String text, long ID_sender, long ID_recipient, String date, String type){
        try (Connection connection = ds.getConnection()) {
            String insItem = "INSERT INTO messages (message, ID_sender, ID_recipient, date, type) VALUES (?,?,?,?,?);";
            PreparedStatement prepareStatement = connection.prepareStatement(insItem);
            prepareStatement.setString(1, text);
            prepareStatement.setLong(2, ID_sender);
            prepareStatement.setLong(3, ID_recipient);
            prepareStatement.setString(4, date);
            prepareStatement.setString(5, type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Message> getMessage(String type, long ID_user, long ID_otheruser){
        List<Message> messages = new ArrayList<>();
        try (Connection connection = ds.getConnection()) {
            String insItem = "Select ID_sender, ID_recipient, date, ID_message, message  from messages where ((ID_recipient = ? and ID_sender = ?) " +
                    "OR (ID_recipient = ? and ID_sender = ?)) and type = ?;";
            PreparedStatement prepareStatement = connection.prepareStatement(insItem);
            prepareStatement.setLong(1, ID_user);
            prepareStatement.setLong(2, ID_otheruser);
            prepareStatement.setLong(3, ID_otheruser);
            prepareStatement.setLong(4, ID_user);
            prepareStatement.setString(5, type);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()){
                messages.add(new Message(resultSet.getLong("ID_sender"),resultSet.getLong("ID_recipient"),
                        resultSet.getString("date"), resultSet.getLong("ID_message"),type,resultSet.getString("message")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return messages;
    }
}
