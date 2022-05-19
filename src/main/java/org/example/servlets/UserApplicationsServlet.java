package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.models.Application;
import org.example.models.User;

import java.io.IOException;
import java.util.List;

import static org.example.service.ApplicationService.getUserApplications;
import static org.example.service.UserService.getServicemenNamesByEmail;

@WebServlet("/userApplications")
public class UserApplicationsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Application> applicationList = getUserApplications(user.getEmail());

        List<String> servicemenNames = getServicemenNamesByEmail(applicationList);

        req.setAttribute("servicemenNames", servicemenNames);
        req.setAttribute("list", applicationList);
        getServletContext().getRequestDispatcher("/userApplications.jsp").forward(req, resp);
    }
}
