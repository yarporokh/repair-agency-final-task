package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.models.User;

import java.io.IOException;

import static org.example.service.ApplicationService.addApplication;

@WebServlet("/createApplication")
public class CreateApplicationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String text = req.getParameter("application-text");
        addApplication(user.getEmail(), text);
        resp.sendRedirect("userApplications");
    }
}
