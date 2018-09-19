package com.xinyan.message.center.web.config;

import com.xinyan.message.center.web.interceptor.LogIdSetUpInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Module Desc:
 *
 * @author: gambo
 * DateTime: 2018/9/17
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogIdSetUpInterceptor())    //指定拦截器类
                .addPathPatterns("/**");        //指定该类拦截的url
    }
}
