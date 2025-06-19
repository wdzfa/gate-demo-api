package com.gate.demo.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        log.warn("[CustomAccessDeniedHandler] Access Denied: {}", accessDeniedException.getMessage());

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        Map<String, Object> problemDetails = new LinkedHashMap<>();
        problemDetails.put("type", "about:blank");
        problemDetails.put("title", "Forbidden");
        problemDetails.put("status", HttpServletResponse.SC_FORBIDDEN);
        problemDetails.put("detail", "You do not have permission to access this resource");
        problemDetails.put("instance", request.getRequestURI());

        new ObjectMapper().writeValue(response.getWriter(), problemDetails);
    }
}

