package pl.blog.javablog.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.blog.javablog.category.CategoryDto;
import pl.blog.javablog.category.CategoryService;
import pl.blog.javablog.post.ContentsPost;
import pl.blog.javablog.post.PostService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "HomeController", value = "")
public class HomeController extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();
    private final PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categories", categoryService.getAllCategories());
        request.setAttribute("posts", postService.findAllPostsToDisplay());
        request.setAttribute("contents", getContents());
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private Map<String, List<ContentsPost>> getContents() {
        Map<String, List<ContentsPost>> contents = new HashMap<>();
        List<CategoryDto> allCategories = categoryService.getAllCategories();

        for (CategoryDto c : allCategories) {
            contents.put(c.getName(), postService.findPostLinksById(categoryService.getCategoryIdByName(c.getName())));
        }
        return contents;
    }
}
