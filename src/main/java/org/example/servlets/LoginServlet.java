package org.example.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.models.User;

import java.io.IOException;

import static org.example.service.UserService.loggedUser;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        User user = loggedUser(email, password);
        if (user != null) {
            session.setAttribute("user", user);
            resp.sendRedirect("profile");
        } else {
            resp.sendRedirect("login.jsp");
        }
    }
}
