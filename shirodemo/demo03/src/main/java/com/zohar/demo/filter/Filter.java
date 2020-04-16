package com.zohar.demo.filter;

import org.springframework.http.HttpRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter
public class Filter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpRequest httpRequest = (HttpRequest) servletRequest;

        System.out.println(httpRequest.getURI());
    }

    @Override
    public void destroy() {

    }
}
