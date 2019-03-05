//package com.futao.springbootdemo.foundation.security;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 自定义成功退出系统的处理器
// *
// * @author futao
// * Created on 2019-01-17.
// */
//public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
//
//    @Override
//    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        System.out.println("谁谁谁退出了系统");
//        super.onLogoutSuccess(request, response, authentication);
//    }
//}
