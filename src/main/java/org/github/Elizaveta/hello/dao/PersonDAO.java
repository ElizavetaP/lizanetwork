package org.github.Elizaveta.hello.dao;

import org.github.Elizaveta.hello.Friendship;
import org.github.Elizaveta.hello.Person;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    private final DataSource ds;

    public PersonDAO() {
        super();
        ds = DataSourceUtils.getDataSource();
    }

    public List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();
        ResultSet resultSet;

        try (Connection connection = ds.getConnection()) {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM USERS");
            while (resultSet.next()) {
                persons.add(new Person(resultSet.getString("FirstName"), resultSet.getString("LastName"),
                        resultSet.getString("email"), resultSet.getInt("ID"), resultSet.getString("sex"),
                        resultSet.getString("country"), resultSet.getString("town"),
                        resultSet.getString("education"), resultSet.getString("job"),
                        resultSet.getDate("birthday"), resultSet.getInt("photo_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return persons;
    }

    public boolean addUser(String firstName, String lastName, String password, String email) {

        for (Person person : getPersons()) {
            if (person.getEmail().equals(email)) {
                return false;
            }
        }
        try (Connection connection = ds.getConnection()) {
            String insItem = "INSERT INTO USERS (FirstName, LastName, email, password) VALUES (?, ?, lower(?), ?);";
            PreparedStatement prepareStatement = connection.prepareStatement(insItem);
            prepareStatement.setString(1, firstName);
            prepareStatement.setString(2, lastName);
            prepareStatement.setString(3, email);
            prepareStatement.setString(4, password);
            prepareStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean login(String password, String email) {
        try (Connection connection = ds.getConnection()) {
            String insItem = "SELECT * FROM USERS where password = ? and lower(email) = lower(?);";
            PreparedStatement prepareStatement = connection.prepareStatement(insItem);
            prepareStatement.setString(1, password);
            prepareStatement.setString(2, email);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Person getUser(int id) {
        Person user = null;
        try (Connection connection = ds.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM USERS where ID = ?;");
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                user = new Person(resultSet.getString("FirstName"), resultSet.getString("LastName"),
                        resultSet.getString("email"), resultSet.getInt("ID"), resultSet.getString("sex"),
                        resultSet.getString("country"), resultSet.getString("town"),
                        resultSet.getString("education"), resultSet.getString("job"),
                        resultSet.getDate("birthday"), resultSet.getInt("photo_id"));

            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public int getID(String email) {
        try (Connection connection = ds.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT ID FROM USERS where lower(email) = lower(?) ;");
            prepareStatement.setString(1, email);
            ResultSet resultSet = prepareStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editUser(int id, String firstname, String lastname, String sex,
                         String country, String town, String birthday,
                         String education, String job, String email) {
        try (Connection connection = ds.getConnection()) {
            String insItem = "update USERS set FirstName = ?, LastName= ?, sex = ?, country = ?" +
                    ", town = ?, birthday = ?, education =?, job = ?,email = ? where ID = ?;";
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
            prepareStatement.setInt(10, id);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> searchByName(String searchname) {
        List<Person> persons = new ArrayList<>();

        String[] names = searchname.trim().split(" ");
        String name1 = "%" + names[0] + "%";
        String insItem;
        PreparedStatement prepareStatement;
        try (Connection connection = ds.getConnection()) {
            if (names.length > 1) {
                String name2 = "%" + names[1] + "%";
                insItem = "SELECT * FROM USERS WHERE (lower(FirstName) LIKE lower(?) AND " +
                        "lower(LastName) LIKE lower(?)) OR (lower(FirstName) LIKE lower(?) " +
                        "AND lower(LastName) LIKE lower(?))";
                prepareStatement = connection.prepareStatement(insItem);
                prepareStatement.setString(1, name1);
                prepareStatement.setString(2, name2);
                prepareStatement.setString(3, name2);
                prepareStatement.setString(4, name1);
            } else {
                insItem = "SELECT * FROM USERS WHERE lower(FirstName) LIKE lower(?) " +
                        "OR lower(LastName) LIKE lower(?)";
                prepareStatement = connection.prepareStatement(insItem);
                prepareStatement.setString(1, name1);
                prepareStatement.setString(2, name1);

                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    persons.add(new Person(resultSet.getString("FirstName"), resultSet.getString("LastName"),
                            resultSet.getString("email"), resultSet.getInt("ID"), resultSet.getString("sex"),
                            resultSet.getString("country"), resultSet.getString("town"),
                            resultSet.getString("education"), resultSet.getString("job"),
                            resultSet.getDate("birthday"), resultSet.getInt("photo_id")));
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return persons;
    }

    public List<Person> getFriends(int id) {
        FriendshipDAO friendshipDAO = new FriendshipDAO();
        List<Friendship> friendship = friendshipDAO.getFriendship(id);
        String idList = "";
        for (int i = 0; i < friendship.size(); i++) {
            if (i < friendship.size() - 1) {
                idList += friendship.get(i).getIdOtheruser() + ", ";
            } else {
                idList += friendship.get(i).getIdOtheruser();
            }
        }
        List<Person> friends = new ArrayList<>();
        try (Connection connection = ds.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS where ID in (" + idList + ");");
            while (resultSet.next()) {
                friends.add(new Person(resultSet.getString("FirstName"), resultSet.getString("LastName"),
                        resultSet.getString("email"), resultSet.getInt("ID"), resultSet.getString("sex"),
                        resultSet.getString("country"), resultSet.getString("town"),
                        resultSet.getString("education"), resultSet.getString("job"),
                        resultSet.getDate("birthday"), resultSet.getInt("photo_id")));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return friends;
    }

}
