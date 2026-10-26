package com.example.headervalidation.controller;

import com.example.headervalidation.dto.HeaderResponse;
import com.example.headervalidation.service.HeaderValidationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HeaderValidationController {

    private final HeaderValidationService headerValidationService;

    public HeaderValidationController(HeaderValidationService headerValidationService) {
        this.headerValidationService = headerValidationService;
    }

    @GetMapping("/headers")
    public HeaderResponse validateHeaders(
            @RequestHeader("X-Client-Id") String clientId,
            @RequestHeader("X-Request-Id") String requestId) {
        return headerValidationService.validateHeaders(clientId, requestId);
    }

    @GetMapping("/intercepted/headers")
    public HeaderResponse validateHeadersWithInterceptor(
            @RequestHeader("X-Client-Id") String clientId,
            @RequestHeader("X-Request-Id") String requestId) {
        return new HeaderResponse("Headers accepted by interceptor", clientId, requestId);
    }
}
