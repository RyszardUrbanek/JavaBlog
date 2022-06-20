package pl.blog.javablog.post;

import java.time.LocalDateTime;

public class PostPagePost {
    private final String title;
    private final String content;
    private final LocalDateTime dateAdded;
    private final String username;

    public PostPagePost(String title, String content, LocalDateTime dateAdded, String username) {
        this.title = title;
        this.content = content;
        this.dateAdded = dateAdded;
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public String getUsername() {
        return username;
    }
}
