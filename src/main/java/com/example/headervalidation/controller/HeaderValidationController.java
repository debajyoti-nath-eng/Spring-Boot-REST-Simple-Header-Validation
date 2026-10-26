/*
 * Copyright (c) 2026 Debajyoti Nath. All rights reserved.
 */
package com.example.headervalidation.controller;

import com.example.headervalidation.dto.HeaderResponse;
import com.example.headervalidation.service.HeaderValidationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST endpoints for validating required request headers.
 *
 * @author Debajyoti Nath (Technical Architect - Java Backend)
 */
@RestController
@RequestMapping("/api")
public class HeaderValidationController {

    private final HeaderValidationService headerValidationService;

    /**
     * Creates the controller with its header-validation service.
     *
     * @param headerValidationService service that validates header values
     */
    public HeaderValidationController(HeaderValidationService headerValidationService) {
        this.headerValidationService = headerValidationService;
    }

    /**
     * Validates headers in the service layer.
     *
     * @param clientId value of the {@code X-Client-Id} request header
     * @param requestId value of the {@code X-Request-Id} request header
     * @return a response containing the accepted header values
     */
    @GetMapping("/headers")
    public HeaderResponse validateHeaders(
            @RequestHeader("X-Client-Id") String clientId,
            @RequestHeader("X-Request-Id") String requestId) {
        return headerValidationService.validateHeaders(clientId, requestId);
    }

    /**
     * Returns the supplied headers after they pass the registered MVC interceptor.
     *
     * @param clientId value of the {@code X-Client-Id} request header
     * @param requestId value of the {@code X-Request-Id} request header
     * @return a response containing the accepted header values
     */
    @GetMapping("/intercepted/headers")
    public HeaderResponse validateHeadersWithInterceptor(
            @RequestHeader("X-Client-Id") String clientId,
            @RequestHeader("X-Request-Id") String requestId) {
        return new HeaderResponse("Headers accepted by interceptor", clientId, requestId);
    }
}
