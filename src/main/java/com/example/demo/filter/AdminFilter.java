package com.example.demo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminFilter implements Filter {

//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        PrintWriter out = new HttpServletResponseWrapper(
                (HttpServletResponse) servletResponse).getWriter();
        out.write("{\n"
                + "    \"status\": 10007,\n"
                + "    \"msg\": \"NEED_LOGIN\",\n"
                + "    \"data\": null\n"
                + "}");
        out.flush();
        out.close();
        return;
    }
//    @Override
//    public void destroy() {
//
//    }
}
