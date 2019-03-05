package com.futao.springbootdemo.foundation;

import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author futao
 * Created on 2018/9/19-15:47.
 */
@WebFilter(filterName = "AppFilter", urlPatterns = "/*")
public class AppFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //白名单
        ArrayList<String> allowOrigins = new ArrayList<>();
//        allowOrigins.add("http://localhost:63343");
//        allowOrigins.add("http://localhost:9527");
//        allowOrigins.add("http://localhost:8080");
//        allowOrigins.add("http://localhost:4444");

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

//        ArrayList<String> allowOrigins = (ArrayList<String>) req.getServletContext().getAttribute("allowOrigins");
//        String origin = request.getHeader("Origin");
//        if (allowOrigins.contains(origin)) {
//            response.setHeader("Access-Control-Allow-Origin", origin);
//        }
//        // Access-Control-Max-Age
//        response.setHeader("Access-Control-Max-Age", "3600");
//        // Access-Control-Allow-Credentials
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        // Access-Control-Allow-Methods
//        response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, DELETE");
//
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

//        config.getServletContext().setAttribute("allowOrigins", allowOrigins);
    }

}
