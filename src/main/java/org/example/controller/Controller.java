package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.jwt.JwtAuthentication;
import org.example.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class Controller {
    private final AuthService authService;

    @PreAuthorize("hasAuthority('GUEST')")
    @GetMapping("hello/guest")
    public ResponseEntity<String> helloGuest() {
        final JwtAuthentication authInfo = authService.getAuthInfo();
        return ResponseEntity.ok("Hello guest " + authInfo.getPrincipal() + "!");
    }

    @PreAuthorize("hasAuthority('PREMIUM_USER')")
    @GetMapping("hello/premium-user")
    public ResponseEntity<String> helloPremiumUser() {
        final JwtAuthentication authInfo = authService.getAuthInfo();
        return ResponseEntity.ok("Hello premium user " + authInfo.getPrincipal() + "!");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("hello/admin")
    public ResponseEntity<String> helloAdmin() {
        final JwtAuthentication authInfo = authService.getAuthInfo();
        return ResponseEntity.ok("Hello admin " + authInfo.getPrincipal() + "!");
    }


}
