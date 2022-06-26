package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserUpdateServletTest {
    @Test
    public void service() throws ServletException, IOException {
        UserUpdateServlet userUpdateServlet = new UserUpdateServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("email")).thenReturn("email@gmail.com");
        when(request.getParameter("change-role")).thenReturn("User");
        when(request.getParameter("add-balance")).thenReturn("100");

        userUpdateServlet.service(request, response);
    }
}