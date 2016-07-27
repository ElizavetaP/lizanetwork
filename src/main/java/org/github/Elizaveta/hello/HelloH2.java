package org.github.Elizaveta.hello;

import java.sql.*;
import java.util.Map;
import java.util.Date;
import java.util.Scanner;

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
        /*String insItem = "ALTER TABLE USERS add column birthday date;"+
                "ALTER TABLE USERS add column education varchar(50);"+
                "ALTER TABLE USERS add column job varchar(50);";
               *//* "INSERT INTO USERS (FirstName, LastName) VALUES ('Liza','Popugaeva');" +
                "INSERT INTO USERS (FirstName, LastName) VALUES ('Sergey','Volkov');" +
                "INSERT INTO USERS (FirstName, LastName) VALUES ('Ostap','Bender');";*//*
       *//* String insItem = "drop table USERS;"+
                "create table USERS(ID bigint auto_increment, FirstName varchar(20), LastName varchar(20)," +
                " country varchar(30), town varchar(30), sex varchar(7), email varchar(40), photo_id bigint, " +
                "password varchar(40));"+
                "INSERT INTO USERS (FirstName, LastName,country,email,password) VALUES ('Liza','Popugaeva'," +
                "'Russia','qwerty@bk.ru','123');"+
                "INSERT INTO USERS (FirstName, LastName,country,email,password) VALUES ('Sergey','Volkov'," +
                "'Russia','qwerty123@bk.ru','123');";*//*
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

        resultSet = statement.executeQuery("SELECT * FROM messages;");


        System.out.println(resultSet);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("type") + " " + resultSet.getString("message"));
        }


       // resultSet.close();
        statement.close();
        connection.close();

    }

    private static Connection getH2Connection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:~/git/hellow/src/main/java/org/github/Elizaveta/hello/helloh2db","sa","");
    }
}
