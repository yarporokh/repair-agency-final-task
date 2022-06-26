package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.models.User;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CreateApplicationServletTest {

    @Test
    public void doPost() throws ServletException, IOException {
        CreateApplicationServlet createApplicationServlet = new CreateApplicationServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        User user = new User();
        user.setEmail("email@gmail.com");

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("application-text")).thenReturn("text");

        createApplicationServlet.doPost(request, response);
        verify(response, times(1)).sendRedirect(any());
    }
}