package fr.sauvageb.chat.servlet.anonymous;

import fr.sauvageb.chat.dao.UserJdbcDao;
import fr.sauvageb.chat.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/users")
public class UserListServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Fetch data from database
        List<User> userList = new UserJdbcDao().findAll();
        // Put data in view
        request.setAttribute("users", userList);
        // displaying page JSP : Java Server Pages (HTML + CSS + JS + Java)
        request.getRequestDispatcher("/WEB-INF/user-list.jsp").forward(request, response);
    }


}
