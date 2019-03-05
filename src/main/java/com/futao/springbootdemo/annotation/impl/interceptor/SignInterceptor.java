package com.futao.springbootdemo.annotation.impl.interceptor;

import com.futao.springbootdemo.annotation.Sign;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author futao
 * Created on 2018/9/18-14:49.
 * springmvc拦截器适配器,或者实现HandlerInterceptor
 */
@Component
public class SignInterceptor extends HandlerInterceptorAdapter {
    /**
     * 请求到达controller之前
     *
     * @param request
     * @param response
     * @param handler
     * @return true继续执行controller，false不执行controller
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            Sign signAnnotation = ((HandlerMethod) handler).getMethodAnnotation(Sign.class);
            Sign classSignAnnotation = ((HandlerMethod) handler).getMethod().getDeclaringClass().getAnnotation(Sign.class);
            //获取请求数据
//            String queryString = request.getQueryString();
////            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
////            StringBuilder sb = new StringBuilder();
////            String line;
////            while ((line = bufferedReader.readLine()) != null) {
////                sb.append(line + "\n");
////            }
////            System.out.println("--------------" + sb);
//            //请求的方法被标记了@Sign注解,并且请求的参数不为空
//            //需要对参数进行加密校验
//            if (ObjectUtils.anyNotNull(signAnnotation, classSignAnnotation) && ObjectUtils.allNotNull(queryString)) {
//                for (String kv : queryString.split("&")) {
//                    int charIndex = kv.indexOf("=");
//                    System.out.println("key: " + kv.substring(0, charIndex));
//                    System.out.println("value: " + kv.substring(charIndex));
//                }
//            }
        }
        return true;
    }
}
