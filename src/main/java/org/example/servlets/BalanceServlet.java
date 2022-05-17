package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.models.User;

import java.io.IOException;

import static org.example.service.UserService.updateUser;

@WebServlet("/balance")
public class BalanceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Double userBalance = user.getBalance();
        Double addedBalance = Double.parseDouble(req.getParameter("add-balance"));
        Double newUserBalance = Double.sum(userBalance, addedBalance);
        user.setBalance(newUserBalance);

        updateUser(user);

        session.setAttribute("user", user);
        resp.sendRedirect("profile");
    }
}
