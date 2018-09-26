package com.futao.springmvcdemo.foundation;

import org.apache.commons.lang3.StringUtils;
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

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        ArrayList<String> allowOrigins = (ArrayList<String>) req.getServletContext().getAttribute("allowOrigins");
        String origin = request.getHeader("Origin");
        String allowOrigin = StringUtils.EMPTY;
        if (allowOrigins.contains(origin)) {
            allowOrigin = origin;
        }
        response.setHeader("Access-Control-Allow-Origin", allowOrigin);
        // Access-Control-Max-Age
        response.setHeader("Access-Control-Max-Age", "3600");
        // Access-Control-Allow-Credentials
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // Access-Control-Allow-Methods
        response.setHeader("Access-Control-Allow-Methods", "PUT,POST, GET, OPTIONS, DELETE");

        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        ArrayList<String> allowOrigins = new ArrayList<>();
        allowOrigins.add("http://localhost:63343");
        config.getServletContext().setAttribute("allowOrigins", allowOrigins);
    }

}
