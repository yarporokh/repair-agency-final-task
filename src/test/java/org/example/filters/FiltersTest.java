package org.example.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.filters.EncodingFilter;
import org.example.filters.LocaleFilter;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class FiltersTest {
    @Test
    public void filtersTest() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);


        LocaleFilter localeFilter = mock(LocaleFilter.class);
        localeFilter.doFilter(request, response, filterChain);
        verify(localeFilter, times(1)).doFilter(request, response, filterChain);

        EncodingFilter encodingFilter = mock(EncodingFilter.class);
        encodingFilter.doFilter(request, response, filterChain);
        verify(encodingFilter, times(1)).doFilter(request, response, filterChain);
    }
}
