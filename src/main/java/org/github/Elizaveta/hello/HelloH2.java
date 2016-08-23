package org.github.Elizaveta.hello;

import java.sql.*;

public class HelloH2 {
    public static void main(String[] args) throws Exception {

        //С помощью DriverManager получим соединение с базой:
        Connection connection = getH2Connection();
        ResultSet resultSet = null;
        //Из соединения получим контейнер для выполнения запросов:
        Statement statement = connection.createStatement();

        //Для выполнения SQL запросов типа UPDATE и INSERT будем использовать следующее выражение:

        /*String createTable = "CREATE TABLE PHOTOS (photo_id bigint auto_increment" +
                ", album_id bigint, photo_name varchar(50), ID bigint);";
        statement.executeUpdate(createTable);
        String createTable2 = "CREATE TABLE ALBUM (album_id bigint, album_name varchar(50)," +
                "description varchar(50), ID bigint);";
        statement.executeUpdate(createTable2);*/
       /* String insItem = *//*"ALTER TABLE USERS add column password varchar(20);"+*//*
                "drop table passwords;";
        statement.executeUpdate(insItem);*/

       /* String insItem4 ="INSERT INTO USERS (FirstName, LastName, password) VALUES (?,?,?);";
        try {PreparedStatement prepareStatement = connection.prepareStatement(insItem4);
            prepareStatement.setString(1, "Name");
            prepareStatement.setString(2, "Name");
            prepareStatement.setString(3, "pass");
        }catch (SQLException e) {
            throw new RuntimeException(e);

        }*/

       /* String insItem3 ="INSERT INTO USERS (FirstName, LastName, password) VALUES ('"+"name"+
                "','"+"name"+"','"+"123"+"');";
        statement.executeUpdate(insItem3);*/
        //Покажем все что нам удалось выбрать из таблицы  с помощью запроса SELECT:
        /*String insItem4 ="update PHOTOS set photo_name = 'qwerty' where photo_name = 'qwerty1' album_id = null'";
        statement.executeUpdate(insItem4);

        resultSet = statement.executeQuery("SELECT * FROM PHOTOS;");
        System.out.println(resultSet.next());
        while (resultSet.next()) {
            System.out.println("USER Name: " + resultSet.getString("photo_id") +
                    resultSet.getString("ID")+" " + resultSet.getString("photo_name"));
        }*/
        /*resultSet = statement.executeQuery("SELECT email FROM USERS where ID = 2;");
        resultSet.next();
        System.out.println(resultSet.getString(1));*/
/*

        resultSet = statement.executeQuery("SELECT * FROM USERS;");
        System.out.println(resultSet);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("ID")+" " +resultSet.getString("password")+
                    resultSet.getString("email"));
        }
*/


        //String insItem = "CREATE TABLE messages (ID_message bigint, ID_sender bigint, ID_recipient bigint, date DATE)";
        /*String insItem = "ALTER TABLE messages add column type varchar(4);";
        statement.executeUpdate(insItem);*/

        resultSet = statement.executeQuery("SELECT * FROM users;");


        System.out.println(resultSet);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("FirstName") + " " + resultSet.getString("password"));
        }


       // resultSet.close();
        statement.close();
        connection.close();

    }


    private static Connection getH2Connection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:~/git/hellow/src/main/java/org/github/Elizaveta/hello/helloh2db","sa","");
    }
}
