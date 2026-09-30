package com.codevictims.bayt.account.controller;

import jakarta.persistence.Access;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthController {

    private final Access access;

    public AuthController(Access access) {
        this.access = access;

      @GetMapping
        public CsrfResponse csrf(CsrfTokentoken) {

            return new CsrfResponse(
                    token.getHeaderName(),
                    token.getToken()
            );
    }







