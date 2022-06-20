package pl.blog.javablog.vote;

public class PostVote {
    private final String username;
    private final Integer postId;
    private final String type;

    public PostVote(String username, Integer discoveryId, String type) {
        this.username = username;
        this.postId = discoveryId;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public Integer getPostId() {
        return postId;
    }

    public String getType() {
        return type;
    }
}
