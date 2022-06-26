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

public class UserApplicationsServletTest {

    @Test(expected = NullPointerException.class)
    public void doGet() throws ServletException, IOException {
        UserApplicationsServlet userApplicationsServlet = new UserApplicationsServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ServletConfig config = mock(ServletConfig.class);


        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(new User());

        userApplicationsServlet.init(config);
        userApplicationsServlet.doGet(request, response);

        verify(request, times(2)).setAttribute(any(), any());
    }
}