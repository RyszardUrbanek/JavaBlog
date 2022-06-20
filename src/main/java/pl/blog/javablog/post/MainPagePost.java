package pl.blog.javablog.post;

import java.time.LocalDateTime;

public class MainPagePost {
    private final Integer id;
    private final String title;
    private final String introduction;
    private final LocalDateTime dateAdded;
    private final String url;
    private final String username;
    private final String category;
    private final Integer voteUp;
    private final Integer voteDown;

    public MainPagePost(Integer id, String title, String introduction, LocalDateTime dateAdded, String url, String username, String category, Integer voteUp, Integer voteDown) {
        this.id = id;
        this.title = title;
        this.introduction = introduction;
        this.dateAdded = dateAdded;
        this.url = url;
        this.username = username;
        this.category = category;
        this.voteUp = voteUp;
        this.voteDown = voteDown;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getCategory() {
        return category;
    }

    public Integer getVoteUp() {
        return voteUp;
    }

    public Integer getVoteDown() {
        return voteDown;
    }
}
