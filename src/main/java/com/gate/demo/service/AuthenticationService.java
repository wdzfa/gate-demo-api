package com.gate.demo.service;

import com.gate.demo.dto.LoginRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    public UserDetails authenticate(LoginRequestDTO loginRequestDTO) throws Exception {

        Authentication authentication = null;
        try {

            UsernamePasswordAuthenticationToken authObject = new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
            authentication = authenticationManager.authenticate(authObject);
        }
        catch (BadCredentialsException e) {
            logger.error("[AuthenticationService][authenticate][Error bad credentials: " + e.toString() + "]");
            throw e;
        }

        logger.info("----- User Authenticated Successfully!!! -----");

        return (UserDetails) authentication.getPrincipal();    }


}
