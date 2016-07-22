package org.github.Elizaveta.hello.dao;

import org.github.Elizaveta.hello.Person;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDAO {
    private final DataSource ds;

    public PersonDAO() {
        super();
        try {
            /* Заставляем ClassLoader подгрузить класс драйвера в память java. */
            InitialContext initContext= new InitialContext();
            ds = (DataSource) initContext.lookup("java:comp/env/jdbc_empDS");
      } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Person> getPersons(){
        List<Person> persons = new ArrayList<>();
        ResultSet resultSet;

        try (Connection connection = ds.getConnection()){
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM USERS");
            while (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String ID = resultSet.getString("ID");
                Person person = new Person(firstName,lastName,password,email);
                person.setID(ID);
                persons.add(person);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return persons;
    }

    public boolean addUser(String firstName, String lastName, String password, String email){

        for (Person person  : getPersons()) {
            if (person.getEmail().equals(email)){
                return false;
            }
        }
        boolean result;
        try (Connection connection = ds.getConnection()){
            String insItem ="INSERT INTO USERS (FirstName, LastName, password, email) VALUES (?, ?, ?, lower(?));";
            PreparedStatement prepareStatement = connection.prepareStatement(insItem);
            prepareStatement.setString(1, firstName);
            prepareStatement.setString(2, lastName);
            prepareStatement.setString(3, password);
            prepareStatement.setString(4, email);
            prepareStatement.executeUpdate();
            result = true;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
        }

    public boolean login(String password, String email){

        for (Person person  : getPersons()) {
            if (person.getEmail().equals(email.toLowerCase())&&person.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public Map<String,String> getUser(String ID) {
        Map<String, String> usermap = new HashMap<>();
        try(Connection connection = ds.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS where ID = " + ID + ";");
            if (resultSet.next()) {
                usermap.put("FirstName", resultSet.getString("FirstName"));
                usermap.put("LastName", resultSet.getString("LastName"));
                usermap.put("sex", resultSet.getString("sex"));
                usermap.put("country", resultSet.getString("country"));
                usermap.put("town", resultSet.getString("town"));
                usermap.put("email", resultSet.getString("email"));
                usermap.put("sex", resultSet.getString("sex"));
                usermap.put("education", resultSet.getString("education"));
                usermap.put("job", resultSet.getString("job"));
                usermap.put("birthday", resultSet.getString("birthday"));
                usermap.put("photo_id", resultSet.getString("photo_id"));

            }
            resultSet.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usermap;
    }

    public String getID(String email) {
        try(Connection connection = ds.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT ID FROM USERS where lower(email) = lower(?) ;");
            prepareStatement.setString(1, email);
            ResultSet resultSet = prepareStatement.executeQuery();
            resultSet.next();
            return resultSet.getString(1);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editUser(String ID, String firstname, String lastname, String sex,
                            String country, String town, String birthday,
                            String education, String job, String email) {
        try(Connection connection = ds.getConnection()) {
            String insItem = "update USERS set FirstName = ?, LastName= ?, sex = ?, country = ?" +
                    ", town = ?, birthday = ?, education =?, job = ?,email = ? where ID = " + ID + ";";
            PreparedStatement prepareStatement = connection.prepareStatement(insItem);
            prepareStatement.setString(1, firstname);
            prepareStatement.setString(2, lastname);
            prepareStatement.setString(3, sex);
            prepareStatement.setString(4, country);
            prepareStatement.setString(5, town);
            prepareStatement.setString(6, birthday);
            prepareStatement.setString(7, education);
            prepareStatement.setString(8, job);
            prepareStatement.setString(9, email);
            prepareStatement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> searchByName(String searchname) {
        List<Person> persons = new ArrayList<>();

        String[] names = searchname.trim().split(" ");
        String name1 = "%" + names[0] + "%";
        String insItem;
        PreparedStatement prepareStatement;
        try(Connection connection = ds.getConnection()) {
        if (names.length>1) {
            String name2 ="%" + names[1] +"%";
            insItem ="SELECT * FROM USERS WHERE (lower(FirstName) LIKE lower(?) AND " +
                    "lower(LastName) LIKE lower(?)) OR (lower(FirstName) LIKE lower(?) " +
                    "AND lower(LastName) LIKE lower(?))";
            prepareStatement = connection.prepareStatement(insItem);
            prepareStatement.setString(1, name1);
            prepareStatement.setString(2, name2);
            prepareStatement.setString(3, name2);
            prepareStatement.setString(4, name1);
        }else {
            insItem ="SELECT * FROM USERS WHERE lower(FirstName) LIKE lower(?) " +
                    "OR lower(LastName) LIKE lower(?)";
            prepareStatement = connection.prepareStatement(insItem);
            prepareStatement.setString(1, name1);
            prepareStatement.setString(2, name1);

        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            String firstName = resultSet.getString("FirstName");
            String lastName = resultSet.getString("LastName");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            Person person = new Person(firstName,lastName,password,email);
            persons.add(person);
        }

        }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return persons;
    }


}
