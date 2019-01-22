//package com.futao.springmvcdemo.foundation.security;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//
///**
// * 学习地址-https://www.baeldung.com/security-spring
// *
// * @author futao
// * Created on 2018/11/6.
// */
////@Configuration
////@EnableWebSecurity
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Bean("authenticationManager")
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user1").password("{noop}user1Pass").roles("USER")
//                .and()
//                .withUser("admin1").password("{noop}admin1Pass").roles("ADMIN");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/test/**").anonymous()
//                //用户必须拥有某种角色
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                //允许任何匿名用户
//                .antMatchers("/anonymous*").anonymous()
//                //TODO("permitAll与 anonymous的区别")
//                .antMatchers("/login*").permitAll()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers("/resources/**").permitAll()
//                .anyRequest().authenticated()
//
//
//                .and()
//                .formLogin()
//                //登录地址
//                .loginPage("/user/mobileLogin")
//                .loginProcessingUrl("/perform_login")
//                //默认登录成功后的跳转地址
//                .defaultSuccessUrl("/homepage.html", true)
//                //.failureUrl("/login.html?error=true")
////                .failureHandler(authenticationFailureHandler())
//
//
//                .and()
//                .logout()
//                //登录请求接口地址
//                .logoutUrl("/perform_logout")
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//
//                .and()
//                .rememberMe().key("uniqueAndSecret")
//                .tokenValiditySeconds(86400);
//
////                .logoutSuccessHandler(logoutSuccessHandler());
////                .httpBasic();
//
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers(HttpMethod.OPTIONS, "/**");
//    }
//
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
//                .and()
//                .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
//                .and()
//                .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
//    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public LogoutSuccessHandler logoutSuccessHandler() {
//        return new CustomLogoutSuccessHandler();
//    }
//}
