package pl.blog.javablog.vote;

import pl.blog.javablog.common.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VoteDao extends BaseDao {
    public void save(Vote vote) {
        final String query = """
                INSERT INTO
                    vote(user_id, post_id, type, date_added)
                VALUES
                    (?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE
                    type = ?
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, vote.getUserId());
            statement.setInt(2, vote.getPostId());
            statement.setString(3, vote.getType().toString());
            statement.setObject(4, vote.getDateAdded());
            statement.setString(5, vote.getType().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer countVoteUpByUserId(Integer userId) {
        final String query = """
                SELECT
                	(SELECT COUNT(post_id) FROM vote WHERE user_id = ? AND type = 'UP')
                    AS
                    vote_count;
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("vote_count");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer countVoteDownByUserId(Integer userId) {
        final String query = """
                SELECT
                	(SELECT COUNT(post_id) FROM vote WHERE user_id = ? AND type = 'DOWN')
                    AS
                    vote_count;
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("vote_count");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countVoteUpByPostId(Integer postId) {
        final String query = """
                SELECT
                	(SELECT COUNT(post_id) FROM vote WHERE post_id = ? AND type = 'UP')
                    AS
                    vote_count;
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, postId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("vote_count");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countVoteDownByPostId(Integer postId) {
        final String query = """
                SELECT
                	(SELECT COUNT(post_id) FROM vote WHERE post_id = ? AND type = 'DOWN')
                    AS
                    vote_count;
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, postId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("vote_count");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
