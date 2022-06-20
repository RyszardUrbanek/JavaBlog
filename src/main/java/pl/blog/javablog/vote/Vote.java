package pl.blog.javablog.vote;

import java.time.LocalDateTime;

public class Vote {
    private Integer userId;
    private Integer postId;
    private Type type;
    private LocalDateTime dateAdded;

    public Vote(Integer userId, Integer discoveryId, Type type, LocalDateTime dateAdded) {
        this.userId = userId;
        this.postId = discoveryId;
        this.type = type;
        this.dateAdded = dateAdded;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public Type getType() {
        return type;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public enum Type {
        UP, DOWN
    }
}
