package com.futao.springbootdemo.controller;

import org.apache.http.HttpStatus;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author futao
 * Created on 2019-01-23.
 */
@ApiIgnore
@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public void handleError(HttpServletRequest request, HttpServletResponse response) throws NoHandlerFoundException {
        if (response.getStatus() == HttpStatus.SC_NOT_FOUND) {
            throw new NoHandlerFoundException(request.getMethod(), request.getRequestURL().toString(), new HttpHeaders());
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}