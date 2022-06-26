package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.models.Role;
import org.example.models.User;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AllApplicationsServletTest {

    @Test
    public void doPost() throws ServletException, IOException {
        AllApplicationsServlet allApplicationsServlet = new AllApplicationsServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        User user = new User();
        user.setEmail("user@gmail.com");

        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("change-serviceman")).thenReturn("email@gmail.com");
        when(request.getParameter("change-payment-status")).thenReturn("Paid");
        when(request.getParameter("change-progress")).thenReturn("Done");
        when(request.getParameter("set-price")).thenReturn("100");
        when(request.getParameter("take-application")).thenReturn("2");
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("default-email")).thenReturn("demail@gmail.com");
        when(request.getParameter("default-progress")).thenReturn(any());


        allApplicationsServlet.doPost(request, response);
        verify(response, times(1)).sendRedirect(any());
    }
}