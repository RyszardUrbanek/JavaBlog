package pl.blog.javablog.post;

public class PostPublication {
    private final String title;
    private final String introduction;
    private final String mainContent;
    private final Integer categoryId;
    private final Integer userId;

    public PostPublication(String title, String introduction, String mainContent, Integer categoryId, Integer userId) {
        this.title = title;
        this.introduction = introduction;
        this.mainContent = mainContent;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getMainContent() {
        return mainContent;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getUserId() {
        return userId;
    }
}
