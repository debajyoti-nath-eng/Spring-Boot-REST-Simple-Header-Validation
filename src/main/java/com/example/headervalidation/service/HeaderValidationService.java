/*
 * Copyright (c) 2026 Debajyoti Nath. All rights reserved.
 */
package com.example.headervalidation.service;

import com.example.headervalidation.dto.HeaderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Validates request-header values and creates the corresponding API response.
 *
 * @author Debajyoti Nath (Technical Architect - Java Backend)
 */
@Service
public class HeaderValidationService {

    /**
     * Ensures both header values are non-blank.
     *
     * @param clientId value of the {@code X-Client-Id} header
     * @param requestId value of the {@code X-Request-Id} header
     * @return a response containing both validated values
     * @throws ResponseStatusException if either value is blank
     */
    public HeaderResponse validateHeaders(String clientId, String requestId) {
        if (clientId.isBlank() || requestId.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "X-Client-Id and X-Request-Id must not be blank");
        }

        return new HeaderResponse("Headers accepted", clientId, requestId);
    }
}
