package pl.blog.javablog.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.blog.javablog.category.CategoryService;
import pl.blog.javablog.post.Post;
import pl.blog.javablog.post.PostPublication;
import pl.blog.javablog.post.PostService;
import pl.blog.javablog.user.UserService;

import java.io.IOException;

@WebServlet(name = "NewPostController", value = "/new")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = "ADMIN")
        }
)
public class NewPostController extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();
    private final UserService userService = new UserService();
    private final PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        postService.savePost(getPostPublication(request));
        response.sendRedirect(request.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private PostPublication getPostPublication(HttpServletRequest request) {
        return new PostPublication(
                request.getParameter("title"),
                request.getParameter("description"),
                request.getParameter("content"),
                categoryService.getCategoryIdByName(request.getParameter("categories")),
                userService.findUserIdByName(request.getUserPrincipal().getName())
        );
    }
}
