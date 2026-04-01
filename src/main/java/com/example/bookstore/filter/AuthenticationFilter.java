package com.example.bookstore.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/books", "/sales", "/customers", "/orders",
                          "/editbook", "/editsale", "/editcustomer", "/editorder",
                          "/deletebook", "/deletesale", "/deletecustomer", "/deleteorder"})
public class AuthenticationFilter implements Filter {

    private static final String LOGIN_PAGE = "/login.jsp";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        boolean isLoginRequest = httpRequest.getRequestURI().endsWith("login.jsp");

        if (isLoggedIn || isLoginRequest) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + LOGIN_PAGE);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
