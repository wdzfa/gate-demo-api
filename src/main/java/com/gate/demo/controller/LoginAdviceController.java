package com.gate.demo.controller;

import com.gate.demo.dto.ErrorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class LoginAdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        ErrorResponseDTO responseDTO = new ErrorResponseDTO();
        responseDTO.setCode(401);
        responseDTO.setMessage("Bad credentials.");

        return ResponseEntity.status(401).body(responseDTO);
    }

}
