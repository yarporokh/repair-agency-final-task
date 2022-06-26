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

public class UsersServletTest {

    @Test(expected = NullPointerException.class)
    public void doGet() throws ServletException, IOException {
        UsersServlet usersServlet = new UsersServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ServletConfig config = mock(ServletConfig.class);
        User user = new User();
        user.setEmail("email@gmail.com");

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("page")).thenReturn("1");

        usersServlet.init(config);
        usersServlet.doGet(request, response);

        verify(request, times(3)).setAttribute(any(), any());

    }
}