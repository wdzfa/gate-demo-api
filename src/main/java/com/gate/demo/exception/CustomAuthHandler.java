package com.gate.demo.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomAuthHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        String message = "Authentication is required to access this resource";

        // Ambil pesan kalau JWT expired
        Object expiredMsg = request.getAttribute("expired");
        if (expiredMsg != null) {
            message = expiredMsg.toString();
        }

        Map<String, Object> problemDetails = new LinkedHashMap<>();
        problemDetails.put("type", "about:blank");
        problemDetails.put("title", "Unauthorized");
        problemDetails.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        problemDetails.put("detail", message);
        problemDetails.put("instance", request.getRequestURI());

        new ObjectMapper().writeValue(response.getWriter(), problemDetails);
    }
}
