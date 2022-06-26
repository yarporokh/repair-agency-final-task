package org.example.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class LoginServletTest {

    @Test(expected = NullPointerException.class)
    public void doPost() throws ServletException, IOException {
        LoginServlet loginServlet = new LoginServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ServletConfig config = mock(ServletConfig.class);
        ServletContext context = mock(ServletContext.class);

        doReturn(context).when(request).getServletContext();
        when(request.getParameter("email")).thenReturn("email@gmail.com");
        when(request.getParameter("password")).thenReturn("password");

        loginServlet.init(config);
        loginServlet.doPost(request, response);

        verify(session, times(1)).setAttribute(any(), any());

    }
}