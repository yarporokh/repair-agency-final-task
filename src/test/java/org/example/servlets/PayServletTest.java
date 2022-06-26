package org.example.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.models.User;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PayServletTest {

    @Test(expected = NullPointerException.class)
    public void doPost() throws ServletException, IOException {
        PayServlet payServlet = new PayServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(session.getAttribute("user")).thenReturn(new User());
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("price")).thenReturn("100");

        payServlet.doPost(request, response);

        verify(response, times(1)).sendRedirect(any());
    }
}