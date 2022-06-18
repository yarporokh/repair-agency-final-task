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

import static org.example.service.UserService.getServicemenEmailsList;

@WebServlet("/sortedApplications")
public class SortedAllApplicationsServlet extends HttpServlet {
    String currentSorting;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        String sorting = req.getParameter("sortingBy");
        currentSorting = sorting == null ? currentSorting : sorting;


        List<Application> list = ApplicationService.getSortedRecords(pageid, total, currentSorting);

        req.setAttribute("sortingBy", sorting);
        req.setAttribute("numberOfPages", totalPages);
        req.setAttribute("emails", servicemenEmails);
        req.setAttribute("applicationList", list);
        getServletContext().getRequestDispatcher("/allApplications.jsp").forward(req, resp);
    }
}
