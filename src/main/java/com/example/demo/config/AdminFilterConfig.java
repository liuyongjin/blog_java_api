package com.example.demo.config;

import com.example.demo.filter.AdminFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminFilterConfig {
    @Bean
    public AdminFilter adminFilter(){
        return new AdminFilter();
    }

    @Bean(name = "adminFilterConf")
    public FilterRegistrationBean adminFilterConfig() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(adminFilter());
        filterRegistrationBean.addUrlPatterns("/article/*");
        filterRegistrationBean.addUrlPatterns("/tag/*");
        filterRegistrationBean.setName("adminFilterConf");
        return filterRegistrationBean;
    }
}
