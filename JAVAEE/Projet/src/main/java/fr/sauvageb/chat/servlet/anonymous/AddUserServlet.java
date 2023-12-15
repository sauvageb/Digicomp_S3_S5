package fr.sauvageb.chat.servlet.anonymous;

import fr.sauvageb.chat.dao.UserJdbcDao;
import fr.sauvageb.chat.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/add-user")
public class AddUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/add-user-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstNameParam");
        String lastName = req.getParameter("lastNameParam");
        String username = req.getParameter("usernameParam");

        User newUser = new User(username, firstName, lastName);
        // Save in database
        new UserJdbcDao().create(newUser);
        // Rediriger l'utilisateur
        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
