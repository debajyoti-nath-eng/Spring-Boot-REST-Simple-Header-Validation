package com.example.headervalidation.controller;

import com.example.headervalidation.service.HeaderValidationService;
import com.example.headervalidation.config.WebConfiguration;
import com.example.headervalidation.interceptor.RequiredHeadersInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HeaderValidationController.class)
@Import({HeaderValidationService.class, RequiredHeadersInterceptor.class, WebConfiguration.class})
class HeaderValidationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsAcceptedHeaders() throws Exception {
        mockMvc.perform(get("/api/headers")
                        .header("X-Client-Id", "client-123")
                        .header("X-Request-Id", "request-456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Headers accepted"))
                .andExpect(jsonPath("$.clientId").value("client-123"))
                .andExpect(jsonPath("$.requestId").value("request-456"));
    }

    @Test
    void rejectsMissingRequiredHeader() throws Exception {
        mockMvc.perform(get("/api/headers")
                        .header("X-Client-Id", "client-123"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void rejectsBlankHeader() throws Exception {
        mockMvc.perform(get("/api/headers")
                        .header("X-Client-Id", " ")
                        .header("X-Request-Id", "request-456"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void interceptorAcceptsAllRequiredHeaders() throws Exception {
        mockMvc.perform(get("/api/intercepted/headers")
                        .header("X-Client-Id", "client-123")
                        .header("X-Request-Id", "request-456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Headers accepted by interceptor"))
                .andExpect(jsonPath("$.clientId").value("client-123"))
                .andExpect(jsonPath("$.requestId").value("request-456"));
    }

    @Test
    void interceptorReportsAllMissingHeadersTogether() throws Exception {
        mockMvc.perform(get("/api/intercepted/headers"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Required headers are missing or blank"))
                .andExpect(jsonPath("$.invalidHeaders.length()").value(2))
                .andExpect(jsonPath("$.invalidHeaders[0]").value("X-Client-Id"))
                .andExpect(jsonPath("$.invalidHeaders[1]").value("X-Request-Id"));
    }

    @Test
    void interceptorReportsAllBlankHeadersTogether() throws Exception {
        mockMvc.perform(get("/api/intercepted/headers")
                        .header("X-Client-Id", " ")
                        .header("X-Request-Id", " "))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.invalidHeaders.length()").value(2));
    }
}
