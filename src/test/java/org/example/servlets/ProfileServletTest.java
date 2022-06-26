package org.example.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProfileServletTest {

    @Test(expected = NullPointerException.class)
    public void service() throws ServletException, IOException {
        ProfileServlet profileServlet = new ProfileServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        profileServlet.service(request, response);

        verify(dispatcher, times(1)).forward(request, response);

    }
}