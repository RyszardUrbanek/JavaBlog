package pl.blog.javablog.user;

import pl.blog.javablog.common.BaseDao;

import java.sql.*;
import java.util.Optional;

public class UserDao extends BaseDao {
    public void save(User user) {
        saveUser(user);
        saveUserRole(user);
    }

    public Optional<User> findById(Integer id) {
        final String query = "SELECT * FROM user WHERE id = (?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet generatedResults = statement.executeQuery();
            if (generatedResults.next()) {
                return Optional.of(mapRow(generatedResults));
            } else {
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Optional<String> getUserRoleByUsername(String username) {
        final String query = "SELECT role_name FROM user_role WHERE username = (?)";
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, username);
            ResultSet generatedKeys = statement.executeQuery();
            if (generatedKeys.next()) {
                return Optional.of(generatedKeys.getString(1));
            } else {
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Optional<User> findByUsername(String name) {
        final String query = "SELECT * FROM user WHERE username = (?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            ResultSet generatedResults = statement.executeQuery();
            if (generatedResults.next()) {
                return Optional.of(mapRow(generatedResults));
            } else {
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void saveUser(User user) {
        final String query = """
                        INSERT INTO
                            user(username, email, registration_date, password)
                        VALUES
                            (?, ?, ?, ?)
                        """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setObject(3, user.getRegistrationDate());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveUserRole(User user) {
        final String query = """
                        INSERT INTO
                            user_role (username)
                        VALUES
                            (?)
                        """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User mapRow(ResultSet users) throws SQLException {
        return new User(
                users.getInt(1),
                users.getString(2),
                users.getString(3),
                users.getTimestamp(4).toLocalDateTime(),
                users.getString(5)
        );
    }
}
