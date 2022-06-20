package pl.blog.javablog.post;

public class ContentsPost {
    private final String title;
    private final String url;

    public ContentsPost(String name, String url) {
        this.title = name;
        this.url = url;
    }

    public String getName() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return title;
    }
}
