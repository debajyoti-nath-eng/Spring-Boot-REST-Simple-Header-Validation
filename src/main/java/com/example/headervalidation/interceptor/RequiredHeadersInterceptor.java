/*
 * Copyright (c) 2026 Debajyoti Nath. All rights reserved.
 */
package com.example.headervalidation.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Validates all required request headers before the matched controller executes.
 *
 * <p>Requests missing one or more required headers receive a JSON
 * {@code 400 Bad Request} response listing all invalid headers.
 *
 * @author Debajyoti Nath (Technical Architect - Java Backend)
 */
@Component
public class RequiredHeadersInterceptor implements HandlerInterceptor {

    private static final List<String> REQUIRED_HEADERS = List.of("X-Client-Id", "X-Request-Id");

    private final ObjectMapper objectMapper;

    /**
     * Creates the interceptor with the JSON serializer for error responses.
     *
     * @param objectMapper serializer used to write the validation response
     */
    public RequiredHeadersInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Allows requests containing every required non-blank header.
     *
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler selected MVC handler
     * @return {@code true} if all required headers are valid; {@code false} otherwise
     * @throws IOException if writing the error response fails
     */
    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        List<String> invalidHeaders = new ArrayList<>();
        for (String header : REQUIRED_HEADERS) {
            String value = request.getHeader(header);
            if (value == null || value.isBlank()) {
                invalidHeaders.add(header);
            }
        }

        if (invalidHeaders.isEmpty()) {
            return true;
        }

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), Map.of(
                "message", "Required headers are missing or blank",
                "invalidHeaders", invalidHeaders));
        return false;
    }
}
