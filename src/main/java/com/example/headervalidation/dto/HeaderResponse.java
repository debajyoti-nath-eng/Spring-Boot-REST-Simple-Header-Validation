/*
 * Copyright (c) 2026 Debajyoti Nath. All rights reserved.
 */
package com.example.headervalidation.dto;

/**
 * API response containing the result of request-header validation.
 *
 * @param message summary of the validation result
 * @param clientId accepted client identifier
 * @param requestId accepted request identifier
 * @author Debajyoti Nath (Technical Architect - Java Backend)
 */
public record HeaderResponse(String message, String clientId, String requestId) {
}
