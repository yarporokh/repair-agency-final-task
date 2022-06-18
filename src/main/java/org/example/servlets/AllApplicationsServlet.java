package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.models.Application;
import org.example.models.User;
import org.example.service.ApplicationService;

import java.io.IOException;
import java.util.List;

import static org.example.service.ApplicationService.updateApplication;
import static org.example.service.UserService.getServicemenEmailsList;

@WebServlet("/allApplications")
public class AllApplicationsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> servicemenEmails = getServicemenEmailsList();
        HttpSession session = req.getSession();
        try {
            User user = (User) session.getAttribute("user");

            req.setAttribute("u", user);
        } catch (Exception e) {

        }

        int pageid = 0;
        try {
            String spageid = req.getParameter("page");
            pageid = Integer.parseInt(spageid);
        } catch (Exception e) {
            pageid = 1;
        }

        int total = 5;

        if (pageid == 1) {
        } else {
            pageid = pageid - 1;
            pageid = pageid * total + 1;
        }

        int totalPages = ApplicationService.getNumberOfRows() / total;
        if (ApplicationService.getNumberOfRows() % total != 0)
            totalPages++;

        List<Application> list = ApplicationService.getRecords(pageid, total);

        req.setAttribute("numberOfPages", totalPages);
        req.setAttribute("emails", servicemenEmails);
        req.setAttribute("applicationList", list);
        getServletContext().getRequestDispatcher("/allApplications.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        double price;
        String servicemanEmail = req.getParameter("change-serviceman");
        String paymentStatus = req.getParameter("change-payment-status");
        String progress = req.getParameter("change-progress");

        if (req.getParameter("set-price") == null)
            price = Double.parseDouble(req.getParameter("default-price"));
        else
            price = Double.parseDouble(req.getParameter("set-price"));

        if (servicemanEmail == null)
            servicemanEmail = req.getParameter("default-email");

        if (req.getParameter("take-application") != null) {
            try {
                HttpSession session = req.getSession();
                User user = (User) session.getAttribute("user");
                servicemanEmail = user.getEmail();
            } catch (Exception e) {

            }
        }


        if (paymentStatus == null)
            paymentStatus = req.getParameter("default-payment-status");

        if (progress == null)
            progress = req.getParameter("default-progress");

        updateApplication(id, price, servicemanEmail, paymentStatus, progress, "");
        resp.sendRedirect("allApplications");
    }
}