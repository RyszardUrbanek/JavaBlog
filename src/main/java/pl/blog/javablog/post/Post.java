package pl.blog.javablog.post;

import java.time.LocalDateTime;

public class Post {
    private Integer id;
    private String title;
    private String introduction;
    private String mainContent;
    private LocalDateTime dateAdded;
    private String url;
    private Integer userId;
    private Integer categoryId;

    public Post(String title, String introduction, String mainContent, LocalDateTime dateAdded,
                Integer userId, Integer categoryId) {
        this.title = title;
        this.introduction = introduction;
        this.mainContent = mainContent;
        this.dateAdded = dateAdded;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public Post(Integer id, String title, String introduction, String mainContent, LocalDateTime dateAdded, String url, Integer userId, Integer categoryId) {
        this.id = id;
        this.title = title;
        this.introduction = introduction;
        this.mainContent = mainContent;
        this.dateAdded = dateAdded;
        this.url = url;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getMainContent() {
        return mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
