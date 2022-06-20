package pl.blog.javablog.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.blog.javablog.vote.PostVote;
import pl.blog.javablog.vote.VoteService;

import java.io.IOException;

@WebServlet(name = "VoteController", value = "/post/vote")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = {"USER", "ADMIN"})
        }
)
public class VoteController extends HttpServlet {
    private final VoteService voteService = new VoteService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostVote postVote = createDiscoveryVote(request);
        voteService.addVote(postVote);
        response.sendRedirect(request.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private PostVote createDiscoveryVote(HttpServletRequest request) {
        Integer voteId = Integer.valueOf(request.getParameter("id"));
        String voteType = request.getParameter("type");
        String username = request.getUserPrincipal().getName();
        return new PostVote(username, voteId, voteType);
    }
}
