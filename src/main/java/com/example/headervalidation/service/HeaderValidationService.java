package com.example.headervalidation.service;

import com.example.headervalidation.dto.HeaderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class HeaderValidationService {

    public HeaderResponse validateHeaders(String clientId, String requestId) {
        if (clientId.isBlank() || requestId.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "X-Client-Id and X-Request-Id must not be blank");
        }

        return new HeaderResponse("Headers accepted", clientId, requestId);
    }
}
