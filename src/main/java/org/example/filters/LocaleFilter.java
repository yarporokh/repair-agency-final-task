package org.example.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.jstl.core.Config;

import java.io.IOException;
import java.util.Locale;


@WebFilter(filterName = "LocaleFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {
    public void init(FilterConfig arg0) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String locale = null;

        final Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("lang")) {
                    locale = cookie.getValue();
                }
            }
        }
        if (req.getParameter("lang") != null) {
            locale = req.getParameter("lang");
            Cookie cookie = new Cookie("lang", locale);
            resp.addCookie(cookie);
        }
        if (locale == null) locale = "en";
        Locale localeObj = new Locale(locale);
        Config.set(req.getSession(), Config.FMT_LOCALE, localeObj);

        chain.doFilter(req, resp);
    }

    public void destroy() {
    }
}
