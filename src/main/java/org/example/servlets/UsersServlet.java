package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.models.User;
import org.example.service.UserService;

import java.io.IOException;
import java.util.List;

import static org.example.service.UserService.getUserRecords;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            User user = (User) session.getAttribute("user");
            req.setAttribute("userEmail", user.getEmail());
        } catch (Exception e) {

        }

        int pageid = 0;
        try {
            String spageid = req.getParameter("page");
            pageid = Integer.parseInt(spageid);
        } catch (Exception e) {
            pageid = 1;
        }

        int total = 10;

        if (pageid == 1) {
        } else {
            pageid = pageid - 1;
            pageid = pageid * total + 1;
        }

        int totalPages = UserService.getNumberOfRows() / total;
        if (UserService.getNumberOfRows() % total != 0)
            totalPages++;

        List<User> list = getUserRecords(pageid, total);

        req.setAttribute("numberOfPages", totalPages);
        req.setAttribute("list", list);
        getServletContext().getRequestDispatcher("/users.jsp").forward(req, resp);
    }

}
