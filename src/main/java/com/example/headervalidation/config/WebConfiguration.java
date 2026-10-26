/*
 * Copyright (c) 2026 Debajyoti Nath. All rights reserved.
 */
package com.example.headervalidation.config;

import com.example.headervalidation.interceptor.RequiredHeadersInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configures MVC behavior for the header-validation API.
 *
 * <p>Registers the required-header interceptor for the endpoint that demonstrates
 * interceptor-based validation.
 *
 * @author Debajyoti Nath (Technical Architect - Java Backend)
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final RequiredHeadersInterceptor requiredHeadersInterceptor;

    /**
     * Creates the web configuration with its header-validation interceptor.
     *
     * @param requiredHeadersInterceptor interceptor that checks required headers
     */
    public WebConfiguration(RequiredHeadersInterceptor requiredHeadersInterceptor) {
        this.requiredHeadersInterceptor = requiredHeadersInterceptor;
    }

    /**
     * Applies header validation to the interceptor-based endpoint.
     *
     * @param registry registry used to register MVC interceptors
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requiredHeadersInterceptor)
                .addPathPatterns("/api/intercepted/headers");
    }
}
