package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.example.service.UserService.updateBalanceAndRole;

@WebServlet("/userUpdate")
public class UserUpdateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String role = req.getParameter("change-role");
        double balance = Double.parseDouble((req.getParameter("add-balance")));

        updateBalanceAndRole(email, role, balance);
        resp.sendRedirect("users");
    }

}
