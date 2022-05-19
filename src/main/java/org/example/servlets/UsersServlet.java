package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.models.User;

import java.io.IOException;
import java.util.List;

import static org.example.service.UserService.getUsers;
import static org.example.service.UserService.updateBalanceAndRole;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
        List<User> userList = getUsers();
        HttpSession session = req.getSession();
        try {
            User user = (User) session.getAttribute("user");
            req.setAttribute("userEmail", user.getEmail());
        } catch (Exception e) {

        }

        req.setAttribute("list", userList);
        getServletContext().getRequestDispatcher("/users.jsp").forward(req, resp);
    }

}
