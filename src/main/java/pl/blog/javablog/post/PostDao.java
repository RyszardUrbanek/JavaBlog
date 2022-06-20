package pl.blog.javablog.post;

import pl.blog.javablog.common.BaseDao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostDao extends BaseDao {
    public void save(Post post) {
        final String query = """
                INSERT INTO
                post(title, date_added, introduction, main_content, url, user_id, category_id)
                VALUES(?, ?, ?, ?, ?, ?, ?)
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, post.getTitle());
            statement.setObject(2, post.getDateAdded());
            statement.setString(3, post.getIntroduction());
            statement.setString(4, post.getMainContent());
            statement.setString(5, "");
            statement.setInt(6, post.getUserId());
            statement.setInt(7, post.getCategoryId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                post.setId(generatedKeys.getInt(1));
                setUrl(post);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Post> findAllBySubstring(String substring) {
        final String query = "SELECT * FROM post WHERE INSTR(title , (?))";
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, substring);
            ResultSet generatedKeys = statement.executeQuery();
            List<Post> posts = new ArrayList<>();
            while (generatedKeys.next()) {
                posts.add(mapRow(generatedKeys));
            }
            return posts;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Post> findAllBySubstringAndCategory(String substring, Integer category) {
        final String query = "SELECT * FROM post WHERE INSTR(title , (?)) AND category_id = (?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, substring);
            statement.setInt(2, category);
            ResultSet generatedKeys = statement.executeQuery();
            List<Post> posts = new ArrayList<>();
            while (generatedKeys.next()) {
                posts.add(mapRow(generatedKeys));
            }
            return posts;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Post> findAllByCategory(Integer categoryId) {
        final String query = "SELECT * FROM post WHERE category_id = (?)";
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, categoryId);
            ResultSet generatedKeys = statement.executeQuery();
            List<Post> posts = new ArrayList<>();
            while (generatedKeys.next()) {
                posts.add(mapRow(generatedKeys));
            }
            return posts;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Optional<Post> findById(Integer id) {
        final String query = "SELECT * FROM post WHERE id = (?)";
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, id);
            ResultSet generatedKeys = statement.executeQuery();
            if (generatedKeys.next()) {
                return Optional.of(mapRow(generatedKeys));
            } else {
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Post> findAll() {
        final String query = "SELECT * FROM post";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet generatedResults = statement.executeQuery(query);
            List<Post> posts = new ArrayList<>();
            while (generatedResults.next()) {
                Post post = mapRow(generatedResults);
                posts.add(post);
            }
            return posts;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void setUrl(Post post) {
        final String query = "UPDATE post SET url = (?) WHERE id = (?)";
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "?id=" + post.getId());
            statement.setInt(2, post.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Post mapRow(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(1);
        String title = resultSet.getString(2);
        LocalDateTime dateAdded = resultSet.getTimestamp(3).toLocalDateTime();
        String description = resultSet.getString(4);
        String mainContent = resultSet.getString(5);
        String url = resultSet.getString(6);
        Integer userId = resultSet.getInt(7);
        Integer categoryId = resultSet.getInt(8);
        return new Post(id, title, description, mainContent, dateAdded, url, userId, categoryId);
    }
}
