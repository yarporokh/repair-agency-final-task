package org.example.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.UserService;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RegisterServletTest {

    @Test(expected = NullPointerException.class)
    public void service() throws ServletException, IOException {
        RegisterServlet registerServlet = new RegisterServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletConfig config = mock(ServletConfig.class);

        when(request.getParameter("firstname")).thenReturn("Firstname");
        when(request.getParameter("lastname")).thenReturn("Lastname");
        when(request.getParameter("email")).thenReturn("email@gmail.com");
        when(request.getParameter("password1")).thenReturn("123456");
        when(request.getParameter("password2")).thenReturn("123456");
        registerServlet.init(config);
        registerServlet.service(request, response);
    }
}