package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.models.User;
import org.example.service.UserService;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class BalanceServletTest {

    @Test(expected = NullPointerException.class)
    public void doPost() throws ServletException, IOException {
        BalanceServlet balanceServlet = new BalanceServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        User user = new User();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("add-balance")).thenReturn("10");

        balanceServlet.doPost(request, response);

        verify(session, times(1)).setAttribute(any(), any());
        verify(response, times(1)).sendRedirect(any());
    }
}