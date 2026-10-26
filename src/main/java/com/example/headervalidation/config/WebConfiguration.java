package com.example.headervalidation.config;

import com.example.headervalidation.interceptor.RequiredHeadersInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final RequiredHeadersInterceptor requiredHeadersInterceptor;

    public WebConfiguration(RequiredHeadersInterceptor requiredHeadersInterceptor) {
        this.requiredHeadersInterceptor = requiredHeadersInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requiredHeadersInterceptor)
                .addPathPatterns("/api/intercepted/headers");
    }
}
