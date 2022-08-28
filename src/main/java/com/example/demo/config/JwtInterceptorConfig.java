package com.example.demo.config;

import com.example.demo.interceptor.JwtAuthenticationInterceptor;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class JwtInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 默认拦截所有路径
//        registry.addInterceptor((HandlerInterceptor) authenticationInterceptor())
//                .addPathPatterns("/**");
        // 注册 registration 拦截器
        InterceptorRegistration registration = registry.addInterceptor(new JwtAuthenticationInterceptor());
        // 拦截所有的路径
        registration.addPathPatterns("/**");

        // 添加不拦截路径 /user/login 是登录的请求, /user/register 注册的请求
        registration.excludePathPatterns(
                "/user/login",
                "/user/register",
                // html 静态资源
                "/**/*.html",
                // js 静态资源
                "/**/*.js",
                // css 静态资源
                "/**/*.css"
        );

//        registry.addInterceptor((HandlerInterceptor) authenticationInterceptor())
//                .addPathPatterns("/*");
    }

//    @Bean
//    public JwtAuthenticationInterceptor authenticationInterceptor() {
//        return new JwtAuthenticationInterceptor();
//    }
}
