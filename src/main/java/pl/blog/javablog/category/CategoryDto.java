package pl.blog.javablog.category;

public class CategoryDto {
    private final String name;
    private final String description;

    public CategoryDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
