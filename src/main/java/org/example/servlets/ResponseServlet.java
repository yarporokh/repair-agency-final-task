package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.example.service.ApplicationService.updateResponseText;

@WebServlet("/response")
public class ResponseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int applicationId = Integer.parseInt(req.getParameter("app-id"));
        String responseText = req.getParameter("response-text");
        updateResponseText(applicationId, responseText);
        resp.sendRedirect("userApplications");
    }
}
