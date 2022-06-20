package pl.blog.javablog.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.blog.javablog.category.CategoryService;
import pl.blog.javablog.user.User;
import pl.blog.javablog.user.UserService;

import java.io.IOException;

@WebServlet(name = "UserPanelController", value = "/settings")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = {"USER", "ADMIN"})
        }
)
public class UserPanelController extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("user", userService.getUserInfoByName(request.getUserPrincipal().getName()));
        request.setAttribute("categories", categoryService.getAllCategories());
        if (userService.getUserRoleByUsername(request.getUserPrincipal().getName()).equals("USER")) {
            request.getRequestDispatcher("/WEB-INF/views/user-panel.jsp").forward(request, response);
        } else if (userService.getUserRoleByUsername(request.getUserPrincipal().getName()).equals("ADMIN")) {
            request.getRequestDispatcher("/WEB-INF/views/admin-panel.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
