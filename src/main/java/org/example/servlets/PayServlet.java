package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.models.User;

import java.io.IOException;

import static org.example.service.ApplicationService.updatePaymentStatus;
import static org.example.service.UserService.updateBalanceAndRole;

@WebServlet("/pay")
public class PayServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        int applicationId = Integer.parseInt(req.getParameter("id"));

        double price = Double.parseDouble(req.getParameter("price"));
        double diff = user.getBalance() - price;

        if (diff < 0.0) {
            resp.sendRedirect("balance.jsp");
        } else {
            updatePaymentStatus(applicationId);
            updateBalanceAndRole(user.getEmail(), user.getRole().toString(), diff);
            resp.sendRedirect("userApplications");
        }
    }
}
