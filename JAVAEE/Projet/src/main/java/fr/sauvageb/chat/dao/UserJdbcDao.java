package fr.sauvageb.chat.dao;

import fr.sauvageb.chat.dao.base.ConnectionManager;
import fr.sauvageb.chat.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDao implements UserDao {

    @Override
    public boolean create(User entity) {
        Connection connection = ConnectionManager.getInstance();
        String query = "INSERT INTO users(username, firstname, lastname, email, password, pictureUrl) VALUES(?,?,?,?,?,?)";
        boolean insertOk = false;
        try (PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            prepareStatement.setString(1, entity.getUsername());
            prepareStatement.setString(2, entity.getFirstname());
            prepareStatement.setString(3, entity.getLastname());
            prepareStatement.setString(4, entity.getEmail());
            prepareStatement.setString(5, entity.getPassword());
            prepareStatement.setString(6, entity.getPictureUrl());

            int rowsAffected = prepareStatement.executeUpdate();

            insertOk = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertOk;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();

        Connection connection = ConnectionManager.getInstance();
        String query = "SELECT id, username, firstname, lastname, email, password, pictureUrl FROM users";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User u = mapToUser(resultSet);
                userList.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    private User mapToUser(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String username = resultSet.getString("username");
        String firstname = resultSet.getString("firstname");
        String lastname = resultSet.getString("lastname");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String pictureUrl = resultSet.getString("pictureUrl");
        return new User(id, username, firstname, lastname, email, password, pictureUrl);
    }

    @Override
    public User findById(Long id) {
        Connection connection = ConnectionManager.getInstance();
        String query = "SELECT id, username, firstname, lastname, email, password, pictureUrl FROM users WHERE id = ?";
        User user = null;
        try (PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            prepareStatement.setLong(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                user = mapToUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public User findByUsername(String usernameSearched) {
        User userFound = null;
        Connection connection = ConnectionManager.getInstance();
        String query = "SELECT id, username, firstname, lastname, email, password, pictureUrl FROM users WHERE username=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, usernameSearched);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userFound = mapToUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userFound;
    }
}
