package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ResponseServletTest {

    @Test
    public void doPost() throws ServletException, IOException {
        ResponseServlet responseServlet = new ResponseServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("app-id")).thenReturn("1");
        when(request.getParameter("response-text")).thenReturn("text");

        responseServlet.doPost(request, response);

        verify(response, times(1)).sendRedirect(any());
    }
}