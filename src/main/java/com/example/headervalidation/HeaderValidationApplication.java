/*
 * Copyright (c) 2026 Debajyoti Nath. All rights reserved.
 */
package com.example.headervalidation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Bootstrap class for the Spring Boot header-validation application.
 *
 * <p>Starts the application context and scans for Spring components.
 *
 * @author Debajyoti Nath (Technical Architect - Java Backend)
 */
@SpringBootApplication
public class HeaderValidationApplication {

    /**
     * Starts the application.
     *
     * @param args command-line arguments passed to Spring Boot
     */
    public static void main(String[] args) {
        SpringApplication.run(HeaderValidationApplication.class, args);
    }
}
