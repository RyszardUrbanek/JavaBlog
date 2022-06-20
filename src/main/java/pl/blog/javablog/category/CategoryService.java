package pl.blog.javablog.category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryService {
    private final CategoryDao categoryDao = new CategoryDao();

    public List<CategoryDto> getAllCategories() {
        return categoryDao.findAll().stream().map(CategoryMapper::mapToCategoryDto).collect(Collectors.toList());
    }

    public Integer getCategoryIdByName(String name) {
        return categoryDao.findByName(name).orElseThrow().getId();
    }

    private static class CategoryMapper {
        static CategoryDto mapToCategoryDto(Category category) {
            return new CategoryDto(category.getName(), category.getDescription());
        }
    }
}
