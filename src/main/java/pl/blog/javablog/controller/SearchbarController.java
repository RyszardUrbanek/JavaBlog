package pl.blog.javablog.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.blog.javablog.category.CategoryDto;
import pl.blog.javablog.category.CategoryService;
import pl.blog.javablog.post.MainPagePost;
import pl.blog.javablog.post.ContentsPost;
import pl.blog.javablog.post.PostService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SearchbarController", value = "/search")
public class SearchbarController extends HttpServlet {
    private final PostService postService = new PostService();
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("posts", returnFoundPosts(request));
        request.setAttribute("contents", getContents());
        request.setAttribute("categories", categoryService.getAllCategories());
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }

    private List<MainPagePost> returnFoundPosts(HttpServletRequest request) {
        String userInput = request.getParameter("s");
        Integer categoryToSearch;
        if (request.getParameter("category").equals("0")) {
            categoryToSearch = 0;
        } else {
            categoryToSearch = categoryService.getCategoryIdByName(request.getParameter("category"));
        }
        Integer dateToSort = Integer.parseInt(request.getParameter("date"));

        return postService.getSearchedPosts(userInput, categoryToSearch, dateToSort);
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