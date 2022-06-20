package pl.blog.javablog.category;

import pl.blog.javablog.common.BaseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDao extends BaseDao {
    public List<Category> findAll() {
        final String query = "SELECT * FROM category";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet generatedResults = statement.executeQuery(query);
            List<Category> categories = new ArrayList<>();
            while (generatedResults.next()) {
                Category category = mapRow(generatedResults);
                categories.add(category);
            }
            return categories;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Optional<Category> findByName(String name) {
        final String query = "SELECT * FROM category WHERE name = (?)";
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

    public Optional<Category> findById(Integer id) {
        final String query = "SELECT * FROM category WHERE id = (?)";
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

    private Category mapRow(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String description = resultSet.getString(3);
        return new Category(id, name, description);
    }
}
