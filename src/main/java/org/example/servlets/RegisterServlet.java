package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.example.service.UserService.addUser;
import static org.example.service.UserService.isUserExist;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");

        if (!password1.equals(password2) || isUserExist(email)) {
            req.setAttribute("regerror", 1);
            getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
        } else {
            addUser(email, password1, firstName, lastName);
            resp.sendRedirect("login.jsp");
        }


    }
}
